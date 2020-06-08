package com.company;

import com.company.Habitat.BeesArray.Singleton;
import com.company.Habitat.Habitat;
import com.company.MovingBees.DroneAI;
import com.company.MovingBees.Pause;
import com.company.MovingBees.WorkerAI;
import com.company.Serialization.DataFile;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends KeyAdapter{

    private final Habitat habitat;
    Singleton st;
    JButton startButton;
    JButton stopButton;
    private Boolean bool;
    private Boolean isStarted;


    private Main() {

        habitat = new Habitat();
        habitat.setVisible(true);
        habitat.addKeyListener(this);
        startButton = habitat.returnStart();
        stopButton = habitat.returnStop();
        st = Singleton.getInstance();
        bool = true;
        DroneAI droneAI = new DroneAI();
        WorkerAI workerAI = new WorkerAI();
        Pause.setAI(droneAI,workerAI);
        Singleton.getInstance().setConsole(habitat);

    }

    @Override
    public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_B) {
                isStarted = st.getIsStart();//isStarted присваиваем то, какое оно в Singleton
                if(isStarted) { //isStarted = true
                    st.Start(habitat, startButton, stopButton);
                    habitat.requestFocus();
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    isStarted = false;//кнопка на клавиатуре нажата и если нажмем еще раз, она работать не будет
                }
            }

         if (e.getKeyCode() == KeyEvent.VK_E ){
             isStarted = st.getIsStart();//isStarted присваиваем true через метод getIsStart который возвращает isStart
             if(!isStarted) {//isStarted = false
                 st.Stop(habitat, startButton, stopButton);
                 habitat.requestFocus();
                 stopButton.setEnabled(false);
                 startButton.setEnabled(true);
                 isStarted = true;
             }
         }
        if(e.getKeyCode() == KeyEvent.VK_T) {
            bool = habitat.return_bool();
            bool=!bool;
            habitat.Timer_show_hide(bool);
        }
    }

    public static void main(String[] args) {
        DataFile.RunApplication();
        new Main();
    }

}
