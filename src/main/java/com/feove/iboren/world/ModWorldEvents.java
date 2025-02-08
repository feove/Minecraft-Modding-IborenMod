package com.feove.iboren.world;


import com.feove.iboren.world.gen.ModOreGeneration;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoad(BiomeLoadingEvent event) {

        Biome.Category biome = event.getCategory();
        ModOreGeneration.generateOres(event);
    }

}
