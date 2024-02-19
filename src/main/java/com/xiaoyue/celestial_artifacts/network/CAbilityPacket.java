package com.xiaoyue.celestial_artifacts.network;

import com.xiaoyue.celestial_artifacts.content.generic.intf.GetCurioSkill;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;
import java.util.function.Supplier;

public class CAbilityPacket {

    public CAbilityPacket() {

    }

    public CAbilityPacket(FriendlyByteBuf buf) {

    }

    public void toBuf(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            ServerLevel level = player.serverLevel();

            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                List<SlotResult> curios = handler.findCurios(itemStack -> itemStack.getItem() instanceof GetCurioSkill);
                for (SlotResult result : curios) {
                    GetCurioSkill curio = (GetCurioSkill) result.stack().getItem();
                    curio.getCurioSkill(level, player, result.slotContext());
                }
            });
        });
        return true;
    }
}