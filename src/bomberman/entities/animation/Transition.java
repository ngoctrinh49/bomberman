package bomberman.entities.animation;

/**
 * enum xác định hướng của nhân vật đang chuyển động.
 */
public enum Transition {
    LEFT(0), RIGHT(1), UP(2), DOWN(3);
    private int direction;

    Transition(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public String toString() {
        switch (direction) {
            case 0:
                return "LEFT";
            case 1:
                return "RIGHT";
            case 2:
                return "UP";
            case 3:
                return "DOWN";
            default:
                return "DEAD";
        }
    }
}
