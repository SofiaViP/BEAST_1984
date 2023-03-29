package org.l06gr06.model;

import org.junit.jupiter.api.Test;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.arena.RandomArenaBuilder;
import org.l06gr06.model.game.elements.Beast;
import org.l06gr06.model.game.elements.Block;
import org.l06gr06.model.game.elements.Wall;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaBuilderTest {

    private Method isAvailableMethod() throws NoSuchMethodException {
        Method method = RandomArenaBuilder.class.getDeclaredMethod("isAvailable", Position.class);
        method.setAccessible(true);
        return method;
    }

    private Method createBeastsMethod() throws NoSuchMethodException {
        Method method = RandomArenaBuilder.class.getDeclaredMethod("createBeasts");
        method.setAccessible(true);
        return method;
    }


    private Method createWallsMethod() throws NoSuchMethodException {
        Method method = RandomArenaBuilder.class.getDeclaredMethod("createWalls");
        method.setAccessible(true);
        return method;
    }

    private Method createBlocksMethod() throws NoSuchMethodException {
        Method method = RandomArenaBuilder.class.getDeclaredMethod("createBlocks");
        method.setAccessible(true);
        return method;
    }
    @Test
    void isAvailable() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RandomArenaBuilder arenaBuilder = new RandomArenaBuilder(20,20,3,6, 5,10);
        arenaBuilder.setOccupied(Arrays.asList(new Position(0,0),new Position(1,1)));
        assertFalse((Boolean) isAvailableMethod().invoke(arenaBuilder,new Position(0,0)));
        assertTrue((Boolean) isAvailableMethod().invoke(arenaBuilder,new Position(2,2)));
    }
    @Test
    void createArena(){
        RandomArenaBuilder arenaBuilder = new RandomArenaBuilder(20,20,3,6, 5,10);
        Arena arena = arenaBuilder.createArena();
        assertEquals(20,arena.getWidth());
        assertEquals(20,arena.getHeight());
        assertEquals(8,arena.getBeasts().size());
        assertEquals(5,arena.getPlayer().getLife());
        assertEquals(0,arena.getPowerUps().size());
        assertEquals(6,arena.getBlocks().size());
        int expected_walls = (arena.getWidth()*2 + (arena.getHeight())*2 -4) + 10;
        assertEquals(expected_walls,arena.getWalls().size());
    }

    @Test
    void createBeasts() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RandomArenaBuilder arenaBuilder = new RandomArenaBuilder(20,20,3,6, 5,10);
        List<Beast> beasts = (List<Beast>) createBeastsMethod().invoke(arenaBuilder);
        assertEquals(1,beasts.get(0).getPhase());
        assertEquals(1,beasts.get(1).getPhase());
        assertEquals(1,beasts.get(2).getPhase());

        assertEquals(0,beasts.get(3).getPhase());
        assertEquals(0,beasts.get(4).getPhase());
        assertEquals(0,beasts.get(5).getPhase());
        assertEquals(0,beasts.get(6).getPhase());
        assertEquals(0,beasts.get(7).getPhase());

        assertEquals(8,beasts.size());
    }

    @Test
    void createWalls() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RandomArenaBuilder arenaBuilder = new RandomArenaBuilder(5,5,0,0, 0,0);
        List<Wall> walls = (List<Wall>) createWallsMethod().invoke(arenaBuilder);

        assertEquals(new Wall(new Position(0,0)),walls.get(0));
        assertEquals(new Wall(new Position(0,4)),walls.get(1));
        assertEquals(new Wall(new Position(1,0)),walls.get(2));
        assertEquals(new Wall(new Position(1,4)),walls.get(3));
        assertEquals(new Wall(new Position(2,0)),walls.get(4));
        assertEquals(new Wall(new Position(2,4)),walls.get(5));
        assertEquals(new Wall(new Position(3,0)),walls.get(6));
        assertEquals(new Wall(new Position(3,4)),walls.get(7));
        assertEquals(new Wall(new Position(4,0)),walls.get(8));
        assertEquals(new Wall(new Position(4,4)),walls.get(9));
        assertEquals(new Wall(new Position(0,1)),walls.get(10));
        assertEquals(new Wall(new Position(4,1)),walls.get(11));
        assertEquals(new Wall(new Position(0,2)),walls.get(12));
        assertEquals(new Wall(new Position(4,2)),walls.get(13));
        assertEquals(new Wall(new Position(0,3)),walls.get(14));
        assertEquals(new Wall(new Position(4,3)),walls.get(15));
        assertEquals(16,walls.size());

        assertEquals(new Position(0,0),arenaBuilder.getOccupied().get(0));
        assertEquals(new Position(0,4),arenaBuilder.getOccupied().get(1));
        assertEquals(new Position(1,0),arenaBuilder.getOccupied().get(2));
        assertEquals(new Position(1,4),arenaBuilder.getOccupied().get(3));
        assertEquals(new Position(2,0),arenaBuilder.getOccupied().get(4));
        assertEquals(new Position(2,4),arenaBuilder.getOccupied().get(5));
        assertEquals(new Position(3,0),arenaBuilder.getOccupied().get(6));
        assertEquals(new Position(3,4),arenaBuilder.getOccupied().get(7));
        assertEquals(new Position(4,0),arenaBuilder.getOccupied().get(8));
        assertEquals(new Position(4,4),arenaBuilder.getOccupied().get(9));
        assertEquals(new Position(0,1),arenaBuilder.getOccupied().get(10));
        assertEquals(new Position(4,1),arenaBuilder.getOccupied().get(11));
        assertEquals(new Position(0,2),arenaBuilder.getOccupied().get(12));
        assertEquals(new Position(4,2),arenaBuilder.getOccupied().get(13));
        assertEquals(new Position(0,3),arenaBuilder.getOccupied().get(14));
        assertEquals(new Position(4,3),arenaBuilder.getOccupied().get(15));
        assertEquals(16, arenaBuilder.getOccupied().size());
    }

    @Test
    void createBlocks() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RandomArenaBuilder arenaBuilder = new RandomArenaBuilder(5,5,0,8, 0,0);
        List<Block> blocks = (List<Block>) createBlocksMethod().invoke(arenaBuilder);
        assertEquals(8,blocks.size());
    }
}
