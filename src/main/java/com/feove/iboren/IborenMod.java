    package com.feove.iboren;

    import com.feove.iboren.block.ModBlocks;
    import com.feove.iboren.client.render.CustomCowRenderer;
    import com.feove.iboren.entity.EntityRegistry;

    import com.feove.iboren.entity.custom.CustomCow;
    import com.feove.iboren.item.ModItems;

    import com.feove.iboren.world.ModWorldEvents;
    import net.minecraft.block.Block;
    import net.minecraft.block.Blocks;
    import net.minecraft.client.renderer.RenderType;
    import net.minecraft.client.renderer.RenderTypeLookup;
    import net.minecraft.entity.EntitySpawnPlacementRegistry;
    import net.minecraft.entity.MobEntity;
    import net.minecraft.entity.passive.AnimalEntity;
    import net.minecraft.world.gen.Heightmap;
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

    import java.util.stream.Collectors;

    // The value here should match an entry in the META-INF/mods.toml file
    @Mod(IborenMod.MOD_ID)
    public class IborenMod
    {
        public static final String MOD_ID = "iboren";

        // Directly reference a log4j logger.
        private static final Logger LOGGER = LogManager.getLogger();

        public IborenMod() {

            IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

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

            });


            LOGGER.info("HELLO FROM PREINIT");
            LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        }


        private void doClientStuff(final FMLClientSetupEvent event) {

        }

        private void enqueueIMC(final InterModEnqueueEvent event)
        {
            // some example code to dispatch IMC to another mod
            InterModComms.sendTo("iboren", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
        }

        private void processIMC(final InterModProcessEvent event)
        {
            // some example code to receive and process InterModComms from other mods
            LOGGER.info("Got IMC {}", event.getIMCStream().
                    map(m->m.getMessageSupplier().get()).
                    collect(Collectors.toList()));
        }
        // You can use SubscribeEvent and let the Event Bus discover methods to call
        @SubscribeEvent
        public void onServerStarting(FMLServerStartingEvent event) {
            // do something when the server starts
            LOGGER.info("HELLO from server starting");
        }

        // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
        // Event bus for receiving Registry Events)
        @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
        public static class RegistryEvents {
            @SubscribeEvent
            public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
                // register a new block here
                LOGGER.info("HELLO from Register Block");
            }
        }
    }
