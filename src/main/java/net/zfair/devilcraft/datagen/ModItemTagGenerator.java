package net.zfair.devilcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, devilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.EVIL_HELMET.get(),
                        ModItems.EVIL_CHESTPLATE.get(),
                        ModItems.EVIL_LEGGINGS.get(),
                        ModItems.EVIL_BOOTS.get());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.EVIL_LOG.get().asItem())
                .add(ModBlocks.EVIL_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_EVIL_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_EVIL_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.EVIL_PLANK.get().asItem());
    }
}
