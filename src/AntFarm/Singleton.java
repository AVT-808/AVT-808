package AntFarm;

        import java.util.ArrayList;

public class Singleton
{
    private static Singleton singleton;
    private ArrayList<Ant> ants;

    private Singleton()
    {
        ants = new ArrayList<>();
    }

    public int getArraySize()
    {
        return ants.size();
    }

    public static Singleton getSingleton()
    {
        if(singleton == null)
        {
            singleton = new Singleton();
        }
        return  singleton;
    }

    public static void addAnt(Ant ant)
    {
        singleton.ants.add(ant);
    }

    public static void clearAnts()
    {
        if (!singleton.ants.isEmpty())
            singleton.ants.clear();
    }

    public static ArrayList<Ant> getAnts(){
        return singleton.ants;
    }
}
