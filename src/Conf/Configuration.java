package Conf;


import javax.swing.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class Configuration {
    File file = new File ("C:\\Users\\алла\\IdeaProjects\\5ая\\src\\conf.txt"); // Указывается путь к файлу
    FileReader fileReader;
    FileWriter fileWriter;
    JFileChooser jFileChooser = new JFileChooser(); // Контейнер, в котором расположены несколько компонентов, списков и кнопок, «управляющих» выбором файлов.

    Map<String, String> config = new LinkedHashMap<>();

    public Configuration() { // Читаем файл
        try{
            fileReader = new FileReader(file);
        }
        catch (FileNotFoundException e) {
            int res = jFileChooser.showDialog(null, "Выбрать файл"); // Функция открытия окна выбора файла с настроенным наименованием кнопки

            if(res == JFileChooser.APPROVE_OPTION) { // APPROVE_OPTION - выбор файла в диалоговом окне прошел успешно;
                file = jFileChooser.getSelectedFile(); // 	Функция чтения выделенного файла
                try {
                    fileReader = new FileReader(file);
                }
                catch (FileNotFoundException fe){
                    fe.printStackTrace();
                }
            }
        }
    }

    public void downloadInformation() {
        char[] cfg = new char[512];
        String[] s1, s2;

        try {
            fileReader.read(cfg); // Читает символы в массив. Возвращает количество прочитанных символов.
            s1 = new String(cfg).split("\n"); // Разделить строку по абзацам

            for (String s : s1) {
                s2 = s.split(": "); // Разделить по :
                config.put(s2[0], s2[1]); // Добавить определение и значение в LinkedHashMap
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try {
            fileReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        setOptions();
        //config.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public void saveInformation(){
        try {
            fileWriter = new FileWriter(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }

         try{
           fileWriter.write("Информация: " + Contr.CheckB.Return_nagatost() +
                   "\nТаймер: " + Contr.Clock.Return_dd() +
                   "\nПериод появления взрослых: " + Contr.Periods.Return_time_birth_big() +
                   "\nПериод появления птенцов: " + Contr.Periods.Return_time_birth_small() +
                   "\nВремя жизни взрослых: " + Existence.Life.Return_lifetime_Big() +
                   "\nВремя жизни птенцов: " + Existence.Life.Return_lifetime_Small() +
                   "\nВероятность рождения взрослого: " + Contr.ComboB.Return_chance_birth_big() +
                   "\nПроцентное соотношение птенцов и взрослых: " + Contr.ComboB.Return_percent() +
                   "\nИнтеллект взрослого: " + Move.Pause.getBigAI() +
                   "\nИнтеллект птенца: " + Move.Pause.getSmallAI() +
                   "\nПриоритет главного потока: " + Move.Prior.getPriorMain() +
                   "\nПриоритет потока движения взрослых: " + Move.Prior.getPriorBaseBig() +
                   "\nПриоритет потока движения птенцов: " + Move.Prior.getPriorBaseSmall());
         }       catch (IOException e){
            e.printStackTrace();
        }

        try{
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setOptions() { // Установка параметров в меню
        Contr.CheckB.getBox().setSelected(Boolean.parseBoolean(config.get("Информация") // Преобразование к булеан
                .replaceAll("\\s+", ""))); //  заменяет пробел ничем в LinkedHashMap<> b djpdhfoftn cnhjre

        Contr.Keyboard.setDd(Boolean.parseBoolean(config.get("Таймер").replaceAll("\\s+", "")));

        Contr.Periods.getTextArea_big().setText(config.get("Период появления взрослых")
                .replaceAll("\\s+", ""));

        Contr.Periods.getTextArea_small().setText(config.get("Период появления птенцов")
                .replaceAll("\\s+", ""));

        if(Float.parseFloat(config.get("Вероятность рождения взрослого").replaceAll("\\s+", "")) >= 0.0f &&
                Float.parseFloat(config.get("Вероятность рождения взрослого").replaceAll("\\s+", "")) <= 1.0f) {
            Contr.ComboB.setComboBoxB(Float.parseFloat(config.get("Вероятность рождения взрослого")
                    .replaceAll("\\s+", "")));
        }

        if(Float.parseFloat(config.get("Процентное соотношение птенцов и взрослых").replaceAll("\\s+", "")) >= 0.0f &&
                Float.parseFloat(config.get("Процентное соотношение птенцов и взрослых").replaceAll("\\s+", "")) <= 1.0f) {
            Contr.ComboB.setComboBoxS(Float.parseFloat(config.get("Процентное соотношение птенцов и взрослых")
                    .replaceAll("\\s+", "")));
        }

        Existence.Life.getTextArea_big().setText(config.get("Время жизни взрослых")
                .replaceAll("\\s+", ""));

        Existence.Life.getTextArea_small().setText(config.get("Время жизни птенцов")
                .replaceAll("\\s+", ""));


        Move.Pause.setBox_Big(Boolean.parseBoolean(config.get("Интеллект взрослого")
                .replaceAll("\\s+", "")));

        Move.Pause.setBox_Small(Boolean.parseBoolean(config.get("Интеллект птенца")
                .replaceAll("\\s+", "")));

        if(Integer.parseInt(config.get("Приоритет потока движения взрослых").replaceAll("\\s+", "")) >= 0 &&
                Integer.parseInt(config.get("Приоритет потока движения взрослых").replaceAll("\\s+", "")) <= 10) {
            Move.Prior.setComboBoxB(Integer.parseInt(config.get("Приоритет потока движения взрослых")
                    .replaceAll("\\s+", "")));
        }

        if(Integer.parseInt(config.get("Приоритет главного потока").replaceAll("\\s+", "")) >= 0 &&
            Integer.parseInt(config.get("Приоритет главного потока").replaceAll("\\s+", "")) <= 10) {
        Move.Prior.setComboBoxM(Integer.parseInt(config.get("Приоритет главного потока")
                .replaceAll("\\s+", "")));
    }
    }
}