package org.l06gr06.controller.menu;

import org.l06gr06.Game;
import org.l06gr06.controller.Controller;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.menu.LevelMenu;
import org.l06gr06.model.menu.ScoreboardMenu;
import org.l06gr06.model.menu.ScoreMenu;
import org.l06gr06.states.LevelMenuState;
import org.l06gr06.states.ScoreboardMenuState;

import java.io.IOException;

public class ScoreMenuController extends Controller<ScoreMenu> {
    public ScoreMenuController(ScoreMenu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case LEFT : { getModel().previousEntry(); break; }
            case RIGHT : { getModel().nextEntry(); break; }
            case SELECT : {
                if (getModel().isSelectedExit())
                    game.setState(null);
                if (getModel().isSelectedScoreboard())
                    game.setState(new ScoreboardMenuState(new ScoreboardMenu()));
                if (getModel().isSelectedPlayAgain())
                    game.setState(new LevelMenuState(new LevelMenu()));
            }
        }
    }
}
