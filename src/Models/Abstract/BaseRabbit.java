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

    public void setRabbitImage(Image rabbitImage) {
        this.rabbitImage = rabbitImage;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }
}
