package com.feove.iboren.events;

import com.feove.iboren.IborenMod;
import com.feove.iboren.commands.IborenCommands;
import com.feove.iboren.entity.EntityRegistry;
import com.feove.iboren.entity.custom.CustomCow;
import com.feove.iboren.entity.custom.RenArcher;
import com.feove.iboren.entity.custom.RenArrow;
import com.feove.iboren.entity.custom.RenZombie;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = IborenMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {

        event.put(EntityRegistry.CUSTOM_COW.get(), CustomCow.setCustomAttributes().build());
        event.put(EntityRegistry.REN_ZOMBIE.get(), RenZombie.setCustomAttributes().build());
        event.put(EntityRegistry.REN_ARCHER.get(), RenArcher.setCustomAttributes().build());

    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        SpawnEggItem.eggs();
    }
}
