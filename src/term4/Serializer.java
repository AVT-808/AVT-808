package term4;

import javax.swing.*;
import java.io.*;
import java.util.Vector;

public class Serializer {
    File file = new File("data/objects.dat");
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    JFileChooser jFileChooser = new JFileChooser();

    public void serialization(){
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            try {
                if(file.createNewFile()){
                    System.out.println("File created");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(Singleton.getSingleton().getHouseVector());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deserialization(){
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            int res = jFileChooser.showDialog(null, "Open");
            if(res == JFileChooser.APPROVE_OPTION){
                file = jFileChooser.getSelectedFile();
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }

        try {
            objectInputStream = new ObjectInputStream(fileInputStream);

            Singleton.getSingleton().clearArea();
            Singleton.getSingleton().setHouseVector((Vector<House>) objectInputStream.readObject());
            MyComponent.setCount(Singleton.getSingleton().getHouseVector().size());
            for(int i = 0; i < Singleton.getSingleton().getHouseVector().size(); i++){
                Singleton.getSingleton().getHouse(i).setTimeOfBirth(MyComponent.getTime());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
