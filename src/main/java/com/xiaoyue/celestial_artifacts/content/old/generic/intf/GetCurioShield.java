package com.xiaoyue.celestial_artifacts.content.old.generic.intf;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import top.theillusivec4.curios.api.SlotContext;

public interface GetCurioShield {

    default void onPlayerBlocked(SlotContext context, Player player, ShieldBlockEvent event) {
    }
}
