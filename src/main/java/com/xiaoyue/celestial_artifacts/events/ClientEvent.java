package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.core.modular.ModularCurio;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.register.CAKeyMapping;
import com.xiaoyue.celestial_artifacts.register.CAbilityPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvent {

	@SubscribeEvent
	public static void onAddInfo(ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();
		if (stack.hasTag()) {
			if (stack.getTag().getBoolean(CAMiscCuriosHandler.AMETHYST_REF)) {
				event.getToolTip().add(CALang.Tooltip.REINFORCE_PLATE_USED.get(
						TextFacet.item(CAItems.AMETHYST_REINFORCE_PLATE.get()))
						.withStyle(ChatFormatting.GRAY));
			}
			if (stack.getTag().getBoolean(CAMiscCuriosHandler.COPPER_REF)) {
				event.getToolTip().add(CALang.Tooltip.REINFORCE_PLATE_USED.get(
						TextFacet.item(CAItems.COPPER_REINFORCE_PLATE.get()))
						.withStyle(ChatFormatting.GRAY));
			}
		}
	}

	@SubscribeEvent
	public static void renderTooltip(RenderTooltipEvent.Color event) {
		ItemStack itemStack = event.getItemStack();
		if (itemStack.getItem() instanceof ModularCurio) {
			event.setBorderStart(0xFF87CEFA);
			event.setBorderEnd(0xFF87CEFA);
		}
	}

	@SubscribeEvent
	public static void onInputKey(InputEvent.Key event) {
		if (CAKeyMapping.ABILITY_KEY.consumeClick()) {
			CelestialArtifacts.HANDLER.toServer(new CAbilityPacket());
		}
	}
}
