package com.feove.iboren.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class RenArcher extends SkeletonEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);
    private boolean isAttacking = false;
    private int attackCooldown = 20;

    private static final double MAX_ATTACK_RANGE = 30.0D;

    public RenArcher(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
        this.populateEquipment();
    }

    private void populateEquipment() {
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
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

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController<E> controller = event.getController();

        if (isDeadOrDying()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.die", false));
            return PlayState.CONTINUE;
        }

        if (isAggressive()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.attack", true));
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.walk", true));
            return PlayState.CONTINUE;
        }

        controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void aiStep() {
        if (!this.isDeadOrDying()) {
            if (this.getTarget() != null) {
                double distanceToTarget = this.distanceToSqr(this.getTarget());

                if (distanceToTarget <= MAX_ATTACK_RANGE * MAX_ATTACK_RANGE) {
                    attackCooldown--;
                    if (attackCooldown <= 0) {
                        isAttacking = true;
                        shootArrow();
                        attackCooldown = 36;
                    }
                } else {
                    isAttacking = false;
                }
            } else {
                isAttacking = false;
            }
        }
        super.aiStep();
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        shootArrow();
    }


    private void shootArrow() {
        if (!this.level.isClientSide && getTarget() != null) {
            LivingEntity target = this.getTarget();
            double distanceToTarget = this.distanceToSqr(target);

            if (distanceToTarget > MAX_ATTACK_RANGE * MAX_ATTACK_RANGE) {
                return;
            }

            ArrowEntity arrow = new ArrowEntity(this.level, this);

            double spawnX = this.getX();
            double spawnY = this.getY() + 0.5D;
            double spawnZ = this.getZ();

            arrow.setPos(spawnX, spawnY, spawnZ);

            // Calculate aim
            double d0 = target.getX() - spawnX;
            double d1 = target.getY(0.33333333D) - spawnY;
            double d2 = target.getZ() - spawnZ;

            double speed = 2.0D;
            arrow.shoot(d0, d1 + d1 * 0.1D, d2, (float) speed, 6.0F);
            arrow.setBaseDamage(1.0D);

            this.level.addFreshEntity(arrow);

            isAttacking = false;
        }
    }


    @Override
    public boolean canTakeItem(ItemStack itemStack) {
        return itemStack.getItem() == Items.ARROW;
    }

    public AnimationFactory getFactory() {
        return factory;
    }
}
