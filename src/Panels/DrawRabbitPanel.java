package Panels;

import Models.Abstract.BaseRabbit;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawRabbitPanel extends JPanel {

    private final List<BaseRabbit> rabbits;


    public DrawRabbitPanel(List<BaseRabbit> rabbits){
        this.rabbits = rabbits;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(rabbits.isEmpty())
            return;
        for (BaseRabbit rabbit : rabbits) {
            graphics.drawImage(rabbit.getRabbitImage(),rabbit.getCoordinates().x,rabbit.getCoordinates().y,70,70,null);
        }
    }
}
