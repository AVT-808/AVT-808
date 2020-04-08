package Models.Abstract;
import java.awt.*;

public abstract class BaseRabbit {
    private Point coordinates;
    protected Image rabbitImage;

    protected BaseRabbit(Point coordinates) {
        this.coordinates = coordinates;
    }

    public Image getRabbitImage() {
        return rabbitImage;
    }

    public Point getCoordinates() {
        return coordinates;
    }

}
