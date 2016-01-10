package com.zql.mc.zmod.itemstack;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * 用于初始化mod所需要的合成列表
 * @author scott
 *
 */
public class RecipeInitor {

    /**
     * 在CommonProxy的preInit中调用
     */
    public static void initAllRecipe() {

        // ------------------------------------------------------无序合成表------------------------------------------------------
        // 泥土*1 => 钻石*1
        GameRegistry.addShapelessRecipe(ArticleStacks.DIAMOND_STACK_1, ArticleStacks.DIRT_STACK_1);

        // 橙色羊毛 * 1 + 黄色羊毛 * 1 => 黑色羊毛 * 1
        GameRegistry.addShapelessRecipe(ArticleStacks.WOOL_BLACK_1, ArticleStacks.WOOL_ORANGE_1,
                ArticleStacks.WOOL_YELLOW_1);

        
        // ------------------------------------------------------有序合成表------------------------------------------------------
        // A B A
        // B A B
        // A B A
        // A为泥土*1，B为沙子*1 ＝> 钻石*1
        GameRegistry.addRecipe(ArticleStacks.DIAMOND_STACK_1, "ABA", "BAB", "ABA", 'A', ArticleStacks.DIRT_STACK_1, 'B',
                ArticleStacks.SAND_STACK_1);

        // -----------------------------------------------------配方表及经验值----------------------------------------------------
        // TODO 暂时不清楚addSmelting是否只是对游戏中现有的制作有用，如烹饪，熔铸等。
        // FIXME
        GameRegistry.addSmelting(ArticleStacks.DIRT_STACK_1, ArticleStacks.DIRT_STACK_1, 1.0f);
    }

}
