package net.zfair.devilcraft.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.zfair.devilcraft.devilcraft;

public class ModBiomeTags {
    public static class Biomes {
        public static final TagKey<Biome> IS_EVIL_BIOME = TagKey.create(
                Registries.BIOME, ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "is_evil_biome"));
    }
}
