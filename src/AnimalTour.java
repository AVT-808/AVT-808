import Habit.*;
import java.awt.event.*;
import java.util.Timer;

public class AnimalTour extends KeyAdapter {
    private Timer timer;
    private ToBeContinued habitatTask;
    private final Habitat habitat;
    private Boolean isStarted;
    private Boolean isShown;

    private final Integer time_birth_small = 2;
    private final Integer time_birth_big = 5;
    private final Float chance_birth_big = 0.8f;
    private final Float percent = 0.5f;

    private AnimalTour()
    {
        habitat = new Habitat("Field", time_birth_small, time_birth_big,chance_birth_big,percent);
        habitat.setVisible(true);
        habitat.addKeyListener(this);
        isStarted = false;
        isShown = true;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {

        if (keyEvent.getKeyCode() == KeyEvent.VK_B)
        {
            isStarted = true;
            timer = new Timer();
            habitatTask = new ToBeContinued(habitat);
            timer.schedule(habitatTask,0,1000);
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_E){
            timer.cancel();
            habitatTask.cancel();
            timer.purge();
            habitat.Stop();
            isStarted = false;
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_T) {
            isShown = !isShown;
            habitat.Clock_yes_no(isShown);
        }
    }

    public static void main(String[] args) {
        new AnimalTour();
    }
}



