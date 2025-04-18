package net.zfair.devilcraft.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.common.IExtensibleEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A custom mob category for modded Minecraft entities, extending vanilla MobCategory behavior.
 * Supports dynamic registration of new categories using Forge's IExtensibleEnum.
 */
public class ModMobCategory implements StringRepresentable, IExtensibleEnum {

    // Custom Codec for ModMobCategory
    public static final Codec<ModMobCategory> CODEC = Codec.STRING.comapFlatMap(
            name -> {
                ModMobCategory category = byName(name);
                return category != null
                        ? DataResult.success(category)
                        : DataResult.error(() -> "Unknown MobCategory: " + name);
            },
            ModMobCategory::getName
    );

    // Map for looking up categories by name
    private static final Map<String, ModMobCategory> BY_NAME = new HashMap<>();
    // List to store all created categories
    private static final List<ModMobCategory> VALUES = new ArrayList<>();

    private final int max;
    private final boolean isFriendly;
    private final boolean isPersistent;
    private final String name;
    private final int noDespawnDistance;
    private final int despawnDistance;

    private ModMobCategory(String name, int maxNumberOfCreature, boolean isPeacefulCreature, boolean isAnimal, int despawnDistance, int noDespawnDistance) {
        this.name = name;
        this.max = maxNumberOfCreature;
        this.isFriendly = isPeacefulCreature;
        this.isPersistent = isAnimal;
        this.despawnDistance = despawnDistance;
        this.noDespawnDistance = noDespawnDistance;
    }

    /**
     * Returns all registered ModMobCategory instances.
     *
     * @return An array of all ModMobCategory instances.
     */
    public static ModMobCategory[] values() {
        return VALUES.toArray(new ModMobCategory[0]);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    /**
     * Gets the maximum number of instances of this mob category per chunk.
     */
    public int getMaxInstancesPerChunk() {
        return this.max;
    }

    /**
     * Returns whether this mob category is peaceful (e.g., non-hostile).
     */
    public boolean isFriendly() {
        return this.isFriendly;
    }

    /**
     * Returns whether mobs in this category are persistent (e.g., animals that don't despawn).
     */
    public boolean isPersistent() {
        return this.isPersistent;
    }

    /**
     * Maps this custom category to a vanilla MobCategory for compatibility with Minecraft's spawn system.
     *
     * @return The corresponding vanilla MobCategory.
     */
    public MobCategory getVanillaCategory() {
        // Map based on properties (e.g., friendly and persistent -> CREATURE)
        if (isFriendly() && isPersistent()) {
            return MobCategory.CREATURE;
        } else if (!isFriendly()) {
            return MobCategory.MONSTER;
        } else {
            return MobCategory.MISC; // Fallback
        }
    }

    /**
     * Creates and registers a new mob category.
     *
     * @param name                   The unique name of the category.
     * @param id                     An identifier (often same as name).
     * @param maxNumberOfCreatureIn  Maximum number of mobs per chunk.
     * @param isPeacefulCreatureIn   Whether the mobs are peaceful.
     * @param isAnimalIn             Whether the mobs are persistent animals.
     * @param despawnDistance        Distance at which mobs may despawn.
     * @return The created mob category.
     */
    public static ModMobCategory create(String name, String id, int maxNumberOfCreatureIn, boolean isPeacefulCreatureIn, boolean isAnimalIn, int despawnDistance) {
        ModMobCategory category = new ModMobCategory(name, maxNumberOfCreatureIn, isPeacefulCreatureIn, isAnimalIn, despawnDistance, 32);
        BY_NAME.put(name, category);
        VALUES.add(category);
        return category;
    }

    /**
     * Retrieves a mob category by its name.
     *
     * @param name The name of the category.
     * @return The corresponding mob category, or null if not found.
     */
    public static ModMobCategory byName(String name) {
        return BY_NAME.get(name);
    }

    public int getDespawnDistance() {
        return this.despawnDistance;
    }

    public int getNoDespawnDistance() {
        return this.noDespawnDistance;
    }
}