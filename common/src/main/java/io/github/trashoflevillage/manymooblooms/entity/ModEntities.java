package io.github.trashoflevillage.manymooblooms.entity;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import io.github.trashoflevillage.manymooblooms.entity.custom.MoobloomEntity;
import io.github.trashoflevillage.trashlib.initializers.EntityInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    private static final EntityInitializer initializer = new EntityInitializer(ManyMooblooms.MOD_ID).addModIdAlias(ManyMooblooms.OLD_MOD_ID);

    public static final RegistryKey<EntityType<?>> MOOBLOOM_KEY = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE,
            Identifier.of(ManyMooblooms.MOD_ID, "moobloom")
    );

    public static final RegistrySupplier<EntityType<MoobloomEntity>> MOOBLOOM = initializer.register(
            "moobloom",
            () -> EntityType.Builder.create(
                    MoobloomEntity::new,
                    SpawnGroup.CREATURE
            ).dimensions(0.9f, 1.4f).build(MOOBLOOM_KEY)
    );

    public static void registerAlL() {}
}
