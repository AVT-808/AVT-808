package com.labi.var11.factory;

import com.labi.var11.models.abstracts.BaseAnimal;

import java.io.IOException;

public interface AbstractFactory {
    BaseAnimal birthCat(Integer xCord,Integer yCord) throws IOException;
    BaseAnimal birthDog(Integer xCord,Integer yCord) throws IOException;
}
