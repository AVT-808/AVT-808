package Contr;

import Habit.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.io.IOException;

public class Keyboard extends KeyAdapter {

    private final Habitat habitat;
    static Boolean dd = true;
    StartStop st;
    JButton button_start;
    JButton button_stop;

    public Keyboard() throws IOException {

        habitat = new Habitat();
        habitat.jFrame.setVisible(true);

        habitat.jFrame.addKeyListener(this);

        button_start=Butt.Return_start();
        button_stop=Butt.Return_stop();

        st = StartStop.getN();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == KeyEvent.VK_B) { st.StarT(habitat, button_start, button_stop); }

        if (keyEvent.getKeyCode() == KeyEvent.VK_E) { st.StoP(habitat,button_start, button_stop); }

        if(keyEvent.getKeyCode() == KeyEvent.VK_T) {
            dd = Clock.Return_dd(); // Проверяем на кнопках

            if (!dd) Line.Clock(true);
            else Line.Clock(false); // Сделать видимым

            dd=!dd; // Меняем на клаве
            Clock.Change_dd(); // Меняем на кнопках

            Clock.Change_point(); // Меняем "выбранность" на экране
        }
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public static void setDd(Boolean u) {
        if (dd!=u) {
            dd = u;
            Clock.Change_dd(); // Меняем на кнопках
            Clock.Change_point(); // Меняем "выбранность" на экране
            if (u) Line.Clock(true);
            else Line.Clock(false);
        }

    }
}
