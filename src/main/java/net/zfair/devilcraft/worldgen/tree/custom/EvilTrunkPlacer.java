package net.zfair.devilcraft.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.nio.charset.CoderResult;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class EvilTrunkPlacer extends TrunkPlacer {
    public static final Codec<EvilTrunkPlacer> CODEC = RecordCodecBuilder.create(evilTrunkPlacerInstance ->
            trunkPlacerParts(evilTrunkPlacerInstance).apply(evilTrunkPlacerInstance, EvilTrunkPlacer::new));

    public EvilTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.EVIL_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader p_226116_, BiConsumer<BlockPos, BlockState> p_226117_, RandomSource p_226118_, int p_226119_, BlockPos p_226120_, TreeConfiguration p_226121_) {
        setDirtAt(p_226116_, p_226117_, p_226118_, p_226120_.below(), p_226121_);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(p_226118_);
        int i = p_226119_ - p_226118_.nextInt(4) - 1;
        int j = 3 - p_226118_.nextInt(3);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int k = p_226120_.getX();
        int l = p_226120_.getZ();
        OptionalInt optionalint = OptionalInt.empty();

        for(int i1 = 0; i1 < p_226119_; ++i1) {
            int j1 = p_226120_.getY() + i1;
            if (i1 >= i && j > 0) {
                k += direction.getStepX();
                l += direction.getStepZ();
                --j;
            }

            if (this.placeLog(p_226116_, p_226117_, p_226118_, blockpos$mutableblockpos.set(k, j1, l), p_226121_)) {
                optionalint = OptionalInt.of(j1 + 1);
            }
        }

        if (optionalint.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k, optionalint.getAsInt(), l), 1, false));
        }

        k = p_226120_.getX();
        l = p_226120_.getZ();
        Direction direction1 = Direction.Plane.HORIZONTAL.getRandomDirection(p_226118_);
        if (direction1 != direction) {
            int j2 = i - p_226118_.nextInt(2) - 1;
            int k1 = 1 + p_226118_.nextInt(3);
            optionalint = OptionalInt.empty();

            for(int l1 = j2; l1 < p_226119_ && k1 > 0; --k1) {
                if (l1 >= 1) {
                    int i2 = p_226120_.getY() + l1;
                    k += direction1.getStepX();
                    l += direction1.getStepZ();
                    if (this.placeLog(p_226116_, p_226117_, p_226118_, blockpos$mutableblockpos.set(k, i2, l), p_226121_)) {
                        optionalint = OptionalInt.of(i2 + 1);
                    }
                }

                ++l1;
            }

            if (optionalint.isPresent()) {
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k, optionalint.getAsInt(), l), 0, false));
            }
        }

        return list;
    }
}
