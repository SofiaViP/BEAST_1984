package org.l06gr06.viewer.game;

import org.l06gr06.gui.GUI;
import org.l06gr06.model.game.elements.Beast;

public class BeastViewer implements ElementViewer<Beast> {
    @Override
    public void draw(Beast beast, GUI gui) {
        gui.drawBeast(beast.getPhase(),beast.getPosition());
    }
}
