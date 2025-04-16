package net.zfair.devilcraft.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.worldgen.ModPlacedFeatures;

public class ModBiomes {
    public static final ResourceKey<Biome> EVIL_BIOME = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "evil_biome"));

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(EVIL_BIOME, evilBiome(placedFeatures, carvers));
    }

    public static Biome evilBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatures, carvers);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, placedFeatures.getOrThrow(ModPlacedFeatures.PREPARE_EVIL_GROUND_PLACED_KEY));
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placedFeatures.getOrThrow(ModPlacedFeatures.EVIL_ORE_PLACED_KEY));
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.EVIL_PLACED_KEY_1)); // 90% chance of 1 tree
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.EVIL_PLACED_KEY_3)); // 10% chance of 3 trees
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(VegetationPlacements.TREES_PLAINS));
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