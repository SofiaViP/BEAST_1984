package org.l06gr06.model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PositionTest {
    @Property
    void getLeft(@ForAll int x, @ForAll int y) {
        assertEquals(x - 1, new Position(x, y).getLeft().getX());
        assertEquals(y, new Position(x, y).getLeft().getY());
    }
    @Property
    void getRight(@ForAll int x, @ForAll int y) {
        assertEquals(x + 1, new Position(x, y).getRight().getX());
        assertEquals(y, new Position(x, y).getRight().getY());
    }
    @Property
    void getUp(@ForAll int x, @ForAll int y) {
        assertEquals(x, new Position(x, y).getUp().getX());
        assertEquals(y - 1, new Position(x, y).getUp().getY());
    }
    @Property
    void getDown(@ForAll int x, @ForAll int y) {
        assertEquals(x, new Position(x, y).getDown().getX());
        assertEquals(y + 1, new Position(x, y).getDown().getY());
    }
    @Property
    void hashCode(@ForAll int x, @ForAll int y){
        Position position1 = new Position(x,y);
        Position position2 = new Position(x,y);
        Position position3 = new Position(x+1,y+1);
        Position position4 = new Position(x+1,y+1);

        assertEquals(position1,position2);
        assertEquals(position3,position4);

        assertEquals(position1.hashCode(),position2.hashCode());
        assertEquals(position3.hashCode(),position4.hashCode());
        assertNotEquals(position3.hashCode(),position1.hashCode());
    }

    @Test
    void equals(){
        Position position = new Position(0,0);
        assertEquals(position, position);
        assertNotEquals(position,null);
    }
}