package term4;

public class ConcreteFactory {
    public static AbstractFactory concreteFactory(TypeOfHouse typeOfHouse){
        switch (typeOfHouse){
            case CAPITAL: return new CapitalHouseFactory();
            case WOODEN: return new WoodenHouseFactory();
            default: throw new RuntimeException();
        }
    }
}
