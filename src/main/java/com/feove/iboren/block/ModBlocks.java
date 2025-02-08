package com.feove.iboren.block;

import com.feove.iboren.IborenMod;
import com.feove.iboren.block.custom.CraftingTable;
import com.feove.iboren.item.ModItemGroup;
import com.feove.iboren.item.ModItems;
import net.minecraft.block.*;
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

    public static final RegistryObject<Block> IBO_ORE =
            registerBlock("ibo_ore",()
                    -> new Block(AbstractBlock.Properties.of(Material.STONE)
                        .harvestLevel(2)
                        .harvestTool(ToolType.PICKAXE)
                        .requiresCorrectToolForDrops()
                        .strength(2,4)
            ));


    public static final RegistryObject<Block> REN_ORE =
            registerBlock("ren_ore",()
                    -> new Block(AbstractBlock.Properties.of(Material.STONE)
                        .harvestLevel(3)
                        .harvestTool(ToolType.PICKAXE)
                        .requiresCorrectToolForDrops()
                        .strength(4,6)
            ));

    public static final RegistryObject<Block> REN_BLOCK =
            registerBlock("ren_block",()
                -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE)
                    .strength(2,4)
                    .requiresCorrectToolForDrops()
            ));

    public static final RegistryObject<Block> IBO_BLOCK =
            registerBlock("ibo_block",()
                    -> new Block(AbstractBlock.Properties.of(Material.METAL)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .strength(2,4)
                    .requiresCorrectToolForDrops()
            ));

    public static final RegistryObject<Block> IBOREN_CRAFTING_TABLE =
            registerBlock("iboren_crafting_table", () ->
                    new CraftingTable(AbstractBlock.Properties.of(Material.METAL)
                    .harvestLevel(2)
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
