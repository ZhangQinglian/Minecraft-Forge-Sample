package com.zql.mc.zmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ZMod.MOD_ID, version = ZMod.VERSION, name = ZMod.MOD_NAME)
public class ZMod {
    public static final String MOD_ID = "ZMod";
    public static final String VERSION = "1.0";
    public static final String MOD_NAME = "ZMod";

    @Mod.Instance(value = ZMod.MOD_ID)
    public static ZMod instance;

    @SidedProxy(clientSide = "com.zql.mc.zmod.ClientOnlyProxy", serverSide = "com.zql.mc.zmod.DedicatedServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
