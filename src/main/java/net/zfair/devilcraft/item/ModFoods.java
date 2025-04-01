package net.zfair.devilcraft.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties DEVIL_MEAT = new FoodProperties.Builder().nutrition(10).fast()
            .saturationMod(1f).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2000,3),1f).build();
}
