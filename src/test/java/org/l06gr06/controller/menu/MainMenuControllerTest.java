package org.l06gr06.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.Game;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.menu.LevelMenu;
import org.l06gr06.model.menu.MainMenu;
import org.l06gr06.states.LevelMenuState;
import org.l06gr06.states.MainMenuState;
import org.l06gr06.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MainMenuControllerTest {
    private MainMenuController controller;

    private Game game;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        MainMenu menu = new MainMenu();
        controller = new MainMenuController(menu);
        game = new Game(null);
    }

    @Test
    void changeState() throws IOException{
        State<?> initial = new MainMenuState(new MainMenu());
        State<?> actual = game.getState();
        assertEquals(initial,actual);

        controller.step(game, GUI.ACTION.SELECT,1);

        State<?> expected = new LevelMenuState(new LevelMenu());
        actual = game.getState();
        assertEquals(expected,actual);

        State<?> state = actual;
        assertEquals(actual,state);
        assertNotEquals(actual,null);
    }

    @Test
    void equals(){
        MainMenuController mainMenuController = controller;
        assertEquals(mainMenuController,controller);
    }

    @Test
    void notEquals(){
        assertNotEquals(controller,null);
        MainMenu mainMenu = new MainMenu();
        mainMenu.setEntries(Arrays.asList(""));
        MainMenuController mainMenuController1 = new MainMenuController(mainMenu);
        assertNotEquals(controller,mainMenuController1);
    }

}
