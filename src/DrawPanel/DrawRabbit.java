package DrawPanel;

import Models.Abstract.BaseRabbit;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawRabbit extends JPanel {

    private final List<BaseRabbit> rabbits;


    public DrawRabbit(List<BaseRabbit> rabbits){
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
