package bomberman.entities.animation.enemies;

import bomberman.entities.ObjectManager;
import bomberman.entities.animation.Transition;
import bomberman.entities.animation.enemies.ai.MediumAI;
import javafx.scene.image.Image;

public class Oneal extends Enemy {

    public Oneal(int x_pixel, int y_pixel, ObjectManager objectManager) {
        super(x_pixel, y_pixel);
        this.speed = 3;
        ai = new MediumAI(objectManager.getBomber(), this);
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

}
