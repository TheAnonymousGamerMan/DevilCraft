package net.zfair.devilcraft.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.zfair.devilcraft.worldgen.placement.ModPlacementModifierTypes;

import java.util.List;
import java.util.stream.Stream;

public class WeightedCountPlacement extends PlacementModifier {
    public static final Codec<WeightedCountPlacement> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.list(WeightedEntry.CODEC).fieldOf("entries").forGetter(placement -> placement.entries)
            ).apply(instance, WeightedCountPlacement::new)
    );

    private final List<WeightedEntry> entries;
    private final int totalWeight;

    public WeightedCountPlacement(List<WeightedEntry> entries) {
        this.entries = entries;
        this.totalWeight = entries.stream().mapToInt(WeightedEntry::weight).sum();
    }

    @Override
    public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
        if (entries.isEmpty() || totalWeight <= 0) {
            return Stream.empty(); // Handle edge case: no entries or zero total weight
        }

        int count = selectCount(random);
        if (count <= 0) {
            return Stream.empty(); // Handle edge case: selected count is 0 or negative
        }

        Stream<BlockPos> result = Stream.of(pos);
        for (int i = 1; i < count; i++) {
            result = Stream.concat(result, Stream.of(pos));
        }
        return result;
    }

    private int selectCount(RandomSource random) {
        int roll = random.nextInt(totalWeight);
        int currentWeight = 0;
        for (WeightedEntry entry : entries) {
            currentWeight += entry.weight();
            if (roll < currentWeight) {
                return entry.count();
            }
        }
        return entries.get(entries.size() - 1).count(); // Fallback to last entry
    }

    @Override
    public PlacementModifierType<?> type() {
        return ModPlacementModifierTypes.WEIGHTED_COUNT.get();
    }

    public record WeightedEntry(int count, int weight) {
        public static final Codec<WeightedEntry> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("count").forGetter(WeightedEntry::count),
                        Codec.INT.fieldOf("weight").forGetter(WeightedEntry::weight)
                ).apply(instance, WeightedEntry::new)
        );
    }
}