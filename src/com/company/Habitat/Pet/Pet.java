package com.company.Habitat.Pet;

public abstract class Pet implements IBehaviour {
    private int x,y;
    private int id;
    private int timeOfBirth;
    public Pet(int id, int birth){
        this.id = id;
        timeOfBirth = birth;
    }


    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getID(){ return id; }
    public int getTimeOfBirth() { return timeOfBirth; }
}
