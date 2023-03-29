package org.l06gr06.controller.menu;

import org.l06gr06.Game;
import org.l06gr06.controller.Controller;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.menu.LevelMenu;
import org.l06gr06.model.menu.ScoreboardMenu;
import org.l06gr06.states.LevelMenuState;

import java.io.IOException;

public class ScoreboardMenuController extends Controller<ScoreboardMenu> {
    public ScoreboardMenuController(ScoreboardMenu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case LEFT: { getModel().previousEntry(); break; }
            case RIGHT: { getModel().nextEntry(); break; }
            case SELECT: {
                if (getModel().isSelectedExit())
                    game.setState(null);
                if (getModel().isSelectedPlayAgain())
                    game.setState(new LevelMenuState(new LevelMenu()));
            }
        }
    }
}
