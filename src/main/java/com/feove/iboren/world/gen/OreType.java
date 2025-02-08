package com.feove.iboren.world.gen;


import com.feove.iboren.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.Lazy;

public enum OreType {

    IBO(Lazy.of(ModBlocks.IBO_ORE).get(),8,24,50)
    ;

    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;

    OreType(Block block, int maxVeinSize, int minHeight, int maxHeight) {

        this.block = (Lazy<Block>) block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;

    }

    public Block getBlock() {
        return block.get();
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

    public static OreType get(Block block) {

        for (OreType ore : OreType.values()) {
            if (ore.getBlock() == block) return ore;
        }
        return null;
    }

}
