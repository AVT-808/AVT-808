package com.company.Panels;

import com.company.Habitat.Habitat;

import javax.swing.*;


public class MenuButtons extends JPanel {

    ShowInfButton chb;
    TimerShowHide timerShowHide;
    ComboBoxProbability comboBoxProbability;
    TimeOfBirth timeOfBirth;

    public MenuButtons(Habitat habitat){

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        chb = new ShowInfButton();// показать инфу да/нет
        add(chb);

        timerShowHide = new TimerShowHide(habitat);//скрыть/показать таймер
        add(timerShowHide);

        comboBoxProbability = new ComboBoxProbability();//вероятность рождения пчел
        add(comboBoxProbability);

        timeOfBirth = new TimeOfBirth();//время рождения тех или других пчел
        add(timeOfBirth);

        setVisible(true);
        setFocusable(false);
    }


    public Boolean return_chb() {
        Boolean bl = chb.return_chb();
        return bl;
    }

    public Boolean return_bool(){
        Boolean bool = timerShowHide.return_bool();
        return bool;
    }
    public void return2_bool(){
        timerShowHide.return2_bool();
    }

    public ComboBoxProbability return_comboBox() {
        return comboBoxProbability;
    }

    public TimeOfBirth return_timerOfBirth(){
        return timeOfBirth;
    }
}
