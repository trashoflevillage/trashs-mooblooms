package io.github.trashoflevillage.mooblooms.items;

import io.github.trashoflevillage.mooblooms.ManyMooblooms;
import io.github.trashoflevillage.mooblooms.blocks.ModBlocks;
import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import io.github.trashoflevillage.trashlib.initializers.ItemInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;

public class ModItems {
    private static final ItemInitializer initializer = new ItemInitializer(ManyMooblooms.MOD_ID).addModIdAlias(ManyMooblooms.OLD_MOD_ID);

    public static final Item MOOBLOOM_SPAWN_EGG = initializer.register("moobloom_spawn_egg",
            (s) -> new SpawnEggItem(ModEntities.MOOBLOOM, s), new Item.Settings());
    
    public static void registerModItems() {
        ManyMooblooms.LOGGER.info("Registering items for " + ManyMooblooms.MOD_ID + ".");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.add(ModItems.MOOBLOOM_SPAWN_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.add(ModBlocks.BUTTERCUP);
            content.add(ModBlocks.HIBISCUS);
            content.add(ModBlocks.GLADIOLI);
            content.add(ModBlocks.DAYFLOWER);
            content.add(ModBlocks.MYOSOTIS);
            content.add(ModBlocks.CENTIAN);
            content.add(ModBlocks.TRILLIUM);
            content.add(ModBlocks.WITHERED_BUTTERCUP);
            content.add(ModBlocks.THUNDERBLOOM);
            content.add(ModBlocks.BELLFLOWER);
            content.add(ModBlocks.BOAT_ORCHID);
            content.add(ModBlocks.BUTTERFLY_CANDY);
            content.add(ModBlocks.BUTTERFLY_WEED);
            content.add(ModBlocks.CONBUSH);
            content.add(ModBlocks.MORNING_GLORY);
            content.add(ModBlocks.SILVER_IRIS);
        });
    }
}