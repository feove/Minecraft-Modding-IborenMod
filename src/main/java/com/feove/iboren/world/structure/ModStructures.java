package com.feove.iboren.world.structure;

import com.feove.iboren.world.structure.structures.TreasureStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ModStructures {

    public static final Structure<NoFeatureConfig> TREASURE_STRUCTURE = new TreasureStructure(NoFeatureConfig.CODEC);

    public static void registerStructures() {

        Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation("iboren", "treasure_structure"), TREASURE_STRUCTURE);
    }
}
