package Models.Abstract;
import Models.Behaviour;
import Models.Direction;

import java.awt.*;
import java.util.UUID;

public abstract class BaseRabbit implements Behaviour {
    private UUID id;
    protected Point coordinates;
    protected Image rabbitImage;
    protected Integer birthTime;
    private Integer deathTime;
    protected Integer speed;
    protected Integer changeDirectionX;
    protected Integer changeDirectionY;


    protected BaseRabbit(Point coordinates, Integer birthTime, Integer deathTime, Integer speed) {
        this.coordinates = coordinates;
        this.birthTime = birthTime;
        this.deathTime = deathTime;
        this.speed = speed;
        this.changeDirectionX = Direction.Right.getDirection();
        this.changeDirectionY = Direction.Up.getDirection();
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

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
