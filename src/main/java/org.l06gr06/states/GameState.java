package org.l06gr06.states;

import org.l06gr06.controller.Controller;
import org.l06gr06.controller.game.ArenaController;
import org.l06gr06.model.game.arena.Arena;
import org.l06gr06.viewer.Viewer;
import org.l06gr06.viewer.game.GameViewer;

import java.io.IOException;

public class GameState extends State<Arena>{

    public GameState(Arena arena) throws IOException {
        super(arena);
    }
    @Override
    public Viewer<Arena> getViewer() {
        return new GameViewer(getModel());
    }
    @Override
    public Controller<Arena> getController() {
        return new ArenaController(getModel());
    }
}
