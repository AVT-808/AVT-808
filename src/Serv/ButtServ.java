package Serv;

import javax.swing.*;
import java.awt.*;

public class ButtServ extends JPanel {

    private static final JButton button_send =  new JButton("Передать");

    public ButtServ() {

        setLayout(new GridLayout(1, 2));

        button_send.addActionListener(e -> {
            Client.sendObj();
            Client.sendUs();
        });

        add(button_send);
    }

}
