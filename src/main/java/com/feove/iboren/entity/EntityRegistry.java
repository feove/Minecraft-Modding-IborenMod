package com.feove.iboren.entity;

import com.feove.iboren.IborenMod;
import com.feove.iboren.entity.custom.CustomCow;
import com.feove.iboren.entity.custom.RenArcher;
import com.feove.iboren.entity.custom.RenArrow;
import com.feove.iboren.entity.custom.RenZombie;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
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
                            .build(new ResourceLocation(IborenMod.MOD_ID, "custom_cow").toString())
            );

    public static final RegistryObject<EntityType<RenZombie>> REN_ZOMBIE =
            ENTITY_TYPES.register("ren_zombie",
            () -> EntityType.Builder.of(RenZombie::new, EntityClassification.MONSTER)
                    .fireImmune()
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation(IborenMod.MOD_ID, "ren_zombie").toString())
            );

    public static final RegistryObject<EntityType<RenArcher>> REN_ARCHER =
            ENTITY_TYPES.register("ren_archer",
                    () -> EntityType.Builder.of(RenArcher::new, EntityClassification.MONSTER)
                            .sized(0.6F, 1.95F)
                            .fireImmune()
                            .build(new ResourceLocation(IborenMod.MOD_ID, "ren_archer").toString())
            );


    public static final RegistryObject<EntityType<RenArrow>> REN_ARROW = ENTITY_TYPES.register("ren_arrow",
            () -> EntityType.Builder.<RenArrow>of(RenArrow::new, EntityClassification.MISC)
                    .sized(0.5F, 0.5F) // Make sure hitbox is not 0
                    .clientTrackingRange(4) // Ensure it's visible to clients
                    .updateInterval(20) // Sync updates properly
                    .build(new ResourceLocation("iboren", "ren_arrow").toString()));

    public static void register(IEventBus eventBus) {

        ENTITY_TYPES.register(eventBus);
    }

}
