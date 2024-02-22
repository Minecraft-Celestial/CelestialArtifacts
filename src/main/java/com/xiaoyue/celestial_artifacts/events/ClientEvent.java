package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.old.generic.BaseICurio;
import com.xiaoyue.celestial_artifacts.network.CMessages;
import com.xiaoyue.celestial_artifacts.network.CAbilityPacket;
import com.xiaoyue.celestial_artifacts.register.Keymapping;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvent {

    @SubscribeEvent
    public static void renderTooltip(RenderTooltipEvent.Color event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof BaseICurio) {
            event.setBorderStart(0xFF87CEFA);
            event.setBorderEnd(0xFF87CEFA);
        }
    }

    @SubscribeEvent
    public static void onInputKey(InputEvent.Key event) {
        if (Keymapping.ABILITY_KEY.consumeClick()) {
            CMessages.sendToServer(new CAbilityPacket());
        }
    }
}
