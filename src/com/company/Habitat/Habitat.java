package com.company.Habitat;


import com.company.Habitat.Pet.Creatures.Cat;
import com.company.Habitat.Pet.Creatures.Dog;
import com.company.Server.Client;
import com.company.Single.Singleton;


import java.util.Random;


public class Habitat {
    private int sizeX;
    private int sizeY;
    private int N1 = 5, P1 = 70;
    private int N2 = 3, P2 = 40;

    private int petsID;

    private int catsTimeOfLife = 3;
    private int dogsTimeOfLife = 2;

    public int quantityPet = 0;
    public int allSpawn = 0;
    public int cats,dogs;


    public Habitat(int x, int y){
        sizeX = x;
        sizeY = y;
    }
    public int getSizeX() { return sizeX; }
    public void setSizeX(int sizeX) { this.sizeX = sizeX; }
    public int getSizeY() { return sizeY; }
    public void setSizeY(int sizeY) { this.sizeY = sizeY; }

    public void update(int time) {

        if(N1!=0)
        if ((new Random().nextInt(100) < P1) && (time % N1 == 0)) {

            petsID = (new Random().nextInt(65550));
            Cat myCat = new Cat(petsID,time);
            myCat.setX(new Random().nextInt(sizeX - 100));
            if(myCat.getX()<50)myCat.setY(myCat.getX() + 100);
            myCat.setY(new Random().nextInt(sizeY - 100));
            if(myCat.getY()<50)myCat.setY(myCat.getY() + 100);

            Singleton.getInstance().SetID(petsID);
            Singleton.getInstance().SetHashMap(allSpawn,myCat.getTimeOfBirth());
            Singleton.getInstance().Add(myCat);
            quantityPet++;
            allSpawn++;
            cats++;
        }
        if(N2!=0)
        if ((new Random().nextInt(100) < P2) && (time % N2 == 0)) {
            petsID = (new Random().nextInt(65550));
            Dog myDog = new Dog(petsID,time);
            myDog.setX(new Random().nextInt(sizeX - 100));
            if(myDog.getX()<50)myDog.setX(myDog.getX() + 100);
            myDog.setY(new Random().nextInt(sizeY - 100));
            if(myDog.getY()<50)myDog.setY(myDog.getY() + 100);

            Singleton.getInstance().SetID(petsID);
            Singleton.getInstance().SetHashMap(allSpawn,myDog.getTimeOfBirth());
            Singleton.getInstance().Add(myDog);
            allSpawn++;
            quantityPet++;
            dogs++;
        }
        Client.Update();
        TimeToDie(time);
    }

    private void TimeToDie(int time){
        for (int i = 0; i < quantityPet; i++) {
            if (Dog.class.isAssignableFrom(Singleton.getInstance().Get(i).getClass())) {
                if(Singleton.getInstance().Get(i).getTimeOfBirth() + dogsTimeOfLife < time){
                    Singleton.getInstance().Delete(i);
                    quantityPet--;

                }

            } else {
                if(Singleton.getInstance().Get(i).getTimeOfBirth() + catsTimeOfLife < time){
                    Singleton.getInstance().Delete(i);
                    quantityPet--;

                }
            }

        }


    }


    public void allClear(){
        Singleton.getInstance().Clear();
        quantityPet = 0;
    }

    public int getP1() {
        return P1;
    }

    public int getP2() {
        return P2;
    }

    public void setP1(int p1) {
        P1 = p1;
    }

    public void setP2(int p2) {
        P2 = p2;
    }

    public int getN1() {
        return N1;
    }

    public int getN2() {
        return N2;
    }

    public void setN1(int n1) {
        N1 = n1;
    }

    public void setN2(int n2) {
        N2 = n2;
    }

    public void setCatsTimeOfLife(int catsTimeOfLife) {
        this.catsTimeOfLife = catsTimeOfLife;
    }

    public void setDogsTimeOfLife(int dogsTimeOfLife) {
        this.dogsTimeOfLife = dogsTimeOfLife;
    }

    public int getCatsTimeOfLife() {
        return catsTimeOfLife;
    }

    public int getDogsTimeOfLife() {
        return dogsTimeOfLife;
    }
}
