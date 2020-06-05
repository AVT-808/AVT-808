package Models.Abstract;
import java.awt.*;
import java.util.UUID;

public abstract class BaseRabbit {
    private UUID id;
    private Point coordinates;
    protected Image rabbitImage;
    private Integer birthTime;
    private Integer deathTime;

    protected BaseRabbit(Point coordinates, Integer birthTime, Integer deathTime) {
        this.coordinates = coordinates;
        this.birthTime = birthTime;
        this.deathTime = deathTime;
        id = UUID.randomUUID();
    }

    public Integer getBirthTime() {
        return birthTime;
    }

    public Integer getDeathTime() {
        return deathTime;
    }

    public UUID getId() {
        return id;
    }

    public Image getRabbitImage() {
        return rabbitImage;
    }

    public Point getCoordinates() {
        return coordinates;
    }

}
