package term4;


import javax.swing.*;
import java.awt.*;


public class MyComponent extends JComponent {

    private static int count = 0;
    int[] x, y;
    AbstractFactory abstractFactory;
    House[] house;

    boolean isFirst = true;
    private static long time = -1;
    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");

    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton jRadioButtonEnabled = new JRadioButton("Enabled");
    JRadioButton jRadioButtonDisabled = new JRadioButton("Disabled");

    JMenuBar jMenuBar = new JMenuBar();
    JMenu jMenuStart = new JMenu("Start");
    JMenu jMenuStop = new JMenu("Stop");

    JCheckBox jCheckBox = new JCheckBox("Inf", true);

    JDialog jDialog = new JDialog();


    MyComponent(){
        house = new House[1000];
        x = new int[1000];
        y = new int[1000];
        add(btnStart);
        add(btnStop);
        btnStart.setBounds(1190, 0, 70, 30);
        btnStop.setBounds(1190, 32, 70, 30);
        btnStop.setEnabled(false);

        buttonGroup.add(jRadioButtonEnabled);
        buttonGroup.add(jRadioButtonDisabled);

        jMenuBar.add(jMenuStart);
        jMenuBar.add(jMenuStop);

        jCheckBox.setBounds(1190, 64, 70, 30);
        add(jCheckBox);



    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;

        graphics2D.drawLine(1188, 0, 1188, 720);

        time++;


        if(isFirst){
            isFirst = false;
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

        if(Math.random() >= WoodenHouse.PROBABILITY && time % WoodenHouse.PERIOD == 0) {
            System.out.println("Wooden");
            x[count] = (int) (Math.random() * 1240);
            y[count] = (int) (Math.random() * 700);
            abstractFactory = ConcreteFactory.concreteFactory(TypeOfHouse.WOODEN);
            house[count] = abstractFactory.createHouse();
            count++;
        }

        if(Math.random() >= CapitalHouse.PROBABILITY && time % CapitalHouse.PERIOD == 0){
            System.out.println("Capital");
            x[count] = (int) (Math.random() * 1240);
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
}