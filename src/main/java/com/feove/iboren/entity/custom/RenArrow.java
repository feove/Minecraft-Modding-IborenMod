package com.feove.iboren.entity.custom;

import com.feove.iboren.entity.EntityRegistry;
import com.feove.iboren.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Predicate;

public class RenArrow extends ArrowEntity {

    public RenArrow(EntityType<? extends RenArrow> type, World world) {
        super(type, world);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }


    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
