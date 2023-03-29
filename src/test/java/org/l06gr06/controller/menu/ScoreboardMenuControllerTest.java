package org.l06gr06.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.Game;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.menu.LevelMenu;
import org.l06gr06.model.menu.ScoreboardMenu;
import org.l06gr06.states.LevelMenuState;
import org.l06gr06.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ScoreboardMenuControllerTest {
    private ScoreboardMenuController controller;
    private ScoreboardMenu menu;
    private  Game game;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        menu = new ScoreboardMenu();
        controller = new ScoreboardMenuController(menu);
        game = new Game(null);
    }

    @Test
    void nextEntry() throws IOException {
        controller.step(null, GUI.ACTION.RIGHT,0);
        assertEquals(1, menu.getCurrentEntry());
    }

    @Test
    void previousEntry() throws IOException {
        controller.step(null, GUI.ACTION.RIGHT,1);
        controller.step(null, GUI.ACTION.LEFT,1);
        assertEquals(0, menu.getCurrentEntry());
    }

    @Test
    void exit() throws IOException{
        controller.step(game, GUI.ACTION.RIGHT,1);
        controller.step(game, GUI.ACTION.SELECT,1);
        assertNull(game.getState());
    }

    @Test
    void playAgain() throws IOException{
        controller.step(game, GUI.ACTION.SELECT,1);
        State<?> expected = new LevelMenuState(new LevelMenu());
        State<?> actual = game.getState();
        assertEquals(expected,actual);
    }
}
