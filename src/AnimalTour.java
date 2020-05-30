import Contr.*;
import Move.BigAI;
import Move.Pause;
import Move.SmallAI;


public class AnimalTour  {

    private AnimalTour() {
        Keyboard keyboard = new Keyboard();
        SmallAI smallAI = new SmallAI();
        BigAI bigAI = new BigAI();
        Pause.setAI(smallAI,bigAI);
    }


    public static void main(String[] args) {
        new AnimalTour();
    }
}
