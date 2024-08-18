package net.sweenus.simplyskills.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.puffish.attributesmod.AttributesMod;
import net.sweenus.simplyskills.registry.EffectRegistry;
import net.sweenus.simplyskills.util.HelperMethods;

public class ExhaustionEffect extends StatusEffect {
    public ExhaustionEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }


    @Override
    public void applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.getWorld().isClient()) {
            if (livingEntity instanceof PlayerEntity player) {
                int amount = (int) (1 + player.getAttributeValue(AttributesMod.STAMINA));
                int frequency = 20;
                if (player.age % frequency == 0) {
                    HelperMethods.decrementStatusEffects(player, EffectRegistry.EXHAUSTION, amount);
                }
            }
        }
        super.applyUpdateEffect(livingEntity, amplifier);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
