package net.zfair.devilcraft.util;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.zfair.devilcraft.devilcraft;


public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_EVIL_TOOL = tag("needs_evil_tool");


        private static TagKey<Block> tag(String name){
            return BlockTags.create(ResourceLocation.tryBuild(devilcraft.MOD_ID,name));
        }

    }

    public static class Items {

        private static TagKey<Item> tag(String name){
            return ItemTags.create(ResourceLocation.tryBuild(devilcraft.MOD_ID, name));
        }
    }
}
