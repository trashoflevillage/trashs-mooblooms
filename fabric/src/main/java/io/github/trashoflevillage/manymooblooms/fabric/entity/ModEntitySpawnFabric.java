package io.github.trashoflevillage.manymooblooms.fabric.entity;

import io.github.trashoflevillage.manymooblooms.entity.ModEntities;
import io.github.trashoflevillage.manymooblooms.entity.custom.MoobloomEntity;
import io.github.trashoflevillage.manymooblooms.util.ModTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

public class ModEntitySpawnFabric {
    public static void registerAll() {
        SpawnRestriction.register(
                ModEntities.MOOBLOOM.get(),
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MoobloomEntity::canSpawn
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(ModTags.Biomes.MOOBLOOM_SPAWNABLE),
                SpawnGroup.CREATURE,
                ModEntities.MOOBLOOM.get(),
                50,
                2,
                4
        );
    }
}
