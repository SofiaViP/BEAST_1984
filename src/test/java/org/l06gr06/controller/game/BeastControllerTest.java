package org.l06gr06.controller.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.elements.Beast;
import org.l06gr06.model.game.elements.Player;
import org.l06gr06.model.game.elements.PowerUp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BeastControllerTest {
    private BeastController controller;
    private Beast egg;
    private Beast beast;
    private Arena arena;
    private Player player;

    @BeforeEach
    void setUp() {
        arena = new Arena(20, 20);
        egg = new Beast(new Position(1,1),0);
        beast = new Beast(new Position(10,10),1);
        player = new Player(new Position(5,5));

        List<Beast> beasts = new ArrayList<>();
        beasts.add(egg);
        beasts.add(beast);

        arena.setPlayer(player);
        arena.setWalls(new ArrayList<>());
        arena.setPowerUps(new ArrayList<>());
        arena.setBlocks(new ArrayList<>());
        arena.setBeasts(beasts);

        controller = new BeastController(arena);
    }

    @Test
    void hatchEgg() throws IOException{
        arena.setTimer(2000);
        controller.step(null, GUI.ACTION.UP, 0);
        Assertions.assertEquals(1, egg.getPhase());
    }

    @Test
    void speedUp() throws IOException {
        Position position = mock(Position.class);
        beast.setPosition(position);

        arena.getPowerUps().add(new PowerUp(new Position(10,9)));
        when(position.getCloser(player.getPosition())).thenReturn(new Position(10,9));

        controller.step(null,GUI.ACTION.UP, controller.getSpeed()+1);
        Assertions.assertEquals(450, controller.getSpeed());
        Assertions.assertEquals(501,controller.getLastMovement());
    }

    @Test
    void evolve() throws IOException {
        Position position = mock(Position.class);
        beast.setPosition(position);

        arena.getPowerUps().add(new PowerUp(new Position(10,9)));
        when(position.getCloser(player.getPosition())).thenReturn(new Position(10,9));

        controller.step(null,GUI.ACTION.UP, controller.getSpeed()+1);
        Assertions.assertEquals(2,beast.getPhase());
        Assertions.assertEquals(5,player.getLife());
    }

    @Test
    void hitImmortalPlayer() throws IOException {
        Position position = mock(Position.class);
        beast.setPosition(position);
        player.setPosition(new Position(10,9));
        player.becomeImmortal();

        when(position.getCloser(player.getPosition())).thenReturn(new Position(10,9));

        controller.step(null,GUI.ACTION.UP, controller.getSpeed()+1);
        Assertions.assertEquals(0,player.getPhase());
    }

    @Test
    void hitNormalPlayer() throws IOException {
        Position position = mock(Position.class);
        beast.setPosition(position);
        player.setPosition(new Position(10,9));

        when(position.getCloser(player.getPosition())).thenReturn(new Position(10,9));

        controller.step(null,GUI.ACTION.UP, controller.getSpeed()+1);
        Assertions.assertEquals(0,player.getPhase());
        Assertions.assertEquals(4,player.getLife());
    }

    @Test
    void noMove() throws IOException {
        controller.step(null, GUI.ACTION.UP,500);
        Assertions.assertEquals(new Position(1,1),egg.getPosition());
        Assertions.assertEquals(new Position(10,10),beast.getPosition());
        Assertions.assertEquals(0,controller.getLastMovement());
    }
    @Test
    void moveBeast() throws IOException {
        Position position = mock(Position.class);
        beast.setPosition(position);

        when(position.getCloser(player.getPosition())).thenReturn(new Position(10,9));

        controller.step(null,GUI.ACTION.UP, controller.getSpeed()+1);

        Assertions.assertEquals(new Position(1,1),egg.getPosition());
        Assertions.assertEquals(new Position(10,9),beast.getPosition());
        Assertions.assertEquals(5,player.getLife());
    }
}
