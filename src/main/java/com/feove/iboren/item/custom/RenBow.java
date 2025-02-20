package com.feove.iboren.item.custom;

import com.feove.iboren.entity.EntityRegistry;
import com.feove.iboren.entity.custom.RenArrow;
import com.feove.iboren.item.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;


import java.util.function.Predicate;

public class RenBow extends BowItem {

    public RenBow(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {

        if (arrow.getOwner() instanceof LivingEntity) {

            RenArrow renArrow = new RenArrow(EntityRegistry.REN_ARROW.get(), arrow.level);
            renArrow.setOwner( arrow.getOwner());
            renArrow.setPos(arrow.getX(), arrow.getY(), arrow.getZ());
            renArrow.xRotO = arrow.xRotO;
            renArrow.yRotO = arrow.yRotO;
            renArrow.setBaseDamage(10.0D);

            return renArrow;

        }
        return arrow;
    }

    @Override
    public void releaseUsing(ItemStack stack, World world, LivingEntity shooter, int timeLeft) {

        super.releaseUsing(stack, world, shooter, timeLeft);

        if (!world.isClientSide && shooter instanceof PlayerEntity) {

            int charge = this.getUseDuration(stack) - timeLeft;
            float power = BowItem.getPowerForTime(charge);
            if (power < 0.1F) return;

            if (world.random.nextFloat() < 0.3F) {
                PlayerEntity player = (PlayerEntity) shooter;

                spawnExtraArrow(stack, world, player, power, 5.0F);
                spawnExtraArrow(stack, world, player, power, -5.0F);
            }
        }
    }

    private void spawnExtraArrow(ItemStack stack, World world, PlayerEntity shooter, float power, float angleOffset) {

        AbstractArrowEntity extraArrow = ((RenArrowItem) ModItems.REN_ARROW.get()).createArrow(world, stack, shooter);

        extraArrow.setBaseDamage(8.0D);

        extraArrow.shootFromRotation(shooter, shooter.xRot, shooter.yRot + angleOffset, 0.0F, power * 3.0F, 1.0F);
        extraArrow.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
        extraArrow.hurtMarked = true;
        extraArrow.animateHurt();
        extraArrow.setOwner(shooter);
        extraArrow.setKnockback(2);

        int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
        if (powerLevel > 0) {
            extraArrow.setBaseDamage(extraArrow.getBaseDamage() + powerLevel * 0.5D + 0.5D);
        }
        int punchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
        if (punchLevel > 0) {
            extraArrow.setKnockback(punchLevel);
        }
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0) {
            extraArrow.setSecondsOnFire(100);
        }

        world.addFreshEntity(extraArrow);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (ren_arrow) -> ren_arrow.getItem() == ModItems.REN_ARROW.get();
    }
}
