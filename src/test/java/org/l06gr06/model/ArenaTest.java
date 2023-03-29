package org.l06gr06.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.elements.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ArenaTest {
    private Arena arena;

    @BeforeEach
    public void setArena(){
        arena = new Arena(10,10);
    }

    @Test
    public void isEgg(){
        arena.setBeasts(Arrays.asList(new Beast(new Position(1,1),2), new Beast(new Position(2,2),1),new Beast(new Position(3,3),0)));
        Assertions.assertTrue(arena.isEgg(new Position(3,3)));
        Assertions.assertFalse(arena.isEgg(new Position(1,1)));
        Assertions.assertFalse(arena.isEgg(new Position(4,4)));
    }

    @Test
    public void isBeast(){
        arena.setBeasts(Arrays.asList(new Beast(new Position(1,1),2), new Beast(new Position(2,2),1),new Beast(new Position(3,3),0)));
        Assertions.assertTrue(arena.isElement(arena.getBeasts(),new Position(1,1)));
        Assertions.assertTrue(arena.isElement(arena.getBeasts(),new Position(3,3)));
        Assertions.assertFalse(arena.isElement(arena.getBeasts(),new Position(4,4)));
    }

    @Test
    public void isWall(){
        arena.setWalls(Arrays.asList(new Wall(new Position(1,1)), new Wall(new Position(2,2)),new Wall(new Position(3,3))));
        Assertions.assertTrue(arena.isElement(arena.getWalls(),new Position(3,3)));
        Assertions.assertTrue(arena.isElement(arena.getWalls(),new Position(1,1)));
        Assertions.assertFalse(arena.isElement(arena.getWalls(),new Position(4,4)));
    }

    @Test
    public void isBlock(){
        arena.setBlocks(Arrays.asList(new Block(new Position(1,1)), new Block(new Position(2,2)),new Block(new Position(3,3))));
        Assertions.assertTrue(arena.isElement(arena.getBlocks(),new Position(3,3)));
        Assertions.assertTrue(arena.isElement(arena.getBlocks(),new Position(1,1)));
        Assertions.assertFalse(arena.isElement(arena.getBlocks(),new Position(4,4)));
    }

    @Test
    public void isPowerUp(){
        Assertions.assertEquals(0,arena.getPowerUps().size());
        arena.setPowerUps(Arrays.asList(new PowerUp(new Position(1,1)), new Heart(new Position(2,2)),new Shield(new Position(3,3))));
        Assertions.assertTrue(arena.isElement(arena.getPowerUps(),new Position(3,3)));
        Assertions.assertTrue(arena.isElement(arena.getPowerUps(),new Position(2,2)));
        Assertions.assertTrue(arena.isElement(arena.getPowerUps(),new Position(1,1)));
        Assertions.assertFalse(arena.isElement(arena.getPowerUps(),new Position(4,4)));
    }

    @Test
    public void isEmpty(){
        arena.setBeasts(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.setBlocks(new ArrayList<>());
        arena.setPowerUps(new ArrayList<>());
        for (int i = 0; i <= 10; i++)
            for (int j = 0; j <= 10; j++)
                Assertions.assertTrue(arena.isEmpty(new Position(i,j)));
    }

    @Test
    public void hatchEggs(){
        arena.setBeasts(Arrays.asList(new Beast(new Position(1,1) ,0), new Beast(new Position(2,2),1), new Beast(new Position(3,3),0), new Beast(new Position(2,4),1)));
        arena.hatchEggs();
        for (Beast beast : arena.getBeasts())
            Assertions.assertTrue(beast.getPhase() != 0);
    }

    @Test
    public void respawnPlayer(){
        arena.setPlayer(new Player(new Position(1,1)));
        Position initialPos = arena.getPlayer().getPosition();
        arena.respawnPlayer();
        Position finalPos = arena.getPlayer().getPosition();
        Assertions.assertNotEquals(finalPos,initialPos);
    }

    @Test
    public void hitPlayer(){
        arena.setPlayer(new Player(new Position(1,1)));
        Position initialPos = arena.getPlayer().getPosition();
        arena.hitPlayer();
        Assertions.assertEquals(4,arena.getPlayer().getLife());
        Position finalPos = arena.getPlayer().getPosition();
        Assertions.assertNotEquals(finalPos,initialPos);
    }
    @Test
    public void equals(){
        Arena arena1 = arena;
        Assertions.assertEquals(arena,arena1);
    }

    @Test
    public void nullArena(){
        Assertions.assertNotEquals(arena, null);
    }
}
