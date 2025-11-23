package com.bubbaboogs.modad.entities.block;

import com.bubbaboogs.modad.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HarvesterBlockEntity extends BlockEntity {
    public HarvesterBlockEntity(BlockPos pos, BlockState state){
        super(ModBlockEntities.HARVESTER_BLOCK_ENTITY, pos, state);
    }
}
