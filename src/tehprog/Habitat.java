package tehprog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Habitat extends JFrame {
    private Timer timer = null;


    private ConcreteFactory factory = new ConcreteFactory();
    private LinkedList<AbstractEmployee> employees= new LinkedList<>();
    private long simTime;

    private boolean toggleTime = false;

    private BufferStrategy screenBuffer;
    private Dimension infoTab = new Dimension(200,100);

    private Dimension avatarDim = new Dimension(125,125);

    public Habitat(int w, int h) {
        super();
        initGUI(new Dimension(w,h));
    }

    public void start(){
        if(timer != null)
            return;
        employees.clear();
        factory.reset();
        simTime = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                AbstractEmployee employee = factory.getEmployee(simTime,
                                                                new Dimension(getWidth()-avatarDim.width,
                                                                              getHeight()-avatarDim.height));
                if(employee != null)
                    employees.add(employee);

                simTime++;
                update();
            }
        }, 0, 1000);
    }

    public void stop(){
        if(timer != null){
            timer.cancel();
            timer = null;
            drawInfo(screenBuffer.getDrawGraphics());
        }
    }



    public void update(){
        Graphics g = screenBuffer.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.clearRect(0,0,getWidth(),getHeight());

        for(AbstractEmployee e : employees)
            g.drawImage(e.getImg(), e.getCoords().x, e.getCoords().y, avatarDim.width, avatarDim.height, null);

        if(toggleTime) {
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(20.0f));
            g.drawString("Время: " + simTime + " сек.", 30, 50);
        }

        g.dispose();
        screenBuffer.show();
    }



    private void initGUI(Dimension dim){
        setResizable(false);
        setSize(dim);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar() == 'b' || e.getKeyChar() == 'B')
                    start();
                else if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E')
                    stop();
                else if(e.getKeyChar() == 't' || e.getKeyChar() == 'T'){
                    toggleTime = !toggleTime;
                    update();
                }
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        createBufferStrategy(2);
        screenBuffer = getBufferStrategy();

        Graphics t = screenBuffer.getDrawGraphics();
        t.setFont(new Font("Arial",Font.BOLD, 14));
        t.drawString("B - start",getWidth()/2-50, getHeight()/2);
        t.drawString("E - stop",getWidth()/2-50, getHeight()/2 + 15);
        t.drawString("T - show time",getWidth()/2-50, getHeight()/2 + 30);
        screenBuffer.show();
    }


    private void drawInfo(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getWidth()/2 - infoTab.width/2, getHeight()/2 - infoTab.height/2,
                infoTab.width, infoTab.height);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(14.0f));
        g.drawString("Время симуляции = "+ simTime +" сек.",
                (getWidth()/2 - infoTab.width/2)+20, (getHeight()/2 - infoTab.height/2) +20);

        g.setFont(new Font("Arial",Font.ITALIC, 14));
        g.drawString("Разработчики = "+ factory.getDeveloperSpawned(),
                (getWidth()/2 - infoTab.width/2)+20, (getHeight()/2 - infoTab.height/2) +40);
        g.setFont(new Font("Arial",Font.BOLD, 14));
        g.drawString("Менеджеры = "+ factory.getManagerSpawned(),
                (getWidth()/2 - infoTab.width/2)+20, (getHeight()/2 - infoTab.height/2) +60);
        g.dispose();
        screenBuffer.show();
    }
}


