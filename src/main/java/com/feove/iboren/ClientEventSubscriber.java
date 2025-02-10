package com.feove.iboren;

import com.feove.iboren.client.render.CustomCowRenderer;
import com.feove.iboren.entity.EntityRegistry;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = IborenMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(
                    EntityRegistry.CUSTOM_COW.get(),
                    CustomCowRenderer::new
            );
        });
    }
}
