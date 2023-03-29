package org.l06gr06.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.menu.ScoreboardMenu;
import org.l06gr06.viewer.menu.ScoreboardMenuViewer;
import org.mockito.Mockito;

import java.io.IOException;

public class ScoreboardMenuViewerTest {
    private GUI gui;
    private ScoreboardMenuViewer viewer;
    private ScoreboardMenu scoreBoardMenu;
    @BeforeEach
    void setUp() throws IOException {
        scoreBoardMenu = new ScoreboardMenu();
        viewer = new ScoreboardMenuViewer(scoreBoardMenu,"scoreTest.csv");
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawText(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(16+scoreBoardMenu.getNumberEntries())).drawText(Mockito.any(Position.class),Mockito.any(String.class),Mockito.any(String.class));
    }
    @Test
    void drawFrame(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(142)).drawWall(Mockito.any(Position.class));
    }
    @Test
    void drawScoreboard(){
        viewer.drawElements(gui);

        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(7, 7), "1.","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(7, 8), "2.","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(7, 9), "3.","#FFFFFF");

        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(12, 7), "~~","#FFFF66");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(12, 8), "~","#FFFF66");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(12, 9), "~~~","#FFFF66");

        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(23, 7), "102","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(23, 8), "40","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(23, 9), "12","#FFFFFF");

        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(34, 7), "4:50","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(34, 8), "3:49","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(34, 9), "23:30","#FFFFFF");
    }
    @Test
    void drawEntries(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(15, 19), "Play again","#FFFF66");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(30, 19), "Exit","#FFFFFF");
    }
}
