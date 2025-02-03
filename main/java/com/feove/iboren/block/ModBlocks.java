package com.feove.iboren.block;

import com.feove.iboren.IborenMod;
import com.feove.iboren.item.ModItemGroup;
import com.feove.iboren.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IborenMod.MOD_ID);

    public static final RegistryObject<Block> IBOREN_ORE =
            registerBlock("ibo_ore",()
                    -> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(2,4)

            ));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {

        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

   private static <T extends Block> void registerBlockItem(String name, Supplier<T> block) {
       ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties().tab(ModItemGroup.IBOREN_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
