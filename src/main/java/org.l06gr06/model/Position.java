package org.l06gr06.model;

import org.l06gr06.gui.GUI;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public Position getDirection(GUI.ACTION action){
        switch (action) {
            case UP : return getUp();
            case DOWN : return getDown();
            case LEFT : return getLeft();
        }
        return getRight();
    }

    public Position getLeft() {return new Position(x - 1, y);}
    public Position getRight() {return new Position(x + 1, y);}
    public Position getUp() {return new Position(x, y - 1);}
    public Position getDown() {return new Position(x, y + 1);}

    public Position getCloser(Position pos2) {

        int offsetX = (int) (Math.random()*2);
        int offsetY = (int) (Math.random()*2);

        if (x < pos2.x && y > pos2.y)
            return new Position(x+offsetX,y-offsetY);
        if (x < pos2.x && y < pos2.y)
            return new Position(x+offsetX,y+offsetY);
        if (x > pos2.x && y < pos2.y)
            return new Position(x-offsetX,y+offsetY);
        return new Position(x-offsetX,y-offsetY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
