package com.feove.iboren.entity.custom;

import com.feove.iboren.entity.EntityRegistry;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class CustomCow extends AnimalEntity {

    public CustomCow(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
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

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity mate) {
        return EntityRegistry.CUSTOM_COW.get().create(serverWorld);
    }
}
