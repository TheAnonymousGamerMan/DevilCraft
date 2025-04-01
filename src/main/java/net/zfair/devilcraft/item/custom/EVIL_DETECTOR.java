package net.zfair.devilcraft.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.zfair.devilcraft.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EVIL_DETECTOR extends Item {
    public EVIL_DETECTOR(Properties pProperties){
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
    if(!pContext.getLevel().isClientSide) {
        BlockPos positionClicked = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        boolean foundblock = false;

        for (int i = 0; i <= positionClicked.getY() + 64 && !foundblock; i++) {
            BlockPos belowPos = positionClicked.below(i);
            BlockState state = pContext.getLevel().getBlockState(belowPos);

            if (isValuableBlock(state)) {
                outputValuableCoordinates(belowPos, player, state.getBlock());
                foundblock = true;
                break;
            }

            int range = 32;
            for (int x = -range; x <= range && !foundblock; x++) {
                for (int z = -range; z <= range && !foundblock; z++) {
                    if (x == 0 && z == 0) continue; // Skip the center (already checked vertically)
                    BlockPos horizontalPos = belowPos.offset(x, 0, z);
                    BlockState horizontalState = pContext.getLevel().getBlockState(horizontalPos);
                    if (isValuableBlock(horizontalState)) {
                        outputValuableCoordinates(horizontalPos, player, horizontalState.getBlock());
                        foundblock = true;
                        break;
                    }
                }
            }
        }

         if(!foundblock) {
             player.sendSystemMessage(Component.literal("No Evil Found"));
         }
    }

    pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
         player-> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.devilcraft.evil_detector.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + "," + blockPos.getY() + "," + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModBlocks.EVIL_ORE.get()) || state.is(ModBlocks.DEEPSLATE_EVIL_ORE.get());
    }
}
