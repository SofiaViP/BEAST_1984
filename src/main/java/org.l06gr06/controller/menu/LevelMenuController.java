package org.l06gr06.controller.menu;

import org.l06gr06.Game;
import org.l06gr06.controller.Controller;
import org.l06gr06.controller.game.ArenaController;
import org.l06gr06.gui.GUI;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.model.game.arena.RandomArenaBuilder;
import org.l06gr06.model.menu.LevelMenu;
import org.l06gr06.states.GameState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class LevelMenuController  extends Controller<LevelMenu> {
    public LevelMenuController(LevelMenu menu) {
        super(menu);
    }

    private void saveLevel(int level) throws IOException {
        URL resource = ArenaController.class.getResource("/score/score.csv");
        assert resource != null;
        BufferedWriter writer = new BufferedWriter(new FileWriter(resource.getFile(), true));
        String str = "";
        //str = str.repeat(level);
        for (int i = 0; i < level;i++) str = str+"~";
        writer.append(str);
        writer.append(',');
        writer.close();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP : { getModel().previousEntry(); break; }
            case DOWN : { getModel().nextEntry(); break; }
            case SELECT : {
                for (int i = 0; i < 3; i++)
                    if (getModel().isSelected(i)) {
                        saveLevel(i+1);
                        Arena arena = new RandomArenaBuilder(50, 20, 2 + 2 * i, 150 - 50 * i, 1 + 3 * i, 15 - 5 * i).createArena();
                        game.setState(new GameState(arena));
                        return;
                    }
                if (getModel().isSelectedExit())
                    game.setState(null);
            }
        }
    }
}