package com.zql.mc.zmod.item.simple_item_01;

import com.zql.mc.zmod.ZMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class StartupClientOnly {
    public static void preInitClientOnly() {
    }

    public static void initClientOnly() {
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(
                ZMod.prependModID(SimpleItem01.ITEM_NAME), "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(StartupCommon.simpleItem01,
                DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    public static void postInitClientOnly() {
    }
}
