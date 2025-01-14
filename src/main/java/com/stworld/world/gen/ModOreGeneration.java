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
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.OBSIDIAN_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.END_STONE_PLACED_KEY);

    }
}

// Example for individual Bioms
// BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.PLAINS),
// GenerationStep.Feature.UNDERGROUND_ORES,
//         ModPlacedFeatures.PINK_GARNET_ORE_PLACED_KEY);

