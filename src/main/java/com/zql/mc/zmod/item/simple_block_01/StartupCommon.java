package com.zql.mc.zmod.item.simple_block_01;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class StartupCommon {
    public static SimpleBlock01 simpleBlock01;

    public static void preInitCommon() {
        simpleBlock01 = (SimpleBlock01) (new SimpleBlock01(Material.rock).setUnlocalizedName(SimpleBlock01.BLOCK_NAME));
        GameRegistry.registerBlock(simpleBlock01, SimpleBlock01.BLOCK_NAME);
    }

    public static void initCommon() {
    }

    public static void postInitCommon() {
    }

}
