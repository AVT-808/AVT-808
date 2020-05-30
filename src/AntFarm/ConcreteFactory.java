package AntFarm;

import java.util.Random;

public class ConcreteFactory implements AbstractFactory
{
    private int N1;
    private int N2;
    private double P1;
    private double P2;
    private int L1;
    private int L2;
    private int antsAmount = 0;
    private int workersAmount = 0;
    private int warriorsAmount = 0;

    public ConcreteFactory() { }

    @Override
    public Ant createAnt(int x, int y, int id, int time) {
        if (time % N1 == 0)
        {
            Random random = new Random();
            double probability = random.nextDouble();
            if (P1 > probability)
            {
                Ant ant = new AntWorker(x, y, id, time, L1);
                workersAmount++; antsAmount++;
                return ant;
            }
        }
        if (time % N2 == 0) {
            Random random = new Random();
            double probability = random.nextDouble();
            if (P2 > probability) {
                Ant ant = new AntWarrior(x, y, id, time, L2);
                warriorsAmount++; antsAmount++;
                return ant;
            }
        }
        return null;
    }

    @Override
    public void setParameters(int N1, int N2, double P1, double P2, int L1, int L2)
    {
        this.N1 = N1;
        this.N2 = N2;
        this.P1 = P1;
        this.P2 = P2;
        this.L1 = L1;
        this.L2 = L2;
    }

    @Override
    public void exterminate()
    {
        antsAmount = 0;
        workersAmount = 0;
        warriorsAmount = 0;
    }
}
