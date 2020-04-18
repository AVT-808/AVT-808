package term4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Console extends JDialog {

    JTextArea jTextArea = new JTextArea();
    JTextField jTextAreaEnter = new JTextField();

    JScrollPane scrollPane = new JScrollPane(jTextArea);


    String text = "Use /help for a list of possible options\n";

    public Console(JFrame owner) {
        super(owner,"Console", true);
        setBackground(Color.BLACK);
        setBounds(650, 300, 600, 400);
        setResizable(false);
        jTextArea.setEditable(false);

        jTextArea.setBackground(Color.BLACK);
        jTextArea.setForeground(Color.WHITE);
        jTextArea.setFont(new Font("Times New Romain",Font.PLAIN,16));
        jTextArea.setLineWrap(true);
        jTextArea.setText(text);

        jTextAreaEnter.setBackground(Color.BLACK);
        jTextAreaEnter.setForeground(Color.WHITE);
        jTextAreaEnter.setFont(new Font("Times New Romain",Font.PLAIN,16));

        setLayout(new FlowLayout(FlowLayout.LEFT));
        scrollPane.setPreferredSize(new Dimension(575,320));
        add(scrollPane);
        jTextAreaEnter.setPreferredSize(new Dimension(575,25));
        add(jTextAreaEnter);
    }

    public void showConsole(){

        jTextAreaEnter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    text += jTextAreaEnter.getText() + "\n";
                    jTextArea.setText(text);

                    switch (jTextAreaEnter.getText()){
                        case "/help" -> {
                            text += """
                                    /set Capital Probability <probability> - Установить вероятность генерации
                                    капитальных домов
                                    /get Capital Probability - Получить вероятность генерации капитальных домов
                                    """;
                            jTextAreaEnter.setText("");
                        }
                        case "/get Capital Probability" ->{
                            text += CapitalHouse.getProbability() + "\n";
                            jTextAreaEnter.setText(null);
                        }
                        default -> {
                            if(isSet()){
                                text += "\n";
                                System.out.println("Set");
                            }
                            else {
                                text += """

                                        Unknown command. Please use /help for a list of possible options
                                        """;
                            }
                            jTextAreaEnter.setText(null);
                        }
                    }
                    jTextArea.setText(text);
                }
            }
        });


                setVisible(true);
    }

    private boolean isSet(){
        String[] strings = jTextAreaEnter.getText().split(" ", 4);

        if (strings.length == 4 && strings[0].equalsIgnoreCase("/set") && strings[1].equalsIgnoreCase("Capital")
                && strings[2].equalsIgnoreCase("Probability")) {

            try {
                if (Double.parseDouble(strings[3]) >= 0 && Double.parseDouble(strings[3]) <= 1){
                    CapitalHouse.setProbability(Double.parseDouble(strings[3]));
                }
                else {
                    text += "Error! The probability should be in the range from 0 to 1.\n";
                }
                return true;
            } catch (RuntimeException re) {
                return false;
            }
        }
        return false;
    }
}
