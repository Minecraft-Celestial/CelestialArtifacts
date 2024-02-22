package com.xiaoyue.celestial_artifacts.data;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

    // catastrophe_scroll
    public static ForgeConfigSpec.BooleanValue CATASTROPHE_SCROLL_START = builder
            .push("catastrophe_scroll")
            .comment("When true, when the player enters the world, the accessory will be directly worn in the accessory bar")
            .define("catastrophe_scroll_start", false);

    public static ForgeConfigSpec.DoubleValue CATASTROPHE_SCROLL_EXPLOSION_DAMAGE = builder
            .comment("This value determines how much explosive damage you will receive due to the Chaos Curse")
            .defineInRange("catastrophe_scroll_explosion_damage", 0.75, 0.01, Integer.MAX_VALUE);

    public static ForgeConfigSpec.DoubleValue CATASTROPHE_SCROLL_OTHER_DAMAGE = builder
            .comment("This value determines how much other damage you will receive due to the Chaos Curse")
            .defineInRange("catastrophe_scroll_other_damage", 0.5, 0.01, Integer.MAX_VALUE);

    public static ForgeConfigSpec.DoubleValue CATASTROPHE_SCROLL_END_CURSE_DAMAGE = builder
            .comment("This value determines how much damage is negatively affected")
            .defineInRange("catastrophe_scroll_end_curse_damage", 0.25, 0.01, 1);

    public static ForgeConfigSpec.DoubleValue CATASTROPHE_SCROLL_ORIGIN_CURSE_DAMAGE = builder
            .comment("This value determines how much your damage will be reduced due to the Origin Curse")
            .defineInRange("catastrophe_scroll_origin_curse_damage", 0.5, 0.01, 1);

    // holy_sword
    public static ForgeConfigSpec.DoubleValue HOLY_SWORD_MAX_ADD_DAMAGE = builder
            .pop()
            .push("holy_sword")
            .comment("This value determines the maximum damage increase")
            .defineInRange("holy_sword_max_add_damage", 0.5, 0, Integer.MAX_VALUE);

    public static ForgeConfigSpec.DoubleValue HOLY_SWORD_LOST_LIFE_ADD_DAMAGE = builder
            .comment("This value determines how much life you lose and how much damage you inflict once")
            .defineInRange("holy_sword_lost_life_add_damage", 1, 1.0, Integer.MAX_VALUE);

    // hidden_bracelet
    public static ForgeConfigSpec.IntValue HIDDEN_BRACELET_EFFECT_INTERVAL = builder
            .pop()
            .push("hidden_bracelet")
            .comment("This value determines how many ticks you will receive a stealth effect once")
            .defineInRange("hidden_bracelet_effect_interval", 140, 1, Integer.MAX_VALUE);

    // angel_heart
    public static ForgeConfigSpec.IntValue ANGEL_HEART_REMOVE_INTERVAL = builder
            .pop()
            .push("angle_heart")
            .comment("This value determines how many ticks remove negative effects once")
            .defineInRange("angel_heart_remove_interval", 600, 1, Integer.MAX_VALUE);

    public static ForgeConfigSpec.IntValue ANGEL_HEART_BLOOD_INTERVAL = builder
            .comment("This value determines how many ticks restore health once")
            .defineInRange("angel_heart_blood_interval", 30, 1, Integer.MAX_VALUE);

    public static ForgeConfigSpec.IntValue ANGEL_HEART_HEAL_AMOUNT = builder
            .comment("This value determines how much health is restored at once")
            .defineInRange("angel_heart_heal_amount", 1, 1, Integer.MAX_VALUE);

    // cursed_protector
    public static ForgeConfigSpec.DoubleValue CURSED_PROTECTOR_JUDGING_LIFE = builder
            .pop()
            .push("cursed_protector")
            .comment("This value determines how much health is greater than the current one in order to trigger damage reduction")
            .defineInRange("cursed_protector_judging_life", 0.35, 0.01, 1);

    public static ForgeConfigSpec.DoubleValue CURSED_PROTECTOR_REDUCE_INJURY = builder
            .comment("This value determines its damage reduction amount")
            .defineInRange("cursed_protector_reduce_injury", 0.25, 0.01, 1);

    // cursed_talisman
    public static ForgeConfigSpec.DoubleValue CURSED_TALISMAN_CRIT_RATE_ADD = builder
            .pop()
            .push("cursed_talisman")
            .comment("This value determines the critical hit rate increased by each enchantment")
            .defineInRange("cursed_talisman_crit_rate_add", 0.04, 0.01, 1);

    public static ForgeConfigSpec.DoubleValue CURSED_TALISMAN_CRIT_DAMAGE_ADD = builder
            .comment("This value determines the critical hit damage increased by each enchantment")
            .defineInRange("cursed_talisman_crit_damage_add", 0.08, 0.01, 1);

    // etching_of_life
    public static ForgeConfigSpec.IntValue ETCHING_OF_LIFE_DROP_CONDITION = builder
            .pop()
            .comment("This value determines how many enemies with maximum health you need to defeat in order to possibly drop it")
            .defineInRange("etching_of_life_drop_condition", 499, 1, Integer.MAX_VALUE);

    public static ForgeConfigSpec spec = builder.build();
}
