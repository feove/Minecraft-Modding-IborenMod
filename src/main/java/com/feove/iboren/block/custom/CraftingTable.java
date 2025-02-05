package com.feove.iboren.block.custom;

import jdk.nashorn.internal.objects.annotations.Function;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class CraftingTable extends CraftingTableBlock {

    public CraftingTable(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {

        if (!world.isClientSide) {
            LOGGER.debug("Crafting table use");
        }

        if (world.isClientSide) {
            // Open the crafting GUI only on the client side
            playerEntity.openMenu((INamedContainerProvider) this);
        }

        return ActionResultType.SUCCESS;
    }



}
