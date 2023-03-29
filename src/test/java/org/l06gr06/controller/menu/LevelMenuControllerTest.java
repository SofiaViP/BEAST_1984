package org.l06gr06.controller.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.Game;
import org.l06gr06.controller.game.ArenaController;
import org.l06gr06.controller.game.PlayerController;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.game.arena.RandomArenaBuilder;
import org.l06gr06.model.menu.LevelMenu;
import org.l06gr06.model.menu.ScoreboardMenu;
import org.l06gr06.states.GameState;
import org.l06gr06.states.State;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LevelMenuControllerTest {
    private LevelMenuController controller;

    private LevelMenu menu;

    private Game game;

    private Method saveLevelMethod() throws NoSuchMethodException {
        Method method = LevelMenuController.class.getDeclaredMethod("saveLevel", int.class);
        method.setAccessible(true);
        return method;
    }
    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        menu = new LevelMenu();
        controller = new LevelMenuController(menu);
        game = new Game(null);
        
        URL resource = ArenaController.class.getResource("/score/score.csv");
        assert resource != null;
        BufferedWriter writer = new BufferedWriter(new FileWriter(resource.getFile(), false));
        writer.append("");
        writer.close();
    }

    @Test
    void nextEntry() throws IOException {
        controller.step(null, GUI.ACTION.DOWN,1);
        assertEquals(1, menu.getCurrentEntry());
    }

    @Test
    void previousEntry() throws IOException {
        controller.step(null, GUI.ACTION.DOWN,1);
        controller.step(null, GUI.ACTION.DOWN,1);
        controller.step(null, GUI.ACTION.UP,1);
        assertEquals(1, menu.getCurrentEntry());
    }

    @Test
    void exit() throws IOException {
        controller.step(game, GUI.ACTION.DOWN,1);
        controller.step(game, GUI.ACTION.DOWN,1);
        controller.step(game, GUI.ACTION.DOWN,1);
        controller.step(game, GUI.ACTION.SELECT,1);
        assertNull(game.getState());
    }
    @Test
    void easy() throws IOException {
        controller.step(game, GUI.ACTION.SELECT,1);
        assertTrue(menu.isSelected(0));
        assertFalse(menu.isSelected(1));
        assertFalse(menu.isSelected(2));
        assertFalse(menu.isSelected(3));
        State<?> expected = new GameState((new RandomArenaBuilder(50, 20, 2, 150, 1, 15).createArena()));
        State<?> actual = game.getState();
        assertEquals(expected,actual);

        URL resource = ScoreboardMenu.class.getResource("/score/score.csv");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null;) lines.add(line);
        String lastScore = lines.get(lines.size()-1);
        assertEquals("~,",lastScore);

    }
    @Test
    void medium() throws IOException {
        controller.step(game, GUI.ACTION.DOWN,1);
        controller.step(game, GUI.ACTION.SELECT,1);
        assertFalse(menu.isSelected(0));
        assertTrue(menu.isSelected(1));
        assertFalse(menu.isSelected(2));
        assertFalse(menu.isSelected(3));

        State<?> expected = new GameState((new RandomArenaBuilder(50, 20, 4, 100, 4, 10).createArena()));
        State<?> actual = game.getState();
        assertEquals(expected,actual);

        URL resource = ScoreboardMenu.class.getResource("/score/score.csv");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null;) lines.add(line);
        String lastScore = lines.get(lines.size()-1);
        assertEquals("~~,",lastScore);
    }
    @Test
    void difficult() throws IOException {
        controller.step(game, GUI.ACTION.DOWN,1);
        controller.step(game, GUI.ACTION.DOWN,1);
        controller.step(game, GUI.ACTION.SELECT,1);
        assertFalse(menu.isSelected(0));
        assertFalse(menu.isSelected(1));
        assertTrue(menu.isSelected(2));
        assertFalse(menu.isSelected(3));
        State<?> expected = new GameState((new RandomArenaBuilder(50, 20, 6, 50, 7, 5).createArena()));
        State<?> actual = game.getState();
        assertEquals(expected,actual);
        assertNotEquals(new GameState((new RandomArenaBuilder(30, 20, 6, 50, 7, 5).createArena())),actual);

        URL resource = ScoreboardMenu.class.getResource("/score/score.csv");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null;) lines.add(line);
        String lastScore = lines.get(lines.size()-1);
        assertEquals("~~~,",lastScore);
    }

    @Test
    void saveLevel() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        saveLevelMethod().invoke(controller,3);

        URL resource = ScoreboardMenu.class.getResource("/score/score.csv");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null;) lines.add(line);
        String lastScore = lines.get(lines.size()-1);
        assertEquals("~~~,",lastScore);
    }

    @AfterEach
    void cleanUp() throws IOException {
        URL resource = ArenaController.class.getResource("/score/score.csv");
        assert resource != null;
        BufferedWriter writer = new BufferedWriter(new FileWriter(resource.getFile(), false));
        writer.append("");
        writer.close();
    }

}
