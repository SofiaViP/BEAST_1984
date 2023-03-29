package org.l06gr06.viewer.game;

import org.l06gr06.gui.GUI;
import org.l06gr06.model.game.elements.Block;

public class BlockViewer implements ElementViewer<Block> {
    @Override
    public void draw(Block block, GUI gui) {
        gui.drawBlock(block.getPosition());
    }
}