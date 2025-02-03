package com.feove.iboren.item;

import com.feove.iboren.IborenMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import com.feove.iboren.item.ModItems;

public class ModItemGroup {

    public static final ItemGroup IBOREN_GROUP = new ItemGroup("iborenTab") {

        @Override
        public ItemStack makeIcon() {

            return new ItemStack(ModItems.IBO.get());
        }
    };
}
