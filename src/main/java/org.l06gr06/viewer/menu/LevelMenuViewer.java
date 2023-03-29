package org.l06gr06.viewer.menu;

import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.menu.LevelMenu;
import org.l06gr06.viewer.Viewer;

public class LevelMenuViewer extends Viewer<LevelMenu> {
    public LevelMenuViewer(LevelMenu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {

        gui.drawText(new Position(20, 5), "LEVELS", "#FFFFFF");
        drawFrame(gui);

        for (int i = 0; i < getModel().getNumberEntries()-1; i++) {
            gui.drawText(
                    new Position(20, 9 + 2 * i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFFF66" : "#FFFFFF");
            for (int j = i; j < getModel().getNumberEntries()-1; j++)
                gui.drawText( new Position(15+j, 13 - 2 * i), "~", "#FFFF66");
        }

        gui.drawText(
                new Position(20,17),
                getModel().getEntry(getModel().getNumberEntries()-1),
                getModel().isSelectedExit() ? "#FFFF66" : "#FFFFFF");
    }
}
