package AntFarm;

import javax.swing.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Configuration
{
    File file = new File ("C:\\Users\\Турбопупс\\IdeaProjects\\t4.lab\\src\\config.txt");
    FileReader fileReader;
    FileWriter fileWriter;
    JFileChooser jFileChooser = new JFileChooser();
    Map<String, String> config = new LinkedHashMap<>();

    Configuration()
    {
        try
        {
            fileReader = new FileReader(file);
        }
        catch (FileNotFoundException e)
        {
            int chooser = jFileChooser.showDialog(null, "Выбрать файл");
            if (chooser == JFileChooser.APPROVE_OPTION)
            {
                file = jFileChooser.getSelectedFile();
                try
                {
                    fileReader = new FileReader(file);
                }
                catch (FileNotFoundException fe)
                {
                    fe.printStackTrace();
                }
            }
        }
    }

    public void load(Habitat antFarm) {
        char[] c = new char[512];
        String[] s1, s2;
        try
        {
            fileReader.read(c);
            s1 = new String(c).split("\n");
            for (String s : s1)
            {
                s2 = s.split(": ");
                config.put(s2[0], s2[1]);
            }
            fileReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        setConfig(antFarm);
        config.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public void save(Habitat antFarm) {
        try
        {
            fileWriter = new FileWriter(file);
            fileWriter.write("Информация о симуляции: " + antFarm.getInformationVisibility().isSelected() +
                "\nОтображение таймера: " + antFarm.getTimerIsVisible().isSelected() +
                "\nИнтеллект муравья-рабочего: " + antFarm.getWorkerAI().isSelected() +
                "\nИнтеллект муравья-воина: " + antFarm.getWarriorAI().isSelected() +
                "\nПриоритет главного потока: " + antFarm.getMainThread().getSelectedItem() +
                "\nПриоритет потока движения муравья-рабочего: " + antFarm.getWorkerThread().getSelectedItem() +
                "\nПриоритет потока движения муравья-воина: " + antFarm.getWarriorThread().getSelectedItem() +
                "\nПериод рождения муравья-рабочего: " + antFarm.getWorkerPeriod().getText() +
                "\nПериод рождения муравья-воина: " + antFarm.getWarriorPeriod().getText() +
                "\nВремя жизни муравья-рабочего: " + antFarm.getWorkerLifetime().getText() +
                "\nВремя жизни муравья-воина: " + antFarm.getWarriorLifetime().getText() +
                "\nВероятность рождения муравья-рабочего: " + antFarm.getWorkerProbability().getSelectedItem() +
                "\nВероятность рождения муравья-воина: " + antFarm.getWarriorProbability().getSelectedItem());
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void setConfig(Habitat antFarm) {
        antFarm.getInformationVisibility().setSelected(Boolean.parseBoolean(config.get("Информация о симуляции")));

        boolean timer = Boolean.parseBoolean(config.get("Отображение таймера"));
        if (timer)
        {
            antFarm.getTimerIsVisible().setSelected(true);
            antFarm.getTimerIsVisible().setEnabled(false);
        }
        else {
            antFarm.getTimerNotVisible().setSelected(true);
            antFarm.getTimerNotVisible().setEnabled(false);
        }
        antFarm.timerVisibility(!timer);

        antFarm.getWorkerAI().setSelected(Boolean.parseBoolean(config.get("Интеллект муравья-рабочего")));
        antFarm.getWarriorAI().setSelected(Boolean.parseBoolean(config.get("Интеллект муравья-воина")));

        antFarm.getMainThread().setSelectedItem(Integer.parseInt(config.get("Приоритет главного потока")));
        antFarm.getWorkerThread().setSelectedItem(Integer.parseInt(config.get("Приоритет потока движения муравья-рабочего")));
        antFarm.getWarriorThread().setSelectedItem(Integer.parseInt(config.get("Приоритет потока движения муравья-воина")));

        antFarm.getWorkerPeriod().setText(config.get("Период рождения муравья-рабочего"));
        antFarm.getWarriorPeriod().setText(config.get("Период рождения муравья-воина"));

        antFarm.getWorkerLifetime().setText(config.get("Время жизни муравья-рабочего"));
        antFarm.getWarriorLifetime().setText(config.get("Время жизни муравья-воина"));

        antFarm.getWorkerProbability().setSelectedItem(Double.parseDouble(config.get("Вероятность рождения муравья-рабочего")));
        antFarm.getWarriorProbability().setSelectedItem(Double.parseDouble(config.get("Вероятность рождения муравья-воина")));
    }
}
