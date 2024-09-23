package io.github.trashoflevillage.mooblooms.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;

public class ModEntitySpawn {
    public static void addEntitySpawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                SpawnGroup.CREATURE,
                ModEntities.MOOBLOOM,
                1,
                1,
                3
        );
    }
}
