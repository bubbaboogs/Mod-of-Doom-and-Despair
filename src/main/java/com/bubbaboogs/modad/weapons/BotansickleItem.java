package com.bubbaboogs.modad.weapons;

import com.bubbaboogs.modad.ModTimer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BotansickleItem extends Item {

    public static ToolMaterial material = new ToolMaterial(null, 700, -1.4f, 0, 15, null);

    public BotansickleItem(Properties settings) {
        super(settings);
    }


    public static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 12.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, 0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        if (world.isClientSide()) return InteractionResult.SUCCESS;

        ServerLevel serverWorld = (ServerLevel) world;
        BlockPos clickedPos = context.getClickedPos();
        clickedPos = new BlockPos(clickedPos.getX(), clickedPos.getY() + 1, clickedPos.getZ());

        BlockPos startPos = clickedPos;
        Player player = context.getPlayer();
        Direction facing = context.getHorizontalDirection();

        //BlockPos start = clickedPos.offset(facing);

        BlockState wallBlock = Blocks.OAK_LEAVES.defaultBlockState();

        for (int y = 0; y < 3; y++) {
            for (int w = -1; w <= 1; w++) {
                for (int d = 0; d < 2; d++) {
                    BlockPos targetPos = startPos.relative(facing, d).offset(
                            facing.getClockWise().getUnitVec3i().multiply(w)).above(y);

                    if (serverWorld.getBlockState(targetPos).isAir()) {
                        serverWorld.setBlockAndUpdate(targetPos, wallBlock);
                    }
                }
            }
        }
        serverWorld.getServer().execute(() -> {
            ModTimer.INSTANCE.schedule(200, () -> {
                for (int y = 0; y < 3; y++) {
                    for (int w = -1; w <= 1; w++) {
                        for (int d = 0; d < 2; d++) {
                            BlockPos targetPos = startPos.relative(facing, d).offset(
                                    facing.getClockWise().getUnitVec3i().multiply(w)).above(y);

                            if (serverWorld.getBlockState(targetPos).is(Blocks.OAK_LEAVES)) {
                                serverWorld.removeBlock(targetPos, false);
                            }
                        }
                    }
                }
            });
        });

        return InteractionResult.SUCCESS;
    }
}
