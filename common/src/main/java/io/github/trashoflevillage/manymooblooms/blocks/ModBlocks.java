package io.github.trashoflevillage.manymooblooms.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import io.github.trashoflevillage.trashlib.initializers.BlockInitializer;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;

public class ModBlocks {
    private static final BlockInitializer initializer = new BlockInitializer(ManyMooblooms.MOD_ID).addModIdAlias(ManyMooblooms.OLD_MOD_ID);

    public static final RegistrySupplier<Block> BUTTERCUP =
            initializer.register(
                    "buttercup", (s) -> new FlowerBlock(StatusEffects.POISON, 10, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BUTTERCUP =
            initializer.register(
                    "potted_buttercup", s -> new FlowerPotBlock(BUTTERCUP.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> HIBISCUS =
            initializer.register(
                    "hibiscus", (s) -> new FlowerBlock(StatusEffects.REGENERATION, 5, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_HIBISCUS =
            initializer.register(
                    "potted_hibiscus", s -> new FlowerPotBlock(HIBISCUS.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> GLADIOLI =
            initializer.register(
                    "gladioli", (s) -> new FlowerBlock(StatusEffects.NIGHT_VISION, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_GLADIOLI =
            initializer.register(
                    "potted_gladioli", s -> new FlowerPotBlock(GLADIOLI.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> DAYFLOWER =
            initializer.register(
                    "dayflower", (s) -> new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 6, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_DAYFLOWER =
            initializer.register(
                    "potted_dayflower", s -> new FlowerPotBlock(DAYFLOWER.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> MYOSOTIS =
            initializer.register(
                    "myosotis", (s) -> new FlowerBlock(StatusEffects.SATURATION, 0.35f, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_MYOSOTIS =
            initializer.register(
                    "potted_myosotis", s -> new FlowerPotBlock(MYOSOTIS.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> CENTIAN =
            initializer.register(
                    "centian", (s) -> new FlowerBlock(StatusEffects.JUMP_BOOST, 15, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_CENTIAN =
            initializer.register(
                    "potted_centian", s -> new FlowerPotBlock(CENTIAN.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> TRILLIUM =
            initializer.register(
                    "trillium", (s) -> new FlowerBlock(StatusEffects.SPEED, 8, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_TRILLIUM =
            initializer.register(
                    "potted_trillium", s -> new FlowerPotBlock(TRILLIUM.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> THUNDERBLOOM =
            initializer.register(
                    "thunderbloom", (s) -> new FlowerBlock(StatusEffects.GLOWING, 8, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_THUNDERBLOOM =
            initializer.register(
                    "potted_thunderbloom", s -> new FlowerPotBlock(THUNDERBLOOM.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> WITHERED_BUTTERCUP =
            initializer.register(
                    "withered_buttercup", (s) -> new WitherRoseBlock(StatusEffects.WITHER, 10, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_WITHERED_BUTTERCUP =
            initializer.register(
                    "potted_withered_buttercup", s -> new FlowerPotBlock(WITHERED_BUTTERCUP.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> BELLFLOWER =
            initializer.register(
                    "bellflower", (s) -> new FlowerBlock(StatusEffects.BLINDNESS, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BELLFLOWER =
            initializer.register(
                    "potted_bellflower", s -> new FlowerPotBlock(BELLFLOWER.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> BOAT_ORCHID =
            initializer.register(
                    "boat_orchid", (s) -> new FlowerBlock(StatusEffects.WATER_BREATHING, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BOAT_ORCHID =
            initializer.register(
                    "potted_boat_orchid", s -> new FlowerPotBlock(BOAT_ORCHID.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> BUTTERFLY_CANDY =
            initializer.register(
                    "butterfly_candy", (s) -> new FlowerBlock(StatusEffects.SPEED, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BUTTERFLY_CANDY =
            initializer.register(
                    "potted_butterfly_candy", s -> new FlowerPotBlock(BUTTERFLY_CANDY.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> BUTTERFLY_WEED =
            initializer.register(
                    "butterfly_weed", (s) -> new FlowerBlock(StatusEffects.NAUSEA, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BUTTERFLY_WEED =
            initializer.register(
                    "potted_butterfly_weed", s -> new FlowerPotBlock(BUTTERFLY_WEED.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);


    public static final RegistrySupplier<Block> CONBUSH =
            initializer.register(
                    "conbush", (s) -> new FlowerBlock(StatusEffects.STRENGTH, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_CONBUSH =
            initializer.register(
                    "potted_conbush", s -> new FlowerPotBlock(CONBUSH.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> MORNING_GLORY =
            initializer.register(
                    "morning_glory", (s) -> new FlowerBlock(StatusEffects.REGENERATION, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_MORNING_GLORY =
            initializer.register(
                    "potted_morning_glory", s -> new FlowerPotBlock(MORNING_GLORY.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> SILVER_IRIS =
            initializer.register(
                    "silver_iris", (s) -> new FlowerBlock(StatusEffects.NIGHT_VISION, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_SILVER_IRIS =
            initializer.register(
                    "potted_silver_iris", s -> new FlowerPotBlock(SILVER_IRIS.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static void registerAll() {}
}
