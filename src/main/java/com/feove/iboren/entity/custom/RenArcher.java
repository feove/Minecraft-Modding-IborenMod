package com.feove.iboren.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SkeletonEntity;
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
        // Add any custom AI goals here if needed
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return SkeletonEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 13.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.ARMOR, 3.0D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController<E> controller = event.getController();

        // Play walk animation when moving
        if (event.isMoving()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.walk", true));
            return PlayState.CONTINUE;
        }

        // Default to idle animation
        controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);

        // Play the death animation
        AnimationController<?> controller = factory.getOrCreateAnimationData(this.getId()).getAnimationControllers().get("controller");
        if (controller != null) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.die", false));
        }
    }

    private void playDieAnimation() {
        AnimationController<?> controller = factory.getOrCreateAnimationData(this.getId()).getAnimationControllers().get("controller");
        if (controller != null) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.ren_archer.die", false));
        }
    }
}
