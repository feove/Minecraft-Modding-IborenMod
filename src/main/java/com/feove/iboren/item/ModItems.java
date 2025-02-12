package com.feove.iboren.item;

import com.feove.iboren.IborenMod;
import com.feove.iboren.entity.EntityRegistry;
import com.feove.iboren.item.custom.ModSpawnEggItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IborenMod.MOD_ID);

    //Should Declarations for an Tab Icon Later

    public static final RegistryObject<Item> IBO = ITEMS.register("ibo",() ->
            new Item(new Item.Properties().tab(ModItemGroup.IBOREN_GROUP)));

    public static final RegistryObject<Item> REN = ITEMS.register("ren",() ->
            new Item(new Item.Properties().tab(ModItemGroup.IBOREN_GROUP)
                    .fireResistant()
                    ));

    public static final RegistryObject<Item> REN_SWORD = ITEMS.register("ren_sword",
            () -> new SwordItem(ModItemTier.REN_SWORD, 1, 3f,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)
                            .fireResistant()
                            .rarity(Rarity.UNCOMMON)
            ));

    public static final RegistryObject<Item> REN_PICKAXE = ITEMS.register("ren_pickaxe",
            () -> new PickaxeItem(ModItemTier.REN_PICKAXE, 1, 2f,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)
                            .fireResistant()
                            .rarity(Rarity.UNCOMMON)
            ));

    public static final RegistryObject<Item> REN_AXE= ITEMS.register("ren_axe",
            () -> new AxeItem(ModItemTier.REN_SWORD, 2, 3f,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)
                            .fireResistant()
                            .rarity(Rarity.UNCOMMON)
            ));

    public static final RegistryObject<Item> REN_SHOVEL = ITEMS.register("ren_shovel",
            () -> new ShovelItem(ModItemTier.REN_SWORD, 2, 3f,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)
                            .fireResistant()
                            .rarity(Rarity.UNCOMMON)
            ));

    public static final RegistryObject<Item> IBO_SWORD = ITEMS.register("ibo_sword",
            () -> new SwordItem(ModItemTier.IBO_SWORD, 1, 1f,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)
            ));

    public static final RegistryObject<Item> IBO_PICKAXE = ITEMS.register("ibo_pickaxe",
            () -> new PickaxeItem(ModItemTier.IBO_PICKAXE, 1, 1f,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)
            ));

    public static final RegistryObject<Item> IBO_AXE = ITEMS.register("ibo_axe",
            () -> new AxeItem(ModItemTier.IBO_AXE, 1, 1f,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)
            ));
    public static final RegistryObject<Item> IBO_SHOVEL = ITEMS.register("ibo_shovel",
            () -> new ShovelItem(ModItemTier.IBO_SHOVEL, 1, 1f,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)
            ));

    public static final RegistryObject<Item> IBO_BOOTS = ITEMS.register("ibo_boots",
            () -> new ArmorItem(ModArmorMaterial.IBO, EquipmentSlotType.FEET,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)));

    public static final RegistryObject<Item> IBO_HELMET = ITEMS.register("ibo_helmet",
            () -> new ArmorItem(ModArmorMaterial.IBO, EquipmentSlotType.HEAD,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)));

    public static final RegistryObject<Item> IBO_CHESTPLATE = ITEMS.register("ibo_chestplate",
            () -> new ArmorItem(ModArmorMaterial.IBO, EquipmentSlotType.CHEST,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)));

    public static final RegistryObject<Item> IBO_LEGGINGS = ITEMS.register("ibo_leggings",
            () -> new ArmorItem(ModArmorMaterial.IBO, EquipmentSlotType.LEGS,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)));

    public static final RegistryObject<Item> REN_BOOTS = ITEMS.register("ren_boots",
            () -> new ArmorItem(ModArmorMaterial.REN, EquipmentSlotType.FEET,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)));

    public static final RegistryObject<Item> REN_HELMET = ITEMS.register("ren_helmet",
            () -> new ArmorItem(ModArmorMaterial.REN, EquipmentSlotType.HEAD,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)));

    public static final RegistryObject<Item> REN_CHESTPLATE = ITEMS.register("ren_chestplate",
            () -> new ArmorItem(ModArmorMaterial.REN, EquipmentSlotType.CHEST,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)));

    public static final RegistryObject<Item> REN_LEGGINGS = ITEMS.register("ren_leggings",
            () -> new ArmorItem(ModArmorMaterial.REN, EquipmentSlotType.LEGS,
                    new Item.Properties().tab(ModItemGroup.IBOREN_TOOLS)));

    public static final RegistryObject<ModSpawnEggItem> PIGEON_SPAWN_EGG = ITEMS.register("custom_cow_spawn_egg",
            () -> new ModSpawnEggItem(EntityRegistry.CUSTOM_COW, 0x879995, 0x576ABC,
                    new Item.Properties().tab(ModItemGroup.IBOREN_GROUP)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}