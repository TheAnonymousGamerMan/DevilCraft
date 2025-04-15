package net.zfair.devilcraft.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.zfair.devilcraft.entity.custom.FireBallProjectileEntity;

public class FireBallItem extends Item {
    public FireBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!level.isClientSide) {
            FireBallProjectileEntity fireball = new FireBallProjectileEntity(level, player);
            fireball.setItem(itemstack);
            fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(fireball);
            
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }

        return InteractionResultHolder.success(itemstack);
    }
}