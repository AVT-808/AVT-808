package term4;

public class Singleton {
    private static Singleton singleton;
    private House[] house;

    private Singleton() {
        house = new House[1000];
    }

    public static Singleton getSingleton(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return  singleton;
    }

    public static void setHouse(int n){
    }
}
