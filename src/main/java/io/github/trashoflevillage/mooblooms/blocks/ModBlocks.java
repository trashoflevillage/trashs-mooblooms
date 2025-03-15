package io.github.trashoflevillage.mooblooms.blocks;

import io.github.trashoflevillage.mooblooms.ManyMooblooms;
import io.github.trashoflevillage.trashlib.initializers.BlockInitializer;
import net.minecraft.block.*;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModBlocks {
    private static final BlockInitializer initializer = new BlockInitializer(ManyMooblooms.MOD_ID).addModIdAlias(ManyMooblooms.OLD_MOD_ID);

    public static final Block BUTTERCUP =
            initializer.register(
                    "buttercup", (s) -> new FlowerBlock(StatusEffects.POISON, 10, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_BUTTERCUP =
            initializer.register(
                    "potted_buttercup", s -> new FlowerPotBlock(BUTTERCUP, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block HIBISCUS =
            initializer.register(
                    "hibiscus", (s) -> new FlowerBlock(StatusEffects.REGENERATION, 5, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_HIBISCUS =
            initializer.register(
                    "potted_hibiscus", s -> new FlowerPotBlock(HIBISCUS, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block GLADIOLI =
            initializer.register(
                    "gladioli", (s) -> new FlowerBlock(StatusEffects.NIGHT_VISION, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_GLADIOLI =
            initializer.register(
                    "potted_gladioli", s -> new FlowerPotBlock(GLADIOLI, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block DAYFLOWER =
            initializer.register(
                    "dayflower", (s) -> new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 6, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_DAYFLOWER =
            initializer.register(
                    "potted_dayflower", s -> new FlowerPotBlock(DAYFLOWER, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block MYOSOTIS =
            initializer.register(
                    "myosotis", (s) -> new FlowerBlock(StatusEffects.SATURATION, 0.35f, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_MYOSOTIS =
            initializer.register(
                    "potted_myosotis", s -> new FlowerPotBlock(MYOSOTIS, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block CENTIAN =
            initializer.register(
                    "centian", (s) -> new FlowerBlock(StatusEffects.JUMP_BOOST, 15, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_CENTIAN =
            initializer.register(
                    "potted_centian", s -> new FlowerPotBlock(CENTIAN, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block TRILLIUM =
            initializer.register(
                    "trillium", (s) -> new FlowerBlock(StatusEffects.SPEED, 8, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_TRILLIUM =
            initializer.register(
                    "potted_trillium", s -> new FlowerPotBlock(TRILLIUM, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block THUNDERBLOOM =
            initializer.register(
                    "thunderbloom", (s) -> new FlowerBlock(StatusEffects.GLOWING, 8, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_THUNDERBLOOM =
            initializer.register(
                    "potted_thunderbloom", s -> new FlowerPotBlock(THUNDERBLOOM, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block WITHERED_BUTTERCUP =
            initializer.register(
                    "withered_buttercup", (s) -> new FlowerBlock(StatusEffects.WITHER, 10, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_WITHERED_BUTTERCUP =
            initializer.register(
                    "potted_withered_buttercup", s -> new FlowerPotBlock(WITHERED_BUTTERCUP, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block BELLFLOWER =
            initializer.register(
                    "bellflower", (s) -> new FlowerBlock(StatusEffects.BLINDNESS, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_BELLFLOWER =
            initializer.register(
                    "potted_bellflower", s -> new FlowerPotBlock(BELLFLOWER, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block BOAT_ORCHID =
            initializer.register(
                    "boat_orchid", (s) -> new FlowerBlock(StatusEffects.WATER_BREATHING, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_BOAT_ORCHID =
            initializer.register(
                    "potted_boat_orchid", s -> new FlowerPotBlock(BOAT_ORCHID, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block BUTTERFLY_CANDY =
            initializer.register(
                    "butterfly_candy", (s) -> new FlowerBlock(StatusEffects.SPEED, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_BUTTERFLY_CANDY =
            initializer.register(
                    "potted_butterfly_candy", s -> new FlowerPotBlock(BUTTERFLY_CANDY, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block BUTTERFLY_WEED =
            initializer.register(
                    "butterfly_weed", (s) -> new FlowerBlock(StatusEffects.NAUSEA, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_BUTTERFLY_WEED =
            initializer.register(
                    "potted_butterfly_weed", s -> new FlowerPotBlock(BUTTERFLY_WEED, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);


    public static final Block CONBUSH =
            initializer.register(
                    "conbush", (s) -> new FlowerBlock(StatusEffects.STRENGTH, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_CONBUSH =
            initializer.register(
                    "potted_conbush", s -> new FlowerPotBlock(CONBUSH, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block MORNING_GLORY =
            initializer.register(
                    "morning_glory", (s) -> new FlowerBlock(StatusEffects.REGENERATION, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_MORNING_GLORY =
            initializer.register(
                    "potted_morning_glory", s -> new FlowerPotBlock(MORNING_GLORY, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final Block SILVER_IRIS =
            initializer.register(
                    "silver_iris", (s) -> new FlowerBlock(StatusEffects.NIGHT_VISION, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final Block POTTED_SILVER_IRIS =
            initializer.register(
                    "potted_silver_iris", s -> new FlowerPotBlock(SILVER_IRIS, s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);


    private static Block registerBlock(String name, BlockFactory factory, AbstractBlock.Settings settings) {
        Identifier id = Identifier.of(ManyMooblooms.MOD_ID, name);
        Block block = factory.create(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)));
        registerBlockItem(name, BlockItem::new, block);
        return Registry.register(Registries.BLOCK, id, block);
    }

    private static Item registerBlockItem(String name, BlockItemFactory factory, Block block) {
        Identifier id = Identifier.of(ManyMooblooms.MOD_ID, name);
        Item.Settings settings = new Item.Settings();
        settings.useBlockPrefixedTranslationKey();
        Item item = factory.create(block, settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        return Registry.register(Registries.ITEM, id, item);
    }

    private static Block registerFlowerBlock(
            String name,
            FlowerBlockFactory factory,
            RegistryEntry<StatusEffect> effect,
            float effectLengthInSeconds
    ) {
        Identifier id = Identifier.of(ManyMooblooms.MOD_ID, name);
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(Blocks.DANDELION);
        Block block = factory.create(effect, effectLengthInSeconds, settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)));
        registerBlockItem(name, BlockItem::new, block);
        return Registry.register(Registries.BLOCK, id, block);
    }

    private static Block registerPotBlock(
            String name,
            PotBlockFactory factory,
            Block flower
    ) {
        Identifier id = Identifier.of(ManyMooblooms.MOD_ID, name);
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION);
        Block block = factory.create(flower, settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)));
        registerBlockItem(name, BlockItem::new, block);
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void registerModBlocks() {
        ManyMooblooms.LOGGER.info("Registering blocks for " + ManyMooblooms.MOD_ID);
    }

    @FunctionalInterface
    interface BlockFactory {
        Block create(AbstractBlock.Settings settings);
    }

    @FunctionalInterface
    public interface BlockItemFactory {
        BlockItem create(Block block, Item.Settings settings);
    }

    @FunctionalInterface
    interface FlowerBlockFactory {
        FlowerBlock create(
                RegistryEntry<StatusEffect> effect,
                float effectLengthInSeconds,
                AbstractBlock.Settings settings
            );
    }

    @FunctionalInterface
    interface PotBlockFactory {
        FlowerPotBlock create(
                Block flower,
                AbstractBlock.Settings settings
        );
    }
}
