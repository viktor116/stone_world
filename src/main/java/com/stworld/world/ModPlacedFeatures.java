package com.stworld.world;

import com.stworld.config.InitValue;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

/**
 * @author soybean
 * @date 2025/1/14 11:41
 * @description
 */
public class ModPlacedFeatures {


    public static final RegistryKey<PlacedFeature> SOUL_SAND_SURFACE_PLACED_KEY = registerKey("soul_sand_surface_placed");
    public static final RegistryKey<PlacedFeature> WITHER_ROSE_PLACED_KEY = registerKey("wither_rose_placed");
    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        // 灵魂沙：贴近地表大量生成
        register(context, SOUL_SAND_SURFACE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SOUL_SAND_SURFACE_KEY),
                ModOrePlacement.modifiersWithCount(80,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(60), YOffset.fixed(320))));

        // 换成这段
        register(context, WITHER_ROSE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.WITHER_ROSE_KEY),
                ModOrePlacement.modifiersWithCount(16,               // 每个chunk 16次尝试
                        PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP));    // 贴地表顶部
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(InitValue.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
