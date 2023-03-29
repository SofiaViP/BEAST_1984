package org.l06gr06.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.elements.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerControllerTest {
    private PlayerController controller;
    private Player player;
    private Arena arena;


    @BeforeEach
    void setUp() {
        arena = new Arena(20, 20);

        player = new Player(new Position(5,5));
        arena.setPlayer(player);

        arena.setWalls(Arrays.asList());
        arena.setPowerUps(Arrays.asList());
        arena.setBlocks(Arrays.asList());
        arena.setBeasts(Arrays.asList());

        controller = new PlayerController(arena);
    }

    @Test
    void rightEmpty() {
        controller.step(null,GUI.ACTION.RIGHT,1+arena.getTimer());
        assertEquals(new Position(6, 5), player.getPosition());
    }

    @Test
    void moveRightBlock() {
        Block block1 = new Block(new Position(6,5));
        Block block2 = new Block(new Position(7,5));
        arena.setBlocks(Arrays.asList(block1, block2));

        controller.step(null,GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(new Position(6, 5), player.getPosition());
        assertEquals(new Position(8, 5), block1.getPosition());
        assertEquals(new Position(7, 5), block2.getPosition());
    }

    @Test
    void noMoveRightBlock() {
        Block block1 = new Block(new Position(6,5));
        Block block2 = new Block(new Position(7,5));
        Block block3 = new Block(new Position(8,5));
        arena.setBlocks(Arrays.asList(block1, block2,block3));

        Wall wall = new Wall(new Position(9,5));
        arena.setWalls(Arrays.asList(wall));

        controller.step(null,GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(new Position(5, 5), player.getPosition());
        assertEquals(new Position(6, 5), block1.getPosition());
        assertEquals(new Position(7, 5), block2.getPosition());
        assertEquals(new Position(8, 5), block3.getPosition());
        assertEquals(new Position(9, 5), wall.getPosition());
    }

    @Test
    void rightWall() {
        Wall wall = new Wall(new Position(6,5));
        arena.setWalls(Arrays.asList(wall));

        controller.step(null,GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(new Position(5, 5), player.getPosition());
        assertEquals(new Position(6, 5), wall.getPosition());
    }

    @Test
    void leftEmpty() {
        controller.step(null,GUI.ACTION.LEFT,1+arena.getTimer());
        assertEquals(new Position(4, 5), player.getPosition());
    }

    @Test
    void moveLeftBlock() {
        Block block1 = new Block(new Position(4,5));
        Block block2 = new Block(new Position(3,5));
        arena.setBlocks(Arrays.asList(block1, block2));

        controller.step(null,GUI.ACTION.LEFT,1+arena.getTimer());

        assertEquals(new Position(4, 5), player.getPosition());
        assertEquals(new Position(2, 5), block1.getPosition());
        assertEquals(new Position(3, 5), block2.getPosition());
    }

    @Test
    void noMoveLeftBlock() {
        Block block = new Block(new Position(4,5));
        arena.setBlocks(Arrays.asList(block));

        Wall wall = new Wall(new Position(3,5));
        arena.setWalls(Arrays.asList(wall));

        controller.step(null,GUI.ACTION.LEFT,1+arena.getTimer());

        assertEquals(new Position(5, 5), player.getPosition());
        assertEquals(new Position(4, 5), block.getPosition());
        assertEquals(new Position(3, 5), wall.getPosition());
    }

    @Test
    void leftWall() {
        Wall wall = new Wall(new Position(4,5));
        arena.setWalls(Arrays.asList(wall));

        controller.step(null,GUI.ACTION.LEFT,1+arena.getTimer());

        assertEquals(new Position(5, 5), player.getPosition());
        assertEquals(new Position(4, 5), wall.getPosition());
    }

    @Test
    void upEmpty() {
        controller.step(null,GUI.ACTION.UP,1+arena.getTimer());
        assertEquals(new Position(5, 4), player.getPosition());
    }

    @Test
    void moveUpBlock() {
        Block block1 = new Block(new Position(5,4));
        Block block2 = new Block(new Position(5,3));
        arena.setBlocks(Arrays.asList(block1, block2));

        controller.step(null,GUI.ACTION.UP,1+arena.getTimer());

        assertEquals(new Position(5, 4), player.getPosition());
        assertEquals(new Position(5, 2), block1.getPosition());
        assertEquals(new Position(5, 3), block2.getPosition());
    }

    @Test
    void noMoveUpBlock() {
        Block block = new Block(new Position(5,4));
        arena.setBlocks(Arrays.asList(block));

        Wall wall = new Wall(new Position(5,3));
        arena.setWalls(Arrays.asList(wall));

        controller.step(null,GUI.ACTION.UP,1+arena.getTimer());

        assertEquals(new Position(5, 5), player.getPosition());
        assertEquals(new Position(5, 4), block.getPosition());
        assertEquals(new Position(5, 3), wall.getPosition());
    }

    @Test
    void upWall() {
        Wall wall = new Wall(new Position(5,4));
        arena.setWalls(Arrays.asList(wall));

        controller.step(null,GUI.ACTION.UP,1+arena.getTimer());

        assertEquals(new Position(5, 5), player.getPosition());
        assertEquals(new Position(5, 4), wall.getPosition());
    }

    @Test
    void downEmpty() {
        controller.step(null,GUI.ACTION.DOWN,1+arena.getTimer());
        assertEquals(new Position(5, 6), player.getPosition());
    }

    @Test
    void moveDownBlock() {
        Block block1 = new Block(new Position(5,6));
        Block block2 = new Block(new Position(5,8));
        arena.setBlocks(Arrays.asList(block1, block2));

        controller.step(null,GUI.ACTION.DOWN,1+arena.getTimer());

        assertEquals(new Position(5, 6), player.getPosition());
        assertEquals(new Position(5, 7), block1.getPosition());
        assertEquals(new Position(5, 8), block2.getPosition());
    }

    @Test
    void noMoveDownBlock() {
        Block block = new Block(new Position(5,6));
        arena.setBlocks(Arrays.asList(block));

        Wall wall = new Wall(new Position(5,7));
        arena.setWalls(Arrays.asList(wall));

        controller.step(null,GUI.ACTION.DOWN,1+arena.getTimer());

        assertEquals(new Position(5, 5), player.getPosition());
        assertEquals(new Position(5, 6), block.getPosition());
        assertEquals(new Position(5, 7), wall.getPosition());
    }

    @Test
    void downWall() {
        Wall wall = new Wall(new Position(5,6));
        arena.setWalls(Arrays.asList(wall));

        controller.step(null,GUI.ACTION.DOWN,1+arena.getTimer());

        assertEquals(new Position(5, 5), player.getPosition());
        assertEquals(new Position(5, 6), wall.getPosition());
    }

    @Test
    void getStats(){
        controller.step(null, GUI.ACTION.DOWN,1000+arena.getTimer());

        long[] stats = controller.getStats();
        long nrEggs = stats[0];
        long nrBeasts = stats[1];
        long nrStrongBeasts = stats[2];
        long nrShields = stats[3];

        assertEquals(0, nrEggs);
        assertEquals(0, nrBeasts);
        assertEquals(0, nrStrongBeasts);
        assertEquals(0, nrShields);
    }

    @Test
    void backToNormal(){
        player.becomeImmortal();

        Beast beast = new Beast(new Position(6,5),1);
        arena.setBeasts(Arrays.asList(beast));

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(5, player.getLife());
        assertEquals(0, player.getPhase());
    }

    @Test
    void decreaseLife(){
        Beast beast = new Beast(new Position(6,5),1);
        arena.setBeasts(Arrays.asList(beast));

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(4, player.getLife());
    }

    @Test
    void increaseLife(){
        Heart heart = new Heart(new Position(6,5));
        List<PowerUp> powerUps = new ArrayList<>();
        powerUps.add(heart);
        arena.setPowerUps(powerUps);

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(6, player.getLife());
        assertEquals(0, powerUps.size());
    }

    @Test
    void noIncreaseLife(){
        Heart heart = new Heart(new Position(6,5));
        List<PowerUp> powerUps = new ArrayList<>();
        powerUps.add(heart);
        arena.setPowerUps(powerUps);

        player.increaseLife();
        player.increaseLife();
        player.increaseLife();

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(8, player.getLife());
        assertEquals(0, powerUps.size());
    }

    @Test
    void immortalTimeout(){
        player.becomeImmortal();
        player.setImmortalTime(1);

        controller.step(null, GUI.ACTION.RIGHT,10002);
        assertEquals(5, player.getLife());
        assertEquals(0, player.getPhase());
    }

    @Test
    void boundaryImmortalTime(){
        player.becomeImmortal();
        player.setImmortalTime(1);

        controller.step(null, GUI.ACTION.RIGHT,10001);
        assertEquals(1, player.getPhase());
    }
    @Test
    void becomeImmortal(){
        Shield shield = new Shield(new Position(6,5));
        List<PowerUp> powerUps = new ArrayList<>();
        powerUps.add(shield);
        arena.setPowerUps(powerUps);

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(1, player.getPhase());
        assertEquals(10, player.getImmortalDuration());
        assertEquals(0, powerUps.size());

        long[] stats = controller.getStats();
        long nrShields = stats[3];
        assertEquals(1, nrShields);
    }

    @Test
    void smashPowerUp(){
        List<PowerUp> powerUps = new ArrayList<>();
        powerUps.add(new PowerUp(new Position(7,5)));
        arena.setPowerUps(powerUps);

        Wall wall = new Wall(new Position(8,5));
        arena.setWalls(Arrays.asList(wall));

        Block block = new Block(new Position(6,5));
        arena.setBlocks(Arrays.asList(block));

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(new Position(6, 5), player.getPosition());
        assertEquals(new Position(7, 5), block.getPosition());
        assertEquals(new Position(8, 5), wall.getPosition());
        assertEquals(0, powerUps.size());
    }

    @Test
    void smashEgg(){
        List<Beast> beasts = new ArrayList<>();
        beasts.add(new Beast(new Position(7,5),0));
        arena.setBeasts(beasts);

        Block block1 = new Block(new Position(6,5));
        Block block2 = new Block(new Position(8,5));
        arena.setBlocks(Arrays.asList(block1,block2));

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(new Position(6, 5), player.getPosition());
        assertEquals(new Position(7, 5), block1.getPosition());
        assertEquals(new Position(8, 5), block2.getPosition());
        assertEquals(0, beasts.size());

        long[] stats = controller.getStats();
        long nrEggs = stats[0];
        assertEquals(1, nrEggs);
    }

    @Test
    void smashBeast(){
        List<Beast> beasts = new ArrayList<>();
        beasts.add(new Beast(new Position(7,5),1));
        arena.setBeasts(beasts);

        Wall wall = new Wall(new Position(8,5));
        arena.setWalls(Arrays.asList(wall));

        Block block = new Block(new Position(6,5));
        arena.setBlocks(Arrays.asList(block));

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(new Position(6, 5), player.getPosition());
        assertEquals(new Position(7, 5), block.getPosition());
        assertEquals(new Position(8, 5), wall.getPosition());
        assertEquals(0, beasts.size());

        long[] stats = controller.getStats();
        long nrBeasts = stats[1];
        assertEquals(1, nrBeasts);
    }

    @Test
    void smashStrongerBeast(){
        List<Beast> beasts = new ArrayList<>();
        beasts.add(new Beast(new Position(7,5),2));
        arena.setBeasts(beasts);

        Wall wall = new Wall(new Position(8,5));
        arena.setWalls(Arrays.asList(wall));

        Block block = new Block(new Position(6,5));
        arena.setBlocks(Arrays.asList(block));

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(new Position(6, 5), player.getPosition());
        assertEquals(new Position(7, 5), block.getPosition());
        assertEquals(new Position(8, 5), wall.getPosition());
        assertEquals(0, beasts.size());

        long[] stats = controller.getStats();
        long nrStrongBeasts = stats[2];
        assertEquals(1, nrStrongBeasts);
    }

    @Test
    void cantSmashStrongerBeast(){
        List<Beast> beasts = new ArrayList<>();
        Beast beast = new Beast(new Position(7,5),2);
        beasts.add(beast);
        arena.setBeasts(beasts);

        Block block1 = new Block(new Position(6,5));
        Block block2 = new Block(new Position(8,5));
        arena.setBlocks(Arrays.asList(block1,block2));

        controller.step(null, GUI.ACTION.RIGHT,1+arena.getTimer());

        assertEquals(new Position(5, 5), player.getPosition());
        assertEquals(new Position(6, 5), block1.getPosition());
        assertEquals(new Position(8, 5), block2.getPosition());
        assertEquals(new Position(7, 5), beast.getPosition());

        long[] stats = controller.getStats();
        long nrStrongBeasts = stats[2];
        assertEquals(0, nrStrongBeasts);
    }
}
