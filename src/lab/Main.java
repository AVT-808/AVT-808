package lab;

import lab.frames.MainFrames;
import lab.habitat.Habitat;
import lab.habitat.creatures.birds.BigBird;
import lab.habitat.creatures.birds.SmallBird;

import javax.swing.*;

public class Main {
    final static Habitat h = new Habitat();
    final static lab.frames.MainFrames g = new lab.frames.MainFrames(h);

    public static void main(String[] args) {
        try {
            h.addCreatureTypes(SmallBird.class, BigBird.class);
            g.setBounds(100,100, 950, 585);
            g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            g.setVisible(true);
            g.requestFocusInWindow();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}