package com.xiaoyue.celestial_artifacts.compat;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.data.CAGLMProvider;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class CAJeiPlugin implements IModPlugin {

	private static final ResourceLocation ID = CelestialArtifacts.loc("main");

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		CAGLMProvider.addInfo(registration::addItemStackInfo);
	}

}
