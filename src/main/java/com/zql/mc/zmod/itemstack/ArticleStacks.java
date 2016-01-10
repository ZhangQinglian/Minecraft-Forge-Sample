package com.zql.mc.zmod.itemstack;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author scott 定义一些常用的物品组
 */
public class ArticleStacks {

    /**
     * 钻石 * 1
     */
    public final static ItemStack DIAMOND_STACK_1 = getItemStack(Items.diamond, 1);
    /**
     * 土块 * 1
     */
    public final static ItemStack DIRT_STACK_1 = getItemStack(Blocks.dirt, 1);

    /**
     * 沙子 * 1
     */
    public final static ItemStack SAND_STACK_1 = getItemStack(Blocks.sand, 1);

    /**
     * 羊毛 * 1 橙色
     */
    public final static ItemStack WOOL_ORANGE_1 = getItemStack(Blocks.wool, 1, 1);

    /**
     * 羊毛 * 1 黄色
     */
    public final static ItemStack WOOL_YELLOW_1 = getItemStack(Blocks.wool, 1, 4);

    /**
     * 羊毛 * 1 黑色
     */
    public final static ItemStack WOOL_BLACK_1 = getItemStack(Blocks.wool, 1, 15);

    /**
     * 
     * @param item
     *            物品
     * @param count
     *            物品数量
     * @return
     */
    public static ItemStack getItemStack(Item item, int count) {
        return new ItemStack(item, count);
    }

    /**
     * 
     * @param block
     *            方块
     * @param count
     *            方块数量
     * @return
     */
    public static ItemStack getItemStack(Block block, int count) {
        return new ItemStack(block, count);
    }

    /**
     * 
     * @param block
     *            方块
     * @param count
     *            方块数量
     * @param metaData
     *            元数据，如方块是羊毛Wool的话，metaData的值是0～15，表示不同的颜色。
     * @return
     */
    public static ItemStack getItemStack(Block block, int count, int metaData) {
        return new ItemStack(block, count, metaData);
    }
}
