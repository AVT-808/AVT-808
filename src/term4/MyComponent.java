package term4;


import javax.swing.*;
import java.awt.*;
import java.text.Format;


public class MyComponent extends JComponent {

    private static int count = 0;
    private int[] x, y;
    private AbstractFactory abstractFactory;
    private House[] house;

    private boolean isFirst = true;
    private static long time = -1;
    private JButton btnStart = new JButton("Start");
    private JButton btnStop = new JButton("Stop");

    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton jRadioButtonEnabled = new JRadioButton("Enabled");
    private JRadioButton jRadioButtonDisabled = new JRadioButton("Disabled");

    private JCheckBox jCheckBox = new JCheckBox("Info", true);

    private JTextField jTextFieldWoodenPeriod = new JTextField("Wooden Period");
    private JTextField jTextFieldCapitalPeriod = new JTextField("Capital Period");

    private Double[] selectionStep = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};

    private JComboBox<Double> jComboBoxWoodenProbability = new JComboBox<Double>(selectionStep);
    private JComboBox<Double> jComboBoxCapitalProbability = new JComboBox<Double>(selectionStep);

    private JMenuBar jMenuBar = new JMenuBar();
    private JMenu jMenuStart = new JMenu("Start");
    private JMenu jMenuStop = new JMenu("Stop");
    private JMenu jMenuInfo = new JMenu("Info");
    private JMenu jMenuTimer = new JMenu("Timer");
    private JMenuItem jMenuItemInfoOn = new JMenuItem("On");
    private JMenuItem jMenuItemInfoOff = new JMenuItem("Off");
    private JMenuItem jMenuItemTimerOn = new JMenuItem("On");
    private JMenuItem jMenuItemTimerOff = new JMenuItem("Off");


    MyComponent(){
        house = new House[1000];
        x = new int[1000];
        y = new int[1000];
        add(btnStart);
        add(btnStop);
        btnStart.setBounds(1180, 0, 80, 30);
        btnStop.setBounds(1180, 32, 80, 30);
        btnStop.setEnabled(false);

        jCheckBox.setBounds(1180, 64, 80, 30);
        add(jCheckBox);

        buttonGroup.add(jRadioButtonEnabled);
        buttonGroup.add(jRadioButtonDisabled);
        jRadioButtonEnabled.setSelected(true);
        jRadioButtonEnabled.setBounds(1180, 98, 80, 30);
        jRadioButtonDisabled.setBounds(1180, 130, 80, 30);
        add(jRadioButtonEnabled);
        add(jRadioButtonDisabled);

        jTextFieldWoodenPeriod.setBounds(1180, 162, 80, 23);
        add(jTextFieldWoodenPeriod);
        jTextFieldCapitalPeriod.setBounds(1180,212, 80,23);
        add(jTextFieldCapitalPeriod);

        jComboBoxWoodenProbability.setBounds(1180, 187, 80, 23);
        add(jComboBoxWoodenProbability);
        jComboBoxCapitalProbability.setBounds(1180, 237, 80, 23);
        add(jComboBoxCapitalProbability);

        jMenuBar.add(jMenuStart);
        jMenuBar.add(jMenuStop);
        jMenuInfo.add(jMenuItemInfoOn);
        jMenuInfo.add(jMenuItemInfoOff);
        jMenuBar.add(jMenuInfo);
        jMenuTimer.add(jMenuItemTimerOn);
        jMenuTimer.add(jMenuItemTimerOff);
        jMenuBar.add(jMenuTimer);



    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;

        graphics2D.drawLine(1178, 0, 1178, 720);

        //time++;


        if(isFirst){
            time++;
            isFirst = false;
            return;
        }

        if(!Main.isStarted()){
            graphics2D.drawString("Time: " + time, 5, 13);
            return;
        }

        if(Main.isPaused()){
            for(int i = 0; i < count; i++) {
                house[i].getImg().paintIcon(this, g, x[i], y[i]);
            }

            if(Main.isTimeVisible()){
                graphics2D.drawString("Time: " + time, 5, 13);
            }

            return;
        }


        if(Main.isStopped()){
            g.setFont(new Font("TimesNewRomain", Font.BOLD, 20));
            g.setColor(Color.GREEN);
            g.drawString("Time: " + MyComponent.getTime(), 560, 320);

            g.setFont(new Font("Arial", Font.ITALIC, 20));
            g.setColor(Color.BLUE);
            g.drawString("Wooden House: " + WoodenHouse.getCount(), 560, 340);

            g.setFont(new Font("Lobster", Font.BOLD, 20));
            g.setColor(Color.MAGENTA);
            g.drawString("Capital House: " + CapitalHouse.getCount(), 560, 360);

            g.setFont(new Font("Calibri", Font.ITALIC, 20));
            g.setColor(Color.RED);
            g.drawString("Total: " + MyComponent.getCount(), 560, 380);


            for(int i = 0; i < house.length; i++)
                house[i] = null;


            return;
        }

        time++;

        if(Math.random() >= WoodenHouse.getProbability() && time % WoodenHouse.getPeriod() == 0) {
            System.out.println("Wooden");
            x[count] = (int) (Math.random() * 1155);
            y[count] = (int) (Math.random() * 700);
            abstractFactory = ConcreteFactory.concreteFactory(TypeOfHouse.WOODEN);
            house[count] = abstractFactory.createHouse();
            count++;
        }

        if(Math.random() >= CapitalHouse.getProbability() && time % CapitalHouse.getPeriod() == 0){
            System.out.println("Capital");
            x[count] = (int) (Math.random() * 1155);
            y[count] = (int) (Math.random() * 700);
            abstractFactory = ConcreteFactory.concreteFactory(TypeOfHouse.CAPITAL);
            house[count] = abstractFactory.createHouse();
            count++;
        }

        if(Main.isTimeVisible()){
            graphics2D.drawString("Time: " + time, 5, 13);
        }



        for(int i = 0; i < count; i++) {
            house[i].getImg().paintIcon(this, g, x[i], y[i]);
        }
    }

    public static long getTime() { return time; }

    public static int getCount() { return count; }

    public JButton getBtnStart() { return btnStart; }

    public JButton getBtnStop() { return btnStop; }

    public JMenuBar getjMenuBar() { return jMenuBar; }

    public JCheckBox getjCheckBox() { return  jCheckBox; }

    public JTextField getjTextFieldWoodenPeriod() { return jTextFieldWoodenPeriod; }

    public JTextField getjTextFieldCapitalPeriod() { return jTextFieldCapitalPeriod; }

    public JRadioButton getjRadioButtonEnabled() { return jRadioButtonEnabled; }

    public JRadioButton getjRadioButtonDisabled() { return jRadioButtonDisabled; }

    public JComboBox<Double> getjComboBoxWoodenProbability() { return jComboBoxWoodenProbability; }

    public JComboBox<Double> getjComboBoxCapitalProbability() { return jComboBoxCapitalProbability; }

    public JMenuItem getjMenuItemInfoOn() { return jMenuItemInfoOn; }

    public JMenuItem getjMenuItemInfoOff() { return jMenuItemInfoOff; }

    public JMenuItem getjMenuItemTimerOn() { return jMenuItemTimerOn; }

    public JMenuItem getjMenuItemTimerOff() { return jMenuItemTimerOff; }

    public JMenu getjMenuStart() { return jMenuStart; }

    public JMenu getjMenuStop() { return jMenuStop; }
}