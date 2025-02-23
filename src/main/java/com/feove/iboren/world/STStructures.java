package com.feove.iboren.world;

import com.feove.iboren.IborenMod;
import com.feove.iboren.world.structure.IborenTreasure;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class STStructures {

    public static final DeferredRegister<Structure<?>> STRUCTURES =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, IborenMod.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> IBOREN_TREASURE =
            STRUCTURES.register("iboren_treasure", () -> new IborenTreasure(NoFeatureConfig.CODEC));

    public static void setupStructures() {
        registerStructure(IBOREN_TREASURE.get(), "iboren_treasure");
    }

    private static void registerStructure(Structure<NoFeatureConfig> structure, String name) {

        Registry.register(Registry.STRUCTURE_FEATURE, name, structure);

        StructureFeature<?, ?> configuredStructure = structure.configured(NoFeatureConfig.INSTANCE);

        Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, name, configuredStructure);
    }

}
