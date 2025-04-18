package net.zfair.devilcraft.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.worldgen.biome.ModBiomeTags;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_EVIL_ORE = registerKey("add_evil_ore");

    public static final ResourceKey<BiomeModifier> ADD_TREE_EVIL = registerKey("add_tree_evil");

    public static final ResourceKey<BiomeModifier> ADD_TREE_GROVE_EVIL = registerKey("add_tree_grove_evil");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_EVIL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModBiomeTags.Biomes.IS_EVIL_BIOME),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.EVIL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TREE_EVIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModBiomeTags.Biomes.IS_EVIL_BIOME),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.EVIL_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_TREE_GROVE_EVIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModBiomeTags.Biomes.IS_EVIL_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.EVIL_GROVE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, name));
    }
}
