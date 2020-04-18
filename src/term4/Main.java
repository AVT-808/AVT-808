package term4;

import javax.swing.*;
import java.awt.event.*;


public class Main {
    private static boolean isTimeVisible = true;
    private static boolean isStopped = false;
    private static boolean isPaused = false;
    private static boolean isStarted = false;

    public static Habitat habitat = new Habitat();

    public static void main(String[] args) {

        Config config = new Config();
        config.loadConfig();

        CapitalHouseAI capitalHouseAI = new CapitalHouseAI();
        WoodenHouseAI woodenHouseAI = new WoodenHouseAI();

        Console console = new Console(habitat.getJFrame());

        Serializer serializer = new Serializer();

        Timer timer = new Timer(1000, e -> habitat.update());

        habitat.update();


        habitat.getJFrame().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'b' || e.getKeyChar() == 'B'){
                    System.out.println("Pressed B");

                    timer.start();
                    isStarted = true;
                    try {
                        CapitalHouse.setPeriod(Integer.parseInt(habitat.getMyComponent().getjTextFieldCapitalPeriod().getText()));
                    }
                    catch (RuntimeException re){
                        JOptionPane.showMessageDialog(habitat.getMyComponent(),
                                "Для капитального дома значение периода выставлено по умолчанию (4 сек.)");
                    }

                    try {
                        WoodenHouse.setPeriod(Integer.parseInt(habitat.getMyComponent().getjTextFieldWoodenPeriod().getText()));
                    }
                    catch (RuntimeException re){
                        JOptionPane.showMessageDialog(habitat.getMyComponent(),
                                "Для деревянного дома значение периода выставлено по умолчанию (3 сек.)");
                    }

                    try{
                        CapitalHouse.setLifetime(Integer.parseInt(habitat.getMyComponent().getjTextFieldCapitalLifetime().getText()));
                    }
                    catch (RuntimeException re){
                        JOptionPane.showMessageDialog(habitat.getMyComponent(),
                                "Для капитального дома значение времени жизни выставлено по умолчанию (10 сек.)");
                    }

                    try {
                        WoodenHouse.setLifetime(Integer.parseInt(habitat.getMyComponent().getjTextFieldWoodenLifetime().getText()));
                    }
                    catch (RuntimeException re){
                        JOptionPane.showMessageDialog(habitat.getMyComponent(),
                                "Для деревянного дома значение времени жизни выставлено по умолчанию (9 сек.)");
                    }

                    habitat.getMyComponent().getjTextFieldCapitalPeriod().setEnabled(false);
                    habitat.getMyComponent().getjTextFieldWoodenPeriod().setEnabled(false);

                    habitat.getMyComponent().getBtnStart().setEnabled(false);
                    habitat.getMyComponent().getBtnStop().setEnabled(true);

                    habitat.getMyComponent().getjComboBoxWoodenProbability().setEnabled(false);
                    habitat.getMyComponent().getjComboBoxCapitalProbability().setEnabled(false);

                    habitat.getMyComponent().getjTextFieldWoodenLifetime().setEnabled(false);
                    habitat.getMyComponent().getjTextFieldCapitalLifetime().setEnabled(false);

                    habitat.getMyComponent().getjComboBoxWoodenPriority().setEnabled(false);
                    habitat.getMyComponent().getjComboBoxCapitalPriority().setEnabled(false);
                }
            }
        });

        habitat.getJFrame().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E'){
                    System.out.println("Pressed E");

                    if(habitat.getMyComponent().getjCheckBox().isSelected()){
                       isPaused = true;
                        switch (JOptionPane.showConfirmDialog(habitat.getMyComponent(),
                                "Time: " + MyComponent.getTime() +
                                        "\nWooden House: " + WoodenHouse.getCount() +
                                        "\nCapital House: " + CapitalHouse.getCount() +
                                        "\nTotal: " + MyComponent.getTotal(),
                                "Information",
                                JOptionPane.OK_CANCEL_OPTION)) {
                            case JOptionPane.OK_OPTION -> {
                                timer.stop();
                                isStopped = true;
                                isPaused = false;

                                habitat.getMyComponent().getjTextFieldCapitalPeriod().setEnabled(true);
                                habitat.getMyComponent().getjTextFieldWoodenPeriod().setEnabled(true);

                                habitat.getMyComponent().getBtnStart().setEnabled(true);
                                habitat.getMyComponent().getBtnStop().setEnabled(false);

                                habitat.getMyComponent().getjComboBoxWoodenProbability().setEnabled(true);
                                habitat.getMyComponent().getjComboBoxCapitalProbability().setEnabled(true);

                                habitat.getMyComponent().getjTextFieldWoodenLifetime().setEnabled(true);
                                habitat.getMyComponent().getjTextFieldCapitalLifetime().setEnabled(true);

                                habitat.getMyComponent().getjComboBoxWoodenPriority().setEnabled(true);
                                habitat.getMyComponent().getjComboBoxCapitalPriority().setEnabled(true);

                                habitat.update();
                                System.out.println("OK");
                            }
                            case JOptionPane.CANCEL_OPTION -> {
                                System.out.println("Cancel");
                                isPaused = false;
                            }
                        }

                    }
                    else {
                        timer.stop();

                        isStopped = true;
                        habitat.update();
                    }



                }
            }
        });

        habitat.getJFrame().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 't' || e.getKeyChar() == 'T') {
                    System.out.println("Pressed T");

                    isTimeVisible = !isTimeVisible;
                    if(isTimeVisible){
                        habitat.getMyComponent().getjRadioButtonEnabled().setSelected(true);
                    }
                    else {
                        habitat.getMyComponent().getjRadioButtonDisabled().setSelected(true);
                    }
                }
            }
        });

        habitat.getMyComponent().getjRadioButtonEnabled().addActionListener(e -> {
            if (habitat.getMyComponent().getjRadioButtonEnabled().isSelected()){
                isTimeVisible = true;
            }
        });

        habitat.getMyComponent().getjRadioButtonDisabled().addActionListener(e -> {
                if (habitat.getMyComponent().getjRadioButtonDisabled().isSelected()) {
                    isTimeVisible = false;
                }
        });

        habitat.getMyComponent().getBtnStart().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer.start();
                isStarted = true;
                    try {
                        CapitalHouse.setPeriod(Integer.parseInt(habitat.getMyComponent().getjTextFieldCapitalPeriod().getText()));
                    }
                    catch (RuntimeException re){
                        JOptionPane.showMessageDialog(habitat.getMyComponent(),
                                "Для капитального дома значение периода выставлено по умолчанию (4 сек.)");
                    }

                    try {
                        WoodenHouse.setPeriod(Integer.parseInt(habitat.getMyComponent().getjTextFieldWoodenPeriod().getText()));
                    }
                    catch (RuntimeException re){
                        JOptionPane.showMessageDialog(habitat.getMyComponent(),
                                "Для деревянного дома значение периода выставлено по умолчанию (3 сек.)");
                    }

                try{
                    CapitalHouse.setLifetime(Integer.parseInt(habitat.getMyComponent().getjTextFieldCapitalLifetime().getText()));
                }
                catch (RuntimeException re){
                    JOptionPane.showMessageDialog(habitat.getMyComponent(),
                            "Для капитального дома значение времени жизни выставлено по умолчанию (10 сек.)");
                }

                try {
                    WoodenHouse.setLifetime(Integer.parseInt(habitat.getMyComponent().getjTextFieldWoodenLifetime().getText()));
                }
                catch (RuntimeException re){
                    JOptionPane.showMessageDialog(habitat.getMyComponent(),
                            "Для деревянного дома значение времени жизни выставлено по умолчанию (9 сек.)");
                }

                habitat.getMyComponent().getjTextFieldCapitalPeriod().setEnabled(false);
                habitat.getMyComponent().getjTextFieldWoodenPeriod().setEnabled(false);

                habitat.getMyComponent().getBtnStart().setEnabled(false);
                habitat.getMyComponent().getBtnStop().setEnabled(true);

                habitat.getMyComponent().getjComboBoxWoodenProbability().setEnabled(false);
                habitat.getMyComponent().getjComboBoxCapitalProbability().setEnabled(false);

                habitat.getMyComponent().getjTextFieldWoodenLifetime().setEnabled(false);
                habitat.getMyComponent().getjTextFieldCapitalLifetime().setEnabled(false);

                habitat.getMyComponent().getjComboBoxWoodenPriority().setEnabled(false);
                habitat.getMyComponent().getjComboBoxCapitalPriority().setEnabled(false);

            }
        });

        habitat.getMyComponent().getBtnStop().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(habitat.getMyComponent().getjCheckBox().isSelected()){
                    isPaused = true;
                    switch (JOptionPane.showConfirmDialog(habitat.getMyComponent(),
                            "Time: " + MyComponent.getTime() +
                                    "\nWooden House: " + WoodenHouse.getCount() +
                                    "\nCapital House: " + CapitalHouse.getCount() +
                                    "\nTotal: " + MyComponent.getTotal(),
                            "Information",
                            JOptionPane.OK_CANCEL_OPTION)) {
                        case JOptionPane.OK_OPTION -> {
                            timer.stop();
                            isStopped = true;
                            isPaused = false;

                            habitat.getMyComponent().getjTextFieldCapitalPeriod().setEnabled(true);
                            habitat.getMyComponent().getjTextFieldWoodenPeriod().setEnabled(true);

                            habitat.getMyComponent().getBtnStart().setEnabled(true);
                            habitat.getMyComponent().getBtnStop().setEnabled(false);

                            habitat.getMyComponent().getjComboBoxWoodenProbability().setEnabled(true);
                            habitat.getMyComponent().getjComboBoxCapitalProbability().setEnabled(true);

                            habitat.getMyComponent().getjTextFieldWoodenLifetime().setEnabled(true);
                            habitat.getMyComponent().getjTextFieldCapitalLifetime().setEnabled(true);

                            habitat.getMyComponent().getjComboBoxWoodenPriority().setEnabled(true);
                            habitat.getMyComponent().getjComboBoxCapitalPriority().setEnabled(true);

                            habitat.update();
                            System.out.println("OK");
                        }
                        case JOptionPane.CANCEL_OPTION -> {
                            System.out.println("Cancel");
                            isPaused = false;
                        }
                    }

                }
                else {
                    timer.stop();

                    isStopped = true;
                    habitat.update();
                }

            }
        });

        habitat.getMyComponent().getjTextFieldWoodenPeriod().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                habitat.getMyComponent().getjTextFieldWoodenPeriod().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(habitat.getMyComponent().getjTextFieldWoodenPeriod().getText().isEmpty()){
                    habitat.getMyComponent().getjTextFieldWoodenPeriod().setText("Wooden Period");
                }
            }
        });

        habitat.getMyComponent().getjTextFieldCapitalPeriod().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                habitat.getMyComponent().getjTextFieldCapitalPeriod().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(habitat.getMyComponent().getjTextFieldCapitalPeriod().getText().isEmpty()){
                    habitat.getMyComponent().getjTextFieldCapitalPeriod().setText("Capital Period");
                }
            }
        });

        habitat.getJFrame().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                habitat.getJFrame().requestFocus();
            }
        });

        habitat.getMyComponent().getjComboBoxWoodenProbability().addActionListener(e ->
                WoodenHouse.setProbability((double)habitat.getMyComponent().getjComboBoxWoodenProbability().getSelectedItem()));

        habitat.getMyComponent().getjComboBoxCapitalProbability().addActionListener(e ->
                CapitalHouse.setProbability((double)habitat.getMyComponent().getjComboBoxCapitalProbability().getSelectedItem()));

        habitat.getMyComponent().getjMenuItemInfoOn().addActionListener(e ->
                habitat.getMyComponent().getjCheckBox().setSelected(true)
        );

        habitat.getMyComponent().getjMenuItemInfoOff().addActionListener(e ->
                        habitat.getMyComponent().getjCheckBox().setSelected(false)
                );

        habitat.getMyComponent().getjMenuItemTimerOn().addActionListener(e -> {
            habitat.getMyComponent().getjRadioButtonEnabled().setSelected(true);
            isTimeVisible = true;
        });

        habitat.getMyComponent().getjMenuItemTimerOff().addActionListener(e -> {
            habitat.getMyComponent().getjRadioButtonDisabled().setSelected(false);
            isTimeVisible = false;
        });

        habitat.getMyComponent().getjMenuStart().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer.start();
                isStarted = true;
                try {
                    CapitalHouse.setPeriod(Integer.parseInt(habitat.getMyComponent().getjTextFieldCapitalPeriod().getText()));
                }
                catch (RuntimeException re){
                    JOptionPane.showMessageDialog(habitat.getMyComponent(),
                            "Для капитального дома значение периода выставлено по умолчанию (4 сек.)");
                }

                try {
                    WoodenHouse.setPeriod(Integer.parseInt(habitat.getMyComponent().getjTextFieldWoodenPeriod().getText()));
                }
                catch (RuntimeException re){
                    JOptionPane.showMessageDialog(habitat.getMyComponent(),
                            "Для деревянного дома значение периода выставлено по умолчанию (3 сек.)");
                }

                try{
                    CapitalHouse.setLifetime(Integer.parseInt(habitat.getMyComponent().getjTextFieldCapitalLifetime().getText()));
                }
                catch (RuntimeException re){
                    JOptionPane.showMessageDialog(habitat.getMyComponent(),
                            "Для капитального дома значение времени жизни выставлено по умолчанию (10 сек.)");
                }

                try {
                    WoodenHouse.setLifetime(Integer.parseInt(habitat.getMyComponent().getjTextFieldWoodenLifetime().getText()));
                }
                catch (RuntimeException re){
                    JOptionPane.showMessageDialog(habitat.getMyComponent(),
                            "Для деревянного дома значение времени жизни выставлено по умолчанию (9 сек.)");
                }

                habitat.getMyComponent().getjTextFieldCapitalPeriod().setEnabled(false);
                habitat.getMyComponent().getjTextFieldWoodenPeriod().setEnabled(false);

                habitat.getMyComponent().getBtnStart().setEnabled(false);
                habitat.getMyComponent().getBtnStop().setEnabled(true);

                habitat.getMyComponent().getjComboBoxWoodenProbability().setEnabled(false);
                habitat.getMyComponent().getjComboBoxCapitalProbability().setEnabled(false);

                habitat.getMyComponent().getjTextFieldWoodenLifetime().setEnabled(false);
                habitat.getMyComponent().getjTextFieldCapitalLifetime().setEnabled(false);
            }
        });

        habitat.getMyComponent().getjMenuStop().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(habitat.getMyComponent().getjCheckBox().isSelected()){
                    isPaused = true;
                    switch (JOptionPane.showConfirmDialog(habitat.getMyComponent(),
                            "Time: " + MyComponent.getTime() +
                                    "\nWooden House: " + WoodenHouse.getCount() +
                                    "\nCapital House: " + CapitalHouse.getCount() +
                                    "\nTotal: " + MyComponent.getTotal(),
                            "Information",
                            JOptionPane.OK_CANCEL_OPTION)) {
                        case JOptionPane.OK_OPTION -> {
                            timer.stop();
                            isStopped = true;
                            isPaused = false;

                            habitat.getMyComponent().getjTextFieldCapitalPeriod().setEnabled(true);
                            habitat.getMyComponent().getjTextFieldWoodenPeriod().setEnabled(true);

                            habitat.getMyComponent().getBtnStart().setEnabled(true);
                            habitat.getMyComponent().getBtnStop().setEnabled(false);

                            habitat.getMyComponent().getjComboBoxWoodenProbability().setEnabled(true);
                            habitat.getMyComponent().getjComboBoxCapitalProbability().setEnabled(true);

                            habitat.getMyComponent().getjTextFieldWoodenLifetime().setEnabled(true);
                            habitat.getMyComponent().getjTextFieldCapitalLifetime().setEnabled(true);

                            habitat.update();
                            System.out.println("OK");
                        }
                        case JOptionPane.CANCEL_OPTION -> {
                            System.out.println("Cancel");
                            isPaused = false;
                        }
                    }

                }
                else {
                    timer.stop();

                    isStopped = true;
                    habitat.update();
                }
            }
        });

        habitat.getMyComponent().getjTextFieldWoodenLifetime().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                habitat.getMyComponent().getjTextFieldWoodenLifetime().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(habitat.getMyComponent().getjTextFieldWoodenLifetime().getText().isEmpty()){
                    habitat.getMyComponent().getjTextFieldWoodenLifetime().setText("Wooden Lifetime");
                }
            }
        });

        habitat.getMyComponent().getjTextFieldCapitalLifetime().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                habitat.getMyComponent().getjTextFieldCapitalLifetime().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(habitat.getMyComponent().getjTextFieldCapitalLifetime().getText().isEmpty()){
                    habitat.getMyComponent().getjTextFieldCapitalLifetime().setText("Capital Lifetime");
                }
            }
        });

        habitat.getMyComponent().getBtnShow().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Singleton singleton = Singleton.getSingleton();
                InformationDialog informationDialog = new InformationDialog(habitat.getJFrame());
                informationDialog.showDialog(singleton.getHouseVector());
            }
        });

        habitat.getMyComponent().getjCheckBoxWoodenHouseAI().addActionListener(e -> {
            if(habitat.getMyComponent().getjCheckBoxWoodenHouseAI().isSelected()){
                woodenHouseAI.startAI();
            }
            else {
                woodenHouseAI.stopAI();
            }
        });

        habitat.getMyComponent().getjCheckBoxCapitalHouseAI().addActionListener(e -> {
            if(habitat.getMyComponent().getjCheckBoxCapitalHouseAI().isSelected()){
                capitalHouseAI.startAI();
            }
            else {
                capitalHouseAI.stopAI();
            }
        });

        habitat.getMyComponent().getjComboBoxWoodenPriority().addActionListener(e ->
                woodenHouseAI.setTheadPriority((int)habitat.getMyComponent().getjComboBoxWoodenPriority().getSelectedItem()));

        habitat.getMyComponent().getjComboBoxCapitalPriority().addActionListener(e ->
                capitalHouseAI.setTheadPriority((int)habitat.getMyComponent().getjComboBoxCapitalPriority().getSelectedItem()));

        habitat.getMyComponent().getjButtonConsole().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                console.showConsole();
            }
        });

        habitat.getJFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                config.saveConfig();
                System.out.println("save");
            }
        });

        habitat.getMyComponent().getjButtonSave().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                serializer.serialization();
            }
        });

        habitat.getMyComponent().getjButtonLoad().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                serializer.deserialization();
            }
        });

    }


    public static boolean isTimeVisible() { return isTimeVisible; }

    public static boolean isStopped() { return isStopped; }

    public static boolean isPaused() { return isPaused; }

    public static boolean isStarted() { return isStarted; }

    public static void setIsTimeVisible(boolean isTimeVisible) { Main.isTimeVisible = isTimeVisible; }
}
