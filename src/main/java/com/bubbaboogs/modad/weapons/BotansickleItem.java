package com.bubbaboogs.modad.weapons;

import com.bubbaboogs.modad.ModTimer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BotansickleItem extends Item {

    public static ToolMaterial material = new ToolMaterial(null, 700, -1.4f, 0, 15, null);

    public BotansickleItem(Settings settings) {
        super(settings);
    }


    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder().add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 12.0f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, 0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient()) return ActionResult.SUCCESS;

        ServerWorld serverWorld = (ServerWorld) world;
        BlockPos clickedPos = context.getBlockPos();
        clickedPos = new BlockPos(clickedPos.getX(), clickedPos.getY() + 1, clickedPos.getZ());

        BlockPos startPos = clickedPos;
        PlayerEntity player = context.getPlayer();
        Direction facing = context.getHorizontalPlayerFacing();

        //BlockPos start = clickedPos.offset(facing);

        BlockState wallBlock = Blocks.OAK_LEAVES.getDefaultState();

        for (int y = 0; y < 3; y++) {
            for (int w = -1; w <= 1; w++) {
                for (int d = 0; d < 2; d++) {
                    BlockPos targetPos = startPos.offset(facing, d).add(
                            facing.rotateYClockwise().getVector().multiply(w)).up(y);

                    if (serverWorld.getBlockState(targetPos).isAir()) {
                        serverWorld.setBlockState(targetPos, wallBlock);
                    }
                }
            }
        }
        serverWorld.getServer().execute(() -> {
            ModTimer.INSTANCE.schedule(200, () -> {
                for (int y = 0; y < 3; y++) {
                    for (int w = -1; w <= 1; w++) {
                        for (int d = 0; d < 2; d++) {
                            BlockPos targetPos = startPos.offset(facing, d).add(
                                    facing.rotateYClockwise().getVector().multiply(w)).up(y);

                            if (serverWorld.getBlockState(targetPos).isOf(Blocks.OAK_LEAVES)) {
                                serverWorld.removeBlock(targetPos, false);
                            }
                        }
                    }
                }
            });
        });

        return ActionResult.SUCCESS;
    }
}
