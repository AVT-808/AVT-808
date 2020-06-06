package lab.frames.components;

import javax.swing.*;

import java.awt.*;


public class ControlBox extends JPanel {
    private JLabel runText = new JLabel("Старт симуляции - B");
    private JLabel stopText = new JLabel("Конец симуляции - E");
    private JLabel showText = new JLabel("Показ статистики - T");
    private JLabel optChLit = new JLabel("Шанс появления птенцов: ");
    private JLabel optChBig = new JLabel("Шанс появления птиц: ");
    private JLabel optIntLit = new JLabel("Интервал появления птенцов: ");
    private JLabel optIntBig = new JLabel("Интервал появления птиц: ");

    public JPanel showInfoPanel = new JPanel();
    public JLabel ticks = new JLabel("Тик: 0");
    public JLabel cLit = new JLabel("Количество птенцов: 0");
    public JLabel cBig = new JLabel("Количество птиц: 0");

    public JButton run = new JButton("Старт");
    public JButton pause = new JButton("Пауза");
    public JButton stop = new JButton("Стоп");
    public JButton current = new JButton("Текущие объекты");
    public JButton showObjectsInfo = new JButton("C");

    public JSpinner SmallBirdChance = new JSpinner(new SpinnerNumberModel(50, 0, 100, 10));
    public JSpinner BigBirdChance = new JSpinner(new SpinnerNumberModel(50, 0, 100, 10));
    public JSpinner textIntervalBig = new JSpinner(new SpinnerNumberModel(2, 1, 100, 1));
    public JSpinner textIntervalLit = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

    String[] itemsLit = {
            "Жизнь птенцов",
            "Жизнь птиц"
    };
    public JComboBox lifecreaturesBox = new JComboBox(itemsLit);

    public JSlider sliderLifeBig = new JSlider(1, 100, 10);
    public JSlider sliderLifeLit = new JSlider(1, 100, 15);

    public JRadioButton showTimeOn = new JRadioButton("Показать время");
    public JRadioButton showTimeOff = new JRadioButton("Скрыть время");
    private ButtonGroup showTimeGroup = new ButtonGroup();

    public JCheckBox showStats = new JCheckBox("Показывать информацию");

    private JPanel runTextPanel = new JPanel();
    private JPanel stopTextPanel = new JPanel();
    private JPanel showTextPanel = new JPanel();
    private JPanel ChancePanelLit = new JPanel();
    private JPanel ChancePanelBig = new JPanel();
    private JPanel IntervalPanelLit = new JPanel();
    private JPanel IntervalPanelBig = new JPanel();
    private JPanel LifeLitPanel = new JPanel();
    private JPanel LifeBigPanel = new JPanel();
    private JPanel BtnPanel = new JPanel();
    private JPanel CheckPanelOFF = new JPanel();
    private JPanel CheckPanelON = new JPanel();
    private JPanel CurrentPanel = new JPanel();


    public ControlBox() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(runTextPanel);
        add(stopTextPanel);
        add(showTextPanel);
        add(BtnPanel);
        add(CurrentPanel);
        add(CurrentPanel);
        add(ChancePanelLit);
        add(ChancePanelBig);
        add(IntervalPanelLit);
        add(IntervalPanelBig);
        add(lifecreaturesBox);
        add(sliderLifeLit);
        add(sliderLifeBig);
        add(LifeLitPanel);
        add(LifeBigPanel);
        add(CheckPanelON);
        add(CheckPanelOFF);
        add(showInfoPanel);

        sliderLifeBig.setVisible(false);

        runTextPanel.add(runText);
        stopTextPanel.add(stopText);
        showTextPanel.add(showText);

        BtnPanel.add(run);
        BtnPanel.add(pause);
        BtnPanel.add(stop);

        CurrentPanel.add(current);

        ChancePanelLit.add(optChLit);
        ChancePanelLit.add(SmallBirdChance);
        SmallBirdChance.setPreferredSize(new Dimension(50,25));

        ChancePanelBig.add(optChBig);
        ChancePanelBig.add(BigBirdChance);
        BigBirdChance.setPreferredSize(new Dimension(50,25));

        IntervalPanelLit.add(optIntLit);
        IntervalPanelLit.add(textIntervalLit);

        IntervalPanelBig.add(optIntBig);
        IntervalPanelBig.add(textIntervalBig);

        showTimeGroup.add(showTimeOn);
        showTimeGroup.add(showTimeOff);

        CheckPanelON.add(showTimeOn);

        CheckPanelOFF.add(showTimeOff);

        showInfoPanel.add(showStats);

        pause.setEnabled(false);
        stop.setEnabled(false);
        showStats.setEnabled(true);
        showObjectsInfo.setEnabled(true);


        sliderLifeLit.setPaintTicks(true);
        sliderLifeLit.setPaintLabels(true);
        sliderLifeLit.setMajorTickSpacing(10);

        sliderLifeBig.setPaintTicks(true);
        sliderLifeBig.setPaintLabels(true);
        sliderLifeBig.setMajorTickSpacing(10);


        showInfoPanel.setLayout(new BoxLayout(showInfoPanel, BoxLayout.Y_AXIS));
        showInfoPanel.add(ticks);
        showInfoPanel.add(cLit);
        showInfoPanel.add(cBig);


        // tool tips
        run.setToolTipText("Запуск симуляции");
        pause.setToolTipText("Пауза");
        stop.setToolTipText("Остановка симуляции, очищение рабочей области");
        showStats.setToolTipText("Окно информации");
        showObjectsInfo.setToolTipText("Текущие объекты");

        SmallBirdChance.setToolTipText("Шанс появления птенцов");
        BigBirdChance.setToolTipText("Шанс появления птиц");
        textIntervalLit.setToolTipText("Интервал появления птенцов");
        textIntervalBig.setToolTipText("Интервал появления птиц");
        sliderLifeLit.setToolTipText("Время жизни птенцов)");
        sliderLifeBig.setToolTipText("Время жизни птиц");
        ticks.setToolTipText("Время симуляции");
        cLit.setToolTipText("Кол-во птенцов");
        cBig.setToolTipText("Кол-во птиц");
    }
}
