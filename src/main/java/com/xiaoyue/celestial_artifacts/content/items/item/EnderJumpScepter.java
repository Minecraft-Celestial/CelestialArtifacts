package com.xiaoyue.celestial_artifacts.content.items.item;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_core.data.CCLangData;
import dev.xkmc.l2library.util.tools.TeleportTool;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderJumpScepter extends Item {
	public static final String CHARGING = "CelestialCore_enderJumpScepter_charging";
	public static final String DIM_POS = "CelestialCore_enderJumpScepter_dimPos";
	public static final String X_POS = "CelestialCore_enderJumpScepter_xPos";
	public static final String Y_POS = "CelestialCore_enderJumpScepter_yPos";
	public static final String Z_POS = "CelestialCore_enderJumpScepter_zPos";
	public EnderJumpScepter() {
		super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
	}

	public ResourceKey<Level> getLevelFromTag(ItemStack stack) {
		String dim = stack.getTag().getString(DIM_POS);
		return ResourceKey.create(Registries.DIMENSION, new ResourceLocation(dim));
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity target, InteractionHand pUsedHand) {
		MinecraftServer server = pPlayer.level().getServer();
		if (pStack.hasTag() && server != null) {
			CompoundTag tag = pStack.getTag();
			int charging = tag.getInt(CHARGING);
			ServerLevel level = server.getLevel(getLevelFromTag(pStack));
			if (charging > 0 && level != null) {
				TeleportTool.performTeleport(target, level, tag.getDouble(X_POS), tag.getDouble(Y_POS), tag.getDouble(Z_POS), target.getYRot(), target.getXRot());
				tag.putInt(CHARGING, charging - 1);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		ItemStack stack = pContext.getItemInHand();
		stack.getOrCreateTag().putDouble(X_POS, pContext.getClickedPos().getX());
		stack.getOrCreateTag().putDouble(Y_POS, pContext.getClickedPos().getY());
		stack.getOrCreateTag().putDouble(Z_POS, pContext.getClickedPos().getZ());
		stack.getOrCreateTag().putString(DIM_POS, pContext.getLevel().dimension().location().toString());
		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
		CompoundTag tag = pStack.getOrCreateTag();
		list.add(CALang.Tooltip.ENDER_JUMP_SCEPTER.get());
		list.add(CALang.Tooltip.SCEPTER_CHARGING.get(TextFacet.item(Items.ENDER_PEARL)));
		list.add(CALang.Tooltip.CURRENT_CHARGING.get(CCLangData.num(tag.getInt(CHARGING))));
		list.add(CALang.Tooltip.CURRENT_LEVEL.get(Component.translatable(tag.getString(DIM_POS)).withStyle(ChatFormatting.AQUA)));
		list.add(CALang.Tooltip.CURRENT_POS.get(CCLangData.num((int) tag.getDouble(X_POS)), CCLangData.num((int) tag.getDouble(Y_POS)), CCLangData.num((int) tag.getDouble(Z_POS))));
	}

	@Override
	public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
		if (pAction.equals(ClickAction.SECONDARY)) {
			if (pOther.is(Items.ENDER_PEARL)) {
				pOther.shrink(1);
				int charging = pStack.getOrCreateTag().getInt(EnderJumpScepter.CHARGING);
				pStack.getOrCreateTag().putInt(EnderJumpScepter.CHARGING, charging + 1);
				return true;
			}
		}
		return false;
	}
}
