package org.l06gr06.model.menu;

import java.util.Arrays;

public class ScoreboardMenu extends Menu{
    public ScoreboardMenu(){
        entries = Arrays.asList("Play again", "Exit");
    }

    public boolean isSelectedPlayAgain(){
        return isSelected(0);
    }
}
