package io.github.trashoflevillage.manymooblooms.block;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import io.github.trashoflevillage.trashlib.initializers.BlockInitializer;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;

public class ModBlocks {
    private static final BlockInitializer INITIALIZER = new BlockInitializer(ManyMooblooms.MOD_ID).addModIdAlias(ManyMooblooms.OLD_MOD_ID);

    public static final RegistrySupplier<Block> BUTTERCUP =
            INITIALIZER.register(
                    "buttercup", (s) -> new FlowerBlock(StatusEffects.POISON, 10, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BUTTERCUP =
            INITIALIZER.register(
                    "potted_buttercup", s -> new FlowerPotBlock(BUTTERCUP.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> HIBISCUS =
            INITIALIZER.register(
                    "hibiscus", (s) -> new FlowerBlock(StatusEffects.REGENERATION, 5, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_HIBISCUS =
            INITIALIZER.register(
                    "potted_hibiscus", s -> new FlowerPotBlock(HIBISCUS.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> GLADIOLI =
            INITIALIZER.register(
                    "gladioli", (s) -> new FlowerBlock(StatusEffects.NIGHT_VISION, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_GLADIOLI =
            INITIALIZER.register(
                    "potted_gladioli", s -> new FlowerPotBlock(GLADIOLI.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> DAYFLOWER =
            INITIALIZER.register(
                    "dayflower", (s) -> new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 6, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_DAYFLOWER =
            INITIALIZER.register(
                    "potted_dayflower", s -> new FlowerPotBlock(DAYFLOWER.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> MYOSOTIS =
            INITIALIZER.register(
                    "myosotis", (s) -> new FlowerBlock(StatusEffects.SATURATION, 0.35f, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_MYOSOTIS =
            INITIALIZER.register(
                    "potted_myosotis", s -> new FlowerPotBlock(MYOSOTIS.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> CENTIAN =
            INITIALIZER.register(
                    "centian", (s) -> new FlowerBlock(StatusEffects.JUMP_BOOST, 15, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_CENTIAN =
            INITIALIZER.register(
                    "potted_centian", s -> new FlowerPotBlock(CENTIAN.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> TRILLIUM =
            INITIALIZER.register(
                    "trillium", (s) -> new FlowerBlock(StatusEffects.SPEED, 8, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_TRILLIUM =
            INITIALIZER.register(
                    "potted_trillium", s -> new FlowerPotBlock(TRILLIUM.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> THUNDERBLOOM =
            INITIALIZER.register(
                    "thunderbloom", (s) -> new FlowerBlock(StatusEffects.GLOWING, 8, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_THUNDERBLOOM =
            INITIALIZER.register(
                    "potted_thunderbloom", s -> new FlowerPotBlock(THUNDERBLOOM.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> WITHERED_BUTTERCUP =
            INITIALIZER.register(
                    "withered_buttercup", (s) -> new WitherRoseBlock(StatusEffects.WITHER, 10, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_WITHERED_BUTTERCUP =
            INITIALIZER.register(
                    "potted_withered_buttercup", s -> new FlowerPotBlock(WITHERED_BUTTERCUP.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> BELLFLOWER =
            INITIALIZER.register(
                    "bellflower", (s) -> new FlowerBlock(StatusEffects.BLINDNESS, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BELLFLOWER =
            INITIALIZER.register(
                    "potted_bellflower", s -> new FlowerPotBlock(BELLFLOWER.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> BOAT_ORCHID =
            INITIALIZER.register(
                    "boat_orchid", (s) -> new FlowerBlock(StatusEffects.WATER_BREATHING, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BOAT_ORCHID =
            INITIALIZER.register(
                    "potted_boat_orchid", s -> new FlowerPotBlock(BOAT_ORCHID.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> BUTTERFLY_CANDY =
            INITIALIZER.register(
                    "butterfly_candy", (s) -> new FlowerBlock(StatusEffects.SPEED, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BUTTERFLY_CANDY =
            INITIALIZER.register(
                    "potted_butterfly_candy", s -> new FlowerPotBlock(BUTTERFLY_CANDY.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> BUTTERFLY_WEED =
            INITIALIZER.register(
                    "butterfly_weed", (s) -> new FlowerBlock(StatusEffects.NAUSEA, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_BUTTERFLY_WEED =
            INITIALIZER.register(
                    "potted_butterfly_weed", s -> new FlowerPotBlock(BUTTERFLY_WEED.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);


    public static final RegistrySupplier<Block> CONBUSH =
            INITIALIZER.register(
                    "conbush", (s) -> new FlowerBlock(StatusEffects.STRENGTH, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_CONBUSH =
            INITIALIZER.register(
                    "potted_conbush", s -> new FlowerPotBlock(CONBUSH.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> MORNING_GLORY =
            INITIALIZER.register(
                    "morning_glory", (s) -> new FlowerBlock(StatusEffects.REGENERATION, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_MORNING_GLORY =
            INITIALIZER.register(
                    "potted_morning_glory", s -> new FlowerPotBlock(MORNING_GLORY.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static final RegistrySupplier<Block> SILVER_IRIS =
            INITIALIZER.register(
                    "silver_iris", (s) -> new FlowerBlock(StatusEffects.NIGHT_VISION, 12, s),
                    AbstractBlock.Settings.copy(Blocks.DANDELION));
    public static final RegistrySupplier<Block> POTTED_SILVER_IRIS =
            INITIALIZER.register(
                    "potted_silver_iris", s -> new FlowerPotBlock(SILVER_IRIS.get(), s),
                    AbstractBlock.Settings.copy(Blocks.POTTED_DANDELION), false);

    public static void registerAll() {
        registerTransparency();
    }

    private static void registerTransparency() {
        INITIALIZER.addTransparentBlocks(
                BUTTERCUP, POTTED_BUTTERCUP,
                HIBISCUS, POTTED_HIBISCUS,
                GLADIOLI, POTTED_GLADIOLI,
                DAYFLOWER, POTTED_DAYFLOWER,
                MYOSOTIS, POTTED_MYOSOTIS,
                CENTIAN, POTTED_CENTIAN,
                TRILLIUM, POTTED_TRILLIUM,
                THUNDERBLOOM, POTTED_THUNDERBLOOM,
                WITHERED_BUTTERCUP, POTTED_WITHERED_BUTTERCUP,
                BELLFLOWER, POTTED_BELLFLOWER,
                BOAT_ORCHID, POTTED_BOAT_ORCHID,
                BUTTERFLY_CANDY, POTTED_BUTTERFLY_CANDY,
                BUTTERFLY_WEED, POTTED_BUTTERFLY_WEED,
                CONBUSH, POTTED_CONBUSH,
                MORNING_GLORY, POTTED_MORNING_GLORY,
                SILVER_IRIS, POTTED_SILVER_IRIS
        );
    }
}
