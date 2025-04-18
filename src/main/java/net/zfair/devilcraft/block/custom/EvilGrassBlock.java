package net.zfair.devilcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolActions;
import net.zfair.devilcraft.block.ModBlocks;

public class EvilGrassBlock extends GrassBlock {
    public EvilGrassBlock(Properties p_53685_) {
        super(p_53685_);
    }

    private boolean canSurvive(BlockState state, Level level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        return !aboveState.canOcclude();
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canSurvive(pState, pLevel, pPos)) {
            pLevel.setBlockAndUpdate(pPos, ModBlocks.EVIL_DIRT.get().defaultBlockState());
        } else if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 10) {
            for (int i = 0; i < 4; ++i) {
                BlockPos targetPos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                BlockState targetState = pLevel.getBlockState(targetPos);
                if (targetState.is(ModBlocks.EVIL_DIRT.get()) && canSurvive(pState, pLevel, targetPos)) {
                    pLevel.setBlockAndUpdate(targetPos, this.defaultBlockState());
                }
            }
        }
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide && !canSurvive(pState, pLevel, pPos)) {
            pLevel.setBlockAndUpdate(pPos, ModBlocks.EVIL_DIRT.get().defaultBlockState());
        }
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
        if (!pLevel.isClientSide() && pFromPos.equals(pPos.above()) && !canSurvive(pState, pLevel, pPos)) {
            pLevel.setBlockAndUpdate(pPos, ModBlocks.EVIL_DIRT.get().defaultBlockState());
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.canPerformAction(ToolActions.HOE_TILL)) {
            pLevel.setBlock(pPos, Blocks.FARMLAND.defaultBlockState(), 11);
            itemstack.hurtAndBreak(1, pPlayer, (p) -> p.broadcastBreakEvent(pHand));
            pLevel.playSound(null, pPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (itemstack.canPerformAction(ToolActions.SHOVEL_FLATTEN)) {
            pLevel.setBlock(pPos, Blocks.DIRT_PATH.defaultBlockState(), 11);
            itemstack.hurtAndBreak(1, pPlayer, (p) -> p.broadcastBreakEvent(pHand));
            pLevel.playSound(null, pPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}

