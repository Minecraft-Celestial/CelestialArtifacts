package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioBreak;
import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioHeal;
import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioShield;
import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioXp;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;

public class OtherMain {

    @SubscribeEvent
    public void onPlayerBreak(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            List<SlotResult> curios = handler.findCurios(itemStack -> itemStack.getItem() instanceof GetCurioBreak);
            for (SlotResult result : curios) {
                GetCurioBreak curio = (GetCurioBreak) result.stack().getItem();
                curio.onPlayerBreak(result.slotContext(), player, event);
            }
        });
    }

    @SubscribeEvent
    public void onPlayerHeal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                List<SlotResult> curios = handler.findCurios(itemStack -> itemStack.getItem() instanceof GetCurioHeal);
                for (SlotResult result : curios) {
                    GetCurioHeal curio = (GetCurioHeal) result.stack().getItem();
                    curio.onPlayerHeal(result.slotContext(), player, event);
                }
            });
        }
    }

    @SubscribeEvent
    public void onPlayerBlocked(ShieldBlockEvent event) {
        if (event.getEntity() instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                List<SlotResult> curios = handler.findCurios(itemStack -> itemStack.getItem() instanceof GetCurioShield);
                for (SlotResult result : curios) {
                    GetCurioShield curio = (GetCurioShield) result.stack().getItem();
                    curio.onPlayerBlocked(result.slotContext(), player, event);
                }
            });
        }
    }

    @SubscribeEvent
    public void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
        Player player = event.getEntity();
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            List<SlotResult> curios = handler.findCurios(itemStack -> itemStack.getItem() instanceof GetCurioXp);
            for (SlotResult result : curios) {
                GetCurioXp curio = (GetCurioXp) result.stack().getItem();
                curio.onPlayerPickupXp(result.slotContext(), player, event);
            }
        });
    }
}
