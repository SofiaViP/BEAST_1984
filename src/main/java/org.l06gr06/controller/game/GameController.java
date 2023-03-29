package org.l06gr06.controller.game;

import org.l06gr06.controller.Controller;
import org.l06gr06.model.game.arena.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }
}
