package org.l06gr06.viewer.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.game.elements.Beast;
import org.mockito.Mockito;

public class BeastViewerTest {
    private Beast beast;
    private BeastViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        beast = new Beast(new Position(20,20),1);
        viewer = new BeastViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.draw(beast, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBeast(1,beast.getPosition());
    }
}