package bomberman.entities.animation.enemies.ai;

import bomberman.entities.animation.Bomber;
import bomberman.entities.animation.enemies.Enemy;

public class MediumAI extends AI {

    Bomber bomber;
    Enemy e;

    int vertical = 1;
    public MediumAI(Bomber bomber, Enemy e) {
        this.bomber = bomber;
        this.e = e;
    }

    @Override
    public int calculateDirection() {

        vertical = random.nextInt(2);

        if (vertical == 1) {
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
}
