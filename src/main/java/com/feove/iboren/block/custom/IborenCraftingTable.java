package com.feove.iboren.block.custom;

import com.feove.iboren.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Random;

public class IborenCraftingTable extends Block {

    public IborenCraftingTable(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {

        if (!world.isClientSide && !playerEntity.isSteppingCarefully()) {

            PlayerUtils.sendMessage(playerEntity, "You right-clicked the Iboren Crafting Table!");
            NetworkHooks.openGui((ServerPlayerEntity) playerEntity, (INamedContainerProvider) blockState.getBlock(), blockPos);
        }

        return ActionResultType.SUCCESS;
    }
}
