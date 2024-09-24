package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

public class ModEntitySpawn {
    public static void addEntitySpawns() {
        SpawnRestriction.register(
                ModEntities.MOOBLOOM,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MoobloomEntity::canSpawn
        );

        TagKey<Biome> moobloomSpawnable = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "moobloom_spawnable"));

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(moobloomSpawnable),
                SpawnGroup.CREATURE,
                ModEntities.MOOBLOOM,
                15,
                2,
                4
        );
    }
}
