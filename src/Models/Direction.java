package Models;

public enum Direction {
    Right (1),
    Left (-1),
    Up(1),
    Down(-1);

    private int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
