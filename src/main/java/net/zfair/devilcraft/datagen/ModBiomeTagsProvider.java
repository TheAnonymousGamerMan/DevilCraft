package net.zfair.devilcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zfair.devilcraft.worldgen.biome.ModBiomeTags;
import net.zfair.devilcraft.worldgen.biome.ModBiomes;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(PackOutput output, String modId, ExistingFileHelper existingFileHelper, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModBiomeTags.Biomes.IS_EVIL_BIOME).add(ModBiomes.EVIL_BIOME);
        tag(ModBiomeTags.Biomes.IS_EVIL_WASTES).add(ModBiomes.EVIL_WASTES);
        tag(ModBiomeTags.Biomes.IS_EVIL_GROVE).add(ModBiomes.EVIL_GROVE);
    }
}