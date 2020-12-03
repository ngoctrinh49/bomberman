package bomberman.entities.animation.enemies.ai;

import bomberman.entities.animation.Bomber;
import bomberman.entities.animation.enemies.Enemy;

public class MediumAI extends AI {

    Bomber bomber;
    Enemy e;
    int oldCheckOver = 2;

    boolean vertical = random.nextBoolean();
    public MediumAI(Bomber bomber, Enemy e) {
        this.bomber = bomber;
        this.e = e;
    }

    @Override
    public int calculateDirection() {
        if (oldCheckOver != checkRunOver() || checkRunOver() == 0 || !e.getIsMoving()) {
            vertical = random.nextBoolean();
        }
        oldCheckOver = checkRunOver();
        if (vertical == true) {
            int v = calculateCol();
            if (v == -1) {
                return calculateRow();
            } else return v;
        } else {
            int h = calculateRow();
            if (h == -1) {
                return calculateCol();
            } else return h;
        }
    }

    protected int calculateCol() {
        if (bomber.getX() == e.getX()) {
            return -1;
        } else if (bomber.getX() < e.getX()) {
            return 0;
        } else {
            return 1;
        }

    }

    protected int calculateRow() {
        if (bomber.getY() == e.getY()) {
            return -1;
        } else if (bomber.getY() < e.getY()) {
            return 2;
        } else {
            return 3;
        }
    }

    protected int checkRunOver() {
        int xSpace = bomber.getX() - e.getX();
        int ySpace = bomber.getY() - e.getY();
        int quadrant;
        if (xSpace > 0 && ySpace > 0) {
            quadrant = 4;
        } else if (xSpace < 0 && ySpace > 0) {
            quadrant = 3;
        } else if (xSpace < 0 && ySpace < 0) {
            quadrant = 2;
        } else if (xSpace > 0 && ySpace < 0) {
            quadrant = 1;
        } else {
            quadrant = 0;
        }
        return quadrant;
    }
}
