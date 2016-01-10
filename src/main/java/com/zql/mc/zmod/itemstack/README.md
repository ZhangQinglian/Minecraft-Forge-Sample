# Minecraft Forge编程入门二 “工艺和食谱”

从现在开始我们就要开始真正写代码了，还没有来得及配置环境的同学可以参考[Minecraft Forge编程入门一 “环境搭建”](http://www.zqlite.com/2016/01/06/minecraft-primer-1/)这篇文章来进行环境搭建。

# 工艺(Craft)和食谱(Recepe)
所谓的工艺和食谱是指在Minecraft中物品的合成和合成表。
- 工艺:指由一种或几种物品合成出另外一种物品，
如常见的火把的合成:
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-06%20at%204.54.19%20PM.png)

- 食谱:泛指物品合成的摆放顺序。有有形和无形两种，其中有形要求合成时物品间的相对位置不能变即形状不变，无形则只要求物品满足条件即可对形状没要求。
如指南针的合成是有形的:
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-06%20at%205.04.08%20PM.png)
稍微改变一下任何一个物品的顺序就无法合成了：
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-06%20at%205.10.02%20PM.png)

无形的食谱较少，当合成材料是一个的时候，可以说他是有形也可以说是无形。
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-06%20at%205.17.23%20PM.png)
或者
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-06%20at%205.18.04%20PM.png)
<!--more-->
# 合成前的基础知识

## ItemStack
在Minecraft中每种类型的方块和物品都不是多实例的，可以理解为你看到的土块在游戏内存中只是一个实例而已。这样做的目的显而易见，就是为了减少内存占用。
那如果我要表示一块土块要怎么做呢？
```java
ItemStack dirtStack = new ItemStack(Blocks.dirt);
```
n块土块呢？
```java
ItemStack dirtStack = new ItemStack(Blocks.dirt,n);
```
那如果不是土块而是羊毛呢？大家都知道羊毛是有颜色之分的？
```java
ItemStack woolStack = new ItemStack(Blocks.wool, 2, 15);
```
ItemStack的构造方法大只就这三种，总结一下起语法：
```java
ItemStack stack = new ItemStack((Object)ItemType,[(int)Stack Size],[(Object) Metadata]);
```
其中`ItemType`是必须的，另外的`Size`和`Metadata`是可选的。
- ItemType表示物品或方块的类型，在forge中使用`Blocks`和`Items`表示。
- Size表示物品或方块的数量。
- Metadata表示ItemType对应物品或方块的附加属性。

如果ItemType是羊毛Blocks.wool的话，Metadata就表示羊毛的颜色,Metadata取十进制Dec行的值。羊毛颜色如下：
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-06%20at%204.34.48%20PM.png)


由于在后面可能大量用到不同种类的ItemStack，这里我们定义一个java类，专门用来提供一些常用的ItemStack。
```java
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
```
读者也可以根据自己的需求往里面加不同的ItemStack。

# 无形合成配方
无形合成配方对物品或方块的顺序位置没有要求。
首先看一下添加无形合成配方的语法：
```java
GameRegistry.addShapelessRecipe(ItemStack result, Object... recipe);
```
其中`result`表示合成结果，`... recipe`表示配方数组。

下面我们看一个简单的例子，用一个土块和一个沙块来合成一个钻石，😄是不是不符合逻辑，没关系，我们要的就是这种效果。
```java
@Mod(modid = ZMod.MODID, version = ZMod.VERSION, name = ZMod.MOD_NAME)
public class ZMod
{
    public static final String MODID = "ZMod";
    public static final String VERSION = "1.0";
    public static final String MOD_NAME = "Z Mod";

    @Instance(value = ZMod.MODID) //Tell Forge what instance to use.
    public static ZMod instance;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        //这句是关键，ArticleStacks是我们在上面定义的工具类，用于提供ItemStack，这句代码的意思是使用一块土块和一块沙块合成一颗钻石，并且是无需的合成配方。
        GameRegistry.addShapelessRecipe(ArticleStacks.DIAMOND_STACK_1, ArticleStacks.DIRT_STACK_1,ArticleStacks.SAND_STACK_1);
    }
}
```
运行结果：
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-06%20at%205.57.55%20PM.png)
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-06%20at%205.58.31%20PM.png)
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-06%20at%205.58.44%20PM.png)

**这就是无形合成配方，你看懂了吗？**

# 有形合成配方
与无形合成配方比较，有形的合成配方在代码书写上稍微有些复杂，我们来看有形合成配方的语法：
```java
GameRegistry.addRecipe(ItemStack result,String row1, [String row2[,String row3]],char itemType1, ItemStack itemStackType1[, ... char itemTypeN, ItemStack itemStackTypeN]);
```
语法看着比较晦涩，还是看几个实际的例子吧。
- 还是土和沙合成钻石，只不过这次的合成配方类似：
| |A|B|
|A| |B|
其中A表示土块，B表示沙块，代码如下：
```java
@EventHandler
public void init(FMLInitializationEvent event)
{
    GameRegistry.addShapedRecipe(ArticleStacks.DIAMOND_STACK_1,
            " AB",
            "A B",
            'A',ArticleStacks.DIRT_STACK_1,
            'B',ArticleStacks.SAND_STACK_1);
}
```
运行结果如下：
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-07%20at%2012.11.41%20PM.png)
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-07%20at%2012.12.07%20PM.png)
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-07%20at%2012.12.16%20PM.png)

结合图片，这段代码应该不难理解了，在配方只有两行的情况下，两行的位置可以是1，2两行或者2，3两行，但不能是2，3两行。即他们之间的相对位置不能变。当然字母可以是其它任何你喜欢的字母。
配方中如有空格子，用英文状态下的空格表示。

- 再看一个三行配方的例子,这次是土块和羊毛合成钻石，配方形状如下：
|A| | |
| | | |
| | |B|
A是土块，B是羊毛
代码如下：
```java
@EventHandler
public void init(FMLInitializationEvent event)
{
    GameRegistry.addShapedRecipe(ArticleStacks.DIAMOND_STACK_1,
            "A  ",
            "   ",
            "  B",
            'A',ArticleStacks.DIRT_STACK_1,
            'B',ArticleStacks.WOOL_BLACK_1);
}
```
运行结果如下：
![](http://7xprgn.com1.z0.glb.clouddn.com/Screen%20Shot%202016-01-07%20at%2012.35.42%20PM.png)
有形的合成配方是不是也是比较简单的？期待你们能想出更多好玩的配方😄。

# 熔炼
在Minecraft中有这么一个叫做`熔炼`的概念，那何谓熔炼呢？
>熔炼指的就是提炼物质，在Minecraft中熔炼包括：melting, baking, cooking, burning, drying, or producing。其实就是常见的烤面包，烤土豆，制作蛋糕等操作。

其语法是：
```java
GameRegistry.addSmelting(Object inputItem, ItemStack result, float experience);
```
inputItem表示熔炼前的物品，可以是Item也可以是Block更可以是ItemStack，result表示熔炼后的物品。experience表示熔炼获得的经验值。

这部分我尝试了将土块熔炼成钻石的操作，结果不起作用。可能这里的熔炼需要物品间有一定的联系。
这里等后面具体讲Blocks和Items的时候在做分析。

# 总结
那么Minecraft的合成部分就讲这么多，后面开始教大家怎么自定义Blocks和Items，谢谢。

文章中涉及的代码均在我的github中：
[Minecraft-Forge-Sample](https://github.com/ZhangQinglian/Minecraft-Forge-Sample)

－－－－－－－－－－－－－－－－华丽丽的分割线－－－－－－－－－－－－－－－－－－－－－
扫描加微信我微信订阅号，文章更新第一时间提醒。
![](http://7xprgn.com1.z0.glb.clouddn.com/qrcode_for_gh_faee5bb1b946_258.jpg)


# 参考资料
[http://www.minecraftforge.net/wiki/Crafting_and_Smelting](http://www.minecraftforge.net/wiki/Crafting_and_Smelting)
[http://minecraft.gamepedia.com/Smelting](http://minecraft.gamepedia.com/Smelting)
