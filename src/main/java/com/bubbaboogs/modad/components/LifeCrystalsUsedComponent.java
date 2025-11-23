package com.bubbaboogs.modad.components;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class LifeCrystalsUsedComponent implements IntComponent, AutoSyncedComponent {
    private int value = 0;
    @Override public int getValue() { return this.value; }
    @Override public void increment() { this.value++; }
    public void setValue(int value) {this.value = value;}

    @Override
    public void readData(ValueInput readView) {
        this.value = readView.getIntOr("life_crystals_used", 10);
    }

    @Override
    public void writeData(ValueOutput writeView) {
        writeView.putInt("life_crystals_used", this.value);
    }
}
