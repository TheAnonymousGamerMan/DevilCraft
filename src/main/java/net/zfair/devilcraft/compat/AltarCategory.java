package net.zfair.devilcraft.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.recipe.AltarRecipe;
import org.jetbrains.annotations.Nullable;



public class AltarCategory implements IRecipeCategory<AltarRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "altar");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID,
            "textures/gui/gui_altar.png");

    public static final RecipeType<AltarRecipe> ALTAR_TYPE =
            new RecipeType<>(UID, AltarRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AltarCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 83);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ALTAR_BLOCK.get()));
    }


    @Override
    public RecipeType<AltarRecipe> getRecipeType() {
        return ALTAR_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.devilcraft.altar_block");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AltarRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 32).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 138, 32).addItemStack(recipe.getResultItem(null));
    }
}
