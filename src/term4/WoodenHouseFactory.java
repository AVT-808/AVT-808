package term4;

public class WoodenHouseFactory implements AbstractFactory {
    @Override
    public House createHouse() {
        return new WoodenHouse();
    }
}
