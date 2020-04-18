package term4;

import javax.swing.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Config {
    File file = new File("config.txt");
    FileReader fileReader;
    FileWriter fileWriter;
    JFileChooser jFileChooser = new JFileChooser();

    Map<String, String> config = new LinkedHashMap<>();

    public Config() {
        try{
            fileReader = new FileReader(file);
        }
        catch (FileNotFoundException e){
            int res = jFileChooser.showDialog(null, "Choose a configuration file");
            if(res == JFileChooser.APPROVE_OPTION){
                file = jFileChooser.getSelectedFile();
                try {
                    fileReader = new FileReader(file);
                }
                catch (FileNotFoundException fe){
                    fe.printStackTrace();
                }
            }
        }
    }

    public void loadConfig(){
        char[] cfg = new char[512];
        String[] s1, s2;

        try {
            fileReader.read(cfg);
            s1 = new String(cfg).split("\n");

            for (String s : s1) {
                s2 = s.split(": ");
                config.put(s2[0], s2[1]);
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

        init();
        config.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public void saveConfig(){
        try {
            fileWriter = new FileWriter(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try{
            fileWriter.write("Information: " + Main.habitat.getMyComponent().getjCheckBox().isSelected() +
                    "\nTimer: " + Main.habitat.getMyComponent().getjRadioButtonEnabled().isSelected() +
                    "\nWooden House Period: " + WoodenHouse.getPeriod() +
                    "\nWooden House Lifetime: " + (WoodenHouse.getLifetime() + 1) +
                    "\nWooden House Probability: " + WoodenHouse.getProbability() +
                    "\nCapital House Period: " + CapitalHouse.getPeriod() +
                    "\nCapital House Lifetime: " + (CapitalHouse.getLifetime() + 1) +
                    "\nCapital House Probability: " + CapitalHouse.getProbability() +
                    "\nWooden House AI: " + Main.habitat.getMyComponent().getjCheckBoxWoodenHouseAI().isSelected() +
                    "\nWooden House AI threads priority: " + Main.habitat.getMyComponent().getjComboBoxWoodenPriority()
                    .getSelectedItem() +
                    "\nCapital House AI: " + Main.habitat.getMyComponent().getjCheckBoxCapitalHouseAI().isSelected() +
                    "\nCapital House AI threads priority: " + Main.habitat.getMyComponent().getjComboBoxCapitalPriority()
                    .getSelectedItem() +
                    "\nEOF: true");
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try{
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void init(){
        Main.habitat.getMyComponent().getjCheckBox().setSelected(Boolean.parseBoolean(config.get("Information")
                .replaceAll("\\s+", "")));

        Main.setIsTimeVisible(Boolean.parseBoolean(config.get("Timer").replaceAll("\\s+", "")));
        if(Boolean.parseBoolean(config.get("Timer").replaceAll("\\s+", ""))){
            Main.habitat.getMyComponent().getjRadioButtonEnabled().setSelected(true);
        }
        else {
            Main.habitat.getMyComponent().getjRadioButtonDisabled().setSelected(true);
        }

        Main.habitat.getMyComponent().getjTextFieldWoodenPeriod().setText(config.get("Wooden House Period")
                .replaceAll("\\s+", ""));
        Main.habitat.getMyComponent().getjTextFieldWoodenLifetime().setText(config.get("Wooden House Lifetime")
                .replaceAll("\\s+", ""));

        if(Double.parseDouble(config.get("Wooden House Probability").replaceAll("\\s+", "")) >= 0 &&
                Double.parseDouble(config.get("Wooden House Probability").replaceAll("\\s+", "")) <= 1) {
            WoodenHouse.setProbability(Double.parseDouble(config.get("Wooden House Probability")
                    .replaceAll("\\s+", "")));
        }

        Main.habitat.getMyComponent().getjTextFieldCapitalPeriod().setText(config.get("Capital House Period")
                .replaceAll("\\s+", ""));
        Main.habitat.getMyComponent().getjTextFieldCapitalLifetime().setText(config.get("Capital House Lifetime")
                .replaceAll("\\s+", ""));

        if(Double.parseDouble(config.get("Capital House Probability").replaceAll("\\s+", "")) >= 0 &&
        Double.parseDouble(config.get("Capital House Probability").replaceAll("\\s+", "")) <= 1)
        CapitalHouse.setProbability(Double.parseDouble(config.get("Capital House Probability")
                .replaceAll("\\s+", "")));

        Main.habitat.getMyComponent().getjCheckBoxWoodenHouseAI().setSelected(Boolean.parseBoolean(
                config.get("Wooden House AI").replaceAll("\\s+", "")));
        Main.habitat.getMyComponent().getjComboBoxWoodenPriority().setSelectedIndex(Integer.parseInt(
                config.get("Wooden House AI threads priority").replaceAll("\\s+", "")) - 1);

        Main.habitat.getMyComponent().getjCheckBoxCapitalHouseAI().setSelected(Boolean.parseBoolean(
                config.get("Capital House AI").replaceAll("\\s+", "")));
        Main.habitat.getMyComponent().getjComboBoxCapitalPriority().setSelectedIndex(Integer.parseInt(
                config.get("Capital House AI threads priority").replaceAll("\\s+", "")) - 1);

        System.out.println(Boolean.parseBoolean(config.get("Wooden House AI").replaceAll("\\s+", "")));
        System.out.println(Main.habitat.getMyComponent().getjCheckBoxWoodenHouseAI().isSelected());
    }
}
