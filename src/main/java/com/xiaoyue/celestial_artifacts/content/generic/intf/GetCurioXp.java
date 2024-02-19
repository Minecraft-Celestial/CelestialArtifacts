package com.xiaoyue.celestial_artifacts.content.generic.intf;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import top.theillusivec4.curios.api.SlotContext;

public interface GetCurioXp {

    default void onPlayerPickupXp(SlotContext context, Player player, PlayerXpEvent.PickupXp event) {
    }
}
