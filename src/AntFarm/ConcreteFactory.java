package AntFarm;

import java.util.Random;

public class ConcreteFactory implements AbstractFactory
{
    private int N1 = 5;
    private int N2 = 3;
    private double P1 = 0.9;
    private double P2 = 0.7;
    private int antsAmount = 0;
    private int workersAmount = 0;
    private int warriorsAmount = 0;

    public ConcreteFactory() {}

    @Override
    public Ant createAnt(int x, int y, int time) {
        if (time % N1 == 0)
        {
            Random random = new Random();
            double probability = random.nextDouble();
            if (P1 > probability)
            {
                Ant ant = new AntWorker(x, y);
                workersAmount++; antsAmount++;
                return ant;
            }
        }
        if (time % N2 == 0) {
            Random random = new Random();
            double probability = random.nextDouble();
            if (P2 > probability) {
                Ant ant = new AntWarrior(x, y);
                warriorsAmount++;
                antsAmount++;
                return ant;
            }
        }
        return null;
    }

    @Override
    public void exterminate()
    {
        antsAmount = 0;
        workersAmount = 0;
        warriorsAmount = 0;
    }
}
