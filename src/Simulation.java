import com.nstu.tp.habitat.Habitat;
import com.nstu.tp.habitat.Task;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;


class Simulation implements KeyListener {
    private final Habitat habitat;
    private Timer timer;
    private Task Task;
    private Boolean isStarted;
    private Boolean isShown;

private Simulation(){
    habitat = new Habitat("Window", 3,4,0.7f,0.7f);
    habitat.setVisible(true);
    habitat.addKeyListener(this);
    isStarted = false;
    isShown = true;

}

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if(key == KeyEvent.VK_B && !isStarted) {
            isStarted = true;
            timer = new Timer();
            Task = new Task(habitat);
            timer.schedule(Task,0,1000);
        }
        if (key == KeyEvent.VK_E && isStarted) {
            timer.cancel();
            Task.cancel();
            timer.purge();
            habitat.stop();
            isStarted = false;
        }
        if(key == KeyEvent.VK_T) {
            isShown = !isShown;
            habitat.timerVis(isShown);

    }


    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


    public static void main(String[] args) {
        new Simulation();
    }
}
