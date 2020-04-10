package AntFarm;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    private Habitat h;

    MyTimerTask(Habitat h)
    {
        this.h = h;
    }

    @Override
    public void run()
    {
        h.update();
    }
}
