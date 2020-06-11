package AntFarm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.Vector;

public class Serialization
{
    File file = new File("C:\\Users\\Турбопупс\\IdeaProjects\\t4.lab\\src\\objects.ser");
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    JFileChooser jFileChooser = new JFileChooser();

    public void serialize()
    {
        try
        {
            fileOutputStream = new FileOutputStream(file);
        }
        catch (FileNotFoundException e)
        {
            try
            {
                if(file.createNewFile())
                    System.out.println("Файл не найден, поэтому для сохранения объектов был создан новый файл.");
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        try
        {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(Singleton.getAnts());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            objectOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deserialize(Habitat antFarm)
    {
        try
        {
            fileInputStream = new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            int res = jFileChooser.showDialog(null, "Выбрать файл");
            if(res == JFileChooser.APPROVE_OPTION){
                file = jFileChooser.getSelectedFile();
                try
                {
                    fileInputStream = new FileInputStream(file);
                }
                catch (FileNotFoundException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        try {
            int workers = 0; int warriors = 0;
            objectInputStream = new ObjectInputStream(fileInputStream);
            Singleton.clearAnts();
            Singleton.setAnts((Vector<Ant>)objectInputStream.readObject());
            if (!Singleton.getAnts().isEmpty())
            {
                for (int i=0; i<Singleton.getArraySize();i++)
                {
                    Singleton.getAnts().get(i).birthTime = antFarm.getTime();
                    if (Singleton.getAnts().get(i).getClass() == AntWorker.class)
                    {
                       Singleton.getAnts().get(i).setImage(ImageIO.read(getClass().getResource("/AntWorker.png")));
                        Singleton.getAnts().get(i).deathTime = Singleton.getAnts().get(i).birthTime +
                                Integer.parseInt(antFarm.getWorkerLifetime().getText());
                        workers++;
                    }
                    else
                    {
                        Singleton.getAnts().get(i).setImage(ImageIO.read(getClass().getResource("/AntWarrior.png")));
                        Singleton.getAnts().get(i).deathTime = Singleton.getAnts().get(i).birthTime +
                                Integer.parseInt(antFarm.getWarriorLifetime().getText());
                        warriors++;
                    }
                    Singleton.addIds(Singleton.getAnts().get(i).id);
                    Singleton.addBirthTime(Singleton.getAnts().get(i).id, Singleton.getAnts().get(i).birthTime);
                }
                antFarm.setAntsAmount(Singleton.getArraySize());
                antFarm.setWorkersAmount(workers);
                antFarm.setWarriorsAmount(warriors);
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            objectInputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
