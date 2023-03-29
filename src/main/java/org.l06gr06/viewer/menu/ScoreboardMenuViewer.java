package org.l06gr06.viewer.menu;

import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.model.menu.ScoreboardMenu;
import org.l06gr06.viewer.Viewer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreboardMenuViewer extends Viewer<ScoreboardMenu>{

    private final List<String> lines;
    public ScoreboardMenuViewer(ScoreboardMenu menu, String file) throws IOException {
        super(menu);
        URL resource = ScoreboardMenuViewer.class.getResource("/score/" + file);
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        lines = readLines(br);
    }
    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        lines.sort(((Comparator<String>) (o1, o2) -> {
            String score1 = o1.split(",")[1];
            String score2 = o2.split(",")[1];
            return Integer.compare(Integer.parseInt(score1), Integer.parseInt(score2));
        }).reversed());
        return lines;
    }

    private void drawScoreboard(GUI gui){
        gui.drawText(new Position(12,5),"Level","#FFFFFF");
        gui.drawText(new Position(23,5),"Score","#FFFFFF");
        gui.drawText(new Position(34,5),"Time","#FFFFFF");
        int size = Math.min(lines.size(), 10);
        for (int i = 0; i < size; i++){
            gui.drawText(new Position(7,i+7), i+1+".", "#FFFFFF");
            String[] line = lines.get(i).split(",");
            for (int j = 0; j < line.length; j++){
                gui.drawText(new Position(j*11+12,i+7),line[j],j == 0 ? "#FFFF66" : "#FFFFFF");
            }
        }
    }

    @Override
    public void drawElements(GUI gui) {

        gui.drawText(new Position(20, 3), "SCOREBOARD", "#FFFFFF");
        drawFrame(gui);
        drawScoreboard(gui);

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(15+i*15, 19),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFFF66" : "#FFFFFF");
    }

}
