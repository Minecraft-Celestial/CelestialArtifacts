package com.xiaoyue.celestial_artifacts;

import com.xiaoyue.celestial_artifacts.config.CommonConfig;
import com.xiaoyue.celestial_artifacts.events.curios.AttackMain;
import com.xiaoyue.celestial_artifacts.events.curios.OtherMain;
import com.xiaoyue.celestial_artifacts.network.CMessages;
import com.xiaoyue.celestial_artifacts.register.Keymapping;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.xiaoyue.celestial_artifacts.register.CAGroups.CREATIVE_MODE_TAB;
import static com.xiaoyue.celestial_artifacts.register.CAItems.ITEM;

@Mod(CelestialArtifacts.MODID)
public class CelestialArtifacts
{
    public static final String MODID = "celestial_artifacts";

    public CelestialArtifacts()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ITEM.register(modEventBus);
        CREATIVE_MODE_TAB.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new AttackMain());
        MinecraftForge.EVENT_BUS.register(new OtherMain());
        AttackEventHandler.register(4500, new AttackMain());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.spec);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        CMessages.register();
    }

}
