package term4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main {
    private static boolean isTimeVisible = true;
    private static boolean isStopped = false;
    private static boolean isPaused = false;
    private static boolean isStarted = false;

    public static void main(String[] args) {
        Habitat habitat = new Habitat();
        System.out.println( habitat.hasFocus());

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

                    habitat.getMyComponent().getjTextFieldCapitalPeriod().setEnabled(false);
                    habitat.getMyComponent().getjTextFieldWoodenPeriod().setEnabled(false);

                    habitat.getMyComponent().getBtnStart().setEnabled(false);
                    habitat.getMyComponent().getBtnStop().setEnabled(true);

                    habitat.getMyComponent().getjComboBoxWoodenProbability().setEnabled(false);
                    habitat.getMyComponent().getjComboBoxCapitalProbability().setEnabled(false);
                }
            }
        });

        habitat.getJFrame().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E'){
                    System.out.println("Pressed E");

                    habitat.getMyComponent().getBtnStart().setEnabled(true);
                    habitat.getMyComponent().getBtnStop().setEnabled(false);

                    if(habitat.getMyComponent().getjCheckBox().isSelected()){
                       isPaused = true;
                        switch (JOptionPane.showConfirmDialog(habitat.getMyComponent(),
                                "Time: " + MyComponent.getTime() +
                                        "\nWooden House: " + WoodenHouse.getCount() +
                                        "\nCapital House: " + CapitalHouse.getCount() +
                                        "\nTotal: " + MyComponent.getCount(),
                                "Information",
                                JOptionPane.OK_CANCEL_OPTION))
                        {
                            case JOptionPane.OK_OPTION:
                                timer.stop();
                                isStopped = true;
                                isPaused = false;
                                habitat.update();
                                System.out.println("OK");
                                break;
                            case JOptionPane.CANCEL_OPTION:
                                System.out.println("Cancel");
                                isPaused = false;
                                break;
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

                habitat.getMyComponent().getjTextFieldCapitalPeriod().setEnabled(false);
                habitat.getMyComponent().getjTextFieldWoodenPeriod().setEnabled(false);

                habitat.getMyComponent().getBtnStart().setEnabled(false);
                habitat.getMyComponent().getBtnStop().setEnabled(true);

                habitat.getMyComponent().getjComboBoxWoodenProbability().setEnabled(false);
                habitat.getMyComponent().getjComboBoxCapitalProbability().setEnabled(false);
            }
        });

        habitat.getMyComponent().getBtnStop().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                habitat.getMyComponent().getBtnStart().setEnabled(true);
                habitat.getMyComponent().getBtnStop().setEnabled(false);

                if(habitat.getMyComponent().getjCheckBox().isSelected()){
                    isPaused = true;
                    switch (JOptionPane.showConfirmDialog(habitat.getMyComponent(),
                            "Time: " + MyComponent.getTime() +
                                    "\nWooden House: " + WoodenHouse.getCount() +
                                    "\nCapital House: " + CapitalHouse.getCount() +
                                    "\nTotal: " + MyComponent.getCount(),
                            "Information",
                            JOptionPane.OK_CANCEL_OPTION))
                    {
                        case JOptionPane.OK_OPTION:
                            timer.stop();
                            isStopped = true;
                            isPaused = false;
                            habitat.update();
                            System.out.println("OK");
                            break;
                        case JOptionPane.CANCEL_OPTION:
                            System.out.println("Cancel");
                            isPaused = false;
                            break;
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

                habitat.getMyComponent().getjTextFieldCapitalPeriod().setEnabled(false);
                habitat.getMyComponent().getjTextFieldWoodenPeriod().setEnabled(false);

                habitat.getMyComponent().getBtnStart().setEnabled(false);
                habitat.getMyComponent().getBtnStop().setEnabled(true);

                habitat.getMyComponent().getjComboBoxWoodenProbability().setEnabled(false);
                habitat.getMyComponent().getjComboBoxCapitalProbability().setEnabled(false);
            }
        });

        habitat.getMyComponent().getjMenuStop().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                habitat.getMyComponent().getBtnStart().setEnabled(true);
                habitat.getMyComponent().getBtnStop().setEnabled(false);

                if(habitat.getMyComponent().getjCheckBox().isSelected()){
                    isPaused = true;
                    switch (JOptionPane.showConfirmDialog(habitat.getMyComponent(),
                            "Time: " + MyComponent.getTime() +
                                    "\nWooden House: " + WoodenHouse.getCount() +
                                    "\nCapital House: " + CapitalHouse.getCount() +
                                    "\nTotal: " + MyComponent.getCount(),
                            "Information",
                            JOptionPane.OK_CANCEL_OPTION))
                    {
                        case JOptionPane.OK_OPTION:
                            timer.stop();
                            isStopped = true;
                            isPaused = false;
                            habitat.update();
                            System.out.println("OK");
                            break;
                        case JOptionPane.CANCEL_OPTION:
                            System.out.println("Cancel");
                            isPaused = false;
                            break;
                    }

                }
                else {
                    timer.stop();

                    isStopped = true;
                    habitat.update();
                }
            }
        });

    }


    public static boolean isTimeVisible() { return isTimeVisible; }

    public static boolean isStopped() { return isStopped; }

    public static boolean isPaused() { return isPaused; }

    public static boolean isStarted() { return isStarted; }
}
