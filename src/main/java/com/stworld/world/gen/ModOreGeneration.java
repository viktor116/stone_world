package com.stworld.world.gen;

import com.stworld.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

/**
 * @author soybean
 * @date 2025/1/14 13:19
 * @description
 */

public class ModOreGeneration {
    public static void generateOres() {
        // 灵魂沙地表覆盖
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                ModPlacedFeatures.SOUL_SAND_SURFACE_PLACED_KEY);

        // 凋零玫瑰
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.WITHER_ROSE_PLACED_KEY);

    }
}

// Example for individual Bioms
// BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.PLAINS),
// GenerationStep.Feature.UNDERGROUND_ORES,
//         ModPlacedFeatures.PINK_GARNET_ORE_PLACED_KEY);

