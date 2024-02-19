package com.xiaoyue.celestial_artifacts.content.generic.intf;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import top.theillusivec4.curios.api.SlotContext;

public interface GetCurioSkill {

    default void getCurioSkill(ServerLevel level, ServerPlayer player, SlotContext context) {
    }
}
