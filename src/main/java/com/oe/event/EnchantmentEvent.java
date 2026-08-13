package com.oe.event;

import com.oe.OEmod;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantmentEvent {
    private static final Identifier JUMP_ID = OEmod.id("jump_forever");
    private static final Logger LOGGER = LoggerFactory.getLogger(OEmod.commonid());

    public static void register(){
        ServerTickEvents.END_SERVER_TICK.register(EnchantmentEvent::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        server.getPlayerManager().getPlayerList().forEach((ServerPlayerEntity player)->{
            if (player.hasVehicle()) {
                if (OEmod.test()) LOGGER.info("[test]player.hasVehicle is true");
                return;
            }
            if (player.getAbilities().flying) {
                if (OEmod.test()) LOGGER.info("[test]player.getAbilities is true");
                return;
            }
            if (player.hasStatusEffect(StatusEffects.SLOW_FALLING)) {
                if (OEmod.test()) LOGGER.info("[test]player.hasStatusEffect is true");
                return;
            }

            var boots = player.getEquippedStack(EquipmentSlot.FEET);
            if (boots.isEmpty()) {
                if (OEmod.test()) LOGGER.info("[test]boots.isEmpty is true");
                return;
            }

            Enchantment jumpEnchant = Registries.ENCHANTMENT.get(JUMP_ID);
            if (jumpEnchant == null) {
                LOGGER.warn("Jump enchantment not found!");
                return;
            }

            int level = EnchantmentHelper.getLevel(jumpEnchant, boots);
            if (level <= 0) {
                if (OEmod.test()) LOGGER.info("[test]level less than 1");
                return;
            }

            if (player.isOnGround()) {
                if (OEmod.test()) LOGGER.info("[test]isOnGround is true");
                LOGGER.info("[test]player jump quest start");
                // 替换无效的 player.jump()
                Vec3d vel = player.getVelocity();
                player.setVelocity(vel.x, 0.42, vel.z);
                player.velocityModified = true;
            }
        });
    }
}
