package com.feove.iboren.entity.custom;

import com.feove.iboren.client.render.RenArrowRenderer;
import com.feove.iboren.entity.EntityRegistry;
import com.feove.iboren.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class RenArrow extends ArrowEntity {

    public ResourceLocation texture;

    public RenArrow(EntityType<? extends RenArrow> type, World world) {
        super(type, world);
        this.texture = RenArrowRenderer.TEXTURE;
    }


    public RenArrow(World world, LivingEntity shooter) {
        super(world, shooter);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(ModItems.REN_ARROW.get());
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
