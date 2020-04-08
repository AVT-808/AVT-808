package Panels;

import Habitat.RabbitList.Singleton;
import Models.Abstract.BaseRabbit;
import javax.swing.*;
import java.awt.*;

public class DrawRabbitPanel extends JPanel {

    private final Singleton rabbits;

    public DrawRabbitPanel() {
        rabbits = Singleton.getInstance();
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(rabbits.getRabbits().isEmpty())
            return;
        for (BaseRabbit rabbit : rabbits.getRabbits()) {
            graphics.drawImage(rabbit.getRabbitImage(),rabbit.getCoordinates().x,rabbit.getCoordinates().y,70,70,null);
        }
    }
}
