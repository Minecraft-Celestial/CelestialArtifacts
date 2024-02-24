package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.ISlotType;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CloneHandler {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        Player original = event.getOriginal();
        Player player = event.getEntity();
        CompoundTag data = original.getPersistentData();
        CompoundTag newData = player.getPersistentData();

        if (data.getBoolean("hello_world")) {
            newData.putBoolean("hello_world", true);
        }

        if (data.getBoolean("cs")) {
            newData.putBoolean("cs", true);
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (LevelUtils.isServerLevel(player.level())) {
                CompoundTag data = player.getPersistentData();
                if (!data.getBoolean("hello_world")) {
                    player.addItem(new ItemStack(CAItems.HEIRLOOM_NECKLACE.get()));
                    if (!CAModConfig.CATASTROPHE_SCROLL_START.get()) {
                        player.addItem(new ItemStack(CAItems.CATASTROPHE_SCROLL.get()));
                    }
                    data.putBoolean("hello_world", true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogged(PlayerEvent.PlayerLoggedInEvent event) {
        if (CAModConfig.CATASTROPHE_SCROLL_START.get()) {
            Player player = event.getEntity();
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> map : curios.entrySet()) {
                    IDynamicStackHandler stacks = map.getValue().getStacks();
                    for (int i = 0; i < stacks.getSlots(); i++) {
                        ItemStack stack = stacks.getStackInSlot(i);
                        if (stack.isEmpty()) {
                            if (!CurioUtils.hasCurio(player, CAItems.CATASTROPHE_SCROLL.get())) {
                                if (!player.getPersistentData().getBoolean("cs")) {
                                    for (ISlotType value : CuriosApi.getSlots().values()) {
                                        if (value.getIdentifier().contains("c_charm")) {
                                            handler.setEquippedCurio("c_charm", i, CAItems.CATASTROPHE_SCROLL.get().getDefaultInstance());
                                            player.getPersistentData().putBoolean("cs", true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}
