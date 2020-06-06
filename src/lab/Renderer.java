package lab;

import lab.habitat.Habitat;
import lab.habitat.ICreature;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

import static java.awt.Image.SCALE_SMOOTH;


public class Renderer extends Thread implements Runnable {
    private JPanel surface;
    private Habitat habitat;

    boolean active = false;

    Timer timer;


    public Renderer(JPanel s, Habitat h) {
        surface = s;
        habitat = h;
    }


    public void run() {
        active = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                render();
            }
        }, 0, 200);
    }


    public void render() {
        if(active) {
            surface.removeAll();

            for(ICreature c : habitat.getCreatures()) {
                surface.add(new JPanel() {
                    {
                        setBounds(
                                (int) (surface.getWidth()  * (c.getX() > 0.9 ? 0.9 : c.getX())),
                                (int) (surface.getHeight() * (c.getY() > 0.9 ? 0.9 : c.getY())),
                                (int) (surface.getWidth()  * 0.1),
                                (int) (surface.getHeight() * 0.1)
                        );
                        setOpaque(true);
                    }

                    protected void paintComponent(Graphics g) {
                        g.drawImage(
                                c.getImage().getScaledInstance(
                                        getWidth(),
                                        getHeight(),
                                        SCALE_SMOOTH
                                ),
                                0, 0, null
                        );
                    }
                });
            }

            surface.updateUI();
        }
    }
}
