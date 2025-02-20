package com.feove.iboren.item.custom;

import com.feove.iboren.entity.custom.RenArrow;
import com.feove.iboren.entity.EntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RenArrowItem extends ArrowItem {

    public RenArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack ammoStack, LivingEntity shooter) {
        RenArrow renArrow = new RenArrow(world, shooter);
        return renArrow;
    }


}
