package net.zfair.devilcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.zfair.devilcraft.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class ModEvilFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModEvilFlammableRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(ModBlocks.EVIL_LOG.get())) {
                return ModBlocks.STRIPPED_EVIL_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(ModBlocks.EVIL_WOOD.get())) {
                return ModBlocks.STRIPPED_EVIL_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
