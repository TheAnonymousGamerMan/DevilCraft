package net.zfair.devilcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.extensions.IForgeItem;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> EVIL_SMELTABLES = List.of(ModItems.EVIL_GEM.get(),
    ModBlocks.EVIL_ORE.get(), ModBlocks.DEEPSLATE_EVIL_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, EVIL_SMELTABLES, RecipeCategory.MISC, ModItems.EVIL_INGOT.get(), 3f, 100, "evil_ingot");
        oreSmelting(pWriter, EVIL_SMELTABLES, RecipeCategory.MISC, ModItems.EVIL_INGOT.get(), 3f, 100, "evil_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EVIL_BLOCK.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', ModItems.EVIL_INGOT.get())
                .unlockedBy("has_evil_ingot", has(ModItems.EVIL_INGOT.get()))
                .save(pWriter,devilcraft.MOD_ID + ":evil_block_from_ingots");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VERY_EVIL_BLOCK.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', ModBlocks.EVIL_BLOCK.get())
                .unlockedBy("has_evil_block", has(ModBlocks.EVIL_BLOCK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":very_evil_block_from_evil_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PURE_EVIL_BLOCK.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', ModBlocks.VERY_EVIL_BLOCK.get())
                .unlockedBy("has_very_evil_block", has(ModBlocks.VERY_EVIL_BLOCK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":pure_evil_block_from_very_evil_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EVIL_DETECTOR.get())
                .pattern("EPE")
                .pattern("VVV")
                .pattern("NNN")
                .define('E', Blocks.GOLD_BLOCK)
                .define('P', Items.PHANTOM_MEMBRANE)
                .define('V', ModItems.EVIL_INGOT.get())
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy("has_evil_ingot", has(ModItems.EVIL_INGOT.get()))
                .save(pWriter, devilcraft.MOD_ID + ":evil_detector_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PURE_EVIL.get())
                .pattern("EPV")
                .pattern("WSG")
                .pattern("LPN")
                .define('S', ModItems.SLOTH.get())
                .define('E', ModItems.WRATH.get())
                .define('P', ModBlocks.PURE_EVIL_BLOCK.get())
                .define('V', ModItems.ENVY.get())
                .define('N', ModItems.GLUTTONY.get())
                .define('L', ModItems.LUST.get())
                .define('W', ModItems.PRIDE.get())
                .define('G', ModItems.GREED.get())
                .unlockedBy("has_pure_evil_block", has(ModBlocks.PURE_EVIL_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PURE_EVIL_BLOCK.get(), 2)
                .pattern("EEE")
                .pattern("EPE")
                .pattern("EEE")
                .define('E', Items.NETHERITE_INGOT)
                .define('P', ModBlocks.PURE_EVIL_BLOCK.get())
                .unlockedBy("has_pure_evil_block", has(ModBlocks.PURE_EVIL_BLOCK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":evil_block_from_netherite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EYE.get())
                .pattern("EEE")
                .pattern("EPE")
                .pattern("EEE")
                .define('E', ModItems.PURE_EVIL.get())
                .define('P', ModItems.UN_EYE.get())
                .unlockedBy("has_pure_evil", has(ModBlocks.EVIL_BLOCK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":eye_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EVIL_DOOR.get(), 3)
                .pattern("EE ")
                .pattern("EE ")
                .pattern("EE ")
                .define('E', ModBlocks.EVIL_PLANK.get())
                .unlockedBy("has_evil_planks", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":door_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EVIL_PRESSURE_PLATE.get())
                .pattern("   ")
                .pattern("   ")
                .pattern("EE ")
                .define('E', ModBlocks.EVIL_PLANK.get())
                .unlockedBy("has_evil_planks", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":pressure_plate_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EVIL_TRAPDOOR.get(), 2)
                .pattern("   ")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', ModBlocks.EVIL_PLANK.get())
                .unlockedBy("has_evil_planks", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":trapdoor_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EVIL_SLAB.get(), 6)
                .pattern("   ")
                .pattern("   ")
                .pattern("EEE")
                .define('E', ModBlocks.EVIL_PLANK.get())
                .unlockedBy("has_evil_planks", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":slab_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EVIL_STAIRS.get(), 4)
                .pattern("E  ")
                .pattern("EE ")
                .pattern("EEE")
                .define('E', ModBlocks.EVIL_PLANK.get())
                .unlockedBy("has_evil_planks", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":stairs_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EVIL_FENCE.get(), 3)
                .pattern("   ")
                .pattern("ESE")
                .pattern("ESE")
                .define('S', Items.STICK)
                .define('E', ModBlocks.EVIL_PLANK.get())
                .unlockedBy("has_evil_planks", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":fence_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EVIL_FENCE_GATE.get())
                .pattern("   ")
                .pattern("SES")
                .pattern("SES")
                .define('S', Items.STICK)
                .define('E', ModBlocks.EVIL_PLANK.get())
                .unlockedBy("has_evil_planks", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":fence_gate_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EVIL_SIGN.get(), 3)
                .pattern("EEE")
                .pattern("EEE")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('E', ModBlocks.EVIL_PLANK.get())
                .unlockedBy("has_evil_planks", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":sign_recipe");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EVIL_HANGING_SIGN.get(), 6)
                .pattern("E E")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.EVIL_PLANK.get())
                .define('E', Items.CHAIN)
                .unlockedBy("has_evil_planks", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":hanging_sign_recipe");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.EVIL_INGOT.get(), 9)
                .requires(ModBlocks.EVIL_BLOCK.get())
                .unlockedBy("has_evil_block", has(ModBlocks.EVIL_BLOCK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":evil_ingots_from_evil_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.EVIL_BLOCK.get(), 9)
                .requires(ModBlocks.VERY_EVIL_BLOCK.get())
                .unlockedBy("has_very_evil_block", has(ModBlocks.VERY_EVIL_BLOCK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":evil_block_from_very_evil_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.EVIL_PLANK.get(), 4)
                .requires(ModBlocks.EVIL_LOG.get())
                .unlockedBy("has_evil_log", has(ModBlocks.EVIL_LOG.get()))
                .save(pWriter, devilcraft.MOD_ID + ":planks_from_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.EVIL_PLANK.get(), 4)
                .requires(ModBlocks.EVIL_WOOD.get())
                .unlockedBy("has_evil_wood", has(ModBlocks.EVIL_LOG.get()))
                .save(pWriter, devilcraft.MOD_ID + ":planks_from_wood");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.EVIL_PLANK.get(), 4)
                .requires(ModBlocks.STRIPPED_EVIL_LOG.get())
                .unlockedBy("has_stripped_evil_log", has(ModBlocks.EVIL_LOG.get()))
                .save(pWriter, devilcraft.MOD_ID + ":planks_from_stripped_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.EVIL_PLANK.get(), 4)
                .requires(ModBlocks.STRIPPED_EVIL_WOOD.get())
                .unlockedBy("has_stripped_evil_wood", has(ModBlocks.EVIL_LOG.get()))
                .save(pWriter, devilcraft.MOD_ID + ":planks_from_stripped_wood");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.EVIL_WOOD.get(), 3)
                .requires(ModBlocks.EVIL_LOG.get(), 4)
                .unlockedBy("has_evil_log", has(ModBlocks.EVIL_LOG.get()))
                .save(pWriter, devilcraft.MOD_ID + ":wood_from_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.STRIPPED_EVIL_WOOD.get(), 3)
                .requires(ModBlocks.STRIPPED_EVIL_LOG.get(), 4)
                .unlockedBy("has_stripped_evil_log", has(ModBlocks.EVIL_LOG.get()))
                .save(pWriter, devilcraft.MOD_ID + ":stripped_wood_from_stripped_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.EVIL_BUTTON.get(), 1)
                .requires(ModBlocks.EVIL_PLANK.get(), 1)
                .unlockedBy("has_evil_plank", has(ModBlocks.EVIL_PLANK.get()))
                .save(pWriter, devilcraft.MOD_ID + ":button_recipe");

    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, devilcraft.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
