package com.xiaoyue.celestial_artifacts.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

public class CMessages {

    public static SimpleChannel channel;

    public static int packetId;

    public static int id() {
        return packetId++;
    }

    public static void register() {

        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(MODID, "aabb"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        channel = net;

        net.messageBuilder(CAbilityPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CAbilityPacket::new)
                .encoder(CAbilityPacket::toBuf)
                .consumerMainThread(CAbilityPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG msg) {
        channel.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer player) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

}
