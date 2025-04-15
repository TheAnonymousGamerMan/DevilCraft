package net.zfair.devilcraft.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.zfair.devilcraft.entity.custom.EvilSpiritEntity;
import net.zfair.devilcraft.entity.custom.FireBallProjectileEntity;

public class EvilSpiritFireballAttackGoal extends Goal {
    private final EvilSpiritEntity evilSpirit;
    private int attackCooldown;
    private final int cooldownTicks = 60;

    public EvilSpiritFireballAttackGoal(EvilSpiritEntity evilSpirit) {
        this.evilSpirit = evilSpirit;
        this.attackCooldown = 0;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = evilSpirit.getTarget();
        if (target == null) {
            return false;
        }
        double distance = evilSpirit.distanceToSqr(target);
        return distance > 25.0; //(5^2 = 25)
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = evilSpirit.getTarget();
        if (target == null) {
            return false;
        }
        double distance = evilSpirit.distanceToSqr(target);
        return distance > 25.0;
    }

    @Override
    public void start() {

        attackCooldown = cooldownTicks;
    }

    @Override
    public void tick() {
        LivingEntity target = evilSpirit.getTarget();
        if (target == null) {
            return;
        }

        if (attackCooldown <= 0) {

            throwFireball(target);

            evilSpirit.setAttacking(true);

            attackCooldown = cooldownTicks;
        } else {
            attackCooldown--;
        }
    }

    private void throwFireball(LivingEntity target) {
        FireBallProjectileEntity fireball = new FireBallProjectileEntity(evilSpirit.level(), evilSpirit);
        double x = evilSpirit.getX();
        double y = evilSpirit.getY() + evilSpirit.getEyeHeight();
        double z = evilSpirit.getZ();
        fireball.setPos(x, y, z);

        double dx = target.getX() - x;
        double dy = (target.getY() + target.getEyeHeight()) - y;
        double dz = target.getZ() - z;
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        if (distance != 0) {
            dx /= distance;
            dy /= distance;
            dz /= distance;
        }
        double speed = 1.5;
        fireball.setDeltaMovement(dx * speed, dy * speed, dz * speed);

        evilSpirit.level().addFreshEntity(fireball);
    }
}