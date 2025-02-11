package com.feove.iboren.entity;

import com.feove.iboren.IborenMod;
import com.feove.iboren.entity.custom.CustomCow;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.rmi.CORBA.Util;


public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, IborenMod.MOD_ID);

    public static final RegistryObject<EntityType<CustomCow>> CUSTOM_COW =
            ENTITY_TYPES.register("custom_cow",
                    () -> EntityType.Builder.of(CustomCow::new, EntityClassification.CREATURE)
                            .sized(1.0F, 1.4F)
                            .build("custom_cow")
            );

    public static void register(IEventBus eventBus) {

        ENTITY_TYPES.register(eventBus);
    }

    public static void registerAttributes() {
        GlobalEntityTypeAttributes.put(CUSTOM_COW.get(),
                MobEntity.createMobAttributes()
                        .add(Attributes.MAX_HEALTH, 10.0D)
                        .add(Attributes.MOVEMENT_SPEED, 0.2D)
                        .build()
        );
    }

}
