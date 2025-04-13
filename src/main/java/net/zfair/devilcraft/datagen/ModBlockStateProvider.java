package net.zfair.devilcraft.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.block.custom.EvilCropBlock;
import net.zfair.devilcraft.devilcraft;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider{
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, devilcraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        topSideBottomBlock(ModBlocks.CRYING_DEVIL_BLOCK);
        topSideBottomBlock(ModBlocks.EVIL_GRASS);

        logBlock(((RotatedPillarBlock) ModBlocks.EVIL_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.EVIL_WOOD.get()), blockTexture(ModBlocks.EVIL_LOG.get()), blockTexture(ModBlocks.EVIL_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_EVIL_LOG.get()), blockTexture(ModBlocks.STRIPPED_EVIL_LOG.get()),
                ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "block/stripped_evil_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_EVIL_WOOD.get()), blockTexture(ModBlocks.STRIPPED_EVIL_WOOD.get()),
                blockTexture(ModBlocks.STRIPPED_EVIL_WOOD.get()));

        blockItem(ModBlocks.EVIL_LOG);
        blockItem(ModBlocks.EVIL_WOOD);
        blockItem(ModBlocks.STRIPPED_EVIL_LOG);
        blockItem(ModBlocks.STRIPPED_EVIL_WOOD);

        leavesBlock(ModBlocks.EVIL_LEAVES);




        sixSidedOrientableBlock(ModBlocks.DEVIL_PUMPKIN);
        sixSidedOrientableBlock(ModBlocks.DEVIL_O_LANTERN);

        blockWithItem(ModBlocks.EVIL_BLOCK);
        blockWithItem(ModBlocks.EVIL_DIRT);
        blockWithItem(ModBlocks.EVIL_PLANK);
        blockWithItem(ModBlocks.DEVIL_PORTAL_FRAME);
        blockWithItem(ModBlocks.PURE_EVIL_BLOCK);
        blockWithItem(ModBlocks.VERY_EVIL_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_EVIL_ORE);
        blockWithItem(ModBlocks.EVIL_ORE);

        stairsBlock(((StairBlock) ModBlocks.EVIL_STAIRS.get()), blockTexture(ModBlocks.EVIL_PLANK.get()));
        slabBlock(((SlabBlock) ModBlocks.EVIL_SLAB.get()), blockTexture(ModBlocks.EVIL_PLANK.get()), blockTexture(ModBlocks.EVIL_PLANK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.EVIL_BUTTON.get()), blockTexture(ModBlocks.EVIL_PLANK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.EVIL_PRESSURE_PLATE.get()), blockTexture(ModBlocks.EVIL_PLANK.get()));

        fenceBlock(((FenceBlock) ModBlocks.EVIL_FENCE.get()), blockTexture(ModBlocks.EVIL_PLANK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.EVIL_FENCE_GATE.get()), blockTexture(ModBlocks.EVIL_PLANK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.EVIL_DOOR.get()), modLoc("block/evil_door_bottom"),modLoc("block/evil_door_top"),"cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.EVIL_TRAPDOOR.get()), modLoc("block/evil_trapdoor"), true, "cutout");

        makeEvilCrop((CropBlock) ModBlocks.EVIL_CROP.get(), "evil_stage","evil_stage");

        simpleBlockWithItem(ModBlocks.EVIL_ROSE.get(), models().cross(blockTexture(ModBlocks.EVIL_ROSE.get()).getPath(),
                blockTexture(ModBlocks.EVIL_ROSE.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_EVIL_ROSE.get(), models().singleTexture("potted_catmint", ResourceLocation.withDefaultNamespace("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.EVIL_ROSE.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.ALTAR_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/altar_block")));

        signBlock(((StandingSignBlock) ModBlocks.EVIL_SIGN.get()), ((WallSignBlock) ModBlocks.EVIL_WALL_SIGN.get()),
                blockTexture(ModBlocks.EVIL_PLANK.get()));

        hangingSignBlock((ModBlocks.EVIL_HANGING_SIGN.get()), ModBlocks.EVIL_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.EVIL_PLANK.get()));

    }
    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.tryParse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(devilcraft.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    public void makeEvilCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> EvilStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }


    private ConfiguredModel[] EvilStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((EvilCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "block/" + textureName + state.getValue(((EvilCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void topSideBottomBlock(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        String name = blockRegistryObject.getId().getPath();

        ModelFile model = models().cube(
                name,
                modLoc("block/" + name + "_bottom"), // Bottom texture
                modLoc("block/" + name + "_top"),    // Top texture
                modLoc("block/" + name + "_side"),   // North (side)
                modLoc("block/" + name + "_side"),   // East (side)
                modLoc("block/" + name + "_side"),   // South (side)
                modLoc("block/" + name + "_side")    // West (side)
        ).texture("particle", modLoc("block/" + name + "_side"));

        simpleBlockWithItem(block, model);
        }
    private void sixSidedBlock(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        String name = blockRegistryObject.getId().getPath();

        ModelFile model = models().cube(
                name,
                modLoc("block/" + name + "_bottom"), // Bottom texture
                modLoc("block/" + name + "_top"),    // Top texture
                modLoc("block/" + name + "_north"),  // North texture
                modLoc("block/" + name + "_east"),   // East texture
                modLoc("block/" + name + "_south"),  // South texture
                modLoc("block/" + name + "_west")    // West texture
        ).texture("particle", modLoc("block/" + name + "_north"));

        simpleBlockWithItem(block, model);
    }
    private void orientableBlock(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        String name = blockRegistryObject.getId().getPath();

        ModelFile model = models().orientable(
                name,
                modLoc("block/" + name + "_side"),   // Side texture
                modLoc("block/" + name + "_front"),  // Front texture
                modLoc("block/" + name + "_top")     // Top texture
        ).texture("particle", modLoc("block/" + name + "_front"));

        getVariantBuilder(block)
                .partialState().with(HorizontalDirectionalBlock.FACING, Direction.NORTH)
                .modelForState().modelFile(model).addModel()
                .partialState().with(HorizontalDirectionalBlock.FACING, Direction.EAST)
                .modelForState().modelFile(model).rotationY(90).addModel()
                .partialState().with(HorizontalDirectionalBlock.FACING, Direction.SOUTH)
                .modelForState().modelFile(model).rotationY(180).addModel()
                .partialState().with(HorizontalDirectionalBlock.FACING, Direction.WEST)
                .modelForState().modelFile(model).rotationY(270).addModel();

        simpleBlockItem(block, model);
    }
    private void sixSidedOrientableBlock(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        String name = blockRegistryObject.getId().getPath();

        ModelFile model = models().cube(
                name,
                modLoc("block/" + name + "_bottom"), // Bottom texture
                modLoc("block/" + name + "_top"),    // Top texture
                modLoc("block/" + name + "_north"),  // North texture (front when facing north)
                modLoc("block/" + name + "_east"),   // East texture
                modLoc("block/" + name + "_south"),  // South texture
                modLoc("block/" + name + "_west")    // West texture
        ).texture("particle", modLoc("block/" + name + "_north"));

        getVariantBuilder(block)
                .partialState().with(HorizontalDirectionalBlock.FACING, Direction.NORTH)
                .modelForState().modelFile(model).addModel()
                .partialState().with(HorizontalDirectionalBlock.FACING, Direction.EAST)
                .modelForState().modelFile(model).rotationY(90).addModel()
                .partialState().with(HorizontalDirectionalBlock.FACING, Direction.SOUTH)
                .modelForState().modelFile(model).rotationY(180).addModel()
                .partialState().with(HorizontalDirectionalBlock.FACING, Direction.WEST)
                .modelForState().modelFile(model).rotationY(270).addModel();

        simpleBlockItem(block, model);
    }
    private void pillarBlock(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        String name = blockRegistryObject.getId().getPath();

        ModelFile model = models().cubeColumn(
                name,
                modLoc("block/" + name + "_side"),   // Side texture
                modLoc("block/" + name + "_top")     // Top/bottom texture (end)
        ).texture("particle", modLoc("block/" + name + "_side"));

        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).addModel();

        simpleBlockItem(block, model);
    }
}
