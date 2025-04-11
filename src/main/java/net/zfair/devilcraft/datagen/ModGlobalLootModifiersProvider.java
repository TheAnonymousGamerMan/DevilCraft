package net.zfair.devilcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.item.ModItems;
import net.zfair.devilcraft.loot.AddItemModifier;
import net.zfair.devilcraft.loot.AddSusBlockItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, devilcraft.MOD_ID);
    }

    @Override
    protected void start() {
        add("envy_from_wither", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/wither")).build()}, ModItems.ENVY.get()
        ));
        add("pride_from_ender_dragon", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/ender_dragon")).build(),}, ModItems.PRIDE.get()
        ));
        add("wrath_from_villager", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/villager")).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()}, ModItems.WRATH.get()
        ));
        add("wrath_from_iron_golem", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/iron_golem")).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()}, ModItems.WRATH.get()
        ));
        add("evil_ingot_from_jungle_temples", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()}, ModItems.EVIL_INGOT.get()
        ));
        add("evil_ingot_from_desert_pyramid", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()}, ModItems.EVIL_INGOT.get()
        ));
        add("evil_gem_from_suspicous_sand_temple", new AddSusBlockItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("archaeology/desert_pyramid")).build() }, ModItems.EVIL_GEM.get()
        ));
        add("evil_gem_from_suspicous_sand_well", new AddSusBlockItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("archaeology/desert_well")).build() }, ModItems.EVIL_GEM.get()
        ));
        add("evil_gem_from_suspicous_gravel_ruin_cold", new AddSusBlockItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("archaeology/ocean_ruin_cold")).build() }, ModItems.EVIL_GEM.get()
        ));
        add("evil_gem_from_suspicous_gravel_ruin_warm", new AddSusBlockItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("archaeology/ocean_ruin_warm")).build() }, ModItems.EVIL_GEM.get()
        ));
    }
}
