package com.bubbaboogs.modad.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PlatformBlock extends Block {
    private static final VoxelShape SHAPE = Shapes.box(0, 13.0 / 16.0, 0, 1, 1, 1);

    public PlatformBlock(Properties settings) {
        super(settings.noOcclusion().noCollision());
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (!(context instanceof EntityCollisionContext entityContext)) return Shapes.empty();

        if (entityContext.getEntity() instanceof Player player) {
            if (context.isAbove(SHAPE, pos, true)) {
                return SHAPE;
            }
        }
        return Shapes.empty();
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return true;
    }


    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return adjacentState.is(this);
    }
}

