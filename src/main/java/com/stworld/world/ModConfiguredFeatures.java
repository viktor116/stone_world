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
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.List;

/**
 * @author soybean
 * @date 2025/1/14 11:41
 * @description
 */
public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> SOUL_SAND_SURFACE_KEY = registerKey("soul_sand_surface");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WITHER_ROSE_KEY = registerKey("wither_rose");

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

        // 地表灵魂沙：替换草方块和沙子
        List<OreFeatureConfig.Target> soulSandTargets = List.of(
                OreFeatureConfig.createTarget(grassBlockReplaceables, Blocks.SOUL_SAND.getDefaultState()),
                OreFeatureConfig.createTarget(sandBlockReplaceables, Blocks.SOUL_SAND.getDefaultState())
        );
        register(context, SOUL_SAND_SURFACE_KEY, Feature.ORE, new OreFeatureConfig(soulSandTargets, 64));

        // 凋零玫瑰
        register(context, WITHER_ROSE_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        SimpleBlockStateProvider.of(Blocks.WITHER_ROSE.getDefaultState())
                )
        );

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(InitValue.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
