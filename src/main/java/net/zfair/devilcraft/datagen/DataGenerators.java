package net.zfair.devilcraft.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.worldgen.ModBiomeModifiers;
import net.zfair.devilcraft.worldgen.ModConfiguredFeatures;
import net.zfair.devilcraft.worldgen.ModPlacedFeatures;
import net.zfair.devilcraft.worldgen.biome.ModBiomes;
import net.zfair.devilcraft.worldgen.dimension.ModDimensions;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = devilcraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        RegistrySetBuilder builder = new RegistrySetBuilder()
                .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
                .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
                .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)

                .add(Registries.BIOME, context -> {
                    HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
                    HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);
                    context.register(ModBiomes.EVIL_BIOME, ModBiomes.evilBiome(placedFeatures, carvers,10, 1, 2));
                    context.register(ModBiomes.EVIL_WASTES, ModBiomes.evilWastes(placedFeatures, carvers,10, 1, 2));
                    context.register(ModBiomes.EVIL_GROVE, ModBiomes.evilGrove(placedFeatures, carvers, 10, 1, 2));
                });


        DatapackBuiltinEntriesProvider registryProvider = new DatapackBuiltinEntriesProvider(
                packOutput,
                lookupProvider,
                builder,
                Set.of(devilcraft.MOD_ID)
        );
        generator.addProvider(event.includeServer(), registryProvider);

        CompletableFuture<HolderLookup.Provider> updatedLookupProvider = registryProvider.getRegistryProvider();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));

        ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagGenerator(packOutput, updatedLookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagGenerator(packOutput, updatedLookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(packOutput));

        generator.addProvider(event.includeServer(), new DataProvider() {
            @Override
            public CompletableFuture<?> run(net.minecraft.data.CachedOutput cache) {
                return updatedLookupProvider.thenCompose(provider -> {
                    ModBiomeTagsProvider biomeTagsProvider = new ModBiomeTagsProvider(
                            packOutput,
                            devilcraft.MOD_ID,
                            existingFileHelper,
                            CompletableFuture.completedFuture(provider)
                    );
                    return biomeTagsProvider.run(cache);
                });
            }


            @Override
            public String getName() {
                return "ModBiomeTagsProviderWrapper";
            }
        });
        }
}