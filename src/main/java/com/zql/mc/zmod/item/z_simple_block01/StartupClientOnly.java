package com.zql.mc.zmod.item.z_simple_block01;

import com.zql.mc.zmod.ZMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupClientOnly {
    public static void preInitClientOnly() {
    }

    public static void initClientOnly() {
        Item itemBlockSimple = GameRegistry.findItem("zmod",SimpleBlock01.BLOCK_NAME);
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(ZMod.MOD_ID + ":" + SimpleBlock01.BLOCK_NAME,
                "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockSimple, DEFAULT_ITEM_SUBTYPE,
                itemModelResourceLocation);
    }

    public static void postInitClientOnly() {
    }
}
