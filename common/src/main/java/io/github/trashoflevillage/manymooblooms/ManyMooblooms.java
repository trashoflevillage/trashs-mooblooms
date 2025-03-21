package io.github.trashoflevillage.manymooblooms;

import io.github.trashoflevillage.manymooblooms.blocks.ModBlocks;

public final class ManyMooblooms {
    public static final String MOD_ID = "manymooblooms";
    public static final String OLD_MOD_ID = "trashs_mooblooms";

    public static void init() {
        ModBlocks.registerAll();
    }
}
