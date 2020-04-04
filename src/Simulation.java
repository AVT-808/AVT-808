import Habitat.Habitat;
import Habitat.HabitatTask;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

class Simulation extends KeyAdapter {
    private Timer timer;
    private HabitatTask habitatTask;
    private final Habitat habitat;
    private Boolean isStarted;
    private Boolean isShown;

    private Simulation() {
        habitat = new Habitat("Habitat", 3,4,0.7f,0.7f);
        habitat.setVisible(true);
        habitat.addKeyListener(this);
        isStarted = false;
        isShown = true;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if(key == KeyEvent.VK_B && !isStarted) {
            isStarted = true;
            timer = new Timer();
            habitatTask = new HabitatTask(habitat);
            timer.schedule(habitatTask,0,1000);
        }
        if (key == KeyEvent.VK_E && isStarted) {
            timer.cancel();
            habitatTask.cancel();
            timer.purge();
            habitat.stop();
            isStarted = false;
        }
        if(key == KeyEvent.VK_T) {
            isShown = !isShown;
            habitat.timerVisibility(isShown);
        }
    }

    public static void main(String[] args) {
        new Simulation();
    }
}
