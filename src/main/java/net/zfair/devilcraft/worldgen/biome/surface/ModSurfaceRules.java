package net.zfair.devilcraft.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(ModBlocks.EVIL_DIRT.get());
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(ModBlocks.EVIL_GRASS.get());
    private static final SurfaceRules.RuleSource EVIL_RACK_BLOCK = makeStateRule(ModBlocks.EVIL_RACK.get());

    public static SurfaceRules.RuleSource makeRules() {

        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource abovePreliminarySurface = SurfaceRules.abovePreliminarySurface();

        // Grass on the topmost surface
        SurfaceRules.RuleSource grassSurface = SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                SurfaceRules.ifTrue(
                        abovePreliminarySurface,
                        SurfaceRules.ifTrue(
                                isAtOrAboveWaterLevel,
                                GRASS_BLOCK
                        )
                )
        );

        SurfaceRules.RuleSource dirtBelow = SurfaceRules.ifTrue(
                SurfaceRules.UNDER_FLOOR,
                SurfaceRules.ifTrue(
                        abovePreliminarySurface,
                        SurfaceRules.ifTrue(
                        isAtOrAboveWaterLevel,
                        DIRT
                        )
                )
        );

        SurfaceRules.RuleSource evilRack = EVIL_RACK_BLOCK;

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.EVIL_BIOME), grassSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.EVIL_BIOME), dirtBelow),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.EVIL_WASTES), evilRack),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.EVIL_GROVE), evilRack)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}