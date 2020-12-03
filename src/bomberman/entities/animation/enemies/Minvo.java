package bomberman.entities.animation.enemies;

import bomberman.BombermanGame;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.Transition;
import bomberman.entities.animation.enemies.ai.MediumAI;
import javafx.scene.image.Image;

public class Minvo extends Enemy {
    public Minvo(int x_pixel, int y_pixel) {
        super(x_pixel, y_pixel);
        this.speed = 6;
        ai = new MediumAI(BombermanGame.objectManager.getBomber(), this);
        for (Transition t : Transition.values()) {
            Image[] images1 = new Image[3];
            findTransition(images1, t);
            images[t.getDirection()] = images1;
        }
        Image[] dieOfMinvo = new Image[1];
        dieOfMinvo [0] = getFxImage("/sprites/minvo_dead.png");
        images[4] = dieOfMinvo ;
    }

    public void findTransition(Image[] image, Transition t) {
        if (t == Transition.UP || t == Transition.RIGHT) {
            image[0] = getFxImage("/sprites/minvo_right1.png");
            image[1] = getFxImage("/sprites/minvo_right2.png");
            image[2] = getFxImage("/sprites/minvo_right3.png");
        } else if (t == Transition.DOWN || t == Transition.LEFT) {
            image[0] = getFxImage("/sprites/minvo_left1.png");
            image[1] = getFxImage("/sprites/minvo_left2.png");
            image[2] = getFxImage("/sprites/minvo_left3.png");
        }
    }

    @Override
    public boolean onActionCollideEnemy(DynamicObject dynamicObject) {
        return false;
    }
}
