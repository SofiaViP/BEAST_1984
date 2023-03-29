package org.l06gr06.model.game.elements;

import org.l06gr06.model.Position;

public class Element {
    private Position position;

    public Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Element element = (Element) o;
        return position.equals(element.position);
    }
}
