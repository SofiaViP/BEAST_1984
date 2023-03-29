package org.l06gr06;

import org.l06gr06.gui.LanternaGUI;
import org.l06gr06.model.menu.MainMenu;
import org.l06gr06.states.MainMenuState;
import org.l06gr06.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(50, 20);
        this.state = new MainMenuState(new MainMenu());
    }
    public Game(LanternaGUI gui) throws FontFormatException, IOException, URISyntaxException {
        this.gui = gui;
        this.state = new MainMenuState(new MainMenu());
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game().start();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    private void start() throws IOException {
        int FPS = 50;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }

        gui.close();
    }
}
