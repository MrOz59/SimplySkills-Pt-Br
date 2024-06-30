package net.sweenus.simplyskills.abilities.compat;

import immersive_melodies.Items;
import immersive_melodies.item.InstrumentItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.sweenus.simplyskills.util.HelperMethods;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProminenceInternalAbilities {

    public static void bardAbility(PlayerEntity player) {
        // If skill unlocked - check modloaded before entering
        ItemStack stack = player.getMainHandStack();
        Item item = stack.getItem();
        int radius = 4;
        int frequency = 40;
        int duration = frequency + 20;

        if (item instanceof InstrumentItem instrument) {

            if (!instrument.isPlaying(stack))
                return;

            if (stack.isOf(Items.BAGPIPE.get())) {
                giveAreaBuffs(player, radius, frequency, duration, StatusEffects.STRENGTH, 2, StatusEffects.REGENERATION, 0, null, 0, null, 0);
            } else if (stack.isOf(Items.FLUTE.get())) {
                giveAreaBuffs(player, radius, frequency, duration, StatusEffects.SPEED, 2, StatusEffects.DOLPHINS_GRACE, 0, null, 0, null, 0);
            } else if (stack.isOf(Items.DIDGERIDOO.get())) {
            giveAreaBuffs(player, radius, frequency, duration, StatusEffects.RESISTANCE, 1, StatusEffects.STRENGTH, 0, null, 0, null, 0);
            } else if (stack.isOf(Items.LUTE.get())) {
                giveAreaBuffs(player, radius, frequency, duration, StatusEffects.REGENERATION, 2, StatusEffects.ABSORPTION, 0, null, 0, null, 0);
            } else if (stack.isOf(Items.PIANO.get())) {
                giveAreaBuffs(player, radius, frequency, duration, StatusEffects.HASTE, 2, StatusEffects.SPEED, 0, null, 0, null, 0);
            } else if (stack.isOf(Items.TRIANGLE.get())) {
                giveAreaBuffs(player, radius, frequency, duration, StatusEffects.SLOW_FALLING, 0, StatusEffects.FIRE_RESISTANCE, 0, null, 0, null, 0);
            } else if (stack.isOf(Items.TRUMPET.get())) {
                giveAreaBuffs(player, radius, frequency, duration, StatusEffects.HASTE, 2, StatusEffects.STRENGTH, 0, null, 0, null, 0);
            }

        }
    }

    public static void giveAreaBuffs(
            PlayerEntity player,
            int radius,
            int tickFrequency,
            int buffDuration,
            StatusEffect buffOne,
            int buffOneAmp,
            StatusEffect buffTwo,
            int buffTwoAmp,
            @Nullable StatusEffect debuffOne,
            int debuffOneAmp,
            @Nullable StatusEffect debuffTwo,
            int debuffTwoAmp) {

        if (player.age % tickFrequency != 0) {
            return;
        }

        Box box = HelperMethods.createBox(player, radius);

        List<Entity> entities = player.getWorld().getOtherEntities(null, box, e -> e instanceof LivingEntity);

        // Apply buffs or debuffs to the entities
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity le) {
                boolean isFriendly = !HelperMethods.checkFriendlyFire(le, player);

                // Apply buffs
                if (isFriendly) {
                    le.addStatusEffect(new StatusEffectInstance(buffOne, buffDuration, buffOneAmp, false, false, true));
                    le.addStatusEffect(new StatusEffectInstance(buffTwo, buffDuration, buffTwoAmp, false, false, true));
                }

                // Apply debuffs if they are not null
                if (!isFriendly) {
                    if (debuffOne != null) {
                        le.addStatusEffect(new StatusEffectInstance(debuffOne, buffDuration, debuffOneAmp));
                    }
                    if (debuffTwo != null) {
                        le.addStatusEffect(new StatusEffectInstance(debuffTwo, buffDuration, debuffTwoAmp));
                    }
                }
            }
        }
    }


}
