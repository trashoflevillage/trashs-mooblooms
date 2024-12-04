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

    public static final Block THUNDERBLOOM = registerFlowerBlock("thunderbloom", StatusEffects.GLOWING, 8);
    public static final Block POTTED_THUNDERBLOOM = registerPotBlock("potted_thunderbloom", THUNDERBLOOM);

    public static final Block WITHERED_BUTTERCUP =
            registerBlock("withered_buttercup", new WitherRoseBlock(StatusEffects.WITHER, 10, AbstractBlock.Settings.copy(Blocks.WITHER_ROSE)));
    public static final Block POTTED_WITHERED_BUTTERCUP =
            registerBlock("potted_withered_buttercup", new FlowerPotBlock(ModBlocks.WITHERED_BUTTERCUP, AbstractBlock.Settings.copy(Blocks.POTTED_WITHER_ROSE)));

    public static final Block BELLFLOWER = registerFlowerBlock("bellflower", StatusEffects.BLINDNESS, 12);
    public static final Block POTTED_BELLFLOWER = registerPotBlock("potted_bellflower", BELLFLOWER);

    public static final Block BOAT_ORCHID = registerFlowerBlock("boat_orchid", StatusEffects.WATER_BREATHING, 12);
    public static final Block POTTED_BOAT_ORCHID = registerPotBlock("potted_boat_orchid", BOAT_ORCHID);

    public static final Block BUTTERFLY_CANDY = registerFlowerBlock("butterfly_candy", StatusEffects.SPEED, 12);
    public static final Block POTTED_BUTTERFLY_CANDY = registerPotBlock("potted_butterfly_candy", BUTTERFLY_CANDY);

    public static final Block BUTTERFLY_WEED = registerFlowerBlock("butterfly_weed", StatusEffects.NAUSEA, 12);
    public static final Block POTTED_BUTTERFLY_WEED = registerPotBlock("potted_butterfly_weed", BUTTERFLY_WEED);

    public static final Block CONBUSH = registerFlowerBlock("conbush", StatusEffects.STRENGTH, 12);
    public static final Block POTTED_CONBUSH = registerPotBlock("potted_conbush", CONBUSH);

    public static final Block MORNING_GLORY = registerFlowerBlock("morning_glory", StatusEffects.REGENERATION, 12);
    public static final Block POTTED_MORNING_GLORY = registerPotBlock("potted_morning_glory", MORNING_GLORY);

    public static final Block SILVER_IRIS = registerFlowerBlock("silver_iris", StatusEffects.NIGHT_VISION, 12);
    public static final Block POTTED_SILVER_IRIS = registerPotBlock("potted_silver_iris", SILVER_IRIS);

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
