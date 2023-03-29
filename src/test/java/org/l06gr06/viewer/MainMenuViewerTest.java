package org.l06gr06.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.menu.MainMenu;
import org.l06gr06.viewer.menu.MainMenuViewer;
import org.mockito.Mockito;

public class MainMenuViewerTest {
    private GUI gui;
    private MainMenuViewer viewer;
    private MainMenu mainMenu;

    @BeforeEach
    void setUp() {
        mainMenu = new MainMenu();
        viewer = new MainMenuViewer(mainMenu);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawText() {
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(8 + mainMenu.getNumberEntries())).drawText(Mockito.any(Position.class), Mockito.any(String.class), Mockito.any(String.class));
    }

    @Test
    void drawFrame(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(142)).drawWall(Mockito.any(Position.class));
    }

    @Test
    void drawEntry(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(15, 18), "[Press enter to play]","#FFFF66");
    }
}
