package AntFarm;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

public class Main extends KeyAdapter
{
    private Habitat antFarm;
    private MyTimerTask myTimerTask;
    private Timer timer;
    private boolean isStarted, isVisible;

    public static void main(String[] args)
    {
        new Main();
    }

    Main()
    {
        antFarm = new Habitat();
        antFarm.setVisible(true);
        antFarm.addKeyListener(this);
        isStarted = false;
        isVisible = true;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        int key = keyEvent.getKeyCode();
        if (key == keyEvent.VK_B && !isStarted)
        {
            isStarted = true;
            timer = new Timer();
            myTimerTask = new MyTimerTask(antFarm);
            timer.schedule(myTimerTask, 0, 1000);
        }
        if (key == keyEvent.VK_E && isStarted)
        {
            timer.cancel();
            timer.purge();
            myTimerTask.cancel();
            antFarm.stop();
            isStarted = false;
        }
        if (key == keyEvent.VK_T)
        {
            antFarm.timerVisibility(isVisible);
            isVisible = !isVisible;
        }
    }
}
