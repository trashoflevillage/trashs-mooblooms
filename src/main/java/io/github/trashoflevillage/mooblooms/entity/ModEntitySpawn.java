package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

public class ModEntitySpawn {
    public static void addEntitySpawns() {
        SpawnRestriction.register(
                ModEntities.MOOBLOOM,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MoobloomEntity::canSpawn
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.spawnsOneOf(EntityType.COW),
                SpawnGroup.CREATURE,
                ModEntities.MOOBLOOM,
                2,
                2,
                4
        );
    }
}
