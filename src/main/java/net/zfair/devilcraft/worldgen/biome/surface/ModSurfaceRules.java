package net.zfair.devilcraft.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.zfair.devilcraft.block.ModBlocks;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(ModBlocks.EVIL_DIRT.get());
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(ModBlocks.EVIL_GRASS.get());


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK));

        SurfaceRules.RuleSource dirt = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DIRT));

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, dirt)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
