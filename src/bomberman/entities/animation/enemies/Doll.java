package bomberman.entities.animation.enemies;

import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.Transition;
import bomberman.entities.animation.enemies.ai.LowAI;
import javafx.scene.image.Image;

public class Doll extends Enemy {
    public Doll(int x_pixel, int y_pixel) {

        super(x_pixel, y_pixel);
        this.speed = 2;
        ai = new LowAI();
        for (Transition t : Transition.values()) {
            Image[] images1 = new Image[3];
            findTransition(images1, t);
            images[t.getDirection()] = images1;
        }
        Image[] dieOfDoll = new Image[1];
        dieOfDoll[0] = getFxImage("/sprites/doll_dead.png");
        images[4] = dieOfDoll;
    }

    public void findTransition(Image[] image, Transition t) {
        if (isLiving) {
            if (t == Transition.UP || t == Transition.RIGHT) {
                image[0] = getFxImage("/sprites/doll_right1.png");
                image[1] = getFxImage("/sprites/doll_right2.png");
                image[2] = getFxImage("/sprites/doll_right3.png");
            } else if (t == Transition.DOWN || t == Transition.LEFT) {
                image[0] = getFxImage("/sprites/doll_left1.png");
                image[1] = getFxImage("/sprites/doll_left2.png");
                image[2] = getFxImage("/sprites/doll_left3.png");
            }
        }
    }

    @Override
    public Transition moveEnemy() {
        if (isMoving) {
            return transition;
        }
        return super.moveEnemy();
    }

    @Override
    public boolean onActionCollideEnemy(DynamicObject dynamicObject) {
        return false;
    }
}
