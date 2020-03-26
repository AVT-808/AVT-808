package term4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class InformationDialog extends JDialog {

    JButton jButtonOK = new JButton("OK");
    JTextArea jTextArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(jTextArea);

    InformationDialog(JFrame owner){
        super(owner, "Information", true);
        setBounds(730, 300, 400, 500);
        add(jButtonOK, BorderLayout.SOUTH);
        jTextArea.setEditable(false);
        add(scrollPane);
    }



    public void showDialog(Vector<House> houseVector){

        jButtonOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });

        String string = "";

        for(House it : houseVector){
            string+="\nID: " + it.getId() +
                    "\nType: " + it.getTypeOfHouse() +
                    "\nTimeOfBirth: " + it.getTimeOfBirth() + "\n";
        }

        jTextArea.setText(string);

        setVisible(true);


    }
}
