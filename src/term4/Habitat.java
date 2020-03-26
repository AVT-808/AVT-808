package term4;

import javax.swing.*;


public class Habitat extends JComponent {
    private JFrame jFrame;
    MyComponent myComponent = new MyComponent();

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public Habitat() {
        jFrame = new JFrame();

        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setTitle("Programming technology");
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(myComponent);

        jFrame.setJMenuBar(myComponent.getjMenuBar());

        jFrame.setResizable(false);

        jFrame.setVisible(true);
    }

    public JFrame getJFrame() { return jFrame; }

    public int getWidth() { return WIDTH; }
    public int getHeight() { return HEIGHT; }

    public MyComponent getMyComponent() {
        return myComponent;
    }

    public void update(){
        System.out.println("Update");

        jFrame.requestFocus();

        jFrame.repaint();

    }

}
