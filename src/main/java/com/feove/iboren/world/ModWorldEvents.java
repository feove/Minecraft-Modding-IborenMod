package com.feove.iboren.world;


import com.feove.iboren.world.gen.ModEntityGeneration;
import com.feove.iboren.world.gen.ModOreGeneration;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModWorldEvents {


    @SubscribeEvent
    public static void biomeLoad(BiomeLoadingEvent event) {

        ModOreGeneration.generateOres(event);
        ModEntityGeneration.onEntitySpawn(event);

    }


}
