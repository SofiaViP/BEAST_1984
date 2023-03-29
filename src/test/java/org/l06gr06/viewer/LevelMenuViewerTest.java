package org.l06gr06.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.menu.LevelMenu;
import org.l06gr06.viewer.menu.LevelMenuViewer;
import org.mockito.Mockito;

public class LevelMenuViewerTest {
    private GUI gui;
    private LevelMenuViewer viewer;
    private LevelMenu levelMenu;
    @BeforeEach
    void setUp() {
        levelMenu = new LevelMenu();
        viewer = new LevelMenuViewer(levelMenu);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawText(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(7+levelMenu.getNumberEntries())).drawText(Mockito.any(Position.class),Mockito.any(String.class),Mockito.any(String.class));
    }

    @Test
    void drawFrame(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(142)).drawWall(Mockito.any(Position.class));
    }

    @Test
    void drawEntries(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(20, 9), "Easy","#FFFF66");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(20, 11), "Medium","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(20, 13), "Difficult","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(20, 17), "Exit","#FFFFFF");
    }

    @Test
    void drawStars(){
        viewer.drawElements(gui);

        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(17, 9), "~","#FFFF66");

        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(17, 11), "~","#FFFF66");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(16, 11), "~","#FFFF66");

        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(15, 13), "~","#FFFF66");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(16, 13), "~","#FFFF66");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(17, 13), "~","#FFFF66");
    }
}

