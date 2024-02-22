package com.xiaoyue.celestial_artifacts.content.old.generic.intf;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.SlotContext;

public interface GetCurioBreak {

    default void onPlayerBreak(SlotContext context, Player player, PlayerEvent.BreakSpeed event) {
    }
}
