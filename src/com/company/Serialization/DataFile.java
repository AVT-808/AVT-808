package com.company.Serialization;


import com.company.MovingBees.Priority;
import com.company.Panels.ComboBoxProbability;
import com.company.Panels.LifeTimeOfBees;

import com.company.Panels.TimeOfBirth;

import java.io.*;

public class DataFile {

    private  static File file = new File("Config.txt");
    private  static File saveFile = new File("Save.txt");
    private  static String text;
   public static int[] bufferInt = new int[8];
    private  static byte[] buffer;
    DataFile(){

    }
    public static void ExitApplication(){
        try(FileOutputStream fos=new FileOutputStream(file))
        {
            DataOutputStream dos = new DataOutputStream(fos);
            text = "Life time of worker: "+ LifeTimeOfBees.life_time_Workers +"\nLife time of Drones: "+ LifeTimeOfBees.life_time_Drones+
                    "\nChance of Workers: "+ (int)(ComboBoxProbability.probabilityOfWorkers*10) +"\nChance of Drones: "+ (int)(ComboBoxProbability.percent*10)+
                    "\nTime of birth workers: "+ TimeOfBirth.birthTimeWorkers +"\nTime of birth workers: "+TimeOfBirth.birthTimeDrones+
                    "\nPriority workers AI: "+ Priority.Boba() +"\nPriority drones AI: " +Priority.Biba();
            buffer = text.getBytes();
            dos.write(buffer, 0, buffer.length);
            dos.close();

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void RunApplication(){
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
    }
}
