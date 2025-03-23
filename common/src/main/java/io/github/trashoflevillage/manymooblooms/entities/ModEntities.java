package io.github.trashoflevillage.manymooblooms.entities;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import io.github.trashoflevillage.trashlib.initializers.EntityInitializer;
import net.minecraft.entity.Entity;

public class ModEntities {
    private static final EntityInitializer initializer = new EntityInitializer(ManyMooblooms.MOD_ID).addModIdAlias(ManyMooblooms.OLD_MOD_ID);

    public static final RegistrySupplier<Entity> MOOBLOOM = initializer.register("moobloom", () -> new MoobloomEntity());
}
