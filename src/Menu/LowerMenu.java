package Menu;

import Cons.ButtConsole;
import Contr.*;
import Habit.Habitat;
import Existence.*;
import Move.Pause;
import Move.Prior;
import Serial.ButtSerial;

import javax.swing.*;
import java.awt.*;

public class LowerMenu extends JPanel {


    public LowerMenu (Habitat habitat) {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        ////////
        ButtConsole buttConsole = new ButtConsole();
        add(buttConsole);
        ////////
        ButtSerial buttSerial = new ButtSerial(habitat);
        add(buttSerial);
        ////////


        setVisible(true);
        setFocusable(false);
    }


}

