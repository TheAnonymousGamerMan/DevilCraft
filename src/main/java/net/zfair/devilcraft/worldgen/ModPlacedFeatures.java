package net.zfair.devilcraft.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.tags.BlockTags;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.worldgen.tree.custom.EvilTrunkPlacer;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> EVIL_KEY = createKey("evil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EVIL_ORE_KEY = createKey("evil_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PREPARE_EVIL_GROUND_KEY = createKey("prepare_evil_ground");

    public static final ResourceKey<PlacedFeature> EVIL_PLACED_KEY_1 = createPlacedKey("evil_placed_key_1");
    public static final ResourceKey<PlacedFeature> EVIL_PLACED_KEY_3 = createPlacedKey("evil_placed_key_3");
    public static final ResourceKey<PlacedFeature> EVIL_ORE_PLACED_KEY = createPlacedKey("evil_ore_placed");
    public static final ResourceKey<PlacedFeature> PREPARE_EVIL_GROUND_PLACED_KEY = createPlacedKey("prepare_evil_ground_placed");

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, name));
    }

    private static ResourceKey<PlacedFeature> createPlacedKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, name));
    }

    public static void bootstrapConfiguredFeatures(BootstapContext<ConfiguredFeature<?, ?>> context) {

        context.register(EVIL_KEY, new ConfiguredFeature<>(Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.EVIL_LOG.get()),
                        new EvilTrunkPlacer(5, 4, 3), // Replace StraightTrunkPlacer with EvilTrunkPlacer
                        BlockStateProvider.simple(ModBlocks.EVIL_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                        new TwoLayersFeatureSize(1, 0, 2)
                )
                        .dirt(BlockStateProvider.simple(Blocks.DIRT))
                        .ignoreVines()
                        .build()
        ));


        context.register(EVIL_ORE_KEY, new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.EVIL_ORE.get().defaultBlockState()),
                                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_EVIL_ORE.get().defaultBlockState())
                        ),
                        3, // Vein size
                        0.0f // Discard chance on air exposure
                )
        ));


        context.register(PREPARE_EVIL_GROUND_KEY, new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(96, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.DIRT))))
        ));
    }

    public static void bootstrapPlacedFeatures(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(EVIL_PLACED_KEY_1, new PlacedFeature(
                configuredFeatures.getOrThrow(EVIL_KEY),
                List.of(
                        CountPlacement.of(1), // Always place 1 tree
                        RarityFilter.onAverageOnceEvery(90),
                        InSquarePlacement.spread(),
                        SurfaceWaterDepthFilter.forMaxDepth(0),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.DIRT))
                )
        ));

        // 10% chance of 3 trees
        context.register(EVIL_PLACED_KEY_3, new PlacedFeature(
                configuredFeatures.getOrThrow(EVIL_KEY),
                List.of(
                        CountPlacement.of(3), // Place 3 trees
                        RarityFilter.onAverageOnceEvery(10), // 10% chance
                        InSquarePlacement.spread(),
                        SurfaceWaterDepthFilter.forMaxDepth(0),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.DIRT))
                )
        ));

        // Evil Ore Placed (previously in evil_ore_placed.json)
        context.register(EVIL_ORE_PLACED_KEY, new PlacedFeature(
                configuredFeatures.getOrThrow(EVIL_ORE_KEY),
                List.of(
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(16)),
                        BiomeFilter.biome()
                )
        ));

        // Prepare Evil Ground Placed
        context.register(PREPARE_EVIL_GROUND_PLACED_KEY, new PlacedFeature(
                configuredFeatures.getOrThrow(PREPARE_EVIL_GROUND_KEY),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        ));
    }
}