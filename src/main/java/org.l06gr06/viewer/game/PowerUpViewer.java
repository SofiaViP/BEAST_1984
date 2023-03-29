package org.l06gr06.viewer.game;

import org.l06gr06.gui.GUI;
import org.l06gr06.model.game.elements.Heart;
import org.l06gr06.model.game.elements.PowerUp;
import org.l06gr06.model.game.elements.Shield;

public class PowerUpViewer implements ElementViewer<PowerUp>{
    @Override
    public void draw(PowerUp powerUp, GUI gui){
        if (powerUp instanceof Shield) gui.drawShield(powerUp.getPosition());
        if (powerUp instanceof Heart) gui.drawHeart(powerUp.getPosition());
    }
}
