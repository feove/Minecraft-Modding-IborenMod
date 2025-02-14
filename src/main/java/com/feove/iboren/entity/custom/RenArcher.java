package com.feove.iboren.entity.custom;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.world.World;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import static com.feove.iboren.entity.custom.RenZombie.RANDOM;

public class RenArcher extends SkeletonEntity {

    public RenArcher(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        // Add goals specific to the RenArcher if necessary
    }


    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        double speed = RANDOM.nextDouble() < 0.1 ? 0.45D : 0.25D;
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 13.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.005D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.0D);
    }


}
