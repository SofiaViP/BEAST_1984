package org.l06gr06.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.menu.ScoreMenu;
import org.l06gr06.viewer.menu.ScoreMenuViewer;
import org.mockito.Mockito;

public class ScoreMenuViewerTest {
    private GUI gui;
    private ScoreMenuViewer viewer;
    private ScoreMenu scoreMenu;
    @BeforeEach
    void setUp() {
        long[] stat = {1,2,3,4,5,61};
        scoreMenu = new ScoreMenu(stat);
        viewer = new ScoreMenuViewer(scoreMenu);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawText(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(11+scoreMenu.getNumberEntries())).drawText(Mockito.any(Position.class),Mockito.any(String.class),Mockito.any(String.class));
    }

    @Test
    void drawFrame(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(142)).drawWall(Mockito.any(Position.class));
    }

    @Test
    void drawBeastStats(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(28, 5), "1 x","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(28, 6), "2 x","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(28, 7), "3 x","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawBeast(0,new Position(32, 2));
        Mockito.verify(gui, Mockito.times(1)).drawBeast(1,new Position(32, 3));
        Mockito.verify(gui, Mockito.times(1)).drawBeast(2,new Position(32, 4));
    }

    @Test
    void drawShieldStat(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(28, 9), "4 x","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawShield(new Position(32,6));
    }

    @Test
    void drawHeartStat(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(28, 11), "5 x","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawHeart(new Position(32,8));
    }

    @Test
    void drawTimer(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(22, 13), "Time: 01:01","#FFFFFF");
    }

    @Test
    void drawScore(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(15, 16), "Final Score: 1664","#FFFF66");
    }

    @Test
    void drawEntries(){
        viewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(6, 19), "Play Again","#FFFF66");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(21, 19), "ScoreBoard","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(36, 19), "Exit","#FFFFFF");
    }
}
