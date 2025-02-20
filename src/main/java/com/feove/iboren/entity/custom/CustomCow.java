package com.feove.iboren.entity.custom;

import com.feove.iboren.entity.EntityRegistry;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.injection.At;

import javax.annotation.Nullable;

import java.util.Random;

import static com.feove.iboren.entity.EntityRegistry.CUSTOM_COW;

public class CustomCow extends CowEntity {

    static final Random COW_RANDOM = new Random();


    public CustomCow(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);

        this.hurt(DamageSource.MAGIC, 1.0F);
        this.goalSelector.addGoal(5, new SwimGoal(this));

    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FOLLOW_RANGE,20);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new WaterAvoidingRandomWalkingGoal(this, 1.0));
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
    }

    @Override
    public boolean isFood(net.minecraft.item.ItemStack stack) {
        return stack.getItem().isEdible();
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource, int lootingMultiplier, boolean hitRecently) {

        if (COW_RANDOM.nextDouble() < 0.6) {

            int foodAmount = 1 + COW_RANDOM.nextInt(3);
            this.spawnAtLocation(new ItemStack(Items.BEEF, foodAmount));
        }
    }


    @Override
    protected SoundEvent getDeathSound() {
        this.playSound(new SoundEvent(CUSTOM_COW.getId()), 0.7F, 2.0F);
        return null;
    }

    @Override
    public SoundEvent getAmbientSound() {
        this.playSound(new SoundEvent(CUSTOM_COW.getId()), 1.0F, 1.0F);
        return null;
    }
}
