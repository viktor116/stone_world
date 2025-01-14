package com.stworld.world;

import com.stworld.config.InitValue;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

/**
 * @author soybean
 * @date 2025/1/14 11:41
 * @description
 */
public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> OBSIDIAN_PLACED_KEY = registerKey("obsidian_placed");
    public static final RegistryKey<PlacedFeature> END_STONE_PLACED_KEY = registerKey("end_stone_placed");
    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, OBSIDIAN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OBSIDIAN_KEY),
                ModOrePlacement.modifiersWithCount(50,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-50), YOffset.fixed(200))));

        register(context, END_STONE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_STONE_KEY),
                ModOrePlacement.modifiersWithCount(50,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-50), YOffset.fixed(200))));
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
