package io.github.trashoflevillage.mooblooms.entity.custom;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import io.github.trashoflevillage.mooblooms.blocks.ModBlocks;
import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import io.github.trashoflevillage.mooblooms.entity.ModEntitySpawn;
import io.github.trashoflevillage.mooblooms.util.ModTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SuspiciousStewIngredient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.recipe.ArmorDyeRecipe;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionTypes;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MoobloomEntity extends CowEntity implements Shearable {
    private static final TrackedData<Boolean> IS_SHEARED;
    private static final TrackedData<Integer> REGROW_TIMER;
    private static final TrackedData<String> TYPE;
    private static final float SPAWN_CHANCE = 0.1f;
    private UUID lightningUUID;

    public MoobloomEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    public MoobloomEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        MoobloomEntity moobloomEntity = ModEntities.MOOBLOOM.create(serverWorld);
        if (moobloomEntity != null) {
            moobloomEntity.setVariant(this.chooseBabyType((MoobloomEntity)passiveEntity));
        }

        return moobloomEntity;
    }

    public static boolean canSpawn(EntityType<MoobloomEntity> entityType, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(ModTags.Blocks.MOOBLOOM_SPAWNABLE_ON) && ((world.getDimension().hasSkyLight() && isLightLevelValidForNaturalSpawn(world, pos)) || !world.getDimension().hasSkyLight());
    }

    private MoobloomVariant chooseBabyType(MoobloomEntity moobloom) {
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLUE, MoobloomVariant.YELLOW))
            return MoobloomVariant.GREEN;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.RED, MoobloomVariant.BLUE))
            return MoobloomVariant.PURPLE;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.RED, MoobloomVariant.YELLOW))
            return MoobloomVariant.ORANGE;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLUE, MoobloomVariant.GREEN))
            return MoobloomVariant.CYAN;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLUE, MoobloomVariant.WHITE))
            return MoobloomVariant.LIGHT_BLUE;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.RED, MoobloomVariant.WHITE))
            return MoobloomVariant.PINK;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.PURPLE, MoobloomVariant.PINK))
            return MoobloomVariant.MAGENTA;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLACK, MoobloomVariant.WHITE))
            return MoobloomVariant.LIGHT_GRAY;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.GRAY, MoobloomVariant.WHITE))
            return MoobloomVariant.LIGHT_GRAY;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLACK, MoobloomVariant.LIGHT_GRAY))
            return MoobloomVariant.GRAY;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.ORANGE, MoobloomVariant.BLACK))
            return MoobloomVariant.BROWN;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.GREEN, MoobloomVariant.WHITE))
            return MoobloomVariant.LIME;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.LIME, MoobloomVariant.BLACK))
            return MoobloomVariant.GREEN;

        MoobloomVariant[] types = new MoobloomVariant[] {
                this.getVariant(),
                moobloom.getVariant()
        };
        return types[this.getWorld().random.nextInt(types.length)];
    }

    private boolean areMoobloomsEqualToColorPair(MoobloomEntity a, MoobloomEntity b, MoobloomVariant c, MoobloomVariant d) {
        return (a.getVariant() == c && b.getVariant() == d) || (b.getVariant() == c && a.getVariant() == d);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ActionResult result = ActionResult.FAIL;
        ItemStack itemStack = player.getStackInHand(hand);

        if (tryConvertToSussyStew(player, hand) ||
            tryToDyeItem(player, hand) ||
            super.interactMob(player, hand) == ActionResult.SUCCESS)
                result = ActionResult.SUCCESS;
        else if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
            this.sheared(SoundCategory.PLAYERS);
            this.emitGameEvent(GameEvent.SHEAR, player);
            if (!this.getWorld().isClient) {
                itemStack.damage(1, player, getSlotForHand(hand));
            }

            return ActionResult.success(this.getWorld().isClient);
        }
        return result;
    }


    private boolean tryConvertToSussyStew(PlayerEntity player, Hand hand) {
        if (this.isBaby()) return false;
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.itemMatches(Items.MUSHROOM_STEW.getRegistryEntry())) {
            SuspiciousStewIngredient s = SuspiciousStewIngredient.of(Item.fromBlock(getVariant().flower.getBlock()));
            ItemStack itemStack2 = new ItemStack(Items.SUSPICIOUS_STEW);
            itemStack2.set(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, s.getStewEffects());
            player.setStackInHand(hand, itemStack2);
            player.playSound(SoundEvents.ENTITY_MOOSHROOM_SUSPICIOUS_MILK, 1, 1);
            return true;
        }
        return false;
    }

    private boolean tryToDyeItem(PlayerEntity player, Hand hand) {
        if (this.isBaby()) return false;

        HashMap<TagKey<Item>, HashMap<String, Item>> dyedItems = getDyeableItemHashmap();
        ItemStack itemStack = player.getStackInHand(hand);



        for (TagKey<Item> i : dyedItems.keySet()) {
            if (itemStack.isIn(i)) {
                if (dyedItems.get(i).containsKey(getVariant().name)) {
                    ItemStack newItem = itemStack.copyComponentsToNewStack(dyedItems.get(i).get(getVariant().name), itemStack.getCount());
                    if (newItem.itemMatches(itemStack.getRegistryEntry())) return false;
                    player.setStackInHand(hand, newItem);
                    player.playSound(SoundEvents.ITEM_DYE_USE, 1.0F, 1.0F);

                    return true;
                }
            }
        }

        ItemStack d = new ItemStack(this.getVariant().dye);

        if (itemStack.isIn(ItemTags.DYEABLE)) {
            Item dye = d.getItem();
            if (dye instanceof DyeItem) {
                ItemStack newItemStack = DyedColorComponent.setColor(itemStack, List.of((DyeItem)dye));
                player.setStackInHand(hand, newItemStack);
                player.playSound(SoundEvents.ITEM_DYE_USE, 1.0F, 1.0F);
                return true;
            }
        } else {
            CraftingRecipeInput[] recipeInputs = {
                    CraftingRecipeInput.create(2, 1,
                            List.of(itemStack, d)),
                    CraftingRecipeInput.create(3, 3,
                            List.of(d, d, d, d, itemStack, d, d, d, d))
            };

            for (CraftingRecipeInput recipeInput : recipeInputs) {
                Optional<Item> newItem = getWorld().getRecipeManager()
                        .getFirstMatch(RecipeType.CRAFTING, recipeInput, getWorld())
                        .map(recipe -> ((CraftingRecipe) recipe.value()).craft(recipeInput, this.getWorld().getRegistryManager()))
                        .map(ItemStack::getItem);

                if (newItem.isPresent()) {
                    ItemStack newItemStack = itemStack.copyComponentsToNewStack(newItem.get(), itemStack.getCount());
                    player.setStackInHand(hand, newItemStack);
                    player.playSound(SoundEvents.ITEM_DYE_USE, 1.0F, 1.0F);
                    return true;
                }
            }
        }

        return false;
    }

    private HashMap<TagKey<Item>, HashMap<String, Item>> getDyeableItemHashmap() {
        HashMap<TagKey<Item>, HashMap<String, Item>> dyedItems = new HashMap<>();

        HashMap<String, Item> entry;
        dyedItems.put(ItemTags.WOOL, new HashMap<>());
        
        entry = dyedItems.get(ItemTags.WOOL);
        entry.put("white", Items.WHITE_WOOL);
        entry.put("light_gray", Items.LIGHT_GRAY_WOOL);
        entry.put("gray", Items.GRAY_WOOL);
        entry.put("black", Items.BLACK_WOOL);
        entry.put("brown", Items.BROWN_WOOL);
        entry.put("red", Items.RED_WOOL);
        entry.put("orange", Items.ORANGE_WOOL);
        entry.put("yellow", Items.YELLOW_WOOL);
        entry.put("lime", Items.LIME_WOOL);
        entry.put("green", Items.GREEN_WOOL);
        entry.put("cyan", Items.CYAN_WOOL);
        entry.put("light_blue", Items.LIGHT_BLUE_WOOL);
        entry.put("blue", Items.BLUE_WOOL);
        entry.put("purple", Items.PURPLE_WOOL);
        entry.put("magenta", Items.MAGENTA_WOOL);
        entry.put("pink", Items.PINK_WOOL);

        dyedItems.put(ItemTags.WOOL_CARPETS, new HashMap<>());
        entry = dyedItems.get(ItemTags.WOOL_CARPETS);
        entry.put("white", Items.WHITE_CARPET);
        entry.put("light_gray", Items.LIGHT_GRAY_CARPET);
        entry.put("gray", Items.GRAY_CARPET);
        entry.put("black", Items.BLACK_CARPET);
        entry.put("brown", Items.BROWN_CARPET);
        entry.put("red", Items.RED_CARPET);
        entry.put("orange", Items.ORANGE_CARPET);
        entry.put("yellow", Items.YELLOW_CARPET);
        entry.put("lime", Items.LIME_CARPET);
        entry.put("green", Items.GREEN_CARPET);
        entry.put("cyan", Items.CYAN_CARPET);
        entry.put("light_blue", Items.LIGHT_BLUE_CARPET);
        entry.put("blue", Items.BLUE_CARPET);
        entry.put("purple", Items.PURPLE_CARPET);
        entry.put("magenta", Items.MAGENTA_CARPET);
        entry.put("pink", Items.PINK_CARPET);

        dyedItems.put(ItemTags.BANNERS, new HashMap<>());
        entry = dyedItems.get(ItemTags.BANNERS);
        entry.put("white", Items.WHITE_BANNER);
        entry.put("light_gray", Items.LIGHT_GRAY_BANNER);
        entry.put("gray", Items.GRAY_BANNER);
        entry.put("black", Items.BLACK_BANNER);
        entry.put("brown", Items.BROWN_BANNER);
        entry.put("red", Items.RED_BANNER);
        entry.put("orange", Items.ORANGE_BANNER);
        entry.put("yellow", Items.YELLOW_BANNER);
        entry.put("lime", Items.LIME_BANNER);
        entry.put("green", Items.GREEN_BANNER);
        entry.put("cyan", Items.CYAN_BANNER);
        entry.put("light_blue", Items.LIGHT_BLUE_BANNER);
        entry.put("blue", Items.BLUE_BANNER);
        entry.put("purple", Items.PURPLE_BANNER);
        entry.put("magenta", Items.MAGENTA_BANNER);
        entry.put("pink", Items.PINK_BANNER);

        dyedItems.put(ItemTags.TERRACOTTA, new HashMap<>());
        entry = dyedItems.get(ItemTags.TERRACOTTA);
        entry.put("white", Items.WHITE_TERRACOTTA);
        entry.put("light_gray", Items.LIGHT_GRAY_TERRACOTTA);
        entry.put("gray", Items.GRAY_TERRACOTTA);
        entry.put("black", Items.BLACK_TERRACOTTA);
        entry.put("brown", Items.BROWN_TERRACOTTA);
        entry.put("red", Items.RED_TERRACOTTA);
        entry.put("orange", Items.ORANGE_TERRACOTTA);
        entry.put("yellow", Items.YELLOW_TERRACOTTA);
        entry.put("lime", Items.LIME_TERRACOTTA);
        entry.put("green", Items.GREEN_TERRACOTTA);
        entry.put("cyan", Items.CYAN_TERRACOTTA);
        entry.put("light_blue", Items.LIGHT_BLUE_TERRACOTTA);
        entry.put("blue", Items.BLUE_TERRACOTTA);
        entry.put("purple", Items.PURPLE_TERRACOTTA);
        entry.put("magenta", Items.MAGENTA_TERRACOTTA);
        entry.put("pink", Items.PINK_TERRACOTTA);

        dyedItems.put(ItemTags.BEDS, new HashMap<>());
        entry = dyedItems.get(ItemTags.BEDS);
        entry.put("white", Items.WHITE_BED);
        entry.put("light_gray", Items.LIGHT_GRAY_BED);
        entry.put("gray", Items.GRAY_BED);
        entry.put("black", Items.BLACK_BED);
        entry.put("brown", Items.BROWN_BED);
        entry.put("red", Items.RED_BED);
        entry.put("orange", Items.ORANGE_BED);
        entry.put("yellow", Items.YELLOW_BED);
        entry.put("lime", Items.LIME_BED);
        entry.put("green", Items.GREEN_BED);
        entry.put("cyan", Items.CYAN_BED);
        entry.put("light_blue", Items.LIGHT_BLUE_BED);
        entry.put("blue", Items.BLUE_BED);
        entry.put("purple", Items.PURPLE_BED);
        entry.put("magenta", Items.MAGENTA_BED);
        entry.put("pink", Items.PINK_BED);

        dyedItems.put(ConventionalItemTags.CONCRETE_POWDERS, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.CONCRETE_POWDERS);
        entry.put("white", Items.WHITE_CONCRETE_POWDER);
        entry.put("light_gray", Items.LIGHT_GRAY_CONCRETE_POWDER);
        entry.put("gray", Items.GRAY_CONCRETE_POWDER);
        entry.put("black", Items.BLACK_CONCRETE_POWDER);
        entry.put("brown", Items.BROWN_CONCRETE_POWDER);
        entry.put("red", Items.RED_CONCRETE_POWDER);
        entry.put("orange", Items.ORANGE_CONCRETE_POWDER);
        entry.put("yellow", Items.YELLOW_CONCRETE_POWDER);
        entry.put("lime", Items.LIME_CONCRETE_POWDER);
        entry.put("green", Items.GREEN_CONCRETE_POWDER);
        entry.put("cyan", Items.CYAN_CONCRETE_POWDER);
        entry.put("light_blue", Items.LIGHT_BLUE_CONCRETE_POWDER);
        entry.put("blue", Items.BLUE_CONCRETE_POWDER);
        entry.put("purple", Items.PURPLE_CONCRETE_POWDER);
        entry.put("magenta", Items.MAGENTA_CONCRETE_POWDER);
        entry.put("pink", Items.PINK_CONCRETE_POWDER);

        dyedItems.put(ConventionalItemTags.CONCRETE, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.CONCRETE);
        entry.put("white", Items.WHITE_CONCRETE);
        entry.put("light_gray", Items.LIGHT_GRAY_CONCRETE);
        entry.put("gray", Items.GRAY_CONCRETE);
        entry.put("black", Items.BLACK_CONCRETE);
        entry.put("brown", Items.BROWN_CONCRETE);
        entry.put("red", Items.RED_CONCRETE);
        entry.put("orange", Items.ORANGE_CONCRETE);
        entry.put("yellow", Items.YELLOW_CONCRETE);
        entry.put("lime", Items.LIME_CONCRETE);
        entry.put("green", Items.GREEN_CONCRETE);
        entry.put("cyan", Items.CYAN_CONCRETE);
        entry.put("light_blue", Items.LIGHT_BLUE_CONCRETE);
        entry.put("blue", Items.BLUE_CONCRETE);
        entry.put("purple", Items.PURPLE_CONCRETE);
        entry.put("magenta", Items.MAGENTA_CONCRETE);
        entry.put("pink", Items.PINK_CONCRETE);

        dyedItems.put(ConventionalItemTags.GLASS_BLOCKS, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.GLASS_BLOCKS);
        entry.put("white", Items.WHITE_STAINED_GLASS);
        entry.put("light_gray", Items.LIGHT_GRAY_STAINED_GLASS);
        entry.put("gray", Items.GRAY_STAINED_GLASS);
        entry.put("black", Items.BLACK_STAINED_GLASS);
        entry.put("brown", Items.BROWN_STAINED_GLASS);
        entry.put("red", Items.RED_STAINED_GLASS);
        entry.put("orange", Items.ORANGE_STAINED_GLASS);
        entry.put("yellow", Items.YELLOW_STAINED_GLASS);
        entry.put("lime", Items.LIME_STAINED_GLASS);
        entry.put("green", Items.GREEN_STAINED_GLASS);
        entry.put("cyan", Items.CYAN_STAINED_GLASS);
        entry.put("light_blue", Items.LIGHT_BLUE_STAINED_GLASS);
        entry.put("blue", Items.BLUE_STAINED_GLASS);
        entry.put("purple", Items.PURPLE_STAINED_GLASS);
        entry.put("magenta", Items.MAGENTA_STAINED_GLASS);
        entry.put("pink", Items.PINK_STAINED_GLASS);

        dyedItems.put(ConventionalItemTags.GLASS_PANES, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.GLASS_PANES);
        entry.put("white", Items.WHITE_STAINED_GLASS_PANE);
        entry.put("light_gray", Items.LIGHT_GRAY_STAINED_GLASS_PANE);
        entry.put("gray", Items.GRAY_STAINED_GLASS_PANE);
        entry.put("black", Items.BLACK_STAINED_GLASS_PANE);
        entry.put("brown", Items.BROWN_STAINED_GLASS_PANE);
        entry.put("red", Items.RED_STAINED_GLASS_PANE);
        entry.put("orange", Items.ORANGE_STAINED_GLASS_PANE);
        entry.put("yellow", Items.YELLOW_STAINED_GLASS_PANE);
        entry.put("lime", Items.LIME_STAINED_GLASS_PANE);
        entry.put("green", Items.GREEN_STAINED_GLASS_PANE);
        entry.put("cyan", Items.CYAN_STAINED_GLASS_PANE);
        entry.put("light_blue", Items.LIGHT_BLUE_STAINED_GLASS_PANE);
        entry.put("blue", Items.BLUE_STAINED_GLASS_PANE);
        entry.put("purple", Items.PURPLE_STAINED_GLASS_PANE);
        entry.put("magenta", Items.MAGENTA_STAINED_GLASS_PANE);
        entry.put("pink", Items.PINK_STAINED_GLASS_PANE);

        dyedItems.put(ConventionalItemTags.SHULKER_BOXES, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.SHULKER_BOXES);
        entry.put("white", Items.WHITE_SHULKER_BOX);
        entry.put("light_gray", Items.LIGHT_GRAY_SHULKER_BOX);
        entry.put("gray", Items.GRAY_SHULKER_BOX);
        entry.put("black", Items.BLACK_SHULKER_BOX);
        entry.put("brown", Items.BROWN_SHULKER_BOX);
        entry.put("red", Items.RED_SHULKER_BOX);
        entry.put("orange", Items.ORANGE_SHULKER_BOX);
        entry.put("yellow", Items.YELLOW_SHULKER_BOX);
        entry.put("lime", Items.LIME_SHULKER_BOX);
        entry.put("green", Items.GREEN_SHULKER_BOX);
        entry.put("cyan", Items.CYAN_SHULKER_BOX);
        entry.put("light_blue", Items.LIGHT_BLUE_SHULKER_BOX);
        entry.put("blue", Items.BLUE_SHULKER_BOX);
        entry.put("purple", Items.PURPLE_SHULKER_BOX);
        entry.put("magenta", Items.MAGENTA_SHULKER_BOX);
        entry.put("pink", Items.PINK_SHULKER_BOX);

        dyedItems.put(ConventionalItemTags.GLAZED_TERRACOTTAS, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.GLAZED_TERRACOTTAS);
        entry.put("white", Items.WHITE_GLAZED_TERRACOTTA);
        entry.put("light_gray", Items.LIGHT_GRAY_GLAZED_TERRACOTTA);
        entry.put("gray", Items.GRAY_GLAZED_TERRACOTTA);
        entry.put("black", Items.BLACK_GLAZED_TERRACOTTA);
        entry.put("brown", Items.BROWN_GLAZED_TERRACOTTA);
        entry.put("red", Items.RED_GLAZED_TERRACOTTA);
        entry.put("orange", Items.ORANGE_GLAZED_TERRACOTTA);
        entry.put("yellow", Items.YELLOW_GLAZED_TERRACOTTA);
        entry.put("lime", Items.LIME_GLAZED_TERRACOTTA);
        entry.put("green", Items.GREEN_GLAZED_TERRACOTTA);
        entry.put("cyan", Items.CYAN_GLAZED_TERRACOTTA);
        entry.put("light_blue", Items.LIGHT_BLUE_GLAZED_TERRACOTTA);
        entry.put("blue", Items.BLUE_GLAZED_TERRACOTTA);
        entry.put("purple", Items.PURPLE_GLAZED_TERRACOTTA);
        entry.put("magenta", Items.MAGENTA_GLAZED_TERRACOTTA);
        entry.put("pink", Items.PINK_GLAZED_TERRACOTTA);

        dyedItems.put(ItemTags.CANDLES, new HashMap<>());
        entry = dyedItems.get(ItemTags.CANDLES);
        entry.put("white", Items.WHITE_CANDLE);
        entry.put("light_gray", Items.LIGHT_GRAY_CANDLE);
        entry.put("gray", Items.GRAY_CANDLE);
        entry.put("black", Items.BLACK_CANDLE);
        entry.put("brown", Items.BROWN_CANDLE);
        entry.put("red", Items.RED_CANDLE);
        entry.put("orange", Items.ORANGE_CANDLE);
        entry.put("yellow", Items.YELLOW_CANDLE);
        entry.put("lime", Items.LIME_CANDLE);
        entry.put("green", Items.GREEN_CANDLE);
        entry.put("cyan", Items.CYAN_CANDLE);
        entry.put("light_blue", Items.LIGHT_BLUE_CANDLE);
        entry.put("blue", Items.BLUE_CANDLE);
        entry.put("purple", Items.PURPLE_CANDLE);
        entry.put("magenta", Items.MAGENTA_CANDLE);
        entry.put("pink", Items.PINK_CANDLE);

        return dyedItems;
    }

    public MoobloomVariant getVariant() {
        return MoobloomVariant.fromName((String)this.dataTracker.get(TYPE));
    }

    public void setVariant(MoobloomVariant variant) {
        this.dataTracker.set(TYPE, variant.name);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        setVariant(MoobloomVariant.fromName(findVariant()));
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    private String findVariant() {
        World world = getWorld();
        RegistryEntry<Biome> biome = world.getBiome(getBlockPos());

        ArrayList<String> colors = new ArrayList<>();
        
        if (biome.isIn(ModEntitySpawn.WHITE_MOOBLOOM_SPAWNABLE)) colors.add("white");
        if (biome.isIn(ModEntitySpawn.LIGHT_GRAY_MOOBLOOM_SPAWNABLE)) colors.add("light_gray");
        if (biome.isIn(ModEntitySpawn.GRAY_MOOBLOOM_SPAWNABLE)) colors.add("gray");
        if (biome.isIn(ModEntitySpawn.BLACK_MOOBLOOM_SPAWNABLE)) colors.add("black");
        if (biome.isIn(ModEntitySpawn.BROWN_MOOBLOOM_SPAWNABLE)) colors.add("brown");
        if (biome.isIn(ModEntitySpawn.RED_MOOBLOOM_SPAWNABLE)) colors.add("red");
        if (biome.isIn(ModEntitySpawn.ORANGE_MOOBLOOM_SPAWNABLE)) colors.add("orange");
        if (biome.isIn(ModEntitySpawn.YELLOW_MOOBLOOM_SPAWNABLE)) colors.add("yellow");
        if (biome.isIn(ModEntitySpawn.LIME_MOOBLOOM_SPAWNABLE)) colors.add("lime");
        if (biome.isIn(ModEntitySpawn.GREEN_MOOBLOOM_SPAWNABLE)) colors.add("green");
        if (biome.isIn(ModEntitySpawn.CYAN_MOOBLOOM_SPAWNABLE)) colors.add("cyan");
        if (biome.isIn(ModEntitySpawn.LIGHT_BLUE_MOOBLOOM_SPAWNABLE)) colors.add("light_blue");
        if (biome.isIn(ModEntitySpawn.BLUE_MOOBLOOM_SPAWNABLE)) colors.add("blue");
        if (biome.isIn(ModEntitySpawn.PURPLE_MOOBLOOM_SPAWNABLE)) colors.add("purple");
        if (biome.isIn(ModEntitySpawn.MAGENTA_MOOBLOOM_SPAWNABLE)) colors.add("magenta");
        if (biome.isIn(ModEntitySpawn.PINK_MOOBLOOM_SPAWNABLE)) colors.add("pink");

        if (colors.isEmpty()) colors.add("yellow");

        return colors.get(this.random.nextInt(colors.size()));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TYPE, MoobloomVariant.YELLOW.name);
        builder.add(IS_SHEARED, false);
        builder.add(REGROW_TIMER, 0);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Type", this.getVariant().asString());
        nbt.putBoolean("IsSheared", this.isSheared());
        nbt.putInt("RegrowTimer", this.getRegrowTimer());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(MoobloomVariant.fromName(nbt.getString("Type")));
        this.setSheared(nbt.getBoolean("IsSheared"));
        this.setRegrowTimer(nbt.getInt("RegrowTimer"));
    }

    public boolean isSheared() {
        return this.dataTracker.get(IS_SHEARED);
    }

    public void setSheared(boolean val) {
        this.dataTracker.set(IS_SHEARED, val);
    }

    public int getRegrowTimer() {
       return this.dataTracker.get(REGROW_TIMER);
    }

    public void setRegrowTimer(int val) {
        this.dataTracker.set(REGROW_TIMER, val);
    }

    static {
        TYPE = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.STRING);
        IS_SHEARED = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        REGROW_TIMER = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        this.getWorld().playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        if (!this.getWorld().isClient()) {
            for(int i = 0; i < 5; ++i) {
                this.getWorld().spawnEntity(
                        new ItemEntity(
                                this.getWorld(),
                                this.getX(),
                                this.getBodyY(1.0),
                                this.getZ(),
                                new ItemStack(this.getVariant().shearedItem)));
            }
            this.setSheared(true);
            this.setRegrowTimer(24000);
        }
    }

    @Override
    public void tick() {
        super.tick();
        flowerRegrowthTick();

        if (this.getVariant() == MoobloomVariant.BLACK &&
                this.hasStatusEffect(StatusEffects.WITHER))
            this.removeStatusEffect(StatusEffects.WITHER);
    }

    private void flowerRegrowthTick() {
        int regrowTimer = this.getRegrowTimer();
        if (regrowTimer > 0) {
            regrowTimer--;
            if (regrowTimer == 0) setSheared(false);
        }
        this.setRegrowTimer(regrowTimer);
    }

    @Override
    public boolean isShearable() {
        return !this.isSheared() && this.isAlive() && !this.isBaby();
    }

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        UUID uUID = lightning.getUuid();
        if (!uUID.equals(this.lightningUUID)) {
            this.setVariant(MoobloomVariant.WHITE);
            this.lightningUUID = uUID;
            this.playSound(SoundEvents.ENTITY_MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        if (this.hasStatusEffect(StatusEffects.WITHER)) {
            MoobloomEntity child = ModEntities.MOOBLOOM.create(this.getWorld());
            if (child != null) {
                ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.WITCH, this.getX(), this.getBodyY(0.5), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
                this.discard();
                child.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
                child.bodyYaw = this.bodyYaw;
                child.setVariant(MoobloomVariant.BLACK);
                child.setBaby(true);
                if (this.hasCustomName()) {
                    child.setCustomName(this.getCustomName());
                    child.setCustomNameVisible(this.isCustomNameVisible());
                }

                if (this.isPersistent()) {
                    child.setPersistent();
                }

                this.getWorld().spawnEntity(child);
                child.getWorld().playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_MOOSHROOM_CONVERT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            }
        }
    }

    @Override
    public void move(MovementType movementType, Vec3d movement) {
        World world = getWorld();

        List<BeeEntity> possibleEntities =
                world.getEntitiesByClass(BeeEntity.class, Box.of(this.getPos(), 5, 5, 5), EntityPredicates.VALID_ENTITY);
        BeeEntity closestEntity =
                world.getClosestEntity(
                        possibleEntities,
                        TargetPredicate.DEFAULT,
                        this,
                        this.getX(), this.getY(), this.getZ());

        if (closestEntity != null && this.distanceTo(closestEntity) <= 2)
            super.move(movementType, movement.multiply(0, 1, 0));
        else super.move(movementType, movement);
    }

    public enum MoobloomVariant implements StringIdentifiable {
        WHITE("white", ModBlocks.THUNDERBLOOM.getDefaultState(), Items.WHITE_DYE),
        LIGHT_GRAY("light_gray", ModBlocks.BELLFLOWER.getDefaultState(), Items.LIGHT_GRAY_DYE),
        GRAY("gray", ModBlocks.BUTTERCUP.getDefaultState(), Items.GRAY_DYE, Items.GRAY_DYE),
        BLACK("black", ModBlocks.WITHERED_BUTTERCUP.getDefaultState(), Items.BLACK_DYE),
        BROWN("brown", ModBlocks.BUTTERCUP.getDefaultState(), Items.BROWN_DYE, Items.BROWN_DYE),
        RED("red", ModBlocks.TRILLIUM.getDefaultState(), Items.RED_DYE),
        ORANGE("orange", Blocks.ORANGE_TULIP.getDefaultState(), Items.ORANGE_DYE),
        YELLOW("yellow", ModBlocks.BUTTERCUP.getDefaultState(), Items.YELLOW_DYE),
        LIME("lime", ModBlocks.BUTTERCUP.getDefaultState(), Items.LIME_DYE, Items.LIME_DYE),
        GREEN("green", ModBlocks.GLADIOLI.getDefaultState(), Items.GREEN_DYE),
        CYAN("cyan", ModBlocks.CENTIAN.getDefaultState(), Items.CYAN_DYE),
        LIGHT_BLUE("light_blue", ModBlocks.MYOSOTIS.getDefaultState(), Items.LIGHT_BLUE_DYE),
        BLUE("blue", ModBlocks.DAYFLOWER.getDefaultState(), Items.BLUE_DYE),
        PURPLE("purple", ModBlocks.BUTTERCUP.getDefaultState(), Items.PURPLE_DYE, Items.PURPLE_DYE),
        MAGENTA("magenta", Blocks.ALLIUM.getDefaultState(), Items.MAGENTA_DYE),
        PINK("pink", ModBlocks.HIBISCUS.getDefaultState(), Items.PINK_DYE);

        public static final EnumCodec<MoobloomVariant> CODEC = StringIdentifiable.createCodec(MoobloomVariant::values);
        final String name;
        final BlockState flower;
        final Item shearedItem;
        final Item dye;

        private MoobloomVariant(final String name, final BlockState flower, Item dye) {
            this.name = name;
            this.flower = flower;
            this.dye = dye;
            this.shearedItem = flower.getBlock().asItem();
        }

        private MoobloomVariant(final String name, final BlockState flower, Item dye,Item shearedItem) {
            this.name = name;
            this.flower = flower;
            this.dye = dye;
            this.shearedItem = shearedItem;
        }

        public BlockState getFlowerState() {
            return this.flower;
        }

        public String asString() {
            return this.name;
        }

        static MoobloomVariant fromName(String name) {
            return (MoobloomVariant)CODEC.byId(name, YELLOW);
        }
    }
}
