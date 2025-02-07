package io.github.trashoflevillage.mooblooms.blocks;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import io.github.trashoflevillage.mooblooms.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BUTTERCUP =
            registerFlowerBlock("buttercup", FlowerBlock::new, StatusEffects.POISON, 10);
    public static final Block POTTED_BUTTERCUP =
            registerPotBlock("potted_buttercup", FlowerPotBlock::new, ModBlocks.BUTTERCUP);

    public static final Block HIBISCUS =
            registerFlowerBlock("hibiscus", FlowerBlock::new, StatusEffects.REGENERATION, 5);
    public static final Block POTTED_HIBISCUS =
            registerPotBlock("potted_hibiscus", FlowerPotBlock::new, HIBISCUS);

    public static final Block GLADIOLI =
            registerFlowerBlock("gladioli", FlowerBlock::new, StatusEffects.NIGHT_VISION, 12);
    public static final Block POTTED_GLADIOLI =
            registerPotBlock("potted_gladioli", FlowerPotBlock::new, GLADIOLI);

    public static final Block DAYFLOWER =
            registerFlowerBlock("dayflower", FlowerBlock::new, StatusEffects.FIRE_RESISTANCE, 6);
    public static final Block POTTED_DAYFLOWER =
            registerPotBlock("potted_dayflower", FlowerPotBlock::new, DAYFLOWER);

    public static final Block MYOSOTIS =
            registerFlowerBlock("myosotis", FlowerBlock::new, StatusEffects.SATURATION, 0.35f);
    public static final Block POTTED_MYOSOTIS =
            registerPotBlock("potted_myosotis", FlowerPotBlock::new, MYOSOTIS);

    public static final Block CENTIAN =
            registerFlowerBlock("centian", FlowerBlock::new, StatusEffects.JUMP_BOOST, 15);
    public static final Block POTTED_CENTIAN =
            registerPotBlock("potted_centian", FlowerPotBlock::new, CENTIAN);

    public static final Block TRILLIUM = registerFlowerBlock("trillium", FlowerBlock::new, StatusEffects.SPEED, 8);
    public static final Block POTTED_TRILLIUM = registerPotBlock("potted_trillium", FlowerPotBlock::new, TRILLIUM);

    public static final Block THUNDERBLOOM = registerFlowerBlock("thunderbloom", FlowerBlock::new, StatusEffects.GLOWING, 8);
    public static final Block POTTED_THUNDERBLOOM = registerPotBlock("potted_thunderbloom", FlowerPotBlock::new, THUNDERBLOOM);

    public static final Block WITHERED_BUTTERCUP =
            registerFlowerBlock("withered_buttercup", FlowerBlock::new, StatusEffects.WITHER, 10);
    public static final Block POTTED_WITHERED_BUTTERCUP =
            registerPotBlock("potted_withered_buttercup", FlowerPotBlock::new, WITHERED_BUTTERCUP);

    public static final Block BELLFLOWER = registerFlowerBlock("bellflower", FlowerBlock::new, StatusEffects.BLINDNESS, 12);
    public static final Block POTTED_BELLFLOWER = registerPotBlock("potted_bellflower", FlowerPotBlock::new, BELLFLOWER);

    public static final Block BOAT_ORCHID = registerFlowerBlock("boat_orchid", FlowerBlock::new, StatusEffects.WATER_BREATHING, 12);
    public static final Block POTTED_BOAT_ORCHID = registerPotBlock("potted_boat_orchid", FlowerPotBlock::new, BOAT_ORCHID);

    public static final Block BUTTERFLY_CANDY = registerFlowerBlock("butterfly_candy", FlowerBlock::new, StatusEffects.SPEED, 12);
    public static final Block POTTED_BUTTERFLY_CANDY = registerPotBlock("potted_butterfly_candy", FlowerPotBlock::new, BUTTERFLY_CANDY);

    public static final Block BUTTERFLY_WEED = registerFlowerBlock("butterfly_weed", FlowerBlock::new, StatusEffects.NAUSEA, 12);
    public static final Block POTTED_BUTTERFLY_WEED = registerPotBlock("potted_butterfly_weed", FlowerPotBlock::new, BUTTERFLY_WEED);

    public static final Block CONBUSH = registerFlowerBlock("conbush", FlowerBlock::new, StatusEffects.STRENGTH, 12);
    public static final Block POTTED_CONBUSH = registerPotBlock("potted_conbush", FlowerPotBlock::new, CONBUSH);

    public static final Block MORNING_GLORY = registerFlowerBlock("morning_glory", FlowerBlock::new, StatusEffects.REGENERATION, 12);
    public static final Block POTTED_MORNING_GLORY = registerPotBlock("potted_morning_glory", FlowerPotBlock::new, MORNING_GLORY);

    public static final Block SILVER_IRIS = registerFlowerBlock("silver_iris", FlowerBlock::new, StatusEffects.NIGHT_VISION, 12);
    public static final Block POTTED_SILVER_IRIS = registerPotBlock("potted_silver_iris", FlowerPotBlock::new, SILVER_IRIS);

    private static Block registerBlock(String name, BlockFactory factory, AbstractBlock.Settings settings) {
        Identifier id = Identifier.of(TrashsMooblooms.MOD_ID, name);
        Block block = factory.create(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)));
        registerBlockItem(name, BlockItem::new, block);
        return Registry.register(Registries.BLOCK, id, block);
    }

    private static Item registerBlockItem(String name, BlockItemFactory factory, Block block) {
        Identifier id = Identifier.of(TrashsMooblooms.MOD_ID, name);
        Item.Settings settings = new Item.Settings();
        Item item = factory.create(block, settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        settings.useBlockPrefixedTranslationKey();
        return Registry.register(Registries.ITEM, id, item);
    }

    private static Block registerFlowerBlock(
            String name,
            FlowerBlockFactory factory,
            RegistryEntry<StatusEffect> effect,
            float effectLengthInSeconds
    ) {
        Identifier id = Identifier.of(TrashsMooblooms.MOD_ID, name);
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
        Identifier id = Identifier.of(TrashsMooblooms.MOD_ID, name);
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION);
        Block block = factory.create(flower, settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)));
        registerBlockItem(name, BlockItem::new, block);
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void registerModBlocks() {
        TrashsMooblooms.LOGGER.info("Registering blocks for " + TrashsMooblooms.MOD_ID);
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
