package bomberman.entities.animation.enemies;

import bomberman.entities.animation.Transition;
import bomberman.entities.animation.enemies.ai.LowAI;
import javafx.scene.image.Image;
import java.util.Random;

import static bomberman.entities.MapLoader.countEnemy;

public class Balloom extends Enemy {
    public Balloom(int x_pixel, int y_pixel) {
        super(x_pixel, y_pixel);
        this.speed = 1;
        ai = new LowAI();

        for (Transition t : Transition.values()) {
            Image[] images1 = new Image[3];
            findTransition(images1, t);
            images[t.getDirection()] = images1;
        }
    }

    public void findTransition(Image[] image, Transition t) {
        if (t == Transition.UP || t == Transition.RIGHT) {
            image[0] = new Image(getClass().getResource("/sprites/balloom_right1.png").toExternalForm());
            image[1] = new Image(getClass().getResource("/sprites/balloom_right2.png").toExternalForm());
            image[2] = new Image(getClass().getResource("/sprites/balloom_right3.png").toExternalForm());
        } else if (t == Transition.DOWN || t == Transition.LEFT) {
            image[0] = new Image(getClass().getResource("/sprites/balloom_left1.png").toExternalForm());
            image[1] = new Image(getClass().getResource("/sprites/balloom_left2.png").toExternalForm());
            image[2] = new Image(getClass().getResource("/sprites/balloom_left3.png").toExternalForm());
        } else {
            image = new Image[1];
            image[0] = new Image(getClass().getResource("/sprites/balloom_dead.png").toExternalForm());
        }
    }
    public Transition moveEnemy() {
        if (isMoving) {
            return transition;
        }
        return super.moveEnemy();
    }
}
