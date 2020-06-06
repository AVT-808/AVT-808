package Move;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.*;

public class Pause extends JPanel {

    private static JCheckBox Box_Big;
    private static JCheckBox Box_Small;
    public static SmallAI smallAI;
    public static BigAI bigAI;

    public Pause() {
        setLayout(new GridLayout(2, 1));
        Box_Big = new JCheckBox("Интел. вз.", true);
        add(Box_Big);
        Box_Big.setFocusable(false);


        Box_Small = new JCheckBox("Интел. пт.", true);
        add(Box_Small);
        Box_Small.setFocusable(false);

        Box_Big.addActionListener(e -> { // Остановка/продолжение движения взрослых
            if (Box_Big.isSelected())
                bigAI.startAI();
            else bigAI.stopAI();
        });


        Box_Small.addActionListener(e -> { // Остановка/продолжение движения птенцов
            if (Box_Small.isSelected())
                smallAI.startAI();
            else smallAI.stopAI();
        });
    }

    public static void setAI(SmallAI smallAI2, BigAI bigAI2) { // Ссылка из AnimalTour
        smallAI = smallAI2;
        bigAI = bigAI2;
    }

    public static Boolean getBigAI() {
        return Box_Big.isSelected();
    }

    public static Boolean getSmallAI() {
        return Box_Small.isSelected();
    }

    public static JCheckBox getBox_Big() {
        return Box_Big;
    }

    public static JCheckBox getBox_Small() {
        return Box_Small;
    }

    public static void setBox_Big(Boolean u){
        Box_Big.setSelected(u);
        if (Box_Big.isSelected())
            bigAI.startAI();
        else bigAI.stopAI();
    }

    public static void setBox_Small(Boolean u){
        Box_Small.setSelected(u);
        if (Box_Small.isSelected())
            smallAI.startAI();
        else smallAI.stopAI();
    }


}
