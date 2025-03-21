package io.github.trashoflevillage.manymooblooms.fabric;

import net.fabricmc.api.ModInitializer;

import io.github.trashoflevillage.manymooblooms.ManyMooblooms;

public final class ManyMoobloomsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        ManyMooblooms.init();
    }
}
