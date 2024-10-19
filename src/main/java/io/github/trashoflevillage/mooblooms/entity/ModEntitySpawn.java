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
    public static final TagKey<Biome> MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "moobloom_spawnable"));
    public static final TagKey<Biome> WHITE_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "white_moobloom_spawnable"));
    public static final TagKey<Biome> LIGHT_GRAY_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "light_gray_moobloom_spawnable"));
    public static final TagKey<Biome> GRAY_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "gray_moobloom_spawnable"));
    public static final TagKey<Biome> BLACK_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "black_moobloom_spawnable"));
    public static final TagKey<Biome> BROWN_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "brown_moobloom_spawnable"));
    public static final TagKey<Biome> RED_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "red_moobloom_spawnable"));
    public static final TagKey<Biome> ORANGE_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "orange_moobloom_spawnable"));
    public static final TagKey<Biome> YELLOW_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "yellow_moobloom_spawnable"));
    public static final TagKey<Biome> LIME_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "lime_moobloom_spawnable"));
    public static final TagKey<Biome> GREEN_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "green_moobloom_spawnable"));
    public static final TagKey<Biome> CYAN_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "cyan_moobloom_spawnable"));
    public static final TagKey<Biome> LIGHT_BLUE_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "light_blue_moobloom_spawnable"));
    public static final TagKey<Biome> BLUE_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "blue_moobloom_spawnable"));
    public static final TagKey<Biome> PURPLE_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "purple_moobloom_spawnable"));
    public static final TagKey<Biome> MAGENTA_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "magenta_moobloom_spawnable"));
    public static final TagKey<Biome> PINK_MOOBLOOM_SPAWNABLE = TagKey.of(RegistryKeys.BIOME, Identifier.of(TrashsMooblooms.MOD_ID, "pink_moobloom_spawnable"));

    public static void addEntitySpawns() {
        SpawnRestriction.register(
                ModEntities.MOOBLOOM,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MoobloomEntity::canSpawn
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.tag(MOOBLOOM_SPAWNABLE),
                SpawnGroup.CREATURE,
                ModEntities.MOOBLOOM,
                50,
                2,
                4
        );
    }
}
