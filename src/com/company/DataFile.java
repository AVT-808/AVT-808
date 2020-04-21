package com.company;

import com.company.BaseAI.AI.CatAI;
import com.company.BaseAI.AI.DogAI;
import com.company.Habitat.Habitat;
import com.company.Habitat.Pet.Creatures.Cat;
import com.company.Habitat.Pet.Creatures.Dog;
import com.company.Single.Singleton;

import java.io.*;

public class DataFile {
    File file = new File("Configuration.txt");
    File saveFile = new File("Save.txt");
    String text;
    int[] bufferInt = new int[8];
    byte[] buffer;
    DataFile(){


    }
    public void ExitApplication(Habitat window,int priorityCat,int priorityDog){
        try(FileOutputStream fos=new FileOutputStream(file))
        {
            DataOutputStream dos = new DataOutputStream(fos);
            text = "Spawn cats: "+ window.getN1()+"\nSpawn dogs: "+ window.getN2()+
                    "\nSpawn chance cats: "+ window.getP1()+"\nSpawn chance dogs: "+ window.getP2()+
                    "\nLife time cats: "+window.getCatsTimeOfLife()+"\nLife time dogs: "+window.getDogsTimeOfLife()+
                    "\nPriority cats AI: "+priorityCat+"\nPriority dogs AI: "+priorityDog;
            buffer = text.getBytes();
            dos.write(buffer, 0, buffer.length);
            dos.close();

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public void RunApplication(Habitat window, CatAI catAI, DogAI dogAI){
        try(FileInputStream fis=new FileInputStream(file))
        {
            DataInputStream dis = new DataInputStream(fis);

            buffer = new byte[fis.available()];
            fis.read(buffer, 0, fis.available());
            text = "";
            int num =0;
            for(int i=0; i<buffer.length;i++){
                if((char)buffer[i]!='\n'){
                    text += (char)buffer[i];
                    try {
                        Integer.parseInt(text);
                    } catch(NumberFormatException e) {
                        text = "";
                    }
                }else {
                    bufferInt[num] = Integer.parseInt(text);
                    text = "";
                    num++;
                }
            }
            bufferInt[num] = Integer.parseInt(text);
            dis.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        window.setN1(bufferInt[0]);
        window.setN2(bufferInt[1]);
        window.setP1(bufferInt[2]);
        window.setP2(bufferInt[3]);
        window.setCatsTimeOfLife(bufferInt[4]);
        window.setDogsTimeOfLife(bufferInt[5]);
        catAI.setPriority(bufferInt[6]);
        dogAI.setPriority(bufferInt[7]);
    }
    public void SavePets(Habitat window){
            try(FileOutputStream fosSave=new FileOutputStream(saveFile))
            {
                text ="";
                DataOutputStream dosSave = new DataOutputStream(fosSave);
                for (int i=0;i<window.quantityPet;i++) {
                    if(Singleton.getInstance().Get(i) instanceof Cat){
                        text +="Номер: "+i+"\n";
                        text +="Петомец: Cat\n";

                    }else {
                        text +="Номер: "+i+"\n";
                        text +="Петомец: Dog\n";
                    }
                    text +="Время рождения: "+Singleton.getInstance().Get(i).getTimeOfBirth()+"\n";
                    text +="Id питомца: "+Singleton.getInstance().Get(i).getID()+"\n";
                    text += "X: "+Singleton.getInstance().Get(i).getX()+"\nY: "+Singleton.getInstance().Get(i).getY()+"\n";
                }
                buffer = text.getBytes();
                dosSave.write(buffer, 0, buffer.length);
                dosSave.close();

            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }


    }
    public void DownloadPets(GUI myGUI, Habitat window){
        myGUI.pause.doClick();
        boolean control = false;
        try(FileInputStream fis=new FileInputStream(saveFile))
        {
            DataInputStream dis = new DataInputStream(fis);

            buffer = new byte[fis.available()];
            fis.read(buffer, 0, fis.available());
            int num = 0;
            for(int i=0; i<buffer.length;i++){
                if((char)buffer[i]!='\n'){
                    if (control){
                        continue;
                    }
                    text += (char)buffer[i];
                    if ((text.equals("D"))||(text.equals("C"))){
                        if (text.equals("C")){
                            text = "1";
                        }else {
                            text = "2";
                        }
                        control = true;
                        continue;
                    }
                    try {
                        Integer.parseInt(text);
                    } catch(NumberFormatException e) {
                        text = "";
                    }
                }else {
                    bufferInt[num] = Integer.parseInt(text);
                    num++;
                    control = false;
                    text = "";
                    if (num == 6){
                        if(bufferInt[1] == 1) {
                            Cat myCat = new Cat(bufferInt[3], myGUI.myTimer);
                            myCat.setX(bufferInt[4]);
                            myCat.setY(bufferInt[5]);
                            Singleton.getInstance().SetID(myCat.getID());
                            Singleton.getInstance().Add(myCat);
                            window.quantityPet++;
                            window.cats++;
                        }else  {
                            Dog myDog = new Dog(bufferInt[3], myGUI.myTimer);
                            myDog.setX(bufferInt[4]);
                            myDog.setY(bufferInt[5]);
                            Singleton.getInstance().SetID(myDog.getID());
                            Singleton.getInstance().Add(myDog);
                            window.quantityPet++;
                            window.dogs++;
                        }
                        num = 0; 
                    }
                }
            }
            dis.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println(text);
    }
}
