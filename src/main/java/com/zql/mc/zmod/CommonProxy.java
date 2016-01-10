package com.zql.mc.zmod;

import com.zql.mc.zmod.itemstack.RecipeInitor;

import net.minecraft.entity.player.EntityPlayer;

/**
 * CommonProxy is used to set up the mod and start it running. It contains all
 * the code that should run on both the Standalone client and the dedicated
 * server. For more background information see here
 * http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.
 * html
 */
public abstract class CommonProxy {

    /**
     * Run before anything else. Read your config, create blocks, items, etc,
     * and register them with the GameRegistry
     */
    public void preInit() {
        // read config first
        
        //register craft and smelt
        RecipeInitor.initAllRecipe();
        
        com.zql.mc.zmod.item.simple_block_01.StartupCommon.preInitCommon();
        com.zql.mc.zmod.item.simple_item_01.StartupCommon.preInitCommon();
    }

    /**
     * Do your mod setup. Build whatever data structures you care about.
     * Register recipes, send FMLInterModComms messages to other mods.
     */
    public void init() {
        com.zql.mc.zmod.item.simple_block_01.StartupCommon.initCommon();
        com.zql.mc.zmod.item.simple_item_01.StartupCommon.initCommon();
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    public void postInit() {
    }

    // helper to determine whether the given player is in creative mode
    // not necessary for most examples
    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

}
