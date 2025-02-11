package com.feove.iboren.world.gen;

import com.feove.iboren.block.ModBlocks;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.ArrayList;
import java.util.List;

public class ModOreGeneration {

    private static final List<ConfiguredFeature<?, ?>> ORE_FEATURES = new ArrayList<>();

    public static void generateOres(final BiomeLoadingEvent event) {


        for (OreType type : OreType.values()) {

            ConfiguredFeature<?, ?> oreFeature = Feature.ORE.configured(
                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                    type.getBlock().get().defaultBlockState(), type.getMaxVeinSize()))
                    .decorated(Placement.RANGE.configured(
                            new TopSolidRangeConfig(type.getMinHeight(), type.getMinHeight(), type.getMaxHeight())))
                    .squared()
                    .count(2); // Count per chunk

            // Add ore feature to generation
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);

        }
    }
}
