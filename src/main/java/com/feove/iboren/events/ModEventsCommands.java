package com.feove.iboren.events;

import com.feove.iboren.commands.IborenCommands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModEventsCommands {


    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        new IborenCommands(event.getDispatcher());
    }

}
