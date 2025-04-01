package net.zfair.devilcraft.item;

import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.devilcraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.zfair.devilcraft.item.custom.EVIL_DETECTOR;
import net.zfair.devilcraft.item.custom.SATAN_PITCHFORK;

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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
