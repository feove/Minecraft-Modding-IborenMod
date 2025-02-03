package com.feove.iboren.item;

import com.feove.iboren.IborenMod;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IborenMod.MOD_ID);


    public static final RegistryObject<Item> IBO = ITEMS.register("ibo",() ->
            new Item(new Item.Properties().tab(ModItemGroup.IBOREN_GROUP)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
