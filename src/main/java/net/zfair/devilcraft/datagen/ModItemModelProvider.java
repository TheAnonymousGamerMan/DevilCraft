package net.zfair.devilcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterial = new LinkedHashMap<>();
    static {
        trimMaterial.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterial.put(TrimMaterials.IRON, 0.2F);
        trimMaterial.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterial.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterial.put(TrimMaterials.COPPER, 0.5F);
        trimMaterial.put(TrimMaterials.GOLD, 0.6F);
        trimMaterial.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterial.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterial.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterial.put(TrimMaterials.AMETHYST, 1.0F);
    }

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
        simpleItem(ModItems.SLOTH);
        simpleItem(ModItems.TEST);
        simpleItem(ModItems.GREED);
        simpleItem(ModItems.WRATH);
        simpleItem(ModItems.GLUTTONY);
        simpleItem(ModItems.PURE_EVIL);
        simpleItem(ModItems.LUST);
        simpleItem(ModItems.EVIL_GEM);

        handheldItem(ModItems.EVIL_SWORD);
        handheldItem(ModItems.EVIL_AXE);
        handheldItem(ModItems.EVIL_PICKAXE);
        handheldItem(ModItems.EVIL_SHOVEL);
        handheldItem(ModItems.EVIL_HOE);

        trimmedArmorItem(ModItems.EVIL_HELMET);
        trimmedArmorItem(ModItems.EVIL_CHESTPLATE);
        trimmedArmorItem(ModItems.EVIL_LEGGINGS);
        trimmedArmorItem(ModItems.EVIL_BOOTS);

        simpleBlockItem(ModBlocks.EVIL_DOOR);
        evenSimplerBlockItem(ModBlocks.EVIL_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.EVIL_FENCE_GATE);
        evenSimplerBlockItem(ModBlocks.EVIL_STAIRS);
        evenSimplerBlockItem(ModBlocks.EVIL_SLAB);

        fenceItem(ModBlocks.EVIL_FENCE, ModBlocks.EVIL_PLANK);
        buttonItem(ModBlocks.EVIL_BUTTON, ModBlocks.EVIL_PLANK);
        trapdoorItem(ModBlocks.EVIL_TRAPDOOR);

    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = devilcraft.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterial.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.withDefaultNamespace(trimPath);
                ResourceLocation trimNameResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                 ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
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

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID,"item/" + item.getId().getPath()));
    }

}
