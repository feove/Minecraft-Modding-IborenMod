package com.feove.iboren.item.custom;

import com.feove.iboren.entity.EntityRegistry;
import com.feove.iboren.entity.custom.RenArrow;
import com.feove.iboren.item.ModItems;
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
            return renArrow;

        }
        return arrow;
    }




    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (ren_arrow) -> ren_arrow.getItem() == ModItems.REN_ARROW.get();
    }
}
