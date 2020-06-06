package lab.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

import lab.Renderer;
import lab.frames.components.ControlBox;
import lab.frames.components.StatusBar;
import lab.habitat.Habitat;
import lab.habitat.ICreature;
import lab.habitat.creatures.birds.BigBird;
import lab.habitat.creatures.birds.SmallBird;

import static java.awt.event.KeyEvent.*;


public class MainForm extends JFrame {

    private JMenuBar menuBar = new JMenuBar();
    private JPanel frame = new JPanel();
    private JPanel creatureBox = new JPanel(true);
    private ControlBox controlBox = new ControlBox();
    private StatusBar statusBar = new StatusBar();

    private Habitat habitat;
    private Timer timer;
    private Instant startPoint, stopPoint, pausePoint;
    private Duration pauseTime = Duration.ZERO;

    private Renderer renderer;

    private boolean running = false;
    private boolean showingInfo = false;
    private boolean showingStats = false;




    public MainForm(Habitat h) {

        habitat = h;
        renderer = new Renderer(creatureBox, habitat);

        // habitat settings
        habitat.addCreatureCreateListener(e -> {
            if(e instanceof BigBird)
                controlBox.cBig.setText("Количество птиц: " + h.getMetrics().getCreatureCountByType(BigBird.class));
            if(e instanceof SmallBird)
                controlBox.cLit.setText("Количество птенцов: " + h.getMetrics().getCreatureCountByType(SmallBird.class));
        });
        habitat.addCreatureDieListener(e -> {
            if(e instanceof BigBird)
                controlBox.cBig.setText("Количество птиц: " + h.getMetrics().getCreatureCountByType(BigBird.class));
            if(e instanceof SmallBird)
                controlBox.cLit.setText("Количество птенцов: " + h.getMetrics().getCreatureCountByType(SmallBird.class));
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(
                e -> {
                    if(KeyEvent.KEY_PRESSED == e.getID())
                        keyPressed(e);
                    return false;
                }
        );

        // frame settings
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("src/lab/assets/icon256.png").getImage());
        setJMenuBar(menuBar);
        add(frame, BorderLayout.CENTER);

        // frame settings
        frame.add(creatureBox, BorderLayout.CENTER);
        frame.add(controlBox, BorderLayout.EAST);
        frame.add(statusBar, BorderLayout.SOUTH);

        // statusBar settings
        statusBar.setStatus("Симуляция ещё не началась", Color.RED);
        statusBar.setPreferredSize(new Dimension(980,20));


        JMenu actionMenu = new JMenu("Действия");
        JMenuItem menuStopItem = new JMenuItem("Стоп");
        JMenuItem menuStartItem = new JMenuItem("Начать");
        JMenuItem menuPauseItem = new JMenuItem("Пауза");

        menuStopItem.setEnabled(false);
        actionMenu.add(menuStartItem);
        menuStartItem.addActionListener((e -> {

            if(!running) {
                runSimulation();
                menuStartItem.setEnabled(false);
                menuStopItem.setEnabled(true);
            }
        } ));

        actionMenu.add(menuPauseItem);
        menuPauseItem.addActionListener((e -> {
            if(running) {
                pauseSimulation();
                menuPauseItem.setText("Продолжить");
            }
            else {
                resumeSimulation();
                menuPauseItem.setText("Пауза");
            }
        }));

        actionMenu.add(menuStopItem);
        menuStopItem.addActionListener(e -> {

            stopSimulation();
            menuStopItem.setEnabled(false);
            menuStartItem.setEnabled(true);
        });

        actionMenu.add(new JMenuItem("Выйти") {{ addActionListener(e -> System.exit(0));}});
        menuBar.add(actionMenu);


        // creatureBox settings
        creatureBox.setLayout(null);
        creatureBox.setBackground(Color.WHITE);

        creatureBox.setPreferredSize(new Dimension(980,620));

        // controlBox settings
        controlBox.run.addActionListener(e ->{

            runSimulation();
            menuStartItem.setEnabled(false);
            menuStopItem.setEnabled(true);

        } );

        controlBox.pause.setEnabled(false);
        controlBox.pause.addActionListener(e -> {
            if(running) {
                pauseSimulation();
                menuPauseItem.setText("Продолжить");
            }
            else {
                resumeSimulation();
                menuPauseItem.setText("Пауза");
            }

        });
        controlBox.stop.setEnabled(false);
        controlBox.stop.addActionListener(e -> {

            stopSimulation();
            menuStartItem.setEnabled(true);
            menuStopItem.setEnabled(false);

    });
        controlBox.showTimeOn.setSelected(true);
        controlBox.showTimeOn.addActionListener(e -> controlBox.ticks.setVisible(true));
        controlBox.showTimeOff.addActionListener(e -> controlBox.ticks.setVisible(false));



        controlBox.BigBirdChance.addChangeListener(e -> BigBird.setBornChance((Integer) controlBox.BigBirdChance.getValue() / 100.0));
        controlBox.SmallBirdChance.addChangeListener(e -> SmallBird.setBornChance((Integer) controlBox.SmallBirdChance.getValue() / 100.0));

        ((JSpinner.DefaultEditor) controlBox.textIntervalLit.getEditor()).getTextField().setFocusable(false);
        controlBox.textIntervalLit.addChangeListener(e -> SmallBird.setPeriod( (Integer) controlBox.textIntervalLit.getValue()));


        ((JSpinner.DefaultEditor) controlBox.textIntervalBig.getEditor()).getTextField().setFocusable(false);
        controlBox.textIntervalBig.addChangeListener(e -> BigBird.setPeriod( (Integer) controlBox.textIntervalBig.getValue()));


        controlBox.sliderLifeBig.addChangeListener(e -> BigBird.setTTL(controlBox.sliderLifeBig.getValue()));
        controlBox.sliderLifeLit.addChangeListener(e -> SmallBird.setTTL(controlBox.sliderLifeLit.getValue()));

        controlBox.showStats.addActionListener(e -> showingStats = !showingStats);

        controlBox.lifecreaturesBox.addActionListener(e-> {
            if (controlBox.lifecreaturesBox.getSelectedIndex() == 0) {
                controlBox.sliderLifeLit.setVisible(true);
                controlBox.sliderLifeBig.setVisible(false);
            }

            if (controlBox.lifecreaturesBox.getSelectedIndex() == 1) {
                controlBox.sliderLifeLit.setVisible(false);
                controlBox.sliderLifeBig.setVisible(true);
            }
        });

        controlBox.current.addActionListener(e -> {
            pauseSimulation();

            StringBuffer sb = new StringBuffer();
            var creatureTTLs = habitat.getCreaturesTTLs();

            for (Integer time : creatureTTLs.keySet()) {
                for (ICreature c : creatureTTLs.get(time)) {

                    sb.append(String.format("<p>ID: %s,Тип: %s, Умрет: %d</p>", c.getID(), c.getClass().getSimpleName(), time));
                }
            }
            String[] options = { "Ok" };
            JOptionPane.showOptionDialog(
                    this,
                    "<html>" + sb + "</html>",
                    "Текущие объекты",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon("src/img/icon64.png"),
                    options,
                    options[0]
            );
            resumeSimulation();

        });


        renderer.run();



    }

    private void runSimulation() {


        running = true;
        habitat.reset();



        startTimer();
        startPoint = Instant.now();

        controlBox.run.setEnabled(false);
        controlBox.pause.setEnabled(true);
        controlBox.stop.setEnabled(true);

        statusBar.setStatus("Симуляция запущена", Color.GREEN);
    }

    private void pauseSimulation() {
        running = false;

        stopTimer();
        pausePoint = Instant.now();
        statusBar.setStatus("Симуляция на паузе", Color.YELLOW);

    }

    private void resumeSimulation() {
        running = true;

        startTimer();


        statusBar.setStatus("Симуляция запущена", Color.GREEN);
    }

    private void stopSimulation() {


        stopPoint = Instant.now();
        if(!running) pauseTime.plus(Duration.between(pausePoint, Instant.now()));

        running = false;

        stopTimer();
        stopPoint = Instant.now();




        if(showingStats) {
            showStatistics();

        }
        else{

        controlBox.run.setEnabled(true);
        controlBox.pause.setEnabled(false);
        controlBox.stop.setEnabled(false);

        controlBox.ticks.setText("Тик: 0");
        controlBox.cBig.setText("Количество птиц: 0");
        controlBox.cLit.setText("Количество птенцов: 0");

        statusBar.setStatus("Симуляция ещё не началась", Color.RED);

            habitat.reset();

}

    }

    public void keyPressed(KeyEvent e) { // Бинд кнопок
        switch (e.getKeyCode()) {
            case VK_B: {            // Бинд кнопки "В"
                if(!running) {
                    runSimulation();

                }
                break;
            }
            case VK_E: {            // Бинд кнопки "Е"
                stopSimulation();
                break;
            }
            case VK_T: {            // Бинд кнопки "Т"
                controlBox.showInfoPanel.setVisible(showingInfo = !showingInfo);
                break;
            }
            case VK_P: {            // Бинд кнопки "Р"
                if(running)
                    pauseSimulation();
                else
                    resumeSimulation();
                break;
            }
        }
    }

    private void startTimer() {
        if(timer != null)
            timer.cancel();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                habitat.update();
                controlBox.ticks.setText("Тик: " + habitat.getMetrics().getTime());
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        if(timer == null)
            return;

        timer.cancel();
    }


        private void showStatistics() {


        controlBox.stop.setEnabled(false);
        controlBox.pause.setEnabled(false);

        int secs = habitat.getMetrics().getTime();
        StringBuffer sb = new StringBuffer();

        int BigBirdCount = habitat.getMetrics().getCreatureCountByType(BigBird.class);
        int SmallBirdCount = habitat.getMetrics().getCreatureCountByType(SmallBird.class);



        sb.append(String.format("<p> Время симуляции: %dс </p>", secs));
        sb.append(String.format("<p> Количество птиц: %d </p>", BigBirdCount));
        sb.append(String.format("<p> Количество птенцов: %d </p>", SmallBirdCount));

        switch (
                JOptionPane.showOptionDialog(this,
                        "<html>" + sb + "</html>",
                        "Общая информация",
                        JOptionPane.OK_CANCEL_OPTION,

                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("src/lab/assets/icon64.png"),
                        new String[] {"Ok","Cancel" },
                        "Cancel"
                ))
        {
            case JOptionPane.OK_OPTION:{


                timer.cancel();
                habitat.reset();

                controlBox.run.setEnabled(true);
                controlBox.pause.setEnabled(false);
                controlBox.stop.setEnabled(false);


                controlBox.ticks.setText("Sec: 0");
                controlBox.cBig.setText("Количество птиц: 0");
                controlBox.cLit.setText("Количество птенцов: 0");
                statusBar.setStatus("Симуляция ещё не началась", Color.RED);
                break;
            }

            case JOptionPane.CANCEL_OPTION:{

                break;}

            default:{

                controlBox.stop.setEnabled(true);
                controlBox.pause.setEnabled(true);
                statusBar.setStatus("Симуляция на паузе", Color.YELLOW);
                break;

            }
        }

    }



}
