package term4;

public class CapitalHouseFactory implements AbstractFactory {
    @Override
    public House createHouse() {
        return new CapitalHouse();
    }
}
