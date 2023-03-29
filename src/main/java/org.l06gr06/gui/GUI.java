package org.l06gr06.gui;

import org.l06gr06.model.Position;

import java.io.IOException;
public interface GUI {
    void drawText(Position position, String text, String color);
    void drawPlayer(int phase, Position position);
    void drawShield(Position position);
    void drawWall(Position position);
    void drawHeart(Position position);
    void drawBeast(int phase, Position position);
    void drawBlock(Position position);
    ACTION getNextAction() throws IOException;
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
