package com.feove.iboren.world;

import com.feove.iboren.IborenMod;
import com.feove.iboren.world.structure.IborenTreasure;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class STStructures {


    public static final DeferredRegister<Structure<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, IborenMod.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> RUN_DOWN_HOUSE = DEFERRED_REGISTRY_STRUCTURE.register("run_down_house", () -> (new IborenTreasure(NoFeatureConfig.CODEC)));


    public static void setupStructures() {

        setupMapSpacingAndLand(
                RUN_DOWN_HOUSE.get(), /* The instance of the structure */
                new StructureSeparationSettings(10 /* average distance apart in chunks between spawn attempts */,
                        5,
                        1234567890),
                true);


    }

    public static <F extends Structure<?>> void setupMapSpacingAndLand(
            F structure,
            StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand) {

        /*
         * We need to add our structures into the map in Structure class
         * alongside vanilla structures or else it will cause errors.
         *
         * If the registration is setup properly for the structure,
         * getRegistryName() should never return null.
         */

        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);


        if (transformSurroundingLand) {
            Structure.NOISE_AFFECTING_FEATURES.add(structure);

        }

        /*
         * This is the map that holds the default spacing of all structures.
         * Always add your structure to here so that other mods can utilize it if needed.
         *
         * However, while it does propagate the spacing to some correct dimensions from this map,
         * it seems it doesn't always work for code made dimensions as they read from this list beforehand.
         *
         * Instead, we will use the WorldEvent.Load event in StructureTutorialMain to add the structure
         * spacing from this list into that dimension or to do dimension blacklisting properly.
         * We also use our entry in DimensionStructuresSettings.DEFAULTS in WorldEvent.Load as well.
         *
         * DEFAULTS requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
         */

        Map<Structure<?>, StructureSeparationSettings> tempDefaults = new HashMap<>(DimensionStructuresSettings.DEFAULTS);
        tempDefaults.put(structure, structureSeparationSettings);

        try {
            Field defaultsField = DimensionStructuresSettings.class.getDeclaredField("DEFAULTS");
            defaultsField.setAccessible(true);

            Map<Structure<?>, StructureSeparationSettings> currentDefaults = (Map<Structure<?>, StructureSeparationSettings>) defaultsField.get(null);
            Map<Structure<?>, StructureSeparationSettings> newDefaults = new HashMap<>(currentDefaults);
            newDefaults.put(structure, structureSeparationSettings);

            defaultsField.set(null, ImmutableMap.copyOf(newDefaults));
        } catch (Exception e) {
            throw new RuntimeException("Failed to modify DimensionStructuresSettings.DEFAULTS", e);
        }


        /*
         * There are very few mods that relies on seeing your structure in the noise settings registry before the world is made.
         *
         * You may see some mods add their spacings to DimensionSettings.BUILTIN_OVERWORLD instead of the NOISE_GENERATOR_SETTINGS loop below but
         * that field only applies for the default overworld and won't add to other worldtypes or dimensions (like amplified or Nether).
         * So yeah, don't do DimensionSettings.BUILTIN_OVERWORLD. Use the NOISE_GENERATOR_SETTINGS loop below instead if you must.
         */

        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();

            /*
             * Pre-caution in case a mod makes the structure map immutable like datapacks do.
             * I take no chances myself. You never know what another mods does...
             *
             * structureConfig requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
             */
            if (structureMap instanceof ImmutableMap) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                try {
                    Field structureConfigField = DimensionStructuresSettings.class.getDeclaredField("field_236193_d_");
                    structureConfigField.setAccessible(true);

                    Map<Structure<?>, StructureSeparationSettings> existingStructureMap =
                            (Map<Structure<?>, StructureSeparationSettings>) structureConfigField.get(settings.getValue().structureSettings());

                    Map<Structure<?>, StructureSeparationSettings> newTempMap = new HashMap<>(existingStructureMap);
                    newTempMap.put(structure, structureSeparationSettings);

                    structureConfigField.set(settings.getValue().structureSettings(), ImmutableMap.copyOf(newTempMap));
                } catch (Exception e) {
                    throw new RuntimeException("Failed to modify structure config", e);
                }


            } else {
                structureMap.put(structure, structureSeparationSettings);
            }
        });

    }
}
