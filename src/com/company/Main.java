package com.company;


import com.company.Habitat.Habitat;
import com.company.Single.Singleton;



public class Main{


    public static void main(String[] args) {
        Habitat myHabitat = new Habitat(1200,1000);
        Singleton.getInstance().setHabitat(myHabitat);
        MyJFrame MyJFrame = new MyJFrame(myHabitat);
    }
}
