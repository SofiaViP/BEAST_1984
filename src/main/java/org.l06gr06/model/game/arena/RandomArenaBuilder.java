package org.l06gr06.model.game.arena;

import org.l06gr06.model.Position;
import org.l06gr06.model.game.elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomArenaBuilder extends ArenaBuilder{
    private final Random rng;
    private final int width;
    private final int height;
    private final int numberOfBeasts;
    private final int numberOfBlocks;
    private final int numberOfEggs;
    private final int numberOfWalls;
    private List<Position> occupied = new ArrayList<>();

    public RandomArenaBuilder(int width, int height, int numberOfBeasts, int numberOfBlocks,  int numberOfEggs, int numberOfWalls) {
        this.rng = new Random();
        this.width = width;
        this.height = height;
        this.numberOfBeasts = numberOfBeasts;
        this.numberOfBlocks = numberOfBlocks;
        this.numberOfEggs = numberOfEggs;
        this.numberOfWalls = numberOfWalls;
    }
    @Override
    protected int getWidth() {
        return width;
    }
    @Override
    protected int getHeight() {
        return height;
    }

    public void setOccupied(List<Position> occupied) {
        this.occupied = occupied;
    }

    public List<Position> getOccupied() {
        return occupied;
    }

    private boolean isAvailable(Position pos){
        for (Position position: occupied)
            if (position.equals(pos)) return false;
        return true;
    }

    private Position randomAvailablePosition(){
        int x, y;
        do {
            x = (rng.nextInt(width - 2) + 1);
            y = (rng.nextInt(height - 2) + 1);
        }
        while (!isAvailable(new Position(x,y)));
        return new Position(x,y);
    }

    @Override
    protected List<Beast> createBeasts() {
        List<Beast> beasts = new ArrayList<>();

        while (beasts.size() < numberOfBeasts + numberOfEggs) {
            Position pos = randomAvailablePosition();
            if (beasts.size() < numberOfBeasts) beasts.add(new Beast(pos, 1));
            else beasts.add(new Beast(pos, 0));
            occupied.add(pos);
        }
        return beasts;
    }

    @Override
    protected Player createPlayer() {
        Position pos = randomAvailablePosition();
        Player player = new Player(pos);
        occupied.add(pos);
        return player;
    }

    @Override
    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            walls.add(new Wall(new Position(x, 0)));
            walls.add(new Wall(new Position(x, height - 1)));
            occupied.add(new Position(x, 0));
            occupied.add(new Position(x, height-1));
        }

        for (int y = 1; y < height - 1; y++) {
            walls.add(new Wall(new Position(0, y)));
            walls.add(new Wall(new Position(width - 1, y)));
            occupied.add(new Position(0, y));
            occupied.add(new Position(width-1, y));
        }
        int i = 0;
        while (i < numberOfWalls) {
            Position pos = randomAvailablePosition();
            walls.add(new Wall(pos));
            occupied.add(pos);
            i++;
        }

        return walls;
    }

    @Override
    protected List<Block> createBlocks() {
        List<Block> blocks = new ArrayList<>();

        while (blocks.size() < numberOfBlocks){
            Position pos = randomAvailablePosition();
            blocks.add(new Block(pos));
            occupied.add(pos);
        }
        return blocks;
    }


}