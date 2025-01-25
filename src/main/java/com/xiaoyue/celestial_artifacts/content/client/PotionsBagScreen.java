package com.xiaoyue.celestial_artifacts.content.client;

import com.xiaoyue.celestial_artifacts.content.container.PotionsBagMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PotionsBagScreen extends AbstractContainerScreen<PotionsBagMenu> {
	public PotionsBagScreen(PotionsBagMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBackground(graphics);
		super.render(graphics, pMouseX, pMouseY, pPartialTick);
		this.renderTooltip(graphics, pMouseX, pMouseY);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float mx, int my, int ticks) {
		ResourceLocation texture = new ResourceLocation("textures/gui/container/generic_54.png");
		int x = (this.width - this.imageWidth) / 2;
		int y = (this.height - this.imageHeight) / 2;
		graphics.blit(texture, x, y, 0, 0, imageWidth, 71);
		graphics.blit(texture, x, y + 71, 0, 126, imageWidth, 96);
	}
}
