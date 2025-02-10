package com.feove.iboren.entity;

import com.feove.iboren.IborenMod;
import com.feove.iboren.entity.custom.CustomCow;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, IborenMod.MOD_ID);

    public static final RegistryObject<EntityType<CustomCow>> CUSTOM_COW =
            ENTITY_TYPES.register("custom_cow",
                    () -> EntityType.Builder.of(CustomCow::new, EntityClassification.CREATURE)
                            .sized(1.0F, 1.4F)
                            .build("custom_cow"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
