package com.bubbaboogs.modad.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;

public class PlatformBlock extends Block {
    private static final VoxelShape SHAPE = VoxelShapes.cuboid(0, 13.0 / 16.0, 0, 1, 1, 1);

    public PlatformBlock(Settings settings) {
        super(settings.nonOpaque().noCollision());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (!(context instanceof EntityShapeContext entityContext)) return VoxelShapes.empty();

        if (entityContext.getEntity() instanceof PlayerEntity player) {
            if (context.isAbove(SHAPE, pos, true)) {
                return SHAPE;
            }
        }
        return VoxelShapes.empty();
    }


    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return true;
    }


    @Override
    public boolean isSideInvisible(BlockState state, BlockState adjacentState, Direction direction) {
        return adjacentState.isOf(this);
    }
}

