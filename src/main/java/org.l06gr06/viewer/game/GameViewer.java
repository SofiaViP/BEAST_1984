package org.l06gr06.viewer.game;

import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.elements.Element;
import org.l06gr06.viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {

    private final WallViewer wallViewer;
    private final BeastViewer beastViewer;
    private final PlayerViewer playerViewer;
    private final BlockViewer blockViewer;
    private final PowerUpViewer powerUpViewer;

    public GameViewer(Arena arena) {
        super(arena);
        this.playerViewer = new PlayerViewer();
        this.wallViewer = new WallViewer();
        this.beastViewer = new BeastViewer();
        this.blockViewer = new BlockViewer();
        this.powerUpViewer = new PowerUpViewer();
    }

    @Override
    public void drawElements(GUI gui) {
        drawElements(gui, getModel().getWalls(), wallViewer);
        drawElements(gui, getModel().getBeasts(), beastViewer);
        drawElement(gui, getModel().getPlayer(), playerViewer);
        drawElements(gui, getModel().getBlocks(), blockViewer);
        drawElements(gui, getModel().getPowerUps(), powerUpViewer);
        drawTimer(gui, getModel().getTimer()/50);
        drawLives(gui, getModel().getPlayer().getLife());
    }
    private void drawLives(GUI gui, int nrLives){
        gui.drawText(new Position(1,1), "Lives: ", "#FFFFFF");
        for (int i = 0; i < nrLives; i++)
            gui.drawText(new Position(i+7,1), "@", "#FC0808");
    }
    private void drawTimer(GUI gui, long time){

        long min = time/60;
        long sec = time%60;
        String timer = String.format("Timer: %02d:%02d", min, sec);
        gui.drawText(new Position(37, 1), timer, "#FFFFFF");
    }
    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}
