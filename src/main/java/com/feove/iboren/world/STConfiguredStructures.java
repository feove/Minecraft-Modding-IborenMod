package com.feove.iboren.world;

import com.feove.iboren.IborenMod;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class STConfiguredStructures {


    public static StructureFeature<?, ?> CONFIGURED_IBOREN_TREASURE = STStructures.RUN_DOWN_HOUSE.get().configured(IFeatureConfig.NONE);



    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(IborenMod.MOD_ID, "configured_run_down_house"), CONFIGURED_IBOREN_TREASURE);

        /* Ok so, this part may be hard to grasp but basically, just add your structure to this to
         * prevent any sort of crash or issue with other mod's custom ChunkGenerators. If they use
         * FlatGenerationSettings.STRUCTURE_FEATURES in it and you don't add your structure to it, the game
         * could crash later when you attempt to add the StructureSeparationSettings to the dimension.
         *
         * (It would also crash with superflat worldtype if you omit the below line
         * and attempt to add the structure's StructureSeparationSettings to the world)
         *
         * Note: If you want your structure to spawn in superflat, remove the FlatChunkGenerator check
         * in StructureTutorialMain.addDimensionalSpacing and then create a superflat world, exit it,
         * and re-enter it and your structures will be spawning. I could not figure out why it needs
         * the restart but honestly, superflat is really buggy and shouldn't be your main focus in my opinion.
         *
         * Requires AccessTransformer ( see resources/META-INF/accesstransformer.cfg )
         */
        try {
            Field structureFeaturesField = FlatGenerationSettings.class.getDeclaredField("STRUCTURE_FEATURES");
            structureFeaturesField.setAccessible(true);

            Map<Structure<?>, StructureFeature<?, ?>> structureFeatures =
                    (Map<Structure<?>, StructureFeature<?, ?>>) structureFeaturesField.get(null);

            Map<Structure<?>, StructureFeature<?, ?>> tempMap = new HashMap<>(structureFeatures);
            tempMap.put(STStructures.RUN_DOWN_HOUSE.get(), CONFIGURED_IBOREN_TREASURE);

            structureFeaturesField.set(null, ImmutableMap.copyOf(tempMap));
        } catch (Exception e) {
            throw new RuntimeException("Failed to modify STRUCTURE_FEATURES", e);
        }

    }










}
