package com.company.Panels;

import com.company.Habitat.Habitat;

import javax.swing.*;


public class MenuButtons extends JPanel {

    ShowInfButton chb;
    CurObj CO;
    TimerShowHide timerShowHide;
    ComboBoxProbability comboBoxProbability;
    TimeOfBirth timeOfBirth;
    LifeTimeOfBees lifeTimeOfBees;


    public MenuButtons(Habitat habitat){

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        CO = new CurObj(habitat);
        add(CO);

        chb = new ShowInfButton();// показать инфу да/нет
        add(chb);

        timerShowHide = new TimerShowHide(habitat);//скрыть/показать таймер
        add(timerShowHide);

        comboBoxProbability = new ComboBoxProbability();//вероятность рождения пчел
        add(comboBoxProbability);

        timeOfBirth = new TimeOfBirth();//время рождения тех или других пчел
        add(timeOfBirth);

        lifeTimeOfBees = new LifeTimeOfBees();//время рождения тех или других пчел
        add(lifeTimeOfBees);



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

    public LifeTimeOfBees return_(){
        return lifeTimeOfBees;
    }
}
