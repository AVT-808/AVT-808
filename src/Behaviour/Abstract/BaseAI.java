package Behaviour.Abstract;

import Behaviour.RabbitAI;

public abstract class BaseAI extends Thread implements RabbitAI {
    protected boolean isMoving;

    protected BaseAI(){
        isMoving = false;
    }
}
