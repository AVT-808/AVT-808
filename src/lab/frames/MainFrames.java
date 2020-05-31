package lab.frames;

import lab.habitat.ICreature;
import lab.habitat.creatures.birds.BigBird;
import lab.habitat.creatures.birds.SmallBird;
import lab.habitat.Habitat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Image.SCALE_SMOOTH;
import static java.awt.event.KeyEvent.*;


public class MainFrames extends JFrame {

    private JLabel timerEnabled = new JLabel("Timer: off");
    private JLabel ticks = new JLabel("Sec: 0");
    private JLabel optChLit = new JLabel("Chance for SmallBirds: ");
    private JLabel optChBig = new JLabel("Chance for BigBird: ");

    private JLabel optPerLit = new JLabel("Period for SmallBirds: ");
    private JLabel optPerBig = new JLabel("Period for BigBird: ");


    private JLabel optLifeBig = new JLabel("Life BigBird: ");
    private JLabel optLifeLittle = new JLabel("Life SmallBirds: ");

    public JSpinner SmallBirdChance = new JSpinner(new SpinnerNumberModel(50, 0, 100, 10));
    public JSpinner BigBirdChance = new JSpinner(new SpinnerNumberModel(50, 0, 100, 10));

    public JSpinner SmallBirdPeriod = new JSpinner(new SpinnerNumberModel(5, 0, 100, 1));
    public JSpinner BigBirdPeriod= new JSpinner(new SpinnerNumberModel(6, 0, 100, 1));

    private JSlider textLifeBig = new JSlider(1, 100, 10);
    private JSlider textLifeLittle = new JSlider(1, 100, 10);

    private JButton run = new JButton("B");
    private JButton pause = new JButton("P");
    private JButton stop = new JButton("E");
    private JButton showTime = new JButton("T");

    private JRadioButton showTimeON = new JRadioButton("Show Time");
    private JRadioButton showTimeOFF = new JRadioButton("Hide Time");

    public JCheckBox showStatsDlg = new JCheckBox("Show Info");

    private JPanel creatureBox = new JPanel();




    private HashMap<ICreature, JComponent> creatureBoxComponents = new HashMap<>();

    private Habitat habitat;
    private boolean running = false;
    private Timer timer;
    private Instant startPoint, stopPoint, pausePoint;
    private Duration pauseTime = Duration.ZERO;
    private boolean showInfo_flag = false;


    public MainFrames(Habitat h) {
        habitat = h;

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(
                e -> {
                    if(KeyEvent.KEY_PRESSED == e.getID())
                        keyPressed(e);
                    return false;
                }
        );


        timerEnabled.setBounds(0, 0, 120, 20);
        ticks.setBounds(120, 0, 120, 20);

        optChLit.setBounds(0, 25, 135, 20);
        optChBig.setBounds(140, 25, 120, 20);

        optPerLit.setBounds(270, 25, 135, 20);
        optPerBig.setBounds(410, 25, 135, 20);

        optLifeBig.setBounds(525, 25, 120, 20);
        optLifeLittle.setBounds(730, 25, 120, 20);

        SmallBirdChance.setBounds(0, 50, 100, 20);
        BigBirdChance.setBounds(140, 50, 100, 20);

        SmallBirdPeriod.setBounds(270, 50, 100, 20);
        BigBirdPeriod.setBounds(410, 50, 100, 20);

        textLifeBig.setBounds(525, 50, 200, 42);
        textLifeLittle.setBounds(730, 50, 200, 42);

        run.setBounds( 425 , 0, 50, 20);
        pause.setBounds(495, 0, 50, 20);
        stop.setBounds(565, 0, 50, 20);
        showTime.setBounds(635, 0, 50, 20);


        showTimeOFF.setBounds(830, 25, 120, 20);
        showTimeON.setBounds(830, 0, 120, 20);

        showStatsDlg.setBounds(695, 0, 120, 20);
        showStatsDlg.setEnabled(true);

        pause.setEnabled(false);
        stop.setEnabled(false);


        creatureBox.setBackground(Color.WHITE);
        creatureBox.setLayout(null);
        creatureBox.setBounds(0, 100, 935, 424);


        timerEnabled.setToolTipText("Таймер");
        ticks.setToolTipText("Время симуляции");
        run.setToolTipText("Запуск симуляции");
        pause.setToolTipText("Пауза");
        stop.setToolTipText("Остановка симуляции, очищение рабочей области");
        showTime.setToolTipText("Скрыть/Показать время симуляции");
        SmallBirdChance.setToolTipText("Шанс появления птенцов");
        BigBirdChance.setToolTipText("Шанс появления птиц");

        SmallBirdPeriod.setToolTipText("Шанс появления птенцов");
        BigBirdPeriod.setToolTipText("Шанс появления птиц");

        textLifeLittle.setToolTipText("Время жизни птенцов");
        textLifeBig.setToolTipText("Время жизни птиц");


        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        setLayout(null);
        setIconImage(new ImageIcon("src/lab/assets/icon256.png").getImage());
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        add(timerEnabled);
        add(ticks);

        add(optChLit);
        add(optChBig);

        add(optPerLit);
        add(optPerBig);

        SmallBirdChance.setPreferredSize(new Dimension(50,25));
        add(SmallBirdChance);

        BigBirdChance.setPreferredSize(new Dimension(50,25));
        add(BigBirdChance);

        SmallBirdPeriod.setPreferredSize(new Dimension(50,25));
        add(SmallBirdPeriod);

        BigBirdPeriod.setPreferredSize(new Dimension(50,25));
        add(BigBirdPeriod);

        add(optLifeBig);
        add(optLifeLittle);

        add(run); // Кнопка старта
        add(pause); // Кнопка паузы
        add(stop); // Кнопка стоп
        add(showTime); // Кнопка скрытия cSec
        add(showTimeON);
        add(showTimeOFF);

        add(showStatsDlg);
        add(textLifeBig);
        add(textLifeLittle);
        add(creatureBox);


        textLifeLittle.setPaintTicks(true);
        textLifeLittle.setPaintLabels(true);
        textLifeLittle.setMajorTickSpacing(10);

        textLifeBig.setPaintTicks(true);
        textLifeBig.setPaintLabels(true);
        textLifeBig.setMajorTickSpacing(10);


        // menuBar settin

        JMenu highMenu = new JMenu("Действия");

        JMenuItem menuStopItem = new JMenuItem("Стоп");


        JMenuItem menuStartItem = new JMenuItem("Начать");
        highMenu.add(menuStartItem);
        menuStartItem.addActionListener(e -> {
            startTimer();
            startPoint = Instant.now();
            running = true;

            run.setEnabled(false);
            pause.setEnabled(true);
            stop.setEnabled(true);
            timerEnabled.setText("Timer: on");

            if(!run.isEnabled()) {
                menuStartItem.setEnabled(false);
                menuStopItem.setEnabled(true);
            }
        });

        JMenuItem menuPauseItem = new JMenuItem("Пауза");
        highMenu.add(menuPauseItem);
        menuPauseItem.addActionListener(e -> {
            if (running) {
                stopTimer();
                pausePoint = Instant.now();
            } else {
                startTimer();
                pauseTime.plus(Duration.between(pausePoint, Instant.now()));
            }
            timerEnabled.setText("Timer: " + (running ? "pause" : "on"));
            menuPauseItem.setText(running ? "Продолжить" : "Пауза");
            running = !running;
        });

      //JMenuItem menuStopItem = new JMenuItem("Стоп");
        highMenu.add(menuStopItem);
        menuStopItem.addActionListener(e -> {
            if(stop.isEnabled())  {
                menuStartItem.setEnabled(true);
                menuStopItem.setEnabled(false);
            }

            timerEnabled.setText("Timer: pause");
            stopTimer();
            stopPoint = Instant.now();

            if (showInfo_flag) showInfo();
            else {
                timer.cancel();
                h.reset();

                creatureBox.removeAll();
                creatureBox.updateUI();

                run.setEnabled(true);
                pause.setEnabled(false);
                stop.setEnabled(false);

                timerEnabled.setText("Timer: off");
                ticks.setText("Sec: 0");
            }
        });

        highMenu.add(new JMenuItem("Выйти") {{ addActionListener(e -> System.exit(0));}});
        menuBar.add(highMenu);


        ((JSpinner.DefaultEditor) SmallBirdChance.getEditor()).getTextField().setFocusable(false);
        SmallBirdChance.addChangeListener(e -> SmallBird.setBornChance((Integer) SmallBirdChance.getValue() / 100));

        ((JSpinner.DefaultEditor) BigBirdChance.getEditor()).getTextField().setFocusable(false);
        BigBirdChance.addChangeListener(e -> BigBird.setBornChance((Integer) BigBirdChance.getValue() / 100));


        ((JSpinner.DefaultEditor) SmallBirdPeriod.getEditor()).getTextField().setFocusable(false);
        SmallBirdPeriod.addChangeListener(e -> SmallBird.setPeriod( (Integer) SmallBirdPeriod.getValue()));


        ((JSpinner.DefaultEditor) BigBirdPeriod.getEditor()).getTextField().setFocusable(false);
        BigBirdPeriod.addChangeListener(e -> BigBird.setPeriod( (Integer) BigBirdPeriod.getValue()));


        textLifeBig.addChangeListener(e -> BigBird.setTTL(textLifeBig.getValue()));
        textLifeLittle.addChangeListener(e -> SmallBird.setTTL(textLifeLittle.getValue()));


        showTimeON.setSelected(true);

        showTimeON.addActionListener(e -> {
            ticks.setVisible(true);
            showTimeOFF.setSelected(false);

            showTimeON.setEnabled(false);
            showTimeOFF.setEnabled(true);
        });

        showTimeOFF.addActionListener(e -> {
            ticks.setVisible(false);
            showTimeON.setSelected(false);

            showTimeON.setEnabled(true);
            showTimeOFF.setEnabled(false);

        });

        showStatsDlg.addActionListener(e -> showInfo_flag = !showInfo_flag );
        menuStartItem.setEnabled(true);
        menuStopItem.setEnabled(false);

        // B
        run.addActionListener(e -> {
            startTimer();
            startPoint = Instant.now();
            running = true;

            run.setEnabled(false);
            pause.setEnabled(true);
            stop.setEnabled(true);

            if(!run.isEnabled()) {
                menuStartItem.setEnabled(false);
                menuStopItem.setEnabled(true);
            }

            timerEnabled.setText("Timer: on");
        });

        // P
        pause.addActionListener(e -> {
            if (running) {
                stopTimer();
                pausePoint = Instant.now();
            } else {
                startTimer();
                pauseTime.plus(Duration.between(pausePoint, Instant.now()));
            }

            menuPauseItem.setText(running ? "Продолжить" : "Пауза");
            timerEnabled.setText("Timer: " + (running ? "pause" : "on"));

            running = !running;
        });

        // E
        stop.addActionListener(e -> {

            if(stop.isEnabled())  {
                menuStartItem.setEnabled(true);
                menuStopItem.setEnabled(false);
            }

            timerEnabled.setText("Timer: pause");
            stopTimer();
            stopPoint = Instant.now();

            if (showInfo_flag) showInfo();
            else {
                timer.cancel();
                h.reset();

                creatureBox.removeAll();
                creatureBox.updateUI();

                run.setEnabled(true);
                pause.setEnabled(false);
                stop.setEnabled(false);

                timerEnabled.setText("Timer: off");
                ticks.setText("Sec: 0");
            }
        });

        // T
        showTime.addActionListener(e -> ticks.setVisible(!ticks.isVisible()) );


        h.addCreatureCreateListener((creature) -> {
            JComponent component = new JComponent() {
                {
                    setBounds(
                            (int) (creatureBox.getWidth() * (Math.min(creature.getX(), 0.9))),
                            (int) (creatureBox.getHeight() * (Math.min(creature.getY(), 0.9))),
                            (int) (creatureBox.getWidth() * 0.1),
                            (int) (creatureBox.getHeight() * 0.1)
                    );
                    setOpaque(true);
                }

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    try {
                        g.drawImage(
                                creature.getImage().getScaledInstance(
                                        getWidth(),
                                        getHeight(),
                                        SCALE_SMOOTH
                                ),
                                0, 0, null
                        );
                    } catch (Exception ignored) {
                    }
                }
            };

            creatureBox.add(component);
            creatureBoxComponents.put(creature, component);
            creatureBox.updateUI();
        });

        h.addCreatureDieListener((creature) -> {
            creatureBox.remove(creatureBoxComponents.get(creature));
            creatureBoxComponents.remove(creature);
            creatureBox.updateUI();
        });
    }


    private void keyPressed(KeyEvent e){ // Бинд кнопок
        switch (e.getKeyCode()) {
            case VK_B: {
                if (run.isEnabled())
                    run.doClick();
                break;
            }
            case VK_E: {
                if (stop.isEnabled())
                    stop.doClick();
                break;
            }
            case VK_T: {
                if (showTime.isEnabled())
                    showTime.doClick();
                break;
            }
            case VK_P: {
                if (pause.isEnabled())
                    pause.doClick();
                break;
            }
        }
    }


    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                ticks.setText("Sec: " + habitat.getMetrics().getTime());
                habitat.update();

            }
        }, 0, 500);
    }

    private void stopTimer() {
        timer.cancel();
    }

    private void showInfo() {

        stop.setEnabled(false);
        pause.setEnabled(false);

        switch (
                JOptionPane.showOptionDialog(this,
                        "<html>" +
                                "<p>Кол-во SmallBird: " + habitat.getMetrics().getCreatureCountByType(SmallBird.class) + "</p>" +
                                "<p>Кол-во BigBird: " + habitat.getMetrics().getCreatureCountByType(BigBird.class) + "</p>" +
                                "<p>Время симуляции: " +
                                Duration.between(startPoint, stopPoint).minus(pauseTime).getSeconds() + "с" +
                                "</p>" +
                                "</html>",
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
                creatureBox.removeAll();
                creatureBox.updateUI();

                run.setEnabled(true);
                pause.setEnabled(false);
                stop.setEnabled(false);

                ticks.setText("Sec: 0");
                timerEnabled.setText("Timer: off");

                break;}

                case JOptionPane.CANCEL_OPTION:{break;}

                default:{
                stop.setEnabled(true);
                pause.setEnabled(true);
                    pause.doClick();
                break;
            }
        }
    }
}








