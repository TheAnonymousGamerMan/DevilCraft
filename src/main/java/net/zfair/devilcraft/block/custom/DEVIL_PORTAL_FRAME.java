package net.zfair.devilcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class DEVIL_PORTAL_FRAME extends Block {
    public DEVIL_PORTAL_FRAME(Properties pProperties) {
    super(pProperties);
    }

    @Override
    public boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }
}


