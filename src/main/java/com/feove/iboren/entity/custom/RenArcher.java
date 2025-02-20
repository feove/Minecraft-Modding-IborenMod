package com.feove.iboren.entity.custom;

import com.feove.iboren.entity.EntityRegistry;
import com.feove.iboren.item.ModItems;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class RenArcher extends SkeletonEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    private int attackCooldown = 60;

    private static final double MAX_ATTACK_RANGE = 30.0D;

    private static final ResourceLocation[] ARCHER_TEXTURES = new ResourceLocation[]{
            new ResourceLocation("iboren", "textures/entity/ren_archer/ren_archer_t1.png"),
            new ResourceLocation("iboren", "textures/entity/ren_archer/ren_archer_t2.png"),
    };

    public final ResourceLocation archer_texture;

    public RenArcher(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
        this.populateEquipment();
        this.archer_texture = ARCHER_TEXTURES[this.random.nextInt(ARCHER_TEXTURES.length)];
    }


    private void populateEquipment() {
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.REN_BOW.get()));
    }

    @Override
    public boolean canTakeItem(ItemStack itemStack) {
        return itemStack.getItem() == ModItems.REN_BOW.get();
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        // nothing to avoid default skeleton shooting
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return SkeletonEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.ARMOR, 3.0D);

    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private int attackAnimationDelay = 240;
    private boolean finishToPrepar = true;

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController<E> controller = event.getController();

        if (isDeadOrDying()) {

            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.die", false));
            return PlayState.STOP;
        }

        if (isAggressive() && finishToPrepar) {

            if (attackAnimationDelay == 0 || controller.getCurrentAnimation() == null) {
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.attacking", true));
                return PlayState.CONTINUE;
            } else {
                attackAnimationDelay--;
            }
        }

        if (isAggressive()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.attack_preparation", false));
            finishToPrepar = true;
            return PlayState.CONTINUE;
        }

        attackAnimationDelay = 240;
        finishToPrepar = false;

        if (event.isMoving()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.walk", true));
            return PlayState.CONTINUE;
        }

        controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void travel(Vector3d movement) {

        super.travel(Vector3d.ZERO);
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = this.getTarget();

        if (target == null) {
            this.level.getNearestPlayer(this, 100.0D);
        }

        if (this.getTarget() != null) {
            this.getLookControl().setLookAt(this.getTarget(), 10.0F, 10.0F);
        }
    }

    @Override
    public void aiStep() {
        if (!isAggressive()) {
            attackCooldown = 60;
        }

        if (!this.isDeadOrDying()) {
            if (this.getTarget() != null) {
                double distanceToTarget = this.distanceToSqr(this.getTarget());

                if (distanceToTarget <= MAX_ATTACK_RANGE * MAX_ATTACK_RANGE) {
                    attackCooldown--;

                    if (attackCooldown <= 0) {

                        shootArrow();
                        attackCooldown = 50;
                    }
                }
            }
        }
        super.aiStep();
    }


    private void shootArrow() {
        if (!this.level.isClientSide && getTarget() != null) {

            LivingEntity target = this.getTarget();
            double distanceToTarget = this.distanceToSqr(target);

            if (distanceToTarget > MAX_ATTACK_RANGE * MAX_ATTACK_RANGE) {
                return;
            }

            RenArrow arrow = new RenArrow(EntityRegistry.REN_ARROW.get(), this.level);

            arrow.setOwner(this);

            double spawnX = this.getX();
            double spawnY = this.getY() + 0.3D;
            double spawnZ = this.getZ() + 1.0D;

            arrow.setPos(spawnX, spawnY, spawnZ);

            double d0 = target.getX() - spawnX;
            double d1 = (target.getY(0.33333333D) - 0.3D) - spawnY + 1.0D;
            double d2 = target.getZ() - spawnZ;

            double speed = 4.0D;
            double accuracy = 2.0F;
            arrow.shoot(d0, d1 + d1 * 0.15D, d2, (float) speed, (float) accuracy);

            //arrow.shoot(d0, d1 + d1 * 0.1D, d2, (float) speed, 4.0F);
            arrow.setBaseDamage(3.0D);
            arrow.setKnockback(2);

            arrow.setNoGravity(true);

            applyRandomBadEffect(arrow);

            this.level.addFreshEntity(arrow);
        }
    }

    private void applyRandomBadEffect(ArrowEntity arrow) {

        Random random = RenZombie.RANDOM;

        EffectInstance[] badEffects = {
                new EffectInstance(Effects.POISON, 100, 1),
                new EffectInstance(Effects.WEAKNESS, 200, 1),
                new EffectInstance(Effects.BLINDNESS, 100, 0),
                new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 150, 2),
                new EffectInstance(Effects.WITHER, 100, 1)
        };

        EffectInstance chosenEffect = badEffects[random.nextInt(badEffects.length)];

        arrow.addEffect(chosenEffect);
    }

    public AnimationFactory getFactory() {
        return factory;
    }

}
