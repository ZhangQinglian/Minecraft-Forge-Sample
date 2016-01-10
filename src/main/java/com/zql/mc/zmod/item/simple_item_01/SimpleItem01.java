package com.zql.mc.zmod.item.simple_item_01;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SimpleItem01 extends Item{

    public static String ITEM_NAME = "simple_item_01";

    public SimpleItem01() {
        //item一组的数量为1
        setMaxStackSize(1);
        //item出现在tab的杂项页面
        setCreativeTab(CreativeTabs.tabMisc);
    }
}
