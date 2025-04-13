package net.zfair.devilcraft.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.zfair.devilcraft.devilcraft;

public class ModWoodTypes {
    public static final WoodType EVIL = WoodType.register(new WoodType(devilcraft.MOD_ID + ":evil", BlockSetType.OAK));
}
