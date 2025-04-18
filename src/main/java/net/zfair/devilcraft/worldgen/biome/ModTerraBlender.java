package net.zfair.devilcraft.worldgen.biome;

import terrablender.api.Regions;

public class ModTerraBlender {
    public static void registerBiomes() {
        Regions.register(new DevilCraftRegion());
    }
}