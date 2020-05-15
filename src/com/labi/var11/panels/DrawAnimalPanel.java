package com.labi.var11.panels;

import com.labi.var11.models.abstracts.BaseAnimal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawAnimalPanel extends JPanel {
    private final List<BaseAnimal> animalList;

    public DrawAnimalPanel(List<BaseAnimal> animalList) {
        this.animalList = animalList;
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(animalList.isEmpty())
            return;
        for (BaseAnimal animal : animalList) {
            graphics.drawImage(animal.getAnimalImage(),animal.getX(),animal.getY(),70,70,null);
        }
    }


}
