package com.stworld.event;

import com.stworld.world.WorldRegister;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * @author soybean
 * @date 2025/1/15 10:35
 * @description
 */
public class EventRegister {
    public static void initialize() {
//        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
//            ServerPlayerEntity player = handler.getPlayer();
//            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(2.0);  // 设置最大生命值为2血（1颗心）
//            player.setHealth(2.0f); // 设置当前生命值为2血（1颗心）
//        });
    }
}
