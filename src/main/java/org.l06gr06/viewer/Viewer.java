package org.l06gr06.viewer;

import org.l06gr06.gui.GUI;
import org.l06gr06.model.Position;
import org.l06gr06.states.State;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawElements(gui);
        gui.refresh();
    }

    protected abstract void drawElements(GUI gui);

    protected void drawFrame(GUI gui){
        for (int x = 0; x < 50; x++) {
            gui.drawWall(new Position(x,-3));
            gui.drawWall(new Position(x,19));
        }

        for (int y = -2; y < 19; y++) {
            gui.drawWall(new Position(0,y));
            gui.drawWall(new Position(49,y));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viewer<?> viewer = (Viewer<?>) o;
        return model.equals(viewer.model);
    }
}
