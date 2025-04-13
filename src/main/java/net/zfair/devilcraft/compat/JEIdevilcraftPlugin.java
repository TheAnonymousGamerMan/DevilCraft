package net.zfair.devilcraft.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.recipe.AltarRecipe;
import net.zfair.devilcraft.screen.AltarBlockScreen;

import java.util.List;

@JeiPlugin
public class JEIdevilcraftPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AltarCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<AltarRecipe> altarRecipes = recipeManager.getAllRecipesFor(AltarRecipe.Type.INSTANCE);
        registration.addRecipes(AltarCategory.ALTAR_TYPE, altarRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AltarBlockScreen.class, 104, 30, 20, 30,
                AltarCategory.ALTAR_TYPE);
    }
}
