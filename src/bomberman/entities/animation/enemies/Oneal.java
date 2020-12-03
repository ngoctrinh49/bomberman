package bomberman.entities.animation.enemies;

import bomberman.BombermanGame;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.Transition;
import bomberman.entities.animation.enemies.ai.MediumAI;
import javafx.scene.image.Image;

public class Oneal extends Enemy {

    public Oneal(int x_pixel, int y_pixel) {
        super(x_pixel, y_pixel);
        this.speed = 2;
        ai = new MediumAI(BombermanGame.objectManager.getBomber(), this);
        for (Transition t : Transition.values()) {
            Image[] images1 = new Image[3];
            findTransition(images1, t);
            images[t.getDirection()] = images1;
        }
        Image[] dieOfOneal = new Image[1];
        dieOfOneal[0] = getFxImage("/sprites/oneal_dead.png");
        images[4] = dieOfOneal;
    }

    public void findTransition(Image[] image, Transition t) {
        if (t == Transition.UP ) {
            image[0] = getFxImage("/sprites/oneal_right2.png");
            image[1] = getFxImage("/sprites/oneal_left2.png");
            image[2] = getFxImage("/sprites/oneal_right2.png");
        } else if (t == Transition.RIGHT) {
            image[0] = getFxImage("/sprites/oneal_right2.png");
            image[1] = getFxImage("/sprites/oneal_right1.png");
            image[2] = getFxImage("/sprites/oneal_right3.png");
        } else if (t == Transition.DOWN ) {
            image[0] = getFxImage("/sprites/oneal_left1.png");
            image[1] = getFxImage("/sprites/oneal_right3.png");
            image[2] = getFxImage("/sprites/oneal_left3.png");
        } else if (t == Transition.LEFT) {
            image[0] = getFxImage("/sprites/oneal_left1.png");
            image[1] = getFxImage("/sprites/oneal_left2.png");
            image[2] = getFxImage("/sprites/oneal_left3.png");
        }
    }

    @Override
    public boolean onActionCollideEnemy(DynamicObject dynamicObject) {
        return false;
    }
}
