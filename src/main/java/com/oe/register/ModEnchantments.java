package com.oe.register;

import com.oe.OEmod;
import com.oe.enchant.JumpForeverEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {

// 禅者行跳
    public static final Enchantment JUMP_FOREVER = new JumpForeverEnchantment();

    public static void register() {
        Registry.register(
            Registries.ENCHANTMENT,
            OEmod.id("jump_forever"),
            JUMP_FOREVER
        );
    }
}
