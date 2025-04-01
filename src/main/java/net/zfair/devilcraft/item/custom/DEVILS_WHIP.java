package net.zfair.devilcraft.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;


public class DEVILS_WHIP extends SwordItem {
    public DEVILS_WHIP(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }


    @Override
    public boolean isEnchantable(ItemStack pStack) {

        return this.getMaxStackSize(pStack) == 1 && this.isDamageable(pStack);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.setSecondsOnFire(2);
        pStack.hurtAndBreak(1, pAttacker, (entity) -> entity.broadcastBreakEvent(pAttacker.getUsedItemHand()));
        return true;
    }
}

