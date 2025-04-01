package net.zfair.devilcraft.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.EVIL_BLOCK.get());
        this.dropSelf(ModBlocks.VERY_EVIL_BLOCK.get());
        this.dropSelf(ModBlocks.PURE_EVIL_BLOCK.get());
        this.dropSelf(ModBlocks.CRYING_DEVIL_BLOCK.get());
        this.dropSelf(ModBlocks.DEVIL_PUMPKIN.get());
        this.dropSelf(ModBlocks.DEVIL_O_LANTERN.get());
        this.dropSelf(ModBlocks.EVIL_FENCE.get());
        this.dropSelf(ModBlocks.EVIL_FENCE_GATE.get());
        this.dropSelf(ModBlocks.EVIL_BUTTON.get());
        this.dropSelf(ModBlocks.EVIL_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.EVIL_STAIRS.get());
        this.dropSelf(ModBlocks.EVIL_GRASS.get());
        this.dropSelf(ModBlocks.EVIL_DIRT.get());
        this.dropSelf(ModBlocks.EVIL_LOG.get());
        this.dropSelf(ModBlocks.EVIL_PLANK.get());
        this.dropSelf(ModBlocks.EVIL_TRAPDOOR.get());

        this.add(ModBlocks.EVIL_SLAB.get(),
        block -> createSlabItemTable(ModBlocks.EVIL_SLAB.get()));

        this.add(ModBlocks.EVIL_DOOR.get(),
                block -> createDoorTable(ModBlocks.EVIL_DOOR.get()));

        this.add(ModBlocks.EVIL_ORE.get(),
        block -> createGenericOreDrop(ModBlocks.EVIL_ORE.get(), ModItems.EVIL_GEM.get()));

        this.add(ModBlocks.DEEPSLATE_EVIL_ORE.get(),
                block -> createGenericOreDrop(ModBlocks.DEEPSLATE_EVIL_ORE.get(), ModItems.EVIL_GEM.get()));

    }
    protected LootTable.Builder createGenericOreDrop(Block pBlock, Item Item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(Item)
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
