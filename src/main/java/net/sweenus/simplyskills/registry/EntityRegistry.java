package net.sweenus.simplyskills.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.sweenus.simplyskills.SimplySkills;
import net.sweenus.simplyskills.entities.SpellTargetEntity;
import net.sweenus.simplyskills.items.GraciousManuscript;
import net.sweenus.simplyskills.items.MalevolentManuscript;
import net.sweenus.simplyskills.items.SkillChronicle;

public class EntityRegistry {

    public static final EntityType<SpellTargetEntity> SPELL_TARGET_ENTITY;
    //public static final EntityType<SpellTargetEntity2> CUSTOM_ENTITY_2;

    static {
        SPELL_TARGET_ENTITY = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(SimplySkills.MOD_ID, "custom_entity_1"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SpellTargetEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
        );
/*
        CUSTOM_ENTITY_2 = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(SimplySkills.MOD_ID, "custom_entity_2"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CustomEntity2::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
        );
 */
    }


    public static void registerEntities() {
        SimplySkills.LOGGER.info("Registering Entities for " + SimplySkills.MOD_ID);
    }


}