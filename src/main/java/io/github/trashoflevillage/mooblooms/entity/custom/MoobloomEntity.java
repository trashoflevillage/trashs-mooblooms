package io.github.trashoflevillage.mooblooms.entity.custom;

import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

public class MoobloomEntity extends CowEntity {
    private static final TrackedData<String> TYPE;
    private static final float SPAWN_CHANCE = 0.1f;

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

    public static boolean canSpawn(EntityType<MooshroomEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return random.nextFloat() < SPAWN_CHANCE && world.getBlockState(pos.down()).isIn(BlockTags.ANIMALS_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }

    private MoobloomVariant chooseBabyType(MoobloomEntity moobloom) {
        MoobloomVariant[] types = new MoobloomVariant[] {
                this.getVariant(),
                moobloom.getVariant()
        };
        return types[this.getWorld().random.nextInt(types.length)];
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

        String[] defaultColors = new String[] {
                "yellow",
                "red"
        };
        return defaultColors[world.random.nextInt(defaultColors.length)];
    }

    private boolean biomeEquals(RegistryEntry<Biome> biome, RegistryKey<Biome> biome2) {
        return biome.value().equals(getWorld().getRegistryManager().get(RegistryKeys.BIOME).get(biome2));

    }

    private boolean biomeHasTag(RegistryEntry<Biome> biome, TagKey<Biome> tag) {
        return biome.isIn(tag);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TYPE, MoobloomVariant.YELLOW.name);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Type", this.getVariant().asString());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(MoobloomEntity.MoobloomVariant.fromName(nbt.getString("Type")));
    }

    static {
        TYPE = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.STRING);
    }

    public enum MoobloomVariant implements StringIdentifiable {
        YELLOW("yellow", Blocks.DANDELION.getDefaultState()),
        RED("red", Blocks.POPPY.getDefaultState()),
        PINK("pink", Blocks.PINK_TULIP.getDefaultState()),
        BLUE("blue", Blocks.CORNFLOWER.getDefaultState());

        public static final StringIdentifiable.EnumCodec<MoobloomEntity.MoobloomVariant> CODEC = StringIdentifiable.createCodec(MoobloomEntity.MoobloomVariant::values);
        final String name;
        final BlockState flower;

        private MoobloomVariant(final String name, final BlockState mushroom) {
            this.name = name;
            this.flower = mushroom;
        }

        public BlockState getFlowerState() {
            return this.flower;
        }

        public String asString() {
            return this.name;
        }

        static MoobloomEntity.MoobloomVariant fromName(String name) {
            return (MoobloomEntity.MoobloomVariant)CODEC.byId(name, YELLOW);
        }
    }
}
