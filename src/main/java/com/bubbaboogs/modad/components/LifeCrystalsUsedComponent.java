package com.bubbaboogs.modad.components;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class LifeCrystalsUsedComponent implements IntComponent, AutoSyncedComponent {
    private int value = 0;
    @Override public int getValue() { return this.value; }
    @Override public void increment() { this.value++; }
    public void setValue(int value) {this.value = value;}

    @Override
    public void readData(ReadView readView) {
        this.value = readView.getInt("life_crystals_used", 10);
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.putInt("life_crystals_used", this.value);
    }
}
