package com.feove.iboren.world;


import com.feove.iboren.entity.EntityRegistry;
import com.feove.iboren.world.gen.ModOreGeneration;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModWorldEvents {


    @SubscribeEvent
    public static void biomeLoad(BiomeLoadingEvent event) {

        ModOreGeneration.generateOres(event);

    }


}
