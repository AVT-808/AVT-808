package AntFarm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class GUI extends JPanel
{
    protected JMenuBar menu;
    protected JToolBar settings;
    protected DrawingAnts draw;
    protected JPanel timerPanel;
    protected JPanel simulation;
    protected JLabel timer;

    //боковая панель управления
    private JButton buttonStart;
    private JButton buttonStop;
    private JCheckBox infoVisibility;
    private ButtonGroup timerVisibility;
    private JRadioButton timerIsVisible;
    private JRadioButton timerNotVisible;
    private JLabel labelWorkerPeriod;
    private JLabel labelWarriorPeriod;
    private JLabel labelWorkerProbability;
    private JLabel labelWarriorProbability;
    private JTextField workerPeriod;
    private JTextField warriorPeriod;
    private JComboBox workerProbability;
    private JComboBox warriorProbability;

    private JLabel labelWorkerLifetime;
    private JLabel labelWarriorLifetime;
    private JTextField workerLifetime;
    private JTextField warriorLifetime;

    private JButton buttonObjects;

    //меню
    private JMenu menuSimulation;
    private JMenu menuInstruments;
    private JMenuItem menuStart;
    private JMenuItem menuStop;
    private JMenuItem menuTimerVisible;
    private JMenuItem menuInformationVisible;

    public GUI(ActionListener actionListener)
    {
        menu = new JMenuBar();
        settings = new JToolBar();

        settings.setLayout(new GridLayout(18,1));
        settings.setFocusable(false);
        settings.setFloatable(false);

        buttonStart = new JButton("Старт");
        buttonStart.addActionListener(actionListener);

        buttonStop = new JButton("Стоп");
        buttonStop.setEnabled(false);
        buttonStop.addActionListener(actionListener);

        buttonObjects = new JButton("Текущие объекты");
        buttonObjects.addActionListener(actionListener);

        infoVisibility = new JCheckBox("Информация о симуляции", true);
        infoVisibility.addActionListener(actionListener);

        timerIsVisible = new JRadioButton("Показать время симуляции");
        timerIsVisible.setSelected(true);
        timerIsVisible.addActionListener(actionListener);
        timerNotVisible = new JRadioButton("Скрыть время симуляции");
        timerNotVisible.addActionListener(actionListener);

        timerVisibility = new ButtonGroup();
        timerVisibility.add(timerIsVisible);
        timerVisibility.add(timerNotVisible);

        labelWorkerPeriod = new JLabel("Период рождения муравья-рабочего");
        workerPeriod = new JTextField();

        labelWarriorPeriod = new JLabel("Период рождения муравья-воина");
        warriorPeriod = new JTextField();

        labelWorkerLifetime = new JLabel("Время жизни муравья-рабочего");
        workerLifetime = new JTextField();

        labelWarriorLifetime = new JLabel("Время жизни муравья-воина");
        warriorLifetime = new JTextField();

        Double[] steps = new Double[] {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};

        labelWorkerProbability = new JLabel("Вероятность рождения муравья-рабочего");
        workerProbability = new JComboBox(steps);

        labelWarriorProbability = new JLabel("Вероятность рождения муравья-воина");
        warriorProbability = new JComboBox(steps);

        settings.add(buttonStart);
        settings.add(buttonStop);
        settings.add(buttonObjects);
        settings.add(infoVisibility);
        settings.add(timerIsVisible);
        settings.add(timerNotVisible);
        settings.add(labelWorkerPeriod);
        settings.add(workerPeriod);
        settings.add(labelWarriorPeriod);
        settings.add(warriorPeriod);
        settings.add(labelWorkerLifetime);
        settings.add(workerLifetime);
        settings.add(labelWarriorLifetime);
        settings.add(warriorLifetime);
        settings.add(labelWorkerProbability);
        settings.add(workerProbability);
        settings.add(labelWarriorProbability);
        settings.add(warriorProbability);

        menu.setLayout(new GridLayout(1, 2));
        menu.setFocusable(false);

        menuSimulation = new JMenu("Симуляция");
        menuInstruments = new JMenu("Инструменты");

        menuStart = new JMenuItem("Старт");
        menuStart.addActionListener(actionListener);
        menuStop = new JMenuItem("Стоп");
        menuStop.addActionListener(actionListener);
        menuTimerVisible = new JMenuItem("Показать/скрыть время симуляции");
        menuTimerVisible.addActionListener(actionListener);
        menuInformationVisible = new JMenuItem("Показать/скрыть информацию о симуляции");
        menuInformationVisible.addActionListener(actionListener);

        menuSimulation.add(menuStart);
        menuSimulation.add(menuStop);
        menuInstruments.add(menuTimerVisible);
        menuInstruments.add(menuInformationVisible);
        menu.add(menuSimulation);
        menu.add(menuInstruments);

        timerPanel = new JPanel();
        timerPanel.setLayout(new GridLayout(1, 1));
        timerPanel.setBackground(Color.lightGray);

        timer = new JLabel("Таймер: 0");
        timer.setHorizontalAlignment(SwingConstants.CENTER);
        timer.setFont(new Font("Arial", Font.BOLD, 20));
        timerPanel.add(timer);

        simulation = new JPanel();
        simulation.setLayout(new GridLayout(2, 1));
        simulation.add(menu);
        simulation.add(timerPanel);

        draw = new DrawingAnts();

        setLayout(new BorderLayout());
        add(simulation, BorderLayout.PAGE_START);
        add(settings, BorderLayout.LINE_END);

        add(draw, BorderLayout.CENTER);
    }

    public JButton getButtonStart()
    {
        return buttonStart;
    }

    public JButton getButtonStop()
    {
        return buttonStop;
    }

    public JButton getButtonObjects() { return buttonObjects;}

    public JCheckBox getInfoVisibility()
    {
        return infoVisibility;
    }

    public JRadioButton getTimerIsVisible()
    {
        return timerIsVisible;
    }

    public JRadioButton getTimerNotVisible()
    {
        return timerNotVisible;
    }

    public JTextField getWorkerPeriod()
    {
        return workerPeriod;
    }

    public JTextField getWarriorPeriod()
    {
        return warriorPeriod;
    }

    public JTextField getWorkerLifetime() {return workerLifetime;}

    public JTextField getWarriorLifetime() {return warriorLifetime;}

    public JComboBox getWorkerProbability()
    {
        return workerProbability;
    }

    public JComboBox getWarriorProbability()
    {
        return warriorProbability;
    }

    public JMenuItem getMenuStart()
    {
        return menuStart;
    }

    public JMenuItem getMenuStop()
    {
        return menuStop;
    }

    public JMenuItem getMenuTimerVisible()
    {
        return menuTimerVisible;
    }

    public JMenuItem getMenuInformationVisible()
    {
        return menuInformationVisible;
    }
}
