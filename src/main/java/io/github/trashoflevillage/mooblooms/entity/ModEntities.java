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
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MoobloomEntity> MOOBLOOM = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TrashsMooblooms.MOD_ID, "moobloom"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MoobloomEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 1.4f))
                    .build());
}
