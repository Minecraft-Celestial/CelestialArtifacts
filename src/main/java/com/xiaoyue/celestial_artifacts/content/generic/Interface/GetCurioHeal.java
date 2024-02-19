package com.xiaoyue.celestial_artifacts.content.generic.Interface;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.SlotContext;

public interface GetCurioHeal {

    default void onPlayerHeal(SlotContext context, Player player, LivingHealEvent event) {
    }
}
