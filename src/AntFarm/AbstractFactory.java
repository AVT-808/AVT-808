package AntFarm;

public interface AbstractFactory
{
    Ant createAnt(int x, int y, int time);
    void exterminate();
}
