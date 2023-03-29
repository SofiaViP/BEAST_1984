package org.l06gr06.states;

import org.l06gr06.controller.Controller;
import org.l06gr06.controller.menu.LevelMenuController;
import org.l06gr06.model.menu.LevelMenu;
import org.l06gr06.viewer.Viewer;
import org.l06gr06.viewer.menu.LevelMenuViewer;

import java.io.IOException;

public class LevelMenuState extends State<LevelMenu>{

    public LevelMenuState(LevelMenu model) throws IOException {
        super(model);
    }
    @Override
    public Viewer<LevelMenu> getViewer() {
        return new LevelMenuViewer(getModel());
    }
    @Override
    public Controller<LevelMenu> getController() {
        return new LevelMenuController(getModel());
    }
}
