package com.bubbaboogs.modad.gui;

import com.bubbaboogs.modad.entities.block.HarvesterBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class HarvesterMenu extends AbstractContainerMenu {
    private final HarvesterBlockEntity blockEntity; // server only
    private final Inventory playerInventory;
    private final NonNullList<ItemStack> items; // actual inventory items

    // Server constructor
    public HarvesterMenu(int syncId, Inventory playerInventory, HarvesterBlockEntity blockEntity) {
        super(ModGUI.HARVESTER_MENU, syncId);
        this.blockEntity = blockEntity;
        this.playerInventory = playerInventory;
        this.items = blockEntity.getItems(); // server uses BE items
        addSlots(new SimpleContainerWrapper(items));
    }

    // Client constructor
    public HarvesterMenu(int syncId, Inventory playerInventory) {
        super(ModGUI.HARVESTER_MENU, syncId);
        this.blockEntity = null; // no BE on client yet
        this.playerInventory = playerInventory;
        this.items = NonNullList.withSize(2, ItemStack.EMPTY); // must match server slot count
        addSlots(new SimpleContainerWrapper(items));
    }

    private void addSlots(Container container) {
        // Slot 0 = input
        this.addSlot(new Slot(container, 0, 56, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return blockEntity != null && blockEntity.canPlaceItem(0, stack);
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });

        // Slot 1 = output
        this.addSlot(new Slot(container, 1, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public int getMaxStackSize() {
                return 64;
            }
        });

        // Player inventory slots (3 rows of 9)
        int startX = 8;
        int startY = 84;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, startX + col * 18, startY + row * 18));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, startX + col * 18, startY + 58));
        }
    }

    // Simple wrapper to let Slots access a NonNullList
    private static class SimpleContainerWrapper implements Container {
        private final NonNullList<ItemStack> items;

        public SimpleContainerWrapper(NonNullList<ItemStack> items) {
            this.items = items;
        }

        @Override
        public int getContainerSize() {
            return items.size();
        }

        @Override
        public boolean isEmpty() {
            for (ItemStack stack : items) if (!stack.isEmpty()) return false;
            return true;
        }

        @Override
        public ItemStack getItem(int index) {
            return items.get(index);
        }

        @Override
        public ItemStack removeItem(int index, int count) {
            ItemStack result = net.minecraft.world.ContainerHelper.removeItem(items, index, count);
            if (!result.isEmpty()) markDirty();
            return result;
        }

        @Override
        public ItemStack removeItemNoUpdate(int index) {
            ItemStack result = net.minecraft.world.ContainerHelper.takeItem(items, index);
            if (!result.isEmpty()) markDirty();
            return result;
        }

        @Override
        public void setItem(int index, ItemStack stack) {
            items.set(index, stack);
            markDirty();
        }

        @Override
        public void setChanged() {
            markDirty();
        }

        @Override
        public boolean stillValid(Player player) {
            return true;
        }

        @Override
        public void clearContent() {
            items.clear();
            markDirty();
        }

        private void markDirty() {
            // optionally notify block entity
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity == null || blockEntity.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            stack = stackInSlot.copy();

            // Move between BE slots and player inventory
            if (index < 2) { // BE slots
                if (!this.moveItemStackTo(stackInSlot, 2, this.slots.size(), true)) return ItemStack.EMPTY;
            } else { // player inventory
                if (!this.moveItemStackTo(stackInSlot, 0, 2, false)) return ItemStack.EMPTY;
            }

            if (stackInSlot.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return stack;
    }

    @Nullable
    public HarvesterBlockEntity getBlockEntity() {
        return blockEntity;
    }
}
