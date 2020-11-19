package bomberman.entities.animation.enemies;

import bomberman.entities.animation.Transition;
import javafx.scene.image.Image;

import java.util.Random;

public class Oneal extends Enemy {

    public Oneal(int x_pixel, int y_pixel) {
        super(x_pixel, y_pixel);
        this.speed = 1;
        for (Transition t : Transition.values()) {
            Image[] images1 = new Image[3];
            findTransition(images1, t);
            images[t.getDirection()] = images1;
        }
    }

    public void findTransition(Image[] image, Transition t) {
        if (t == Transition.UP || t == Transition.RIGHT) {
            image[0] = new Image(getClass().getResource("/sprites/oneal_right1.png").toExternalForm());
            image[1] = new Image(getClass().getResource("/sprites/oneal_right2.png").toExternalForm());
            image[2] = new Image(getClass().getResource("/sprites/oneal_right3.png").toExternalForm());
        } else if (t == Transition.DOWN || t == Transition.LEFT) {
            image[0] = new Image(getClass().getResource("/sprites/oneal_left1.png").toExternalForm());
            image[1] = new Image(getClass().getResource("/sprites/oneal_left2.png").toExternalForm());
            image[2] = new Image(getClass().getResource("/sprites/oneal_left3.png").toExternalForm());
        } else {
            image = new Image[1];
            image[0] = new Image(getClass().getResource("/sprites/oneal_dead.png").toExternalForm());
        }
    }

    /**
     * pt chuyển động của enemy.
     */
    public Transition moveEnemy() {
        if (isMoving) {
            return transition;
        }
        Random random = new Random();
        int transition = random.nextInt(3);
        switch (transition) {
            case 0:
                return Transition.LEFT;
            case 1:
                return Transition.RIGHT;
            case 2:
                return Transition.UP;
            case 3:
                return Transition.DOWN;
        }
        return Transition.DOWN;
    }
}
