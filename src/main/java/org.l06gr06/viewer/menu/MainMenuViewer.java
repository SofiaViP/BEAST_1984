package org.l06gr06.viewer.menu;

import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.menu.MainMenu;
import org.l06gr06.viewer.Viewer;

public class MainMenuViewer extends Viewer<MainMenu> {
    public MainMenuViewer(MainMenu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {

        gui.drawText(new Position(6, 6),  "HHHH     HHHHH    HHH     HHHH   HHHHH", "#FFFFFF");
        gui.drawText(new Position(6, 7),  "H   H    H       H   H   H         H", "#FFFFFF");
        gui.drawText(new Position(6, 8),  "H   H    H       H   H   H         H"  , "#FFFFFF");
        gui.drawText(new Position(6, 9),  "HHHH     HHH     HH HH    HHH      H", "#FFFFFF");
        gui.drawText(new Position(6, 10), "H   H    H       H   H       H     H", "#FFFFFF");
        gui.drawText(new Position(6, 11), "H   H    H       H   H       H     H", "#FFFFFF");
        gui.drawText(new Position(6, 12), "HHHH     HHHHH   H   H   HHHH      H", "#FFFFFF");
        gui.drawText(new Position(25, 9), "&", "#00FFEF");

        drawFrame(gui);

        gui.drawText(new Position(15, 18), getModel().getEntry(0), "#FFFF66");
    }
}
