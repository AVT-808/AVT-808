package Habit;

import java.util.TimerTask;

public class ToBeContinued extends TimerTask {

    private final Habitat habitat;

    public ToBeContinued(Habitat habitat){
        this.habitat = habitat;
    }

    @Override
    public void run() { // В классе TimerTask имеется абстрактный метод run(), который следует переопределить.
        habitat.Update();
    }
}

