package org.l06gr06.states;

import org.l06gr06.controller.Controller;
import org.l06gr06.controller.menu.ScoreboardMenuController;
import org.l06gr06.model.menu.ScoreboardMenu;
import org.l06gr06.viewer.Viewer;
import org.l06gr06.viewer.menu.ScoreboardMenuViewer;

import java.io.IOException;

public class ScoreboardMenuState extends State<ScoreboardMenu>{

    public ScoreboardMenuState(ScoreboardMenu model) throws IOException {
        super(model);
    }
    @Override
    public Viewer<ScoreboardMenu> getViewer() throws IOException {
        return new ScoreboardMenuViewer(getModel(),"score.csv");
    }
    @Override
    public Controller<ScoreboardMenu> getController(){
        return new ScoreboardMenuController(getModel());
    }
}
