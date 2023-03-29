package org.l06gr06.model.game.elements;


import org.l06gr06.model.Position;

public class Player extends Element {
    private int life;
    private int phase;
    private long immortalTime;
    private final long immortalDuration;

    public Player(Position position) {
        super(position);
        this.life = 5;
        this.phase = 0;
        this.immortalDuration = 10;
    }
    public void becomeImmortal(){
        this.phase = 1;
        immortalTime = System.currentTimeMillis();
    }
    public void backToNormal(){this.phase = 0;}
    public void decreaseLife() {
        this.life--;
    }

    public void increaseLife() {
        this.life++;
    }

    public int getLife() {
        return life;
    }
    public int getPhase(){return phase;}

    public boolean isImmortal() {return phase == 1;}
    public long getImmortalTime(){
        return immortalTime;
    }

    public void setImmortalTime(long immortalTime) {
        this.immortalTime = immortalTime;
    }

    public long getImmortalDuration(){
        return immortalDuration;
    }
}
