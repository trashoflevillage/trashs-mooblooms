package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.ManyMooblooms;
import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import io.github.trashoflevillage.trashlib.initializers.EntityInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    private static final EntityInitializer initializer = new EntityInitializer(ManyMooblooms.MOD_ID).addModIdAlias(ManyMooblooms.OLD_MOD_ID);

    public static final RegistryKey<EntityType<?>> MOOBLOOM_KEY = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE,
            Identifier.of(ManyMooblooms.MOD_ID, "moobloom")
    );

    public static final EntityType<MoobloomEntity> MOOBLOOM = initializer.register(
            "moobloom", MOOBLOOM_KEY,
            EntityType.Builder.create(
                    MoobloomEntity::new,
                    SpawnGroup.CREATURE
            ).dimensions(0.9f, 1.4f)
    );
}
