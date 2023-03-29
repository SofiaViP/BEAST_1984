package org.l06gr06.model.game.elements;

import org.l06gr06.model.Position;

public class PowerUp extends Element {
    private final long creationTime;
    private long duration;

    public PowerUp(Position position) {
        super(position);
        this.duration = 10;
        this.creationTime = System.currentTimeMillis();
    }

    public long getCreationTime(){ return creationTime; }

    public long getDuration() { return duration; }

    public void setDuration(long duration) { this.duration = duration; }

}
