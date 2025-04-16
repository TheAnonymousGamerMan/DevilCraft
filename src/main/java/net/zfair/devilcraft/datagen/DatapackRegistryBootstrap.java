package net.zfair.devilcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.worldgen.ModPlacedFeatures;
import net.zfair.devilcraft.worldgen.biome.ModBiomes;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DatapackRegistryBootstrap {
    public static DataProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        RegistrySetBuilder builder = new RegistrySetBuilder()
                .add(Registries.BIOME, ModBiomes::bootstrap)
                .add(Registries.CONFIGURED_FEATURE, ModPlacedFeatures::bootstrapConfiguredFeatures)
                .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrapPlacedFeatures);

        return new DatapackBuiltinEntriesProvider(
                output,
                lookupProvider,
                builder,
                Set.of(devilcraft.MOD_ID)
        );
    }
}