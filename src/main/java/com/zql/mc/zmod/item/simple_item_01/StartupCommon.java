package com.zql.mc.zmod.item.simple_item_01;


import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {

    public static SimpleItem01 simpleItem01;
    
    public static void preInitCommon() {
        simpleItem01 = (SimpleItem01) (new SimpleItem01().setUnlocalizedName(SimpleItem01.ITEM_NAME));
        GameRegistry.registerItem(simpleItem01, SimpleItem01.ITEM_NAME);
    }

    public static void initCommon() {
    }

    public static void postInitCommon() {
    }
}
