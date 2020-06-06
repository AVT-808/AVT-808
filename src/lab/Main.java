package lab;

import lab.frames.MainForm;
import lab.habitat.Habitat;
import lab.habitat.creatures.birds.BigBird;
import lab.habitat.creatures.birds.SmallBird;

import javax.swing.*;

public class Main {
    final static Habitat h = new Habitat();
    final static MainForm g = new MainForm(h);

    public static void main(String[] args) {
        try {
            h.addCreatureTypes(SmallBird.class, BigBird.class);
            g.setBounds(100,100, 1425, 710);
            g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            g.setVisible(true);
            g.requestFocusInWindow();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
