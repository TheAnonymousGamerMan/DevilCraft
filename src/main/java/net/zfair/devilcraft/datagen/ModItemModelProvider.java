package net.zfair.devilcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, devilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.DEVIL_HORNS);
        simpleItem(ModItems.ENVY);
        simpleItem(ModItems.EYE);
        simpleItem(ModItems.EVIL_INGOT);
        simpleItem(ModItems.UN_EYE);
        simpleItem(ModItems.EVIL_DETECTOR);
        simpleItem(ModItems.PRIDE);
        simpleItem(ModItems.DEVIL_MEAT);
        simpleItem(ModItems.DEVIL_SPAWN_EGG);
        simpleItem(ModItems.DEVIL_WHIP);
        simpleItem(ModItems.SATAN_PITCHFORK);
        simpleItem(ModItems.SLOTH);
        simpleItem(ModItems.TEST);
        simpleItem(ModItems.GREED);
        simpleItem(ModItems.WRATH);
        simpleItem(ModItems.GLUTTONY);
        simpleItem(ModItems.PURE_EVIL);
        simpleItem(ModItems.LUST);
        simpleItem(ModItems.EVIL_GEM);

        simpleBlockItem(ModBlocks.EVIL_DOOR);
        evenSimplerBlockItem(ModBlocks.EVIL_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.EVIL_FENCE_GATE);
        evenSimplerBlockItem(ModBlocks.EVIL_STAIRS);
        evenSimplerBlockItem(ModBlocks.EVIL_SLAB);

        fenceItem(ModBlocks.EVIL_FENCE, ModBlocks.EVIL_PLANK);
        buttonItem(ModBlocks.EVIL_BUTTON, ModBlocks.EVIL_PLANK);
        trapdoorItem(ModBlocks.EVIL_TRAPDOOR);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(devilcraft.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID,"item/" + item.getId().getPath()));
    }

}
