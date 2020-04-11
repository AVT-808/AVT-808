package term4;

public class WoodenHouseAI extends BaseAI {

    public WoodenHouseAI() {
        thread = new Thread(this);
        isMoving = true;
        thread.start();
    }

    @Override
    public void run() {
        while (true){
            synchronized (this){
                while (!isMoving){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int i = 0; i < Singleton.getSingleton().getHouseVector().size(); i++){
                    if(Singleton.getSingleton().getHouse(i) instanceof WoodenHouse){
                        Singleton.getSingleton().getHouse(i).move();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public synchronized void startAI() {
        isMoving = true;
        notify();
    }

    @Override
    public void stopAI() { isMoving = false; }

    @Override
    public void setTheadPriority(int priority) { thread.setPriority(priority); }
}
