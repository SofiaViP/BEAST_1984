package org.l06gr06.viewer.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.game.elements.Heart;
import org.l06gr06.model.game.elements.Shield;
import org.mockito.Mockito;
public class PowerUpViewerTest {
    private Shield shield;
    private Heart heart;
    private PowerUpViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        heart = new Heart(new Position(20, 20));
        shield = new Shield(new Position(10,10));
        viewer = new PowerUpViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawHeart() {
        viewer.draw(heart, gui);
        Mockito.verify(gui, Mockito.times(1)).drawHeart(heart.getPosition());
    }
    @Test
    void drawShield() {
        viewer.draw(shield, gui);
        Mockito.verify(gui, Mockito.times(1)).drawShield(shield.getPosition());
    }
}

