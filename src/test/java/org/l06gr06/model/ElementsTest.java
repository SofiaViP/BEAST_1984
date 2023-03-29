package org.l06gr06.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.l06gr06.model.game.elements.*;

public class ElementsTest {
    @Test
    public void element(){
        Element element = new Element(new Position(20,20));
        element.setPosition(new Position(21,20));
        Assertions.assertEquals(new Position(21,20), element.getPosition());
    }
    @Test
    public void increaseLife(){
        Player player = new Player(new Position(20,20));
        player.increaseLife();
        Assertions.assertEquals(6, player.getLife());
    }
    @Test
    public void decreaseLife(){
        Player player = new Player(new Position(20,20));
        player.decreaseLife();
        Assertions.assertEquals(4, player.getLife());
    }
    @Test
    public void immortalPlayer(){
        Player player = new Player(new Position(20,20));
        player.becomeImmortal();
        player.setImmortalTime(10);
        Assertions.assertEquals(1, player.getPhase());
        Assertions.assertEquals(10, player.getImmortalTime());
    }
    @Test
    public void normalPlayer(){
        Player player = new Player(new Position(20,20));
        player.becomeImmortal();
        player.backToNormal();
        Assertions.assertEquals(0, player.getPhase());
    }

    @Test
    public void eggEvolve(){
        Beast beast = new Beast(new Position(20,20),0);
        beast.evolve();
        Assertions.assertEquals(1, beast.getPhase());
    }

    @Test
    public void beastEvolve(){
        Beast beast = new Beast(new Position(20,20),1);
        beast.evolve();
        Assertions.assertEquals(2, beast.getPhase());
    }

    @Test
    public void strongerBeastEvolve(){
        Beast beast = new Beast(new Position(20,20),2);
        beast.evolve();
        Assertions.assertEquals(2, beast.getPhase());
    }

    @Test
    public void powerUpDuration(){
        PowerUp powerUp = new PowerUp(new Position(20,20));
        Assertions.assertEquals(10,powerUp.getDuration());
    }

    @Test
    public void powerUpCreationTime(){
        long time = System.currentTimeMillis();
        PowerUp powerUp = new PowerUp(new Position(20,20));
        Assertions.assertEquals(time,powerUp.getCreationTime());
    }

    @Test
    public void equals(){
        Element element = new Element(new Position(20,20));
        Assertions.assertEquals(new Element(new Position(20,20)),element);
    }

    @Test
    public void notEquals(){
        Element element = new Element(new Position(20,20));
        Element element1 = new Element(new Position(21,20));
        Assertions.assertNotEquals(element, element1);
    }
    @Test
    public void nullElement(){
        Element element = new Element(new Position(20,20));
        Assertions.assertNotEquals(element, null);
    }
}
