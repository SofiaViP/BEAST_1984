package org.l06gr06.states;

import org.l06gr06.Game;
import org.l06gr06.controller.Controller;
import org.l06gr06.gui.GUI;
import org.l06gr06.viewer.Viewer;

import java.io.IOException;

public abstract class State<T> {

    private final T model;
    private Controller<T> controller;
    private Viewer<T> viewer;

    public State(T model) throws IOException {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Viewer<T> getViewer() throws IOException;
    protected abstract Controller<T> getController();

    public T getModel(){return model;}

    public void setViewer(Viewer<T> viewer) {this.viewer = viewer;}

    public void setController(Controller<T> controller) { this.controller = controller;}

    public void step(Game game, GUI gui, long time) throws IOException{
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, time);
        viewer.draw(gui);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State<?> state = (State<?>) o;
        return model.equals(state.model) && viewer.equals(state.viewer) && controller.equals(state.controller);
    }
}