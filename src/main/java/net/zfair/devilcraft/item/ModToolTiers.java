package net.zfair.devilcraft.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier Evil = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 1f, 4f, 25,
                    ModTags.Blocks.NEEDS_EVIL_TOOL, () -> Ingredient.of(ModItems.EVIL_INGOT.get())),
            ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "evil_ingot"), List.of(Tiers.NETHERITE),List.of()
    );
}
