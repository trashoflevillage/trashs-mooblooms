package io.github.trashoflevillage.mooblooms.items;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import io.github.trashoflevillage.mooblooms.blocks.ModBlocks;
import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MOOBLOOM_SPAWN_EGG = registerItem("moobloom_spawn_egg",
            new SpawnEggItem(ModEntities.MOOBLOOM, 0xFDD500, 0xFBF8DE, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TrashsMooblooms.MOD_ID, name), item);
    }
    
    public static void registerModItems() {
        TrashsMooblooms.LOGGER.info("Registering items for " + TrashsMooblooms.MOD_ID + ".");

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
        });
    }
}