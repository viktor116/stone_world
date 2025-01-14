package com.stworld.world;

import com.stworld.config.InitValue;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

/**
 * @author soybean
 * @date 2025/1/14 11:41
 * @description
 */
public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> OBSIDIAN_KEY = registerKey("obsidian");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_STONE_KEY = registerKey("end_stone");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);
        RuleTest grassBlockReplaceables = new BlockMatchRuleTest(Blocks.GRASS_BLOCK);
        RuleTest sandBlockReplaceables = new BlockMatchRuleTest(Blocks.SAND);

        // 创建混合生成目标 - 每个配置都包含多个目标
        List<OreFeatureConfig.Target> mixedObsidianTargets = List.of(
                OreFeatureConfig.createTarget(grassBlockReplaceables, Blocks.OBSIDIAN.getDefaultState()),
                OreFeatureConfig.createTarget(sandBlockReplaceables, Blocks.OBSIDIAN.getDefaultState()),
                OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.OBSIDIAN), Blocks.END_STONE.getDefaultState())
        );

        List<OreFeatureConfig.Target> mixedEndStoneTargets = List.of(
                OreFeatureConfig.createTarget(grassBlockReplaceables, Blocks.END_STONE.getDefaultState()),
                OreFeatureConfig.createTarget(sandBlockReplaceables, Blocks.END_STONE.getDefaultState()),
                OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE), Blocks.OBSIDIAN.getDefaultState())
        );

        // 使用较小的矿脉大小和更分散的生成
        register(context, OBSIDIAN_KEY, Feature.ORE, new OreFeatureConfig(mixedObsidianTargets, 50));
        register(context, END_STONE_KEY, Feature.ORE, new OreFeatureConfig(mixedEndStoneTargets, 50));

//        List<OreFeatureConfig.Target> obsidianPlaceGrass = List.of(OreFeatureConfig.createTarget(grassBlockReplaceables, Blocks.OBSIDIAN.getDefaultState()));
//        List<OreFeatureConfig.Target> endStonePlaceGrass = List.of(OreFeatureConfig.createTarget(grassBlockReplaceables, Blocks.END_STONE.getDefaultState()));
//
//        register(context, OBSIDIAN_KEY, Feature.ORE, new OreFeatureConfig(obsidianPlaceGrass, 30));
//        register(context, END_STONE_KEY, Feature.ORE, new OreFeatureConfig(endStonePlaceGrass, 30));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(InitValue.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
