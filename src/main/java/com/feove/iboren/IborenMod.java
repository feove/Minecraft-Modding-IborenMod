    package com.feove.iboren;

    import com.feove.iboren.block.ModBlocks;
    import com.feove.iboren.entity.EntityRegistry;
    import com.feove.iboren.item.ModItems;

    import com.feove.iboren.world.ModWorldEvents;
    import com.feove.iboren.world.STStructures;
    import net.minecraft.block.Block;
    import net.minecraft.block.Blocks;

    import net.minecraft.entity.EntitySpawnPlacementRegistry;
    import net.minecraft.entity.MobEntity;
    import net.minecraft.entity.passive.AnimalEntity;
    import net.minecraft.world.gen.Heightmap;
    import net.minecraft.world.gen.feature.structure.Structure;
    import net.minecraft.world.gen.settings.DimensionStructuresSettings;
    import net.minecraft.world.gen.settings.StructureSeparationSettings;
    import net.minecraftforge.common.MinecraftForge;
    import net.minecraftforge.event.RegistryEvent;
    import net.minecraftforge.eventbus.api.IEventBus;
    import net.minecraftforge.eventbus.api.SubscribeEvent;
    import net.minecraftforge.fml.InterModComms;
    import net.minecraftforge.fml.common.Mod;
    import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
    import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
    import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
    import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
    import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
    import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;
    import software.bernie.geckolib3.GeckoLib;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.stream.Collectors;

    @Mod(IborenMod.MOD_ID)
    public class IborenMod
    {
        public static final String MOD_ID = "iboren";

        private static final Logger LOGGER = LogManager.getLogger();

        public IborenMod() {

            GeckoLib.initialize();

            IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

            STStructures.STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());

            MinecraftForge.EVENT_BUS.register(ModWorldEvents.class);

            ModItems.ITEMS.register(eventBus);
            ModBlocks.BLOCKS.register(eventBus);
            EntityRegistry.ENTITY_TYPES.register(eventBus);

            eventBus.addListener(this::setup);
            eventBus.addListener(this::enqueueIMC);
            eventBus.addListener(this::processIMC);
            eventBus.addListener(this::doClientStuff);

            MinecraftForge.EVENT_BUS.register(this);
        }

        private void setup(final FMLCommonSetupEvent event) {


            event.enqueueWork(() -> {

                EntitySpawnPlacementRegistry.register(EntityRegistry.CUSTOM_COW.get(),EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                        Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);

                EntitySpawnPlacementRegistry.register(EntityRegistry.REN_ZOMBIE.get(),EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                        Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::checkMobSpawnRules);

                EntitySpawnPlacementRegistry.register(EntityRegistry.REN_ARCHER.get(),EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                        Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::checkMobSpawnRules);

                Map<Structure<?>, StructureSeparationSettings> defaults = new HashMap<>(DimensionStructuresSettings.DEFAULTS);
                defaults.put(STStructures.IBOREN_TREASURE.get(), new StructureSeparationSettings(3, 2, 1234567890));
                DimensionStructuresSettings.DEFAULTS.putAll(defaults);

            });


            LOGGER.info("HELLO FROM PREINIT");
            LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        }

        private void doClientStuff(final FMLClientSetupEvent event) {

        }

        private void enqueueIMC(final InterModEnqueueEvent event) {

            InterModComms.sendTo("iboren", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
        }

        private void processIMC(final InterModProcessEvent event) {
            LOGGER.info("Got IMC {}", event.getIMCStream().
                    map(m->m.getMessageSupplier().get()).
                    collect(Collectors.toList()));
        }

        @SubscribeEvent
        public void onServerStarting(FMLServerStartingEvent event) {
            LOGGER.info("HELLO from server starting");
        }


        @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
        public static class RegistryEvents {
            @SubscribeEvent
            public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
                LOGGER.info("HELLO from Register Block");
            }
        }
    }
