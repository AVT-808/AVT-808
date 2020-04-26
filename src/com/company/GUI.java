package com.company;


import com.company.BaseAI.AI.CatAI;
import com.company.BaseAI.AI.DogAI;
import com.company.Habitat.Habitat;
import com.company.Habitat.Pet.Creatures.Cat;
import com.company.Habitat.Pet.Creatures.Dog;
import com.company.Server.Client;
import com.company.Single.Singleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.event.KeyEvent.*;

public class GUI extends JPanel {

    private DataFile myFile;
    public MyTimerTask timerTask = new MyTimerTask();

    public int myTimer = 0;
    public int nameList;
    Habitat window;
    boolean endApp = false;
    boolean timeVisible = true;
    boolean lineVisible = true;
    boolean useKeys = true;
    boolean useDialog = true;

    boolean dialogOn = false;
    boolean isPressedOn = false;
    boolean isPressedOnB = false;

    boolean beginning = true;

    JFrame jFrame;
    CatAI catAI = new CatAI();
    DogAI dogAI = new DogAI();

    public JButton buttonServer = new JButton("Server");
    ActionListener actionListenerForServer = new serverActionListener();
    public JButton buttonDown = new JButton("SwapUser");
    ActionListener actionListenerForDown = new downActionListener();
    public JButton buttonConsole = new JButton("Console");
    ActionListener actionListenerForConsole = new consoleActionListener();
    public JButton buttonCatAI = new JButton("Cat AI");
    ActionListener actionListenerForCatAI = new catAIActionListener();
    public JButton buttonDogAI = new JButton("Dog Ai");
    ActionListener actionListenerForDogAI = new dogAIActionListener();
    public JButton run = new JButton("Run");
    public JButton pause = new JButton("Pause");
    public JButton history = new JButton("History");
    ActionListener actionListenerForHistory = new historyActionListener();
    public JRadioButton showTime = new JRadioButton("Show time",true);
    public JRadioButton hideTime = new JRadioButton("Hide time",false);
    public JLabel selectTime = new JLabel("Time Settings:");
    public JLabel selectCatsTimeSpawn = new JLabel("Spawn cat:");
    public JLabel selectDogsTimeSpawn = new JLabel("Spawn dog:");
    public JLabel selectCatsSpawn = new JLabel("Chance cat:");
    public JLabel selectDogsSpawn = new JLabel("Chance dog:");
    public JLabel selectCatsAI = new JLabel("Cats AI:");
    public JLabel selectDogsAI = new JLabel("Dogs AI:");
    ButtonGroup group = new ButtonGroup();

    public JCheckBox screenResolution = new JCheckBox("Show info when press \"Stop\" ");
    public JTextField spawnCats = new JTextField();
    ActionListener actionListenerForCats = new spawnCatsActionListener();
    public JTextField spawnDogs = new JTextField();
    ActionListener actionListenerForDogs = new spawnDogsActionListener();

    public JLabel selectLifeOfCats = new JLabel("Life cat:");
    public JLabel selectLifeOfDogs = new JLabel("Life dog:");
    public JTextField lifeCats = new JTextField();
    ActionListener actionListenerLifeCats = new lifeCatsActionListener();
    public JTextField lifeDogs = new JTextField();
    ActionListener actionListenerLifeDogs = new lifeDogsActionListener();


    public JComboBox catsComboBox = new JComboBox();
    ActionListener catsComboBoxActionListener = new catsComboBoxActionListener();

    public JComboBox dogsComboBox = new JComboBox();
    ActionListener dogsComboBoxActionListener = new dogsComboBoxActionListener();

    public JComboBox priorityCatAI = new JComboBox();
    ActionListener priorityAIActionListenerForCats = new priorityCatAIActionListener();

    public JComboBox priorityDogAI = new JComboBox();
    ActionListener priorityAIActionListenerForDogs = new priorityDogAIActionListener();

    public GUI(Habitat window, JFrame jFrame, DataFile dataFile){
        this.window = window;
        this.jFrame = jFrame;
        new Client();
        myFile = dataFile;
        myFile.RunApplication(window,catAI,dogAI);


        lifeCats.addActionListener(actionListenerLifeCats);
        lifeDogs.addActionListener(actionListenerLifeDogs);
        lifeCats.setText(String.valueOf(window.getCatsTimeOfLife()));
        lifeDogs.setText(String.valueOf(window.getDogsTimeOfLife()));
        spawnCats.setToolTipText("Select time spawn for cat");
        spawnDogs.setToolTipText("Select time spawn for dog");
        catsComboBox.setToolTipText("Select chance spawn for cat");
        dogsComboBox.setToolTipText("Select chance spawn for dog");
        priorityCatAI.setToolTipText("Select cats priority AI");
        priorityDogAI.setToolTipText("Select dogs priority AI");

        catsComboBox.addItem("0%");
        catsComboBox.addItem("10%");
        catsComboBox.addItem("20%");
        catsComboBox.addItem("30%");
        catsComboBox.addItem("40%");
        catsComboBox.addItem("50%");
        catsComboBox.addItem("60%");
        catsComboBox.addItem("70%");
        catsComboBox.addItem("80%");
        catsComboBox.addItem("90%");
        catsComboBox.addItem("100%");
        catsComboBox.setSelectedIndex(window.getP1()/10);
        catsComboBox.setBackground(Color.white);
        catsComboBox.setPreferredSize(new Dimension(130,30));

        dogsComboBox.addItem("0%");
        dogsComboBox.addItem("10%");
        dogsComboBox.addItem("20%");
        dogsComboBox.addItem("30%");
        dogsComboBox.addItem("40%");
        dogsComboBox.addItem("50%");
        dogsComboBox.addItem("60%");
        dogsComboBox.addItem("70%");
        dogsComboBox.addItem("80%");
        dogsComboBox.addItem("90%");
        dogsComboBox.addItem("100%");
        dogsComboBox.setSelectedIndex(window.getP2()/10);
        dogsComboBox.setBackground(Color.white);
        dogsComboBox.setPreferredSize(new Dimension(130,30));

        priorityCatAI.addItem("1");
        priorityCatAI.addItem("2");
        priorityCatAI.addItem("3");
        priorityCatAI.addItem("4");
        priorityCatAI.addItem("5");
        priorityCatAI.addItem("6");
        priorityCatAI.addItem("7");
        priorityCatAI.addItem("8");
        priorityCatAI.addItem("9");
        priorityCatAI.addItem("10");
        priorityCatAI.setSelectedIndex(catAI.getPriority()-1);
        priorityCatAI.setBackground(Color.white);
        priorityCatAI.setPreferredSize(new Dimension(130,30));

        priorityDogAI.addItem("1");
        priorityDogAI.addItem("2");
        priorityDogAI.addItem("3");
        priorityDogAI.addItem("4");
        priorityDogAI.addItem("5");
        priorityDogAI.addItem("6");
        priorityDogAI.addItem("7");
        priorityDogAI.addItem("8");
        priorityDogAI.addItem("9");
        priorityDogAI.addItem("10");
        priorityDogAI.setSelectedIndex(dogAI.getPriority()-1);
        priorityDogAI.setBackground(Color.white);
        priorityDogAI.setPreferredSize(new Dimension(130,30));

        spawnCats.setText(String.valueOf(window.getN1()));
        spawnCats.addActionListener(actionListenerForCats);
        spawnDogs.setText(String.valueOf(window.getN2()));
        spawnDogs.addActionListener(actionListenerForDogs);
        catsComboBox.addActionListener(catsComboBoxActionListener);
        dogsComboBox.addActionListener(dogsComboBoxActionListener);
        history.addActionListener(actionListenerForHistory);
        buttonCatAI.addActionListener(actionListenerForCatAI);
        buttonDogAI.addActionListener(actionListenerForDogAI);
        buttonConsole.addActionListener(actionListenerForConsole);
        buttonServer.addActionListener(actionListenerForServer);
        buttonDown.addActionListener(actionListenerForDown);
        priorityCatAI.addActionListener(priorityAIActionListenerForCats);
        priorityDogAI.addActionListener(priorityAIActionListenerForDogs);

        add(buttonServer);
        add(buttonDown);
        add(buttonConsole);
        add(buttonCatAI);
        add(buttonDogAI);
        add(catsComboBox);
        add(dogsComboBox);
        add(priorityCatAI);
        add(priorityDogAI);
        group.add(showTime);
        group.add(hideTime);
        showTime.setBackground(Color.white);
        hideTime.setBackground(Color.white);
        add(screenResolution);
        add(showTime);
        add(hideTime);
        add(selectTime);
        add(spawnCats);
        add(spawnDogs);
        add(selectCatsTimeSpawn);
        add(selectCatsSpawn);
        add(selectDogsTimeSpawn);
        add(selectDogsSpawn);
        add(selectCatsAI);
        add(selectDogsAI);
        add(history);
        add(lifeCats);
        add(lifeDogs);
        add(selectLifeOfCats);
        add(selectLifeOfDogs);


        add(run);
        add(pause);
        run.setEnabled(false);
        pause.setEnabled(false);
        run.setBackground(Color.RED);
        pause.setBackground(Color.RED);
        screenResolution.setBackground(Color.white);

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
         if(beginning){
            selectLifeOfCats.setVisible(false);
            selectLifeOfDogs.setVisible(false);
            buttonServer.setVisible(false);
            buttonDown.setVisible(false);
            buttonConsole.setVisible(false);
            buttonCatAI.setVisible(false);
            buttonDogAI.setVisible(false);
            lifeCats.setVisible(false);
            lifeDogs.setVisible(false);
            history.setVisible(false);
            selectCatsTimeSpawn.setVisible(false);
            selectDogsTimeSpawn.setVisible(false);
            selectCatsSpawn.setVisible(false);
            selectDogsSpawn.setVisible(false);
            selectCatsAI.setVisible(false);
            selectDogsAI.setVisible(false);
            catsComboBox.setVisible(false);
            dogsComboBox.setVisible(false);
            priorityCatAI.setVisible(false);
            priorityDogAI.setVisible(false);
            spawnCats.setVisible(false);
            spawnDogs.setVisible(false);
            selectTime.setVisible(false);
            showTime.setVisible(false);
            hideTime.setVisible(false);
            run.setVisible(false);
            pause.setVisible(false);
            screenResolution.setVisible(false);
            lineVisible = false;
            timeVisible = false;
            g.setColor(Color.BLACK);
            Font myFont = new Font("Times Roman", Font.BOLD, 100);
            g.setFont(myFont);
            g.drawString("Press \"B\" to start", 150,window.getSizeY()/2);
        }

        history.setBounds(200,30,100,30);
        selectCatsTimeSpawn.setBounds(670,0,200,30);
        selectDogsTimeSpawn.setBounds(670,30,200,30);
        selectCatsSpawn.setBounds(990,0,130,30);
        selectDogsSpawn.setBounds(990,30,140,30);
        selectCatsAI.setBounds(1015,60,50,30);
        selectDogsAI.setBounds(1015,90,50,30);
        lifeCats.setBounds(880,0,100,30);
        lifeDogs.setBounds(880,30,100,30);
        selectLifeOfCats.setBounds(830,0,100,30);
        selectLifeOfDogs.setBounds(830,30,100,30);
        catsComboBox.setBounds(1060,0,150,30);
        dogsComboBox.setBounds(1060,30,150,30);
        priorityCatAI.setBounds(1060,60,150,30);
        priorityDogAI.setBounds(1060,90,150,30);

        spawnCats.setBounds(740,0,60,30);
        spawnDogs.setBounds(740,30,60,30);

        selectTime.setBounds(100,0,90,20);
        showTime.setBounds(100,20,90,20);
        hideTime.setBounds(100,40,90,20);
        run.setBounds(0,0,80,30);
        pause.setBounds(0,30,80,30);
        screenResolution.setLocation(200,0);
        buttonConsole.setBounds(200,60,100,30);
        buttonServer.setBounds(200,90,100,30);
        buttonDown.setBounds(300,90,100,30);
        buttonCatAI.setBounds(300,30,70,15);
        buttonDogAI.setBounds(300,45,70,15);


        if(lineVisible) {
            nameList = 150;
            g.setColor(Color.white);
            g.fillRect(0, 0, 10000, 120);
            g.setColor(Color.BLACK);
            Font myFont = new Font("Times Roman", Font.PLAIN, 15);
            g.setFont(myFont);
            g.drawString("User online:", 0, 135);
            for (int i =0;i<Client.CountUsers();i++){
                g.drawString(Client.NameUsers(i), 0, nameList);
                nameList += 15;
            }
            myFont = new Font("Times Roman", Font.BOLD, 15);
            g.setFont(myFont);
            g.drawString(Client.YouNow()+"(You)", 0, nameList);


        }
        if(timeVisible) {
            g.setColor(Color.BLACK);
            Font myFont = new Font("Times Roman", Font.BOLD, 40);
            g.setFont(myFont);
            g.drawString("Timer: " + myTimer + " sec.", 400, 45);
        }
        if(endApp){
            JFrame endFrame = new JFrame("Результаты");
            endFrame.setAlwaysOnTop(true);
            endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            endFrame.setSize(400,400);
            endFrame.setLocationRelativeTo(null);
            endFrame.setResizable(false);

            JPanel endPanel = new JPanel(){
              public void paint(Graphics g){
                  super.paint(g);

                  g.setColor(Color.BLACK);
                  Font myFont=new Font("Arial",Font.BOLD,20);
                  g.setFont(myFont);
                  g.drawString("Время работы: "+myTimer ,100,100);
                  g.setColor(Color.GREEN);
                  myFont=new Font("Times new Roman",Font.ITALIC,20);
                  g.setFont(myFont);
                  g.drawString("Количество кошек: "+window.cats ,100,140);
                  g.setColor(Color.ORANGE);
                  myFont=new Font("Calibri",Font.PLAIN,20);
                  g.setFont(myFont);
                  g.drawString("Количество собак: "+window.dogs ,100,180);
                  g.setColor(Color.RED);
                  myFont=new Font("Georgia",Font.BOLD + Font.ITALIC,20);
                  g.setFont(myFont);
                  int quantityPet = window.dogs + window.cats;
                  g.drawString("Всего питомцев: "+quantityPet ,100,220);
              }
            };
            endFrame.add(endPanel);



            endFrame.setVisible(true);
            endApp = false;
        }

            for (int i = 0; i < window.quantityPet; i++) {
                if (Dog.class.isAssignableFrom(Singleton.getInstance().Get(i).getClass())) {
                    Image imageDog = new ImageIcon("img/dog.png").getImage();
                    g.drawImage(imageDog, Singleton.getInstance().Get(i).getX(), Singleton.getInstance().Get(i).getY(), 50, 50, null);
                } else {
                    Image imageCat = new ImageIcon("img/cat.png").getImage();
                    g.drawImage(imageCat, Singleton.getInstance().Get(i).getX(), Singleton.getInstance().Get(i).getY(), 50, 50, null);
                }

            }



        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) { }


            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case VK_B:
                        if(!useKeys)break;
                        if(!isPressedOnB) {
                            buttonServer.setVisible(true);
                            buttonDown.setVisible(true);
                            buttonConsole.setVisible(true);
                            buttonCatAI.setVisible(true);
                            buttonDogAI.setVisible(true);
                            selectLifeOfCats.setVisible(true);
                            selectLifeOfDogs.setVisible(true);
                            lifeCats.setVisible(true);
                            lifeDogs.setVisible(true);
                            history.setVisible(true);
                            spawnCats.setVisible(true);
                            spawnCats.setEnabled(false);
                            spawnDogs.setVisible(true);
                            spawnDogs.setEnabled(false);
                            run.setBackground(Color.RED);
                            pause.setBackground(Color.GREEN);
                            timerTask.StartWork();
                            lifeCats.setEnabled(false);
                            lifeDogs.setEnabled(false);
                            run.setEnabled(false);
                            pause.setEnabled(true);
                            isPressedOnB = true;
                            selectCatsTimeSpawn.setVisible(true);
                            selectDogsTimeSpawn.setVisible(true);
                            selectCatsSpawn.setVisible(true);
                            selectDogsSpawn.setVisible(true);
                            selectCatsAI.setVisible(true);
                            selectDogsAI.setVisible(true);
                            catsComboBox.setVisible(true);
                            catsComboBox.setEnabled(false);
                            dogsComboBox.setVisible(true);
                            dogsComboBox.setEnabled(false);
                            priorityCatAI.setVisible(true);
                            priorityCatAI.setEnabled(false);
                            priorityDogAI.setVisible(true);
                            priorityDogAI.setEnabled(false);
                            selectTime.setVisible(true);
                            showTime.setVisible(true);
                            hideTime.setVisible(true);
                            run.setVisible(true);
                            pause.setVisible(true);
                            screenResolution.setVisible(true);
                            lineVisible = true;
                            timeVisible = true;
                            beginning = false;


                            repaint();
                        }
                        break;
                    case VK_T:
                        if(!useKeys)break;
                        if(!isPressedOn) {
                            timeVisible = !timeVisible;
                            isPressedOn = true;

                            if(timeVisible){
                                showTime.setSelected(true);
                                hideTime.setSelected(false);
                            }else{
                                showTime.setSelected(false);
                                hideTime.setSelected(true);
                            }
                            repaint();
                        }
                        break;
                    case VK_E:
                        timerTask.StopWork();
                        if(!useKeys)break;
                        useKeys = false;
                        Client.Disconnect();


                        timeVisible = false;
                        endApp = true;
                        lineVisible = false;
                        selectLifeOfCats.setVisible(false);
                        selectLifeOfDogs.setVisible(false);
                        lifeCats.setVisible(false);
                        lifeDogs.setVisible(false);
                        history.setVisible(false);
                        selectTime.setVisible(false);
                        showTime.setVisible(false);
                        hideTime.setVisible(false);
                        run.setVisible(false);
                        pause.setVisible(false);
                        screenResolution.setVisible(false);
                        selectCatsTimeSpawn.setVisible(false);
                        selectDogsTimeSpawn.setVisible(false);
                        selectCatsSpawn.setVisible(false);
                        selectDogsSpawn.setVisible(false);
                        selectCatsAI.setVisible(false);
                        selectDogsAI.setVisible(false);
                        spawnCats.setVisible(false);
                        spawnDogs.setVisible(false);
                        catsComboBox.setVisible(false);
                        dogsComboBox.setVisible(false);
                        priorityCatAI.setVisible(false);
                        priorityDogAI.setVisible(false);
                        buttonCatAI.setVisible(false);
                        buttonDogAI.setVisible(false);
                        buttonConsole.setVisible(false);
                        buttonServer.setVisible(false);
                        buttonDown.setVisible(false);
                        window.allClear();

                        myFile.ExitApplication(window, catAI.getPriority(),dogAI.getPriority());

                        repaint();
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == VK_T) {
                    if (!useKeys) return;
                    isPressedOn = false;

                }



            }
        });requestFocusInWindow();

        if(dialogOn){
            infDialog info = new infDialog(jFrame,window,myTimer);
            dialogOn = false;
            timerTask.StartWork();
            run.setEnabled(false);
            pause.setEnabled(true);
            run.setBackground(Color.RED);
            pause.setBackground(Color.GREEN);
            repaint();
        }

        showTime.addItemListener(e -> {
            timeVisible = true;
            repaint();
        });
        hideTime.addItemListener(e -> {
            timeVisible = false;
            repaint();
        });



        screenResolution.addActionListener(e -> {
            if(!isPressedOn) {
                useDialog = !useDialog;
                repaint();
                isPressedOn = true;
            }
        });
        isPressedOn = false;


        run.addActionListener(e -> {
            if(!isPressedOn) {
                if (catAI.isFlag()) {
                    catAI.startAI();
                }
                if (dogAI.isFlag()) {
                    dogAI.startAI();
                }
                timerTask.StartWork();
                run.setEnabled(false);
                pause.setEnabled(true);
                run.setBackground(Color.RED);
                pause.setBackground(Color.GREEN);
                spawnCats.setEnabled(false);
                spawnDogs.setEnabled(false);
                isPressedOn = true;
                catsComboBox.setEnabled(false);
                dogsComboBox.setEnabled(false);
                priorityCatAI.setEnabled(false);
                priorityDogAI.setEnabled(false);
                lifeCats.setEnabled(false);
                lifeDogs.setEnabled(false);
                repaint();
            }
        });isPressedOn = false;
        pause.addActionListener(e -> {
            if (!catAI.isFlag()) {
                catAI.stopAI();
            }
            if (!dogAI.isFlag()) {
                dogAI.stopAI();
            }
            timerTask.WaitWork();
            run.setEnabled(true);
            pause.setEnabled(false);
            run.setBackground(Color.GREEN);
            pause.setBackground(Color.RED);
            catsComboBox.setEnabled(true);
            dogsComboBox.setEnabled(true);
            priorityCatAI.setEnabled(true);
            priorityDogAI.setEnabled(true);
            spawnCats.setEnabled(true);
            spawnDogs.setEnabled(true);
            lifeCats.setEnabled(true);
            lifeDogs.setEnabled(true);
            if (!useDialog)
                dialogOn = true;
            isPressedOnB = false;
            repaint();
            requestFocusInWindow();

        });




    }
    private class consoleActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Console myConsole = new Console(jFrame, window, catsComboBox, dogsComboBox);
            myConsole.showConsole();
        }
    }
    private class serverActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            pause.doClick();
            Client.Swap();
            run.doClick();
        }
    }
    private class downActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Client.Download();
        }
    }
    private class catsComboBoxActionListener implements ActionListener {
        int num;
        @Override
        public void actionPerformed(ActionEvent e) {
            num = catsComboBox.getSelectedIndex();
            window.setP1(num*10);
            requestFocusInWindow();
        }
    }
    private class dogsComboBoxActionListener implements ActionListener {
        int num;
        @Override
        public void actionPerformed(ActionEvent e) {
            num = dogsComboBox.getSelectedIndex();
            window.setP2(num*10);
            requestFocusInWindow();
        }
    }
    private class spawnCatsActionListener implements ActionListener {
        String number;
        @Override
        public void actionPerformed(ActionEvent e) {
            number = spawnCats.getText();
            try{
                window.setN1(Integer.parseInt(number));
            }catch (Throwable number){
                JOptionPane.showMessageDialog(null, "Ввод некорректного значения!!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class lifeCatsActionListener implements ActionListener {
        String number;
        @Override
        public void actionPerformed(ActionEvent e) {
            number = lifeCats.getText();
            try{
                window.setCatsTimeOfLife(Integer.parseInt(number));
            }catch (Throwable number){
                JOptionPane.showMessageDialog(null, "Ввод некорректного значения!!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class lifeDogsActionListener implements ActionListener {
        String number;
        @Override
        public void actionPerformed(ActionEvent e) {
            number = lifeDogs.getText();
            try{
                window.setDogsTimeOfLife(Integer.parseInt(number));
            }catch (Throwable number){
                JOptionPane.showMessageDialog(null, "Ввод некорректного значения!!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class spawnDogsActionListener implements ActionListener {
        String number;
        @Override
        public void actionPerformed(ActionEvent e) {
            number = spawnDogs.getText();
            try{
                window.setN2(Integer.parseInt(number));
            }catch (Throwable number){
                JOptionPane.showMessageDialog(null, "Ввод некорректного значения!!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class historyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //
            //
            //
            //
            //

            //
            //
            //
            //
            //
            //
            JTextArea display = new JTextArea(21,33);
            display.setEditable(false);


            for (int i = 0; i<window.quantityPet;i++){

                if(Singleton.getInstance().Get(i) instanceof Cat){
                    display.append("Номер: "+i+"\n");
                    display.append("Петомец: Кошка  \n");

                }else {
                    display.append("Номер: "+i+"\n");
                    display.append("Петомец: Собака\n");
                }
                display.append("Время рождения: "+Singleton.getInstance().Get(i).getTimeOfBirth()+"\n");
                display.append("Id питомца: "+Singleton.getInstance().Get(i).getID()+"\n");
            }
            JFrame infFrame = new JFrame("Питомцы");
            infFrame.setSize(400,400);
            infFrame.setLocationRelativeTo(null);
            infFrame.setResizable(false);
            JPanel middlePanel=new JPanel();
            JScrollPane scroll = new JScrollPane(display);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            middlePanel.add(scroll);
            infFrame.add(middlePanel);
            infFrame.setVisible(true);
        }
    }
    private class catAIActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (catAI.isFlag()) {
                catAI.startAI();
            } else {
                catAI.stopAI();
            }

        }
    }
    private class dogAIActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (dogAI.isFlag()) {
                dogAI.startAI();
            } else {
                dogAI.stopAI();
            }
        }
    }
    public class MyTimerTask {
        public Timer timer = new Timer(true);

        public void StartWork() {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {

                    if(myTimer != 0)
                        window.update(myTimer);


                    myTimer++;

                    repaint();
                }
            };

            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        }

        public void StopWork() {
            timer.cancel();
        }
        public void WaitWork() {
            timer.cancel();
            timer = new Timer();
        }
    }
    private class priorityCatAIActionListener implements ActionListener {
        int num;
        @Override
        public void actionPerformed(ActionEvent e) {
            num = priorityCatAI.getSelectedIndex() + 1;
            catAI.setPriority(num);
            requestFocusInWindow();
        }

    }
    private class priorityDogAIActionListener implements ActionListener {
        int num;
        @Override
        public void actionPerformed(ActionEvent e) {
            num = priorityDogAI.getSelectedIndex() + 1;
            dogAI.setPriority(num);
            requestFocusInWindow();
        }

    }
}
