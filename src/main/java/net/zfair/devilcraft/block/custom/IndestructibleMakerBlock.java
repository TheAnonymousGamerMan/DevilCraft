package net.zfair.devilcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.zfair.devilcraft.block.entity.IndestructibleMakerBlockEntity;

public class IndestructibleMakerBlock extends Block implements EntityBlock {
    public IndestructibleMakerBlock() {
        super(Properties.copy(Blocks.STONE).strength(2.0F).noLootTable());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new IndestructibleMakerBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof IndestructibleMakerBlockEntity indestructibleMakerBlockEntity) {
                    indestructibleMakerBlockEntity.startRadiusInput(player);
                    return InteractionResult.SUCCESS;
                }
            } else {
                player.sendSystemMessage(Component.literal("You must be in Creative mode to set the radius."));
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!level.isClientSide) {
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}