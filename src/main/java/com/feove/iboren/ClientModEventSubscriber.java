package com.feove.iboren;

import com.feove.iboren.client.render.CustomCowRenderer;
import com.feove.iboren.client.render.RenZombieRenderer;
import com.feove.iboren.entity.EntityRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "iboren", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.REN_ZOMBIE.get(), RenZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.CUSTOM_COW.get(), CustomCowRenderer::new);


    }
}
