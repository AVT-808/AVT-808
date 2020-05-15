package com.labi.var11.factory;

import com.labi.var11.models.Cat;
import com.labi.var11.models.Dog;
import com.labi.var11.models.abstracts.BaseAnimal;

import java.io.IOException;

public class AnimalFactory implements AbstractFactory{
    @Override
    public BaseAnimal birthCat(Integer xCord, Integer yCord) throws IOException {
        return new Cat(xCord, yCord);
    }

    @Override
    public BaseAnimal birthDog(Integer xCord, Integer yCord) throws IOException {
        return new Dog(xCord, yCord);
    }
}
