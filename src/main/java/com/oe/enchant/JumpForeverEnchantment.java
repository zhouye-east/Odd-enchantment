package com.oe.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class JumpForeverEnchantment extends Enchantment {

    public JumpForeverEnchantment() {
        super(
            Rarity.RARE,
            EnchantmentTarget.ARMOR_FEET,
            new EquipmentSlot[]{EquipmentSlot.FEET}
        );
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }


    

    /**
     * 禁止村民交易附魔书
     */
    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    /**
     * 禁止战利品、钓鱼、生物掉落等随机获取
     */
    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }
}