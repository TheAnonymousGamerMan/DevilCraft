package net.zfair.devilcraft.worldgen.biome;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.worldgen.ModPlacedFeatures;

public class ModBiomes {
    public static final ResourceKey<Biome> EVIL_BIOME = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "evil_biome"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(EVIL_BIOME, evilBiome(context));
    }

    private static Biome evilBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Add default biome features
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);

        // Add custom features
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.PREPARE_EVIL_GROUND_PLACED_KEY));
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.EVIL_ORE_PLACED_KEY));
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).getOrThrow(VegetationPlacements.TREES_PLAINS));
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.EVIL_PLACED_KEY));
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .skyColor(0x77ADFF)
                        .fogColor(0xC0D8FF)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
}