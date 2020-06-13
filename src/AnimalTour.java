import Contr.*;
import Move.BigAI;
import Move.Pause;
import Move.SmallAI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import Conf.*;
import Serv.Client;


public class AnimalTour  {

    private AnimalTour() throws IOException {
        Keyboard keyboard = new Keyboard();
        Client.Connection();
        SmallAI smallAI = new SmallAI();
        BigAI bigAI = new BigAI();
        Pause.setAI(smallAI,bigAI);

        Configuration configuration = new Configuration();
        configuration.downloadInformation();

        keyboard.getHabitat().getjFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                configuration.saveInformation();
                System.out.println("\nДанные успешно сохранены в конфигурационный файл\n");
            }
        });



    }


    public static void main(String[] args) throws IOException {
        new AnimalTour();
    }
}
