package net.zfair.devilcraft.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.devilcraft;

public class ModBlockStateProvider extends BlockStateProvider{
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, devilcraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        topSideBottomBlock(ModBlocks.CRYING_DEVIL_BLOCK);
        topSideBottomBlock(ModBlocks.EVIL_GRASS);

        pillarBlock(ModBlocks.EVIL_LOG);

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
