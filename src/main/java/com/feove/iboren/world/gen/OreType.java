package com.feove.iboren.world.gen;

import com.feove.iboren.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.fml.RegistryObject;

public enum OreType {

    IBO_ORE(ModBlocks.IBO_ORE, 5, 0, 20),
    REN_ORE(ModBlocks.REN_ORE,3,0,20);

    private final RegistryObject<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;

    OreType(RegistryObject<Block> block, int maxVeinSize, int minHeight, int maxHeight) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public RegistryObject<Block> getBlock() {
        return block;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }
}
