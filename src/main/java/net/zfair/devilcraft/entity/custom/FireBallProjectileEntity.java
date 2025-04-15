package net.zfair.devilcraft.entity.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.zfair.devilcraft.entity.ModEntities;
import net.zfair.devilcraft.item.ModItems;

public class FireBallProjectileEntity extends ThrowableItemProjectile {
    public FireBallProjectileEntity(EntityType<? extends FireBallProjectileEntity> entityType, Level level) {
        super(entityType, level);
    }

    public FireBallProjectileEntity(Level level) {
        super(ModEntities.FIREBALL_PROJECTILE.get(), level);
    }

    public FireBallProjectileEntity(Level level, LivingEntity shooter) {
        super(ModEntities.FIREBALL_PROJECTILE.get(), shooter, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FIREBALL_ITEM.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide()) {
            if (result.getEntity() instanceof LivingEntity target) {

                if (target instanceof EvilSpiritEntity) {
                    return;
                }
                target.hurt(this.damageSources().onFire(), 5.0F);
                target.setSecondsOnFire(5);
            }
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.level().addParticle(net.minecraft.core.particles.ParticleTypes.FLAME,
                    this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }
}