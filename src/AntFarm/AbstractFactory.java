package AntFarm;

public interface AbstractFactory
{
    Ant createAnt(int x, int y, int time);
    void setParameters(int N1, int N2, double P1, double P2);
    void exterminate();
}
