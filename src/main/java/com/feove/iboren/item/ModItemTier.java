package com.feove.iboren.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {

    REN_SWORD(3, 150, 4f, 12f, 10, ModItems.REN_SWORD),
    REN_PICKAXE(3,150,4f,4f,10,ModItems.REN_PICKAXE),
    REN_AXE(3,150,4f,5f,10,ModItems.REN_AXE),
    REN_SHOVEL(3,150,4f,2f,10,ModItems.REN_SHOVEL),
    IBO_SWORD(2,100,3f,8f,10,ModItems.IBO_SWORD);

    private final LazyValue<Ingredient> repairMaterial;

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;


    ModItemTier(int harvestLevel, int maxUses, float efficiency,
                float attackDamage, int enchantability,
                Supplier<Item> repairMaterialSupplier) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(() -> Ingredient.of(repairMaterialSupplier.get()));
    }


    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
