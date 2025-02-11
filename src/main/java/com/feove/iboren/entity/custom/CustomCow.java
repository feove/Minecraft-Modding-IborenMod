package com.feove.iboren.entity.custom;

import com.feove.iboren.entity.EntityRegistry;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

import static com.feove.iboren.entity.EntityRegistry.CUSTOM_COW;

public class CustomCow extends CowEntity {


    public CustomCow(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
        // Add here for custom Movements
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE,0.5D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new WaterAvoidingRandomWalkingGoal(this, 1.0));
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
    }

    @Override
    public boolean isFood(net.minecraft.item.ItemStack stack) {
        return stack.getItem().isEdible(); // Example food behavior
    }

    @Override
    protected SoundEvent getDeathSound() {
        this.playSound(new SoundEvent(CUSTOM_COW.getId()), 0.7F, 2.0F);
        return null;
    }


}
