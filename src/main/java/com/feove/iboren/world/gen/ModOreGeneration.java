package com.feove.iboren.world.gen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;


import static net.minecraft.util.registry.Registry.CONFIGURED_FEATURE_REGISTRY;

public class ModOreGeneration {


    public static void generateOres(final BiomeLoadingEvent event) {

        for (OreType type : OreType.values()) {

            ConfiguredFeature<?, ?> oreFeature = Feature.ORE.configured(
                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                    type.getBlock().defaultBlockState(), type.getMaxVeinSize()))
                    .decorated(Placement.RANGE.configured(
                            new TopSolidRangeConfig(type.getMinHeight(), type.getMinHeight(), type.getMaxHeight())))
                    .squared()
                    .count(10); // Count per chunk

            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
        }
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> registerOreFeature(OreType ore,
                                                                           OreFeatureConfig oreFeatureConfig,
                                                                           ConfiguredPlacement<?> configuredPlacement) {

        ResourceLocation featureName = new ResourceLocation("iboren", ore.name() + "_ore");

        ConfiguredFeature<?, ?> configuredFeature = new ConfiguredFeature<>(Feature.ORE, oreFeatureConfig)
                .decorated(configuredPlacement);

        return RegistryKey.create(CONFIGURED_FEATURE_REGISTRY, featureName);
    }
}
