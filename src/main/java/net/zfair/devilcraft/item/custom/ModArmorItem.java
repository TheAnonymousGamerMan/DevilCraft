package net.zfair.devilcraft.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.zfair.devilcraft.item.ModArmorMaterials;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {

    private static final Map<ArmorMaterial, List<MobEffectInstance>> MATERIAL_TO_EFFECTS_MAP;

    static {
        Map<ArmorMaterial, List<MobEffectInstance>> tempMap = new HashMap<>();
        tempMap.put(ModArmorMaterials.EVIL, List.of(

                new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20, 1, false, false, false),
                new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 1, false, false, false),
                new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20, 0, false, false, false),
                new MobEffectInstance(MobEffects.NIGHT_VISION, 20, 1, false, false, false)
        ));
        MATERIAL_TO_EFFECTS_MAP = Collections.unmodifiableMap(tempMap);
    }

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
        if (!level.isClientSide) {
            if (hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player);
            } else {
                removeArmorEffects(player);
            }
        }
    }

    private void addStatusEffectsForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                             List<MobEffectInstance> mapStatusEffects) {
        for (MobEffectInstance effect : mapStatusEffects) {
            boolean hasPlayerEffect = player.hasEffect(effect.getEffect());
            if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {

                if (effect.getEffect() == MobEffects.NIGHT_VISION) {
                    player.addEffect(new MobEffectInstance(
                            MobEffects.NIGHT_VISION,
                            Integer.MAX_VALUE,
                            effect.getAmplifier(),
                            effect.isAmbient(),
                            effect.isVisible(),
                            effect.showIcon()
                    ));
                } else {

                    player.addEffect(new MobEffectInstance(effect));
                }
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECTS_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapStatusEffects = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectsForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    private void removeArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECTS_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapStatusEffects = entry.getValue();

            if (!hasCorrectArmorOn(mapArmorMaterial, player)) {
                for (MobEffectInstance effect : mapStatusEffects) {
                    if (player.hasEffect(effect.getEffect())) {
                        player.removeEffect(effect.getEffect());
                    }
                }
            }
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
            ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
            ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
            ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
            ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

            return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                    leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}