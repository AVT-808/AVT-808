import Habit.*;
import Contr.*;

import javax.swing.*;
import java.awt.event.*;

public class AnimalTour extends KeyAdapter {

    private final Habitat habitat;
    private Boolean dd;
    StarT_StoP st;
    JButton button_start;
    JButton button_stop;

    private AnimalTour() {

        habitat = new Habitat();

        habitat.setVisible(true);

        habitat.addKeyListener(this);

        dd = true;

        button_start=habitat.Return_start();
        button_stop=habitat.Return_stop();

        st = StarT_StoP.getN();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == KeyEvent.VK_B) { st.StarT(habitat, button_start, button_stop); }

        if (keyEvent.getKeyCode() == KeyEvent.VK_E) { st.StoP(habitat,button_start, button_stop); }

        if(keyEvent.getKeyCode() == KeyEvent.VK_T) {

            dd = habitat.Return_dd();
            dd=!dd;
            habitat.Clock_yes_no(dd);
        }
    }

    public static void main(String[] args) { new AnimalTour(); }
}
