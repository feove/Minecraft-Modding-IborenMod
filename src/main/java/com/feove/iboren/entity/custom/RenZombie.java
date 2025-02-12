package com.feove.iboren.entity.custom;

import com.feove.iboren.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Random;

public class RenZombie extends ZombieEntity {

    private static final Random RANDOM = new Random();

    public RenZombie(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.populateEquipment();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        double speed = RANDOM.nextDouble() < 0.1 ? 0.45D : 0.25D;
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, speed)
                .add(Attributes.ATTACK_DAMAGE, 13.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.005D)
                .add(Attributes.ARMOR, 3.0D);
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
            double chance = RANDOM.nextDouble() * 100;

            if (chance < 45.0) {
                this.spawnAtLocation(new ItemStack(Items.ROTTEN_FLESH));
            }

            else if (chance < 68.0) {
                int ingotAmount = 2 + RANDOM.nextInt(4);
                this.spawnAtLocation(new ItemStack(Items.IRON_INGOT, ingotAmount));
            }

            else if (chance < 70.0) {
                ItemStack heldItem = this.getItemBySlot(EquipmentSlotType.MAINHAND);
                if (!heldItem.isEmpty()) {
                    this.spawnAtLocation(heldItem);
                }
            }

            else if (chance < 73.0) {
                if (RANDOM.nextDouble() < 0.7) {
                    this.spawnAtLocation(createEnchantedBook(false));
                } else {
                    this.spawnAtLocation(createEnchantedBook(true));
                }
            }

            else if (chance < 73.5) {
                this.spawnAtLocation(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
            }

            else if (chance < 74.0) {
                this.spawnAtLocation(new ItemStack(ModItems.REN.get()));
            }

            else if (chance < 75.5) {
                this.spawnAtLocation(new ItemStack(Items.ANVIL));
            }

            else if (chance < 76.5) {
                ItemStack enchantedSword = new ItemStack(Items.IRON_SWORD);
                enchantedSword.enchant(Enchantments.SHARPNESS, 1 + RANDOM.nextInt(5));
                this.spawnAtLocation(enchantedSword);
            }

            else if (chance < 77.0) {
                this.spawnAtLocation(new ItemStack(Items.DIAMOND_SWORD));
            }

            else if (chance < 77.5) {
                this.spawnAtLocation(new ItemStack(Items.ENDER_PEARL));
            }

            else if (chance < 78.0) {
                ItemStack enchantedBow = new ItemStack(Items.BOW);
                enchantedBow.enchant(Enchantments.POWER_ARROWS, 1 + RANDOM.nextInt(5));
                this.spawnAtLocation(enchantedBow);
            }

            else if (chance < 88.0) {
                this.spawnAtLocation(new ItemStack(ModItems.REN_ZOMBIE_SPAWN_EGG.get()));
            }

            for (EquipmentSlotType slot : EquipmentSlotType.values()) {
                if (slot.getType() == EquipmentSlotType.Group.ARMOR) {
                    ItemStack equipped = this.getItemBySlot(slot);
                    if (!equipped.isEmpty() && RANDOM.nextDouble() < 0.05) {
                        this.spawnAtLocation(equipped);
                    }
                }
            }

            if (RANDOM.nextDouble() < 0.0025) {
                int randomArmor = RANDOM.nextInt(4);
                switch(randomArmor) {
                    case 0:
                        this.spawnAtLocation(new ItemStack(ModItems.REN_HELMET.get()));
                        break;
                    case 1:
                        this.spawnAtLocation(new ItemStack(ModItems.REN_CHESTPLATE.get()));
                        break;
                    case 2:
                        this.spawnAtLocation(new ItemStack(ModItems.REN_LEGGINGS.get()));
                        break;
                    case 3:
                        this.spawnAtLocation(new ItemStack(ModItems.REN_BOOTS.get()));
                        break;
                }
            }


            if (RANDOM.nextDouble() < 0.001) {
                EquipmentSlotType[] armorSlots = {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
                for (EquipmentSlotType slot : armorSlots) {
                    ItemStack armorItem = getRandomArmor(slot);
                    if (!armorItem.isEmpty()) {
                        this.spawnAtLocation(armorItem);
                    }
                }
            }

        }

        if (RANDOM.nextDouble() < 0.10) {
            AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
            cloud.setRadius(3.0F);
            cloud.setDuration(200);
            cloud.setWaitTime(0);
            double effectRoll = RANDOM.nextDouble();
            if (effectRoll < 0.25) {

                cloud.addEffect(new EffectInstance(Effects.POISON, 200, 1));
            } else if (effectRoll < 0.50) {

                cloud.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 1, 1));
            } else if (effectRoll < 0.75) {

                cloud.addEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
            } else {

                cloud.addEffect(new EffectInstance(Effects.BLINDNESS, 200, 1));
            }
            this.level.addFreshEntity(cloud);
        }
    }

    private ItemStack createEnchantedBook(boolean full) {
        ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
        int level = full ? 5 : 1;
        Enchantment[] possibleEnchantments = {
                Enchantments.FIRE_PROTECTION,
                Enchantments.SHARPNESS,
                Enchantments.UNBREAKING,
                Enchantments.FIRE_ASPECT,
                Enchantments.BLOCK_FORTUNE,
                Enchantments.BLOCK_EFFICIENCY
        };
        Enchantment chosen = possibleEnchantments[RANDOM.nextInt(possibleEnchantments.length)];
        book.enchant(chosen, level);
        return book;
    }

    private ItemStack getRandomArmor(EquipmentSlotType slot) {
        ItemStack armor = ItemStack.EMPTY;

        if (slot == EquipmentSlotType.HEAD) {
            armor = new ItemStack(ModItems.REN_HELMET.get());
        } else if (slot == EquipmentSlotType.CHEST) {
            armor = new ItemStack(ModItems.REN_CHESTPLATE.get());
        } else if (slot == EquipmentSlotType.LEGS) {
            armor = new ItemStack(ModItems.REN_LEGGINGS.get());
        } else if (slot == EquipmentSlotType.FEET) {
            armor = new ItemStack(ModItems.REN_BOOTS.get());
        }

        if (!armor.isEmpty() && RANDOM.nextDouble() < 0.5) {
            Enchantment[] enchantments = {
                    Enchantments.PROJECTILE_PROTECTION,
                    Enchantments.THORNS,
                    Enchantments.UNBREAKING,
                    Enchantments.RESPIRATION
            };
            Enchantment enchantment = enchantments[RANDOM.nextInt(enchantments.length)];
            armor.enchant(enchantment, RANDOM.nextInt(3) + 1);
        }
        return armor;
    }

    private void populateEquipment() {
        // 60% chance to have a weapon
        if (RANDOM.nextDouble() < 0.6) {
            double weaponChance = RANDOM.nextDouble();
            if (weaponChance < 0.3) {

                this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } else if (weaponChance < 0.35) {

                this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.REN_SWORD.get()));
            } else if (weaponChance < 0.4) {

                this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.REN_AXE.get()));
            }
        }

        if (RANDOM.nextDouble() < 0.20) {
            this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(ModItems.REN_HELMET.get()));
        }
        if (RANDOM.nextDouble() < 0.20) {
            this.setItemSlot(EquipmentSlotType.CHEST, new ItemStack(ModItems.REN_CHESTPLATE.get()));
        }
        if (RANDOM.nextDouble() < 0.20) {
            this.setItemSlot(EquipmentSlotType.LEGS, new ItemStack(ModItems.REN_LEGGINGS.get()));
        }
        if (RANDOM.nextDouble() < 0.20) {
            this.setItemSlot(EquipmentSlotType.FEET, new ItemStack(ModItems.REN_BOOTS.get()));
        }
    }
}
