package org.l06gr06.model.game.elements;

import org.l06gr06.model.Position;

public class Beast extends Element{
    private int phase;

    public Beast(Position position, int phase) {
        super(position);
        this.phase = phase;
    }

    public void evolve() {
        if (phase < 2)
            this.phase++;
    }

    public int getPhase() { return phase; }

}
