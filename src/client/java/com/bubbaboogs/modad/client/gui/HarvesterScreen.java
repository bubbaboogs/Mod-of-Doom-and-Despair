package com.bubbaboogs.modad.client.gui;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.entities.block.HarvesterBlockEntity;
import com.bubbaboogs.modad.gui.HarvesterMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class HarvesterScreen extends AbstractContainerScreen<HarvesterMenu> {

    private static final ResourceLocation HARVESTER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "textures/gui/harvester.png");

    public HarvesterScreen(HarvesterMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }


    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;

        // Draw the full GUI background
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, HARVESTER_TEXTURE, x, y,
                0f, 0f, this.imageWidth, this.imageHeight, 256, 256);

        // Draw progress bar if there is input
        HarvesterBlockEntity be = this.menu.getBlockEntity();
        if (be != null && be.getInput().isPresent()) {
            int progressPixels = getProgressPixels(be);
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, HARVESTER_TEXTURE,
                    79 + x, 34 + y, // screen pos
                    80, 35,         // texture pos of the progress bar in your texture
                    progressPixels, 15, // width/height of progress bar
                    256, 256);
        }
    }

    // Scale harvest progress (0-1) to pixels wide
    private int getProgressPixels(HarvesterBlockEntity be) {
        int progress = be.getTicksSinceLast();
        int max = be.getCurrentHarvestTime();
        return Math.min(24, (int) ((float) progress / max * 24f));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // Draw background
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);

        // Draw main GUI
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        // Draw tooltips over slots
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Draw the title text
        guiGraphics.drawString(this.font, this.title, 8, 6, 0x404040, false);
        // Draw player inventory label
        guiGraphics.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 94, 0x404040, false);
    }
}
