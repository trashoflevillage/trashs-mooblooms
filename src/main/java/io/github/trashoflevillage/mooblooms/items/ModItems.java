package io.github.trashoflevillage.mooblooms.items;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import io.github.trashoflevillage.mooblooms.blocks.ModBlocks;
import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MOOBLOOM_SPAWN_EGG = registerSpawnEggItem("moobloom_spawn_egg",
            SpawnEggItem::new, ModEntities.MOOBLOOM, 0xFDD500, 0xFBF8DE, new Item.Settings());

    private static Item registerItem(String name, ItemFactory factory, Item.Settings settings) {
        Identifier id = Identifier.of(TrashsMooblooms.MOD_ID, name);
        Item item = factory.create(settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        return Registry.register(Registries.ITEM, id, item);
    }

    private static Item registerSpawnEggItem(
             String name,
             SpawnEggItemFactory factory,
             EntityType<? extends MobEntity> entity,
             int primaryColor,
             int secondaryColor,
             Item.Settings settings
    ) {
        Identifier id = Identifier.of(TrashsMooblooms.MOD_ID, name);
        SpawnEggItem item = factory.create(entity, primaryColor, secondaryColor, settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        return Registry.register(Registries.ITEM, id, item);
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
            content.add(ModBlocks.BELLFLOWER);
            content.add(ModBlocks.BOAT_ORCHID);
            content.add(ModBlocks.BUTTERFLY_CANDY);
            content.add(ModBlocks.BUTTERFLY_WEED);
            content.add(ModBlocks.CONBUSH);
            content.add(ModBlocks.MORNING_GLORY);
            content.add(ModBlocks.SILVER_IRIS);
        });
    }

    @FunctionalInterface
    public interface ItemFactory {
        Item create(Item.Settings settings);
    }

    @FunctionalInterface
    public interface SpawnEggItemFactory {
        SpawnEggItem create(EntityType<? extends MobEntity> entity, int primaryColor, int secondaryColor, Item.Settings settings);
    }
}