package org.l06gr06.controller.game;

import org.l06gr06.Game;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.elements.PowerUp;

import java.io.IOException;

public class PowerUpController extends GameController{
    private long creationTime;

    public PowerUpController(Arena arena){
        super(arena);
        this.creationTime = 5;
    }

    public void step(Game game, GUI.ACTION action, long time) throws IOException {

        Arena arena = getModel();

        if(!arena.getPowerUps().isEmpty()) {
            PowerUp powerUp = arena.getPowerUps().get(0);
            if (time - powerUp.getCreationTime() > powerUp.getDuration() * 1000)
                arena.getPowerUps().remove(powerUp);
        }

        if (arena.getTimer()/50 == creationTime){
            arena.createPowerUp();
            creationTime += 5;
        }
    }
}
