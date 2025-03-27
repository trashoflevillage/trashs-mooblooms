package io.github.trashoflevillage.manymooblooms.entity;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import io.github.trashoflevillage.manymooblooms.entity.custom.MoobloomEntity;
import io.github.trashoflevillage.trashlib.initializers.EntityInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    private static final EntityInitializer INITIALIZER = new EntityInitializer(ManyMooblooms.MOD_ID).addModIdAlias(ManyMooblooms.OLD_MOD_ID);

    public static final RegistryKey<EntityType<?>> MOOBLOOM_KEY = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE,
            Identifier.of(ManyMooblooms.MOD_ID, "moobloom")
    );

    public static final RegistrySupplier<EntityType<MoobloomEntity>> MOOBLOOM = INITIALIZER.register(
            "moobloom",
            () -> EntityType.Builder.create(
                    MoobloomEntity::new,
                    SpawnGroup.CREATURE
            ).dimensions(0.9f, 1.4f).build(MOOBLOOM_KEY)
    );

    public static void registerAlL() {
        registerAttributes();
    }

    private static void registerAttributes() {
        EntityAttributeRegistry.register(ModEntities.MOOBLOOM, CowEntity::createCowAttributes);
    }
}
