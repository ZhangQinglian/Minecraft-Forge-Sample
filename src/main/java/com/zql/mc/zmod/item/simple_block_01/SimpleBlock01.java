package com.zql.mc.zmod.item.simple_block_01;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SimpleBlock01 extends Block {

    public static final String BLOCK_NAME = "simple_block_01";
    public SimpleBlock01(Material material){
        super(material);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}

