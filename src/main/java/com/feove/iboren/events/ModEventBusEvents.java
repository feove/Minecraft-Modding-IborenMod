package com.feove.iboren.events;

import com.feove.iboren.IborenMod;
import com.feove.iboren.entity.EntityRegistry;
import com.feove.iboren.entity.custom.CustomCow;
import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = IborenMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event){

        event.put(EntityRegistry.CUSTOM_COW.get(), CustomCow.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        SpawnEggItem.eggs();
    }
}
