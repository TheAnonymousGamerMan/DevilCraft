package net.zfair.devilcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zfair.devilcraft.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.minecraftforge.common.Tags.Blocks.NEEDS_NETHERITE_TOOL;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, devilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
this.tag(BlockTags.NEEDS_IRON_TOOL)
        .add(ModBlocks.PURE_EVIL_BLOCK.get(),
                ModBlocks.VERY_EVIL_BLOCK.get(),
                ModBlocks.EVIL_BLOCK.get(),
                ModBlocks.DEEPSLATE_EVIL_ORE.get(),
                ModBlocks.EVIL_ORE.get()
        );

this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
        .add(ModBlocks.CRYING_DEVIL_BLOCK.get());

this.tag(NEEDS_NETHERITE_TOOL)
        .add(ModBlocks.EVIL_ORE.get(),
                ModBlocks.DEEPSLATE_EVIL_ORE.get(),
                ModBlocks.EVIL_LOG.get(),
                ModBlocks.EVIL_PLANK.get(),
                ModBlocks.EVIL_FENCE.get(),
                ModBlocks.EVIL_DOOR.get(),
                ModBlocks.EVIL_SLAB.get(),
                ModBlocks.EVIL_FENCE_GATE.get(),
                ModBlocks.EVIL_STAIRS.get(),
                ModBlocks.EVIL_BUTTON.get(),
                ModBlocks.EVIL_TRAPDOOR.get(),
                ModBlocks.EVIL_PRESSURE_PLATE.get()
        );
this.tag(ModTags.Blocks.NEEDS_EVIL_TOOL)
        .add(ModBlocks.ALTAR_BLOCK.get(),
                ModBlocks.EVIL_RACK.get()
        );


this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .add(ModBlocks.PURE_EVIL_BLOCK.get(),
                ModBlocks.VERY_EVIL_BLOCK.get(),
                ModBlocks.EVIL_BLOCK.get(),
                ModBlocks.DEEPSLATE_EVIL_ORE.get(),
                ModBlocks.EVIL_ORE.get(),
                ModBlocks.ALTAR_BLOCK.get(),
                ModBlocks.EVIL_RACK.get()
                );

this.tag(BlockTags.MINEABLE_WITH_AXE)
        .add(ModBlocks.DEVIL_PUMPKIN.get(),
                ModBlocks.DEVIL_O_LANTERN.get(),
                ModBlocks.EVIL_PLANK.get(),
                ModBlocks.EVIL_LOG.get(),
                ModBlocks.EVIL_FENCE.get(),
                ModBlocks.EVIL_DOOR.get(),
                ModBlocks.EVIL_SLAB.get(),
                ModBlocks.EVIL_FENCE_GATE.get(),
                ModBlocks.EVIL_STAIRS.get(),
                ModBlocks.EVIL_BUTTON.get(),
                ModBlocks.EVIL_TRAPDOOR.get(),
                ModBlocks.EVIL_PRESSURE_PLATE.get()
        );

this.tag(BlockTags.MINEABLE_WITH_HOE)
        .add(ModBlocks.EVIL_LEAVES.get());

this.tag(BlockTags.LOGS_THAT_BURN)
        .add(ModBlocks.EVIL_LOG.get())
        .add(ModBlocks.STRIPPED_EVIL_LOG.get())
        .add(ModBlocks.EVIL_WOOD.get())
        .add(ModBlocks.STRIPPED_EVIL_WOOD.get());

this.tag(BlockTags.PLANKS)
        .add(ModBlocks.EVIL_PLANK.get()
        );

this.tag(BlockTags.FENCES)
        .add(ModBlocks.EVIL_FENCE.get()
        );
this.tag(BlockTags.FENCE_GATES)
        .add(ModBlocks.EVIL_FENCE_GATE.get()
        );

this.tag(BlockTags.DIRT)
        .add(ModBlocks.EVIL_DIRT.get())
        .add(ModBlocks.EVIL_GRASS.get())
        .add(ModBlocks.EVIL_RACK.get()
        );
this.tag(BlockTags.LEAVES)
        .add(ModBlocks.EVIL_LEAVES.get()
        );

    }
}