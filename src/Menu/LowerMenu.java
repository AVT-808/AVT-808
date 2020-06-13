package Menu;

import Cons.ButtConsole;
import Habit.Habitat;
import Serial.ButtSerial;
import Serv.ButtServ;

import javax.swing.*;

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
        ButtServ buttServ = new ButtServ();
        add(buttServ);
        ////////

        setVisible(true);
        setFocusable(false);
    }


}

