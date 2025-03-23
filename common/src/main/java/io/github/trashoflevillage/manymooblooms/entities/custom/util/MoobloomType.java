package io.github.trashoflevillage.manymooblooms.entities.custom.util;


import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import io.github.trashoflevillage.manymooblooms.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MoobloomType {
    private static final HashMap<Identifier, MoobloomType> registeredTypes = new HashMap<>();

    public static final MoobloomType WHITE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "white"), new MoobloomType(ModBlocks.THUNDERBLOOM.get().getDefaultState(), Items.WHITE_DYE));
    public static final MoobloomType LIGHT_GRAY =
            register(Identifier.of(ManyMooblooms.MOD_ID, "light_gray"), new MoobloomType(ModBlocks.BELLFLOWER.get().getDefaultState(), Items.LIGHT_GRAY_DYE)).setFlowerScale(0.5f);
    public static final MoobloomType GRAY =
            register(Identifier.of(ManyMooblooms.MOD_ID, "gray"), new MoobloomType(ModBlocks.SILVER_IRIS.get().getDefaultState(), Items.GRAY_DYE));
    public static final MoobloomType BLACK =
            register(Identifier.of(ManyMooblooms.MOD_ID, "black"), new MoobloomType(ModBlocks.WITHERED_BUTTERCUP.get().getDefaultState(), Items.BLACK_DYE)).setFlowerScale(1f);
    public static final MoobloomType BROWN =
            register(Identifier.of(ManyMooblooms.MOD_ID, "brown"), new MoobloomType(ModBlocks.BOAT_ORCHID.get().getDefaultState(), Items.BROWN_DYE));
    public static final MoobloomType RED =
            register(Identifier.of(ManyMooblooms.MOD_ID, "red"), new MoobloomType(ModBlocks.TRILLIUM.get().getDefaultState(), Items.RED_DYE));
    public static final MoobloomType ORANGE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "orange"), new MoobloomType(ModBlocks.BUTTERFLY_WEED.get().getDefaultState(), Items.ORANGE_DYE));
    public static final MoobloomType YELLOW =
            register(Identifier.of(ManyMooblooms.MOD_ID, "yellow"), new MoobloomType(ModBlocks.BUTTERCUP.get().getDefaultState(), Items.YELLOW_DYE)).setFlowerScale(1f);
    public static final MoobloomType LIME =
            register(Identifier.of(ManyMooblooms.MOD_ID, "lime"), new MoobloomType(ModBlocks.CONBUSH.get().getDefaultState(), Items.LIME_DYE));
    public static final MoobloomType GREEN =
            register(Identifier.of(ManyMooblooms.MOD_ID, "green"), new MoobloomType(ModBlocks.GLADIOLI.get().getDefaultState(), Items.GREEN_DYE)).setFlowerScale(1f);
    public static final MoobloomType CYAN =
            register(Identifier.of(ManyMooblooms.MOD_ID, "cyan"), new MoobloomType(ModBlocks.CENTIAN.get().getDefaultState(), Items.CYAN_DYE));
    public static final MoobloomType LIGHT_BLUE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "light_blue"), new MoobloomType(ModBlocks.MYOSOTIS.get().getDefaultState(), Items.LIGHT_BLUE_DYE));
    public static final MoobloomType BLUE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "blue"), new MoobloomType(ModBlocks.DAYFLOWER.get().getDefaultState(), Items.BLUE_DYE));
    public static final MoobloomType PURPLE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "purple"), new MoobloomType(ModBlocks.MORNING_GLORY.get().getDefaultState(), Items.PURPLE_DYE));
    public static final MoobloomType MAGENTA =
            register(Identifier.of(ManyMooblooms.MOD_ID, "magenta"), new MoobloomType(ModBlocks.BUTTERFLY_CANDY.get().getDefaultState(), Items.MAGENTA_DYE));
    public static final MoobloomType PINK =
            register(Identifier.of(ManyMooblooms.MOD_ID, "pink"), new MoobloomType(ModBlocks.HIBISCUS.get().getDefaultState(), Items.PINK_DYE)).setFlowerScale(1f);

    private final BlockState flowerState;
    private final Item dyeItem;
    private float flowerScale = 0.6f;
    private Function<ItemStack, Boolean> breedingItemFactory = (i) -> i.isIn(ItemTags.COW_FOOD);

    public MoobloomType(BlockState flowerState, Item dyeItem) {
        this.flowerState = flowerState;
        this.dyeItem = dyeItem;
    }

    public BlockState getFlowerState() {
        return flowerState;
    }

    public Item getDyeItem() {
        return dyeItem;
    }

    public Item getItemFromShearing() {
        return flowerState.getBlock().asItem();
    }

    public int getRegrowTimer() {
        return 12000;
    }

    public MoobloomType setFlowerScale(float f) {
        flowerScale = f;
        return this;
    }

    public float getFlowerScale() {
        return flowerScale;
    }

    public static MoobloomType register(Identifier id, MoobloomType value) {
        registeredTypes.put(id, value);
        return value;
    }

    public Identifier getId() {
        if (registeredTypes.containsValue(this)) {
            for (Map.Entry<Identifier, MoobloomType> i : registeredTypes.entrySet()) {
                if (i.getValue() == this) return i.getKey();
            }
        }
        return null;
    }

    public static MoobloomType get(Identifier id) {
        return registeredTypes.get(id);
    }

    public static void registerAll() {}

    public Function<ItemStack, Boolean> getBreedingItemFactory() {
        return breedingItemFactory;
    }

    public MoobloomType setBreedingItemFactory(Function<ItemStack, Boolean> breedingItemFactory) {
        this.breedingItemFactory = breedingItemFactory;
        return this;
    }
}