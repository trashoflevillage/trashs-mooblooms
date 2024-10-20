package io.github.trashoflevillage.mooblooms.blocks;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BUTTERCUP =
            registerBlock("buttercup", new FlowerBlock(StatusEffects.POISON, 10, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final Block POTTED_BUTTERCUP =
            registerBlock("potted_buttercup", new FlowerPotBlock(ModBlocks.BUTTERCUP, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block HIBISCUS =
            registerBlock("hibiscus", new FlowerBlock(StatusEffects.REGENERATION, 5, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final Block POTTED_HIBISCUS =
            registerBlock("potted_hibiscus", new FlowerPotBlock(ModBlocks.HIBISCUS, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block GLADIOLI =
            registerBlock("gladioli", new FlowerBlock(StatusEffects.NIGHT_VISION, 12, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final Block POTTED_GLADIOLI =
            registerBlock("potted_gladioli", new FlowerPotBlock(ModBlocks.GLADIOLI, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block DAYFLOWER =
            registerBlock("dayflower", new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 6, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final Block POTTED_DAYFLOWER =
            registerBlock("potted_dayflower", new FlowerPotBlock(ModBlocks.DAYFLOWER, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block MYOSOTIS =
            registerBlock("myosotis", new FlowerBlock(StatusEffects.SATURATION, 0.35f, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final Block POTTED_MYOSOTIS =
            registerBlock("potted_myosotis", new FlowerPotBlock(ModBlocks.MYOSOTIS, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block CENTIAN =
            registerBlock("centian", new FlowerBlock(StatusEffects.JUMP_BOOST, 15, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final Block POTTED_CENTIAN =
            registerBlock("potted_centian", new FlowerPotBlock(ModBlocks.CENTIAN, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));

    public static final Block TRILLIUM = registerFlowerBlock("trillium", StatusEffects.SPEED, 8);
    public static final Block POTTED_TRILLIUM = registerPotBlock("potted_trillium", TRILLIUM);

    private static Block registerPotBlock(String name, Block flower) {
        return registerBlock(name, new FlowerPotBlock(flower, AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION)));
    }

    private static Block registerFlowerBlock(String name, RegistryEntry<StatusEffect> effect, int effectLengthInSeconds) {
        return registerBlock(name, new FlowerBlock(effect, effectLengthInSeconds, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK,
                Identifier.of(TrashsMooblooms.MOD_ID, name),
                block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(TrashsMooblooms.MOD_ID, name),
                new BlockItem(block,
                        new Item.Settings()));
    }

    public static void registerModBlocks() {
        TrashsMooblooms.LOGGER.info("Registering blocks for " + TrashsMooblooms.MOD_ID);
    }
}
