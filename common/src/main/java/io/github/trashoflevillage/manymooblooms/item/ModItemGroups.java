package io.github.trashoflevillage.manymooblooms.item;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import io.github.trashoflevillage.manymooblooms.block.ModBlocks;
import io.github.trashoflevillage.trashlib.initializers.ItemGroupInitializer;
import net.minecraft.item.ItemGroup;

public class ModItemGroups {
    private static final ItemGroupInitializer INITIALIZER = new ItemGroupInitializer(ManyMooblooms.MOD_ID);

    //public static final RegistrySupplier<ItemGroup> BASE_GROUP = INITIALIZER.register("base", ModItems.MOOBLOOM_SPAWN_EGG.get().getDefaultStack());

    public static void registerAll() {}
}
