package net.zfair.devilcraft.item;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.entity.ModEntities;
import net.zfair.devilcraft.item.custom.FireBallItem;
import net.zfair.devilcraft.item.custom.ModArmorItem;
import net.zfair.devilcraft.item.custom.SATAN_PITCHFORK;
import net.zfair.devilcraft.devilcraft;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.zfair.devilcraft.item.custom.EVIL_DETECTOR;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, devilcraft.MOD_ID);

    public static final RegistryObject<Item> TEST = ITEMS.register("test",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EYE = ITEMS.register("eye",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEVIL_SPAWN_EGG = ITEMS.register("devil_spawn_egg",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UN_EYE = ITEMS.register("un_eye",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EVIL_GEM = ITEMS.register("evil_gem",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EVIL_INGOT = ITEMS.register("evil_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LUST = ITEMS.register("lust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLUTTONY = ITEMS.register("gluttony",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GREED = ITEMS.register("greed",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SLOTH = ITEMS.register("sloth",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WRATH = ITEMS.register("wrath",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENVY = ITEMS.register("envy",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PRIDE = ITEMS.register("pride",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PURE_EVIL = ITEMS.register("pure_evil",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEVIL_HORNS = ITEMS.register("devil_horns",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEVIL_MEAT = ITEMS.register("devil_meat",
            () -> new Item(new Item.Properties().food(ModFoods.DEVIL_MEAT)));

    public static final RegistryObject<Item> EVIL_DETECTOR = ITEMS.register("evil_detector",
            () -> new EVIL_DETECTOR(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> SATAN_PITCHFORK = ITEMS.register("satan_pitchfork",
            () -> new SATAN_PITCHFORK(Tiers.NETHERITE, 200, -2.4f, new Item.Properties().durability(5000)));

    public static final RegistryObject<Item> DEVIL_WHIP = ITEMS.register("devil_whip",
            () -> new SATAN_PITCHFORK(Tiers.NETHERITE, 10, -1.2f, new Item.Properties().durability(2000)));

    public static final RegistryObject<Item> EVIL_SWORD = ITEMS.register("evil_sword",
            () -> new SwordItem(ModToolTiers.Evil, 4, -2.4f, new Item.Properties()));

    public static final RegistryObject<Item> EVIL_PICKAXE = ITEMS.register("evil_pickaxe",
            () -> new PickaxeItem(ModToolTiers.Evil, 1, -2.6f, new Item.Properties()));

    public static final RegistryObject<Item> EVIL_AXE = ITEMS.register("evil_axe",
            () -> new AxeItem(ModToolTiers.Evil, 7, -3f, new Item.Properties()));

    public static final RegistryObject<Item> EVIL_SHOVEL = ITEMS.register("evil_shovel",
            () -> new ShovelItem(ModToolTiers.Evil, 0, -3f, new Item.Properties()));

    public static final RegistryObject<Item> EVIL_HOE = ITEMS.register("evil_hoe",
            () -> new HoeItem(ModToolTiers.Evil, -4, 0, new Item.Properties()));

    public static final RegistryObject<Item> EVIL_HELMET = ITEMS.register("evil_helmet",
            () -> new ModArmorItem(ModArmorMaterials.EVIL, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> EVIL_CHESTPLATE = ITEMS.register("evil_chestplate",
            () -> new ArmorItem(ModArmorMaterials.EVIL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> EVIL_LEGGINGS = ITEMS.register("evil_leggings",
            () -> new ArmorItem(ModArmorMaterials.EVIL, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> EVIL_BOOTS = ITEMS.register("evil_boots",
            () -> new ArmorItem(ModArmorMaterials.EVIL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> EVIL_SEEDS = ITEMS.register("evil_seeds",
            () -> new ItemNameBlockItem(ModBlocks.EVIL_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> EVIL_SPIRIT_SPAWN_EGG = ITEMS.register("evil_spirit_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.EVIL_SPIRIT, 0xfc031c, 0x82030f,
                    new Item.Properties()));

    public static final RegistryObject<Item> EVIL_SIGN = ITEMS.register("evil_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.EVIL_SIGN.get(), ModBlocks.EVIL_WALL_SIGN.get()));

    public static final RegistryObject<Item> EVIL_HANGING_SIGN = ITEMS.register("evil_hanging_sign",
            () -> new HangingSignItem(ModBlocks.EVIL_HANGING_SIGN.get(), ModBlocks.EVIL_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> FIREBALL_ITEM = ITEMS.register("fireball_item",
            () -> new FireBallItem(new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
