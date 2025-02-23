package com.feove.iboren.world;


import com.feove.iboren.world.gen.ModEntityGeneration;
import com.feove.iboren.world.gen.ModOreGeneration;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.feove.iboren.world.STStructures.IBOREN_TREASURE;

public class ModWorldEvents {


    @SubscribeEvent
    public static void biomeLoad(BiomeLoadingEvent event) {

        ModOreGeneration.generateOres(event);
        ModEntityGeneration.onEntitySpawn(event);

    }


}
