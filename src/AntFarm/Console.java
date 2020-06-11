package AntFarm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Console extends JDialog
{
    JTextArea commands = new JTextArea();
    JTextField commandLine = new JTextField();
    JScrollPane scrollPane = new JScrollPane(commands);

    String string = "Используйте /help для получения списка команд.\nВведите команду:\n";

    public Console(JFrame jFrame) {
        super(jFrame, "Консоль", false);
        setSize(600,400);
        setResizable(false);
        commands.setEditable(false);

        commands.setBackground(Color.BLACK);
        commands.setForeground(Color.WHITE);
        commands.setFont(new Font("Times New Romain", Font.PLAIN, 14));
        commands.setLineWrap(true);
        commands.setText(string);

        commandLine.setBackground(Color.BLACK);
        commandLine.setForeground(Color.WHITE);
        commandLine.setFont(new Font("Times New Romain", Font.PLAIN, 14));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(commandLine, BorderLayout.PAGE_END);
        commandLine.setFocusable(true);
        commandLine.requestFocus();
    }

    public void ConsoleOperation(Main m){
        commandLine.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    string += commandLine.getText() + "\n";
                    commands.setText(string);
                    String getTextString = commandLine.getText();
                    if (getTextString.equals("/help"))
                    {
                        string += "\nСписок команд:\n/Старт - запуск симуляции\n/Стоп - остановка симуляции\n\n";
                    }
                    else
                    {
                        if (getTextString.equals("/Старт"))
                        {
                            if (Main.isStarted)
                                string += "\nОшибка! Симуляция уже запущена!\n";
                            else
                            {
                                string += "\nСимуляция запущена.\n";
                                m.startSimulation();
                            }
                        }
                        else
                        {
                            if(getTextString.equals("/Стоп"))
                            {
                                if (Main.isStarted)
                                {
                                    string += "\nСимуляция остановлена\n";
                                    m.stopSimulation();
                                }
                                else
                                    string+="\nОшибка! Симуляция еще не запущена\n";
                            }
                            else
                            {
                                string += "\nНеизвестная команда. Используйте /help для получения списка команд.\n";
                            }
                        }
                    }
                        commands.setText(string);
                        commandLine.setText(null);
                        commandLine.requestFocus();
                    }
                }
        });
    }
}
