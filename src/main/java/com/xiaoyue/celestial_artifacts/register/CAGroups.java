package com.xiaoyue.celestial_artifacts.register;

import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;
import static com.xiaoyue.celestial_artifacts.register.CAItems.*;

public class CAGroups {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> CURIOS = CREATIVE_MODE_TAB.register("curios", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .title(ToolTipUtils.addLocalTooltip("itemGroup.celestial_artifacts.curios"))
            .icon(() -> AMETHYST_RING.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                // ring
                output.accept(GOLD_RING.get());
                output.accept(AMETHYST_RING.get());
                output.accept(DIAMOND_RING.get());
                output.accept(EMERALD_RING.get());
                output.accept(FLIGHT_RING.get());
                output.accept(NETHERITE_RING.get());
                output.accept(NETHER_FIRE.get());
                output.accept(THUNDER_RING.get());
                output.accept(RING_OF_LIFE.get());
                output.accept(FREEZE_RING.get());
                // charm
                output.accept(WAR_DEAD_BADGE.get());
                output.accept(UNDEAD_CHARM.get());
                output.accept(DESTROYER_BADGE.get());
                output.accept(HEART_OF_REVENGE.get());
                output.accept(TWISTED_BRAIN.get());
                output.accept(TWISTED_HEART.get());
                output.accept(CORRUPT_BADGE.get());
                output.accept(CURSED_PROTECTOR.get());
                output.accept(CURSED_TALISMAN.get());
                output.accept(CURSED_TOTEM.get());
                output.accept(HOLY_TALISMAN.get());
                output.accept(HOLY_SWORD.get());
                output.accept(ANGEL_HEART.get());
                output.accept(ANGEL_PEARL.get());
                output.accept(DEMON_CURSE.get());
                output.accept(KNIGHT_SHELTER.get());
                output.accept(SOUL_BOX.get());
                output.accept(SOLAR_MAGNET.get());
                output.accept(GLUTTONY_BADGE.get());
                output.accept(GREEDY_HEART.get());
                output.accept(MAGIC_HORSESHOE.get());
                output.accept(BEARING_STAMEN.get());
                output.accept(ABYSS_WILL_BADGE.get());
                output.accept(SANDS_TALISMAN.get());
                output.accept(SACRIFICIAL_OBJECT.get());
                output.accept(DEMON_HEART.get());
                // scroll
                output.accept(TRAVELER_SCROLL.get());
                output.accept(SEA_GOD_SCROLL.get());
                output.accept(SKYWALKER_SCROLL.get());
                output.accept(TWISTED_SCROLL.get());
                // bracelet
                output.accept(EMERALD_BRACELET.get());
                output.accept(LIFE_BRACELET.get());
                output.accept(PRECIOUS_BRACELET.get());
                output.accept(RED_RUBY_BRACELET.get());
                output.accept(HIDDEN_BRACELET.get());
                output.accept(SCARLET_BRACELET.get());
                output.accept(CHARMING_BRACELET.get());
                output.accept(SPIRIT_BRACELET.get());
                // pendant
                output.accept(UNOWNED_PENDANT.get());
                output.accept(CHAOTIC_PENDANT.get());
                output.accept(SHADOW_PENDANT.get());
                // necklace
                output.accept(STAR_NECKLACE.get());
                output.accept(CROSS_NECKLACE.get());
                output.accept(GALLOP_NECKLACE.get());
                output.accept(FANG_NECKLACE.get());
                output.accept(PRECIOUS_NECKLACE.get());
                output.accept(HOLY_NECKLACE.get());
                output.accept(HEIRLOOM_NECKLACE.get());
                output.accept(EMERALD_NECKLACE.get());
                output.accept(ENDER_PROTECTOR.get());
                output.accept(RED_HEART_NECKLACE.get());
                output.accept(LOCK_OF_ABYSS.get());
                output.accept(SPIRIT_NECKLACE.get());
                // back
                output.accept(MAGIC_ARROW_BAG.get());
                output.accept(FLAME_ARROW_BAG.get());
                output.accept(SPIRIT_ARROW_BAG.get());
                output.accept(IRON_SCABBARD.get());
                output.accept(LEECH_SCABBARD.get());
                output.accept(TWISTED_SCABBARD.get());
                output.accept(TITAN_SCABBARD.get());
                // head
                output.accept(SEA_GOD_CROWN.get());
                output.accept(PRAYER_CROWN.get());
                output.accept(ABYSS_CORE.get());
                output.accept(GUARDIAN_EYE.get());
                output.accept(EVIL_EYE.get());
                output.accept(SPIRIT_CROWN.get());
                // cs
                output.accept(CATASTROPHE_SCROLL.get());
                // etching
                output.accept(CHAOTIC_ETCHING.get());
                output.accept(ORIGIN_ETCHING.get());
                output.accept(ETCHING_OF_LIFE.get());
                output.accept(TRUTH_ETCHING.get());
                output.accept(DESIRE_ETCHING.get());
                output.accept(NIHILITY_ETCHING.get());
                output.accept(END_ETCHING.get());
                // tool
                output.accept(EARTH_AXE.get());
                output.accept(EARTH_PICKAXE.get());
                output.accept(EARTH_HOE.get());
                output.accept(EARTH_SHOVEL.get());
                // item
                output.accept(REPENT_MIRROR.get());
                output.accept(BACKTRACK_MIRROR.get());
                output.accept(PURIFIED_POWDER.get());
                // food
                output.accept(UNLUCKY_POTATO.get());
            }).build());
}