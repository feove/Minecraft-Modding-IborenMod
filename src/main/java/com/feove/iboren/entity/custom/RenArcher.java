package com.feove.iboren.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
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

    public RenArcher(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return SkeletonEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.1D)
                .add(Attributes.ATTACK_DAMAGE, 13.0D)
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

        if (isDeadOrDying()){
            this.setNoGravity(true);
            this.fallDistance = 0.0F;
            this.noCulling = true;
            this.dead = true;

            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.die", true));
            return PlayState.CONTINUE;
        }

        if (isAggressive()){

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
    public boolean canTakeItem(ItemStack itemStack) {
        return itemStack.getItem() == Items.ARROW;
    }

    public AnimationFactory getFactory() {
        return factory;
    }



}
