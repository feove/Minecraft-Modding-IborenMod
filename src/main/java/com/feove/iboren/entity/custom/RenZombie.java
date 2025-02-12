package com.feove.iboren.entity.custom;

import com.feove.iboren.block.ModBlocks;
import com.feove.iboren.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

public class RenZombie extends ZombieEntity {

    private static final Random RANDOM = new Random();

    public RenZombie(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.populateEquipment(); // Assigns armor and weapons
        this.setCustomHorse(); // Assigns the horse if needed
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.22D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 13.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 100.0D);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (!super.doHurtTarget(entity)) {
            return false;
        } else if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 100, 1));
        }
        return true;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource, int lootingMultiplier, boolean hitRecently) {
        if (!this.level.isClientSide) {
            double chance = RANDOM.nextDouble() * 100; // Random number between 0-100

            // Drops Rotten Flesh
            if (chance < 45.0) {
                this.spawnAtLocation(new ItemStack(Items.ROTTEN_FLESH));
            }
            // Drops Iron Ingots (2-5)
            else if (chance < 85.0) {
                int ironAmount = 2 + RANDOM.nextInt(4); // Random number between 2 and 5
                this.spawnAtLocation(new ItemStack(Items.IRON_INGOT, ironAmount));
            }
            // Drops Weapon held by zombie
            else if (chance < 90.0) {
                ItemStack heldItem = this.getItemBySlot(EquipmentSlotType.MAINHAND);
                if (!heldItem.isEmpty()) {
                    this.spawnAtLocation(heldItem);
                }
            }
            // 0.5% chance: Drops the Notch Apple
            else if (chance < 95.0) {
                this.spawnAtLocation(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
            }
            // 1% chance: Drops iboren:ren item
            else if (chance < 96.5) {
                this.spawnAtLocation(new ItemStack(ModItems.REN.get()));
            }
            // 0.5% chance for Anvil
            else if (chance < 97.5) {
                this.spawnAtLocation(new ItemStack(Items.ANVIL));
            }
            // 1% chance for Enchanted Items (Sword)
            else if (chance < 98.5) {
                ItemStack enchantedSword = new ItemStack(Items.IRON_SWORD);
                enchantedSword.enchant(Enchantments.SHARPNESS, 1);
                this.spawnAtLocation(enchantedSword);
            }
            // 1% chance for Diamond Item
            else if (chance < 99.0) {
                this.spawnAtLocation(new ItemStack(Items.DIAMOND_SWORD)); // Change item if needed
            }
            // 0.5% chance for Ender Pearl
            else if (chance < 99.5) {
                this.spawnAtLocation(new ItemStack(Items.ENDER_PEARL));
            }
            // 0.5% chance for Enchanted Bow
            else if (chance < 99.7) {
                ItemStack enchantedBow = new ItemStack(Items.BOW);
                enchantedBow.enchant(Enchantments.POWER_ARROWS, 1);
                this.spawnAtLocation(enchantedBow);
            }else if (chance < 99.9) {
                this.spawnAtLocation(new ItemStack(ModBlocks.REN_BLOCK.get()));
            } else {
                this.spawnAtLocation(new ItemStack(ModItems.REN_ZOMBIE_SPAWN_EGG.get())); // Make sure ModItems.REN_BLOCK exists
            }

            // Drop armor items with a small chance (30%)
            for (EquipmentSlotType slot : EquipmentSlotType.values()) {
                if (slot.getType() == EquipmentSlotType.Group.ARMOR) {
                    ItemStack equipped = this.getItemBySlot(slot);
                    if (!equipped.isEmpty() && RANDOM.nextDouble() < 0.3) {
                        this.spawnAtLocation(equipped);
                    }
                }
            }

            // Rare drops for iboren armor (0.05%)
            if (RANDOM.nextDouble() < 0.0005) { // 0.05% chance
                int randomArmor = RANDOM.nextInt(3); // Picks 0, 1, or 2
                if (randomArmor == 0) {
                    this.spawnAtLocation(new ItemStack(ModItems.REN_CHESTPLATE.get()));
                } else if (randomArmor == 1) {
                    this.spawnAtLocation(new ItemStack(ModItems.REN_LEGGINGS.get()));
                } else {
                    this.spawnAtLocation(new ItemStack(ModItems.REN_BOOTS.get()));
                }
            }
        }
    }

    private void populateEquipment() {
        // Randomly give a weapon
        if (RANDOM.nextDouble() < 0.6) { // 60% chance to have a weapon
            if (RANDOM.nextBoolean()) {
                this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } else {
                this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.REN_SWORD.get()));
            }
        }

        // Randomly equip armor pieces
        if (RANDOM.nextDouble() < 0.02) { // 40% chance to have a helmet
            this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(Items.IRON_HELMET));
        }
        if (RANDOM.nextDouble() < 0.03) { // 30% chance to have a chestplate
            this.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
        }
        if (RANDOM.nextDouble() < 0.02) { // 20% chance to have leggings
            this.setItemSlot(EquipmentSlotType.LEGS, new ItemStack(Items.IRON_LEGGINGS));
        }
        if (RANDOM.nextDouble() < 0.02) { // 20% chance to have boots
            this.setItemSlot(EquipmentSlotType.FEET, new ItemStack(Items.IRON_BOOTS));
        }
    }

    private void setCustomHorse() {

        if (RANDOM.nextDouble() < 0.1) {
            HorseEntity horse = EntityType.HORSE.create(this.level);
            if (horse != null) {
                horse.setPos(this.getX(), this.getY(), this.getZ());
                horse.setTamed(true);
                this.level.addFreshEntity(horse);
                this.startRiding(horse);
            }
        }
    }
}
