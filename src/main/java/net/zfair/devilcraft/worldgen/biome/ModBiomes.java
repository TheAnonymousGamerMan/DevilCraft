package net.zfair.devilcraft.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.entity.ModEntities;
import net.zfair.devilcraft.entity.ModMobCategories;
import net.zfair.devilcraft.worldgen.ModPlacedFeatures;

import java.util.logging.Logger;

public class ModBiomes {
    private static final Logger LOGGER = Logger.getLogger(ModBiomes.class.getName());

    public static final ResourceKey<Biome> EVIL_BIOME = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "evil_biome"));

    public static final ResourceKey<Biome> EVIL_WASTES = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "evil_wastes"));

    public static final ResourceKey<Biome> EVIL_GROVE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "evil_grove"));

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(EVIL_BIOME, evilBiome(placedFeatures, carvers, 10, 1, 2));
        context.register(EVIL_WASTES, evilWastes(placedFeatures, carvers, 10, 1, 2));
        context.register(EVIL_GROVE, evilGrove(placedFeatures, carvers, 10, 1, 2));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome evilBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers,
                                    int weight, int minCount, int maxCount) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(ModMobCategories.EVIL_CREATURES.getVanillaCategory(),
                new MobSpawnSettings.SpawnerData(ModEntities.EVIL_SPIRIT.get(), weight, minCount, maxCount));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatures, carvers);


        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xc22323)
                        .waterFogColor(0xc92424)
                        .skyColor(0x8c0101)
                        .fogColor(0xc92424)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
    public static Biome evilWastes(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers,
                                   int weight, int minCount, int maxCount) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(ModMobCategories.EVIL_CREATURES.getVanillaCategory(),
                new MobSpawnSettings.SpawnerData(ModEntities.EVIL_SPIRIT.get(), weight, minCount, maxCount));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatures, carvers);


        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xc22323)
                        .waterFogColor(0xc92424)
                        .skyColor(0x8c0101)
                        .fogColor(0xc92424)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
    public static Biome evilGrove(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers,
                                  int weight, int minCount, int maxCount) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(ModMobCategories.EVIL_CREATURES.getVanillaCategory(),
                new MobSpawnSettings.SpawnerData(ModEntities.EVIL_SPIRIT.get(), weight, minCount, maxCount));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatures, carvers);


        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xc22323)
                        .waterFogColor(0xc92424)
                        .skyColor(0x8c0101)
                        .fogColor(0xc92424)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
}