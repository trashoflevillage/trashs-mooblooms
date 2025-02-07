package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final RegistryKey<EntityType<?>> MOOBLOOM_KEY = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE,
            Identifier.of(TrashsMooblooms.MOD_ID, "moobloom")
    );

    public static final EntityType<MoobloomEntity> MOOBLOOM = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(TrashsMooblooms.MOD_ID, "moobloom"
            ), EntityType.Builder.create(MoobloomEntity::new, SpawnGroup.CREATURE).dimensions(0.9f, 1.4f)
                    .build(MOOBLOOM_KEY));
}
