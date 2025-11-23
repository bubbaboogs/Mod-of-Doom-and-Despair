package com.bubbaboogs.modad.entities.block;

import com.bubbaboogs.modad.ModBlockEntities;
import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.components.ModComponents;
import com.bubbaboogs.modad.gui.HarvesterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class HarvesterBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, StackedContentsCompatible {
    private int ticksSinceLast;
    protected final ContainerData dataAccess;
    protected NonNullList<ItemStack> items;
    public HarvesterBlockEntity(BlockPos pos, BlockState state){
        super(ModBlockEntities.HARVESTER_BLOCK_ENTITY, pos, state);
        this.dataAccess = new ContainerData() {
            @Override
            public int get(int i) {
                switch (i) {
                    case 0 -> {
                        return HarvesterBlockEntity.this.ticksSinceLast;
                    }
                    default -> {
                        return 0;
                    }
                }
            }

            @Override
            public void set(int i, int j) {
                switch(i){
                    case 0 -> HarvesterBlockEntity.this.ticksSinceLast = j;
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
        this.items = NonNullList.withSize(2, ItemStack.EMPTY);
    }

    @Override
    protected void saveAdditional(ValueOutput writeView) {
        ContainerHelper.saveAllItems(writeView, this.items);

        super.saveAdditional(writeView);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("modad.container.harvester");
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.items = nonNullList;
    }

    public void setItem(int i, ItemStack itemStack) {
        ItemStack itemStack2 = (ItemStack)this.items.get(i);
        boolean isSame = !itemStack.isEmpty() && ItemStack.isSameItemSameComponents(itemStack2, itemStack);
        this.items.set(i, itemStack);
        itemStack.limitSize(this.getMaxStackSize(itemStack));
        if (i == 0 && !isSame) {
            Level level1 = this.level;
            if (level1 instanceof ServerLevel) {
                this.setChanged();
            }
        }

    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == 1) return false; // output slot
        return slot == 0 && stack.getComponents().has(ModComponents.HARVEST_TIME); // only valid input
    }


    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory inventory) {
        return new HarvesterMenu(syncId, inventory, this);
    }


    @Override
    protected void loadAdditional(ValueInput readView) {
        super.loadAdditional(readView);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(readView, this.items);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    @Override
    public int @NotNull[] getSlotsForFace(Direction direction) {
        return switch (direction) {
            case UP -> new int[]{0};       // input
            case DOWN -> new int[]{1};     // output
            default -> new int[]{};        // sides = nothing
        };
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return direction == Direction.UP && index == 0 && canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return direction == Direction.DOWN && index == 1;
    }

    @Override
    public int getContainerSize() {
        return 2;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, HarvesterBlockEntity be) {
        if (level.isClientSide()) return;

        be.ticksSinceLast++;
        ModOfDoomAndDespair.LOGGER.info("ticksSinceLast: " + be.ticksSinceLast);

        ItemStack input = be.items.get(0);
        ItemStack output = be.items.get(1);

        if (input.isEmpty()){
            be.ticksSinceLast = 0;
            return;
        }

        var harvestTime = input.getComponents().get(ModComponents.HARVEST_TIME);
        if (harvestTime == null) return;

        if (be.ticksSinceLast < harvestTime) return;

        be.ticksSinceLast = 0;

        if (output.isEmpty()) {
            be.items.set(1, new ItemStack(input.getItem(), 1));
            be.setChanged();
            return;
        }

        if (output.getItem() == input.getItem() && output.getCount() < output.getMaxStackSize()) {
            output.grow(1);
            be.setChanged();
        }
    }



    @Override
    public void fillStackedContents(StackedItemContents stackedItemContents) {

    }

    public Optional<ItemStack> getInput() {
        return items.get(0).isEmpty() ? Optional.empty() : Optional.of(items.get(0));
    }

    public int getTicksSinceLast() {
        return ticksSinceLast;
    }

    public int getCurrentHarvestTime() {
        ItemStack input = items.get(0);
        if (input.isEmpty()) return 1;
        Integer time = input.getComponents().get(ModComponents.HARVEST_TIME);
        return time == null ? 1 : time;
    }

}
