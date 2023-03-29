package org.l06gr06.viewer.menu;

import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.menu.ScoreMenu;
import org.l06gr06.viewer.Viewer;

public class ScoreMenuViewer extends Viewer<ScoreMenu>{

    public ScoreMenuViewer(ScoreMenu menu) {
        super(menu);
    }

    private void drawScore(GUI gui){

        gui.drawText(new Position(13,6), "Beasts killed: ","#FFFFFF");
        gui.drawText(new Position(19,9), "Shields: ","#FFFFFF");
        gui.drawText(new Position(11,11), "Remaining Lives: ","#FFFFFF");

        long[] stats = getModel().getStats();
        long nrEggs = stats[0], nrBeasts = stats[1], nrStrongBeasts = stats[2], nrShields = stats[3], nrLives = stats[4], time = stats[5];

        for (int i = 0; i < 3; i++){
            gui.drawText(new Position(28,i+5), stats[i] + " x","#FFFFFF");
            gui.drawBeast(i,new Position(32,i+2));
        }

        gui.drawText(new Position(28,9), nrShields + " x","#FFFFFF");
        gui.drawShield(new Position(32,6));

        gui.drawText(new Position(28,11), nrLives + " x","#FFFFFF");
        gui.drawHeart(new Position(32,8));

        long min = time/60, sec = time%60;
        String txt = String.format("%02d:%02d", min, sec);
        gui.drawText(new Position(22,13), "Time: " + txt,"#FFFFFF");

        long score = nrEggs * 75 + nrBeasts * 150 + nrStrongBeasts * 300 + nrShields * 50 + nrLives * 50 - time;
        gui.drawText(new Position(15,16), "Final Score: " + score,"#FFFF66");
    }

    @Override
    public void drawElements(GUI gui) {

        gui.drawText(new Position(20, 3), "YOUR SCORE", "#FFFFFF");
        drawFrame(gui);
        drawScore(gui);

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(6+i*15, 19),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFFF66" : "#FFFFFF");
    }
}
