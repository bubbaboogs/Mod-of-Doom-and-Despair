package com.bubbaboogs.modad.networking;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class ModPayloads {
    public record HandlePlayerDropPayload(int entityId) implements CustomPayload {
        public static final Identifier GIVE_GLOWING_EFFECT_PAYLOAD_ID = Identifier.of(ModOfDoomAndDespair.MOD_ID, "give_glowing_effect");
        public static final Id<HandlePlayerDropPayload> ID = new Id<>(GIVE_GLOWING_EFFECT_PAYLOAD_ID);
        public static final PacketCodec<RegistryByteBuf, HandlePlayerDropPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, HandlePlayerDropPayload::entityId, HandlePlayerDropPayload::new);

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public static void initialize(){
        PayloadTypeRegistry.playC2S().register(HandlePlayerDropPayload.ID, HandlePlayerDropPayload.CODEC);
    }
}
