package org.l06gr06.controller.game;

import org.l06gr06.Game;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.elements.Beast;
import org.l06gr06.model.game.elements.PowerUp;

import java.io.IOException;

public class BeastController extends GameController {
    private long lastMovement;
    private long speed;
    private final long hatchingTime;
    public BeastController(Arena arena) {
        super(arena);
        this.speed = 500;
        this.lastMovement = 0;
        this.hatchingTime = 40;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (getModel().getTimer()/50 == hatchingTime)
            getModel().hatchEggs();

        if (time - lastMovement > speed) {
            for (Beast beast : getModel().getBeasts()){
                Position playerPos = getModel().getPlayer().getPosition();
                Position beastPos = beast.getPosition();
                if (beast.getPhase() != 0) moveBeast(beast, beastPos.getCloser(playerPos));
            }
            this.lastMovement = time;
        }
    }

    private void moveBeast(Beast beast, Position position) {

        Arena arena = getModel();

        if (!arena.canMove(position) || arena.isElement(arena.getBeasts(),position)) return;

        else if (arena.isElement(arena.getPowerUps(),position)) {
            this.speed -= 50;
            beast.evolve();
            arena.getPowerUps().remove(new PowerUp(position));
        }

        else if (arena.isPlayer(position))
            arena.hitPlayer();

        beast.setPosition(position);
    }

    public long getSpeed() {
        return speed;
    }

    public long getLastMovement() {
        return lastMovement;
    }
}