package net.zfair.devilcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.zfair.devilcraft.network.ModMessages;
import net.zfair.devilcraft.network.packet.RequestRadiusInputS2CPacket;

public class IndestructibleMakerBlockEntity extends BlockEntity {
    private int radius = 1; // Default radius
    private Player awaitingInputPlayer = null;

    public IndestructibleMakerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INDESTRUCTIBLE_MAKER_BLOCK_ENTITY.get(), pos, state);
    }

    public int getRadius() {
        return radius + 1;
    }

    public void setRadius(int newRadius) {
        if (newRadius >= 1 && newRadius <= 100) {
            this.radius = newRadius;
            setChanged();
            if (level != null) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        } else if (level != null && awaitingInputPlayer != null) {
            awaitingInputPlayer.sendSystemMessage(Component.literal("Radius must be between 1 and 100."));
        }
    }

    public void startRadiusInput(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.awaitingInputPlayer = player;
            player.sendSystemMessage(Component.literal("Enter the new radius (1-100) in chat."));
            ModMessages.sendToPlayer(new RequestRadiusInputS2CPacket(worldPosition), serverPlayer);
        }
    }

    public void processRadiusInput(Player player, String input) {
        if (player == awaitingInputPlayer) {
            try {
                int newRadius = Integer.parseInt(input.trim());
                setRadius(newRadius);
                if (radius == newRadius) {
                    player.sendSystemMessage(Component.literal("Radius set to " + newRadius + "."));
                }
            } catch (NumberFormatException e) {
                player.sendSystemMessage(Component.literal("Invalid number. Please enter a number between 1 and 100."));
            }
            awaitingInputPlayer = null;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("Radius", radius);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        radius = tag.getInt("Radius");
        if (radius < 1 || radius > 100) {
            radius = 1;
        }
    }
}