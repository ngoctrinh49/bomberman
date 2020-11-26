package bomberman.entities.animation.enemies;

import bomberman.entities.animation.Transition;

public class Doll extends Enemy{
    public Doll(int x_pixel, int y_pixel) {
        super(x_pixel, y_pixel);
    }

    @Override
    public Transition moveEnemy() {
        return null;
    }
}
