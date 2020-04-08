package Habitat;

import java.util.TimerTask;

public class HabitatTask extends TimerTask {
    private final HabitatFrame habitat;

    public HabitatTask(HabitatFrame habitat){
        this.habitat = habitat;
    }

    @Override
    public void run() {
        habitat.update();
    }
}
