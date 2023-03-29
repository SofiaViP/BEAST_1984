package org.l06gr06.controller.game;

import org.l06gr06.Game;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.elements.*;

import java.util.List;

public class PlayerController extends GameController {
    private GUI.ACTION action;
    private final long[] stats;

    public PlayerController(Arena arena) {
        super(arena);
        this.stats = new long[6];
    }
    private void movePlayerLeft() {
        movePlayer(getModel().getPlayer().getPosition().getLeft());
    }

    private void movePlayerRight() {
        movePlayer(getModel().getPlayer().getPosition().getRight());
    }

    private void movePlayerUp() {
        movePlayer(getModel().getPlayer().getPosition().getUp());
    }

    private void movePlayerDown() {
        movePlayer(getModel().getPlayer().getPosition().getDown());
    }

    private void moveblock(Position position){

        Arena arena = getModel();
        List<Block> blocks = arena.getBlocks();

        int j = blocks.indexOf(new Block(position));
        Block block = blocks.get(j);

        Position nextAvailablePos = new Position(position.getX(), position.getY());
        do nextAvailablePos = nextAvailablePos.getDirection(action);
        while (arena.isElement(blocks,nextAvailablePos));

        if (arena.isElement(arena.getWalls(),nextAvailablePos)) return;

        boolean nextToWall = arena.isElement(arena.getWalls(),nextAvailablePos.getDirection(action));
        boolean nextToBlock = arena.isElement(blocks,nextAvailablePos.getDirection(action));

        if (nextToWall || nextToBlock){
            if (arena.isElement(arena.getPowerUps(),nextAvailablePos))
                arena.getPowerUps().remove(new PowerUp(nextAvailablePos));

            else if (arena.isElement(arena.getBeasts(),nextAvailablePos)){
                int i = arena.getBeasts().indexOf(new Beast(nextAvailablePos,0));
                Beast beast = arena.getBeasts().get(i);
                if (beast.getPhase() < 2 || nextToWall){
                    stats[beast.getPhase()]++;
                    arena.getBeasts().remove(i);
                }
            }
        }
        if (!arena.isEmpty(nextAvailablePos)) return;
        block.setPosition(nextAvailablePos);
        arena.getPlayer().setPosition(position);
    }
    private void movePlayer(Position position) {

        Arena arena = getModel();

        if (arena.isElement(arena.getBlocks(),position)) {
            moveblock(position);
            return;
        }

        Player player = arena.getPlayer();

        if (arena.canMove(position))
            player.setPosition(position);

        if (arena.isElement(arena.getBeasts(),position) && !arena.isEgg(position))
            getModel().hitPlayer();

        else if (arena.isElement(arena.getPowerUps(),position)) {
            int i = arena.getPowerUps().indexOf(new PowerUp(position));
            if (arena.getPowerUps().get(i) instanceof Heart && player.getLife() < 8)
                player.increaseLife();
            else {
                player.becomeImmortal();
                stats[3]++;
            }
            getModel().getPowerUps().remove(i);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {

        this.action = action;

        if (time - getModel().getPlayer().getImmortalTime() > getModel().getPlayer().getImmortalDuration() * 1000)
            getModel().getPlayer().backToNormal();

        if (action == GUI.ACTION.UP) movePlayerUp();
        if (action == GUI.ACTION.RIGHT) movePlayerRight();
        if (action == GUI.ACTION.DOWN) movePlayerDown();
        if (action == GUI.ACTION.LEFT) movePlayerLeft();
    }

    public long[] getStats() {
        return stats;
    }
}