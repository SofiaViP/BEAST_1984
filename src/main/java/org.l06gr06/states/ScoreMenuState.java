package org.l06gr06.states;

import org.l06gr06.controller.Controller;
import org.l06gr06.controller.menu.ScoreMenuController;
import org.l06gr06.model.menu.ScoreMenu;
import org.l06gr06.viewer.Viewer;
import org.l06gr06.viewer.menu.ScoreMenuViewer;

import java.io.IOException;

public class ScoreMenuState extends State<ScoreMenu>{

    public ScoreMenuState(ScoreMenu model) throws IOException {super(model);}
    @Override
    public Viewer<ScoreMenu> getViewer() {return new ScoreMenuViewer(getModel());}
    @Override
    public Controller<ScoreMenu> getController(){return new ScoreMenuController(getModel());}
}
