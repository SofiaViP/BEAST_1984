package org.l06gr06.model.game.arena;

import org.l06gr06.model.Position;
import org.l06gr06.model.game.elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final int width;
    private final int height;
    private Player player;
    private List<Beast> beasts;
    private List<Wall> walls;
    private List<Block> blocks;
    private List<PowerUp> powerUps;
    private long timer;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.beasts = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        this.timer = 0;
    }

    public long getTimer() {
        return timer;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public List<Beast> getBeasts() {
        return beasts;
    }
    public List<Wall> getWalls() {
        return walls;
    }
    public Player getPlayer() {
        return player;
    }
    public List<PowerUp> getPowerUps() {
        return powerUps;
    }
    public List<Block> getBlocks() {
        return blocks;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setBeasts(List<Beast> beasts) {
        this.beasts = beasts;
    }
    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
    public void setPowerUps(List<PowerUp> powerUps) {
        this.powerUps = powerUps;
    }

    public void increaseTimer() { timer++; }

    public boolean canMove(Position position){
        return !isEgg(position) && !isElement(blocks,position) && !isElement(walls,position);
    }

    private Position randomAvailablePosition(){
        Random  rng = new Random();
        int x, y;
        do {
            x = (rng.nextInt(width - 2) + 1);
            y = (rng.nextInt(height - 5) + 4);
        }
        while (!isEmpty(new Position(x,y)));
        return new Position(x,y);
    }
    public boolean isEmpty(Position position) {
        return !isElement(blocks,position) && !isElement(walls,position) && !isElement(beasts,position) && !isElement(powerUps,position);
    }

    public boolean isPlayer(Position position) {
        return player.getPosition().equals(position);
    }
    public boolean isEgg(Position position) {
        for (Beast beast : beasts)
            if (beast.getPosition().equals(position) && beast.getPhase() == 0)
                return true;
        return false;
    }

    public boolean isElement(List<? extends Element> elements, Position position){
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return true;
        return false;
    }

    public void createPowerUp(){
        int a = (int) (Math.random()*2);
        Position pos = randomAvailablePosition();
        if (a == 0) powerUps.add(new Shield(pos));
        else powerUps.add(new Heart(pos));
    }
    public void hatchEggs(){
        for (Beast beast : beasts)
            if (beast.getPhase() == 0) beast.evolve();
    }

    public void respawnPlayer(){
        Position pos = randomAvailablePosition();
        player.setPosition(pos);
    }

    public void hitPlayer(){
        if (player.isImmortal())
            player.backToNormal();
        else {
            player.decreaseLife();
            respawnPlayer();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arena arena = (Arena) o;
        return height == arena.height &&
                width == arena.width &&
                beasts.size() == arena.beasts.size() &&
                blocks.size() == arena.blocks.size() &&
                walls.size() == arena.walls.size();
    }
}