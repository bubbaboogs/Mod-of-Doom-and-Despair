package com.bubbaboogs.modad.networking;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class ModPayloads {
    public record HandlePlayerDropPayload(int entityId) implements CustomPacketPayload {
        public static final ResourceLocation GIVE_GLOWING_EFFECT_PAYLOAD_ID = ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "give_glowing_effect");
        public static final Type<HandlePlayerDropPayload> ID = new Type<>(GIVE_GLOWING_EFFECT_PAYLOAD_ID);
        public static final StreamCodec<RegistryFriendlyByteBuf, HandlePlayerDropPayload> CODEC = StreamCodec.composite(ByteBufCodecs.INT, HandlePlayerDropPayload::entityId, HandlePlayerDropPayload::new);

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return ID;
        }
    }

    public static void initialize(){
        PayloadTypeRegistry.playC2S().register(HandlePlayerDropPayload.ID, HandlePlayerDropPayload.CODEC);
    }
}
