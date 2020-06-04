package AntFarm;

public class WorkerAI extends BaseAI
{
    public static Thread thread;
    public boolean isMoving;
    Singleton ants = Singleton.getSingleton();

    WorkerAI()
    {
        thread = new Thread(this, "WorkerAI");
        isMoving = true;
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this)
            {
                while (!isMoving)
                {
                    try
                    {
                        wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (ants.getArraySize()!=0)
                {
                    for (int i=0; i<ants.getArraySize();i++)
                    {
                        if (ants.getAnts().get(i).getClass() == AntWorker.class)
                            ants.getAnts().get(i).move();
                    }
                }
                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public synchronized void startAI()
    {
        isMoving = true;
        notify();
    }

    @Override
    public void stopAI()
    {
        isMoving = false;
    }
}
