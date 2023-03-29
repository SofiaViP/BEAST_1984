package org.l06gr06.viewer.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.elements.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
public class GameViewerTest {
    private GUI gui;
    private GameViewer viewer;
    private Arena arena;
    @BeforeEach
    void setUp() {
        arena = new Arena(20, 20);
        arena.setPlayer(new Player(new Position(5,8)));
        gui = Mockito.mock(GUI.class);
        viewer = new GameViewer(arena);
    }
    @Test
    void drawWalls() throws IOException {
        arena.setWalls(Arrays.asList(new Wall(new Position(1,2)), new Wall(new Position(2,3)), new Wall(new Position(3,4))));
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(1, 2));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(2, 3));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(3, 4));
        Mockito.verify(gui, Mockito.times(3)).drawWall(Mockito.any(Position.class));
    }
    @Test
    void drawBeasts() throws IOException {
        arena.setBeasts(Arrays.asList(new Beast(new Position(4,5), 0), new Beast(new Position(5,6),1)));
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawBeast(0,new Position(4, 5));
        Mockito.verify(gui, Mockito.times(1)).drawBeast(1,new Position(5, 6));
        Mockito.verify(gui, Mockito.times(2)).drawBeast(Mockito.anyInt(),Mockito.any(Position.class));
    }
    @Test
    void drawPlayer() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(0, new Position(5, 8));
    }
    @Test
    void drawBlocks() throws IOException {
        arena.setBlocks(Arrays.asList(new Block(new Position(6, 7)), new Block(new Position(7,7)), new Block(new Position(7,8))));
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawBlock(new Position(6, 7));
        Mockito.verify(gui, Mockito.times(1)).drawBlock(new Position(7, 7));
        Mockito.verify(gui, Mockito.times(1)).drawBlock(new Position(7, 8));
        Mockito.verify(gui, Mockito.times(3)).drawBlock(Mockito.any(Position.class));
    }
    @Test
    void drawPowerUps() throws IOException {
        arena.setPowerUps(Arrays.asList(new Heart(new Position(1,1)), new Shield(new Position(8,8)), new Heart(new Position(9,9))));
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawHeart(new Position(1, 1));
        Mockito.verify(gui, Mockito.times(1)).drawShield(new Position(8, 8));
        Mockito.verify(gui, Mockito.times(1)).drawHeart(new Position(9, 9));
        Mockito.verify(gui, Mockito.times(2)).drawHeart(Mockito.any(Position.class));
        Mockito.verify(gui, Mockito.times(1)).drawShield(Mockito.any(Position.class));
    }
    @Test
    void drawText() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(7)).drawText(Mockito.any(Position.class),Mockito.any(String.class),Mockito.any(String.class));
    }
    @Test
    void drawLives() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(1,1),"Lives: ", "#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(7,1),"@", "#FC0808");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(8,1),"@", "#FC0808");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(9,1),"@", "#FC0808");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(10,1),"@", "#FC0808");
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(11,1),"@", "#FC0808");
    }
    @Test
    void drawTimer() throws IOException {
        arena.setTimer(3050);
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawText(new Position(37,1),"Timer: 01:01", "#FFFFFF");
    }
    @Test
    void refreshAndClear() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
    @Test
    void equals(){
        GameViewer gameViewer1 = viewer;
        Assertions.assertEquals(gameViewer1,viewer);
        GameViewer gameViewer2 = new GameViewer(new Arena(20,20));
        Assertions.assertEquals(gameViewer2,viewer);
    }
    @Test
    void notEquals(){
        GameViewer gameViewer2 = new GameViewer(new Arena(19,20));
        Assertions.assertNotEquals(gameViewer2,viewer);
        Assertions.assertNotEquals(viewer,null);
    }
}
