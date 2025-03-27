package io.github.trashoflevillage.manymooblooms.item;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import io.github.trashoflevillage.manymooblooms.entity.ModEntities;
import io.github.trashoflevillage.trashlib.initializers.ItemInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;

public class ModItems {
    private static final ItemInitializer INITIALIZER = new ItemInitializer(ManyMooblooms.MOD_ID).addModIdAlias(ManyMooblooms.OLD_MOD_ID);

    public static final RegistrySupplier<Item> MOOBLOOM_SPAWN_EGG = INITIALIZER.register("moobloom_spawn_egg",
            (s) -> new SpawnEggItem(ModEntities.MOOBLOOM.get(), s));

    public static void registerAll() {}
}
