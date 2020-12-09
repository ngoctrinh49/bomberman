package bomberman.entities.animation.enemies;

import bomberman.BombermanGame;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.Transition;
import bomberman.entities.animation.bomb.Bomb;
import bomberman.entities.animation.bomb.Flame;
import bomberman.entities.animation.enemies.ai.MediumAI;
import bomberman.entities.unmoving.Brick;
import bomberman.entities.unmoving.StaticObject;
import bomberman.entities.unmoving.Wall;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Kondoria extends Enemy {

    public Kondoria(int x_pixel, int y_pixel) {
        super(x_pixel, y_pixel);
        this.speed = 1;
        ai = new MediumAI(BombermanGame.objectManager.getBomber(), this);
        for (Transition t : Transition.values()) {
            Image[] images1 = new Image[3];
            findTransition(images1, t);
            images[t.getDirection()] = images1;
        }
        Image[] dieOfKondoria = new Image[1];
        dieOfKondoria[0] = getFxImage("/sprites/kondoria_dead.png");
        images[4] = dieOfKondoria;
    }

    public void findTransition(Image[] image, Transition t) {
        if (t == Transition.UP || t == Transition.RIGHT) {
            image[0] = getFxImage("/sprites/kondoria_right1.png");
            image[1] = getFxImage("/sprites/kondoria_right2.png");
            image[2] = getFxImage("/sprites/kondoria_right3.png");
        } else if (t == Transition.DOWN || t == Transition.LEFT) {
            image[0] = getFxImage("/sprites/kondoria_left1.png");
            image[1] = getFxImage("/sprites/kondoria_left2.png");
            image[2] = getFxImage("/sprites/kondoria_left3.png");
        } else {
            image = new Image[1];
            image[0] = getFxImage("/sprites/Kondoria_dead.png");
        }
    }


    public boolean checkCanMoveThrough(int x, int y) {
        ArrayList<StaticObject> staticObjects = manager.getStaticObjectInRec(x, y, width -1, height -1);
        for (int i = 0; i < staticObjects.size(); i++) {
            StaticObject object = staticObjects.get(i);
            if (object instanceof Bomb) {
                if (((Bomb) object).isExploded()) {
                    kill();
                    return true;
                }
                return false;
            }
            if (object instanceof Flame) {
                kill();
                return true;    //enermy bị giết thì có thể đi qua.
            }
        }
        return true;
    }

    @Override
    public boolean onActionCollideEnemy(DynamicObject dynamicObject) {
        return false;
    }

    @Override
    public void move(Transition transition) {
        if (transition == this.transition) {
            indexOfFrame++;
        } else {
            indexOfFrame = 0;
            this.transition = transition;
        }

        int x_new = 0, y_new = 0;
        switch (transition) {
            case UP:
                y_new = -speed;
                break;
            case DOWN:
                y_new = speed;
                break;
            case LEFT:
                x_new = -speed;
                break;
            case RIGHT:
                x_new = speed;
                break;
            default:
                break;
        }
            x_new += x;
            y_new += y;
        isMoving = canMove(x_new, y_new);
        if (isMoving) {
            x = x_new;
            y = y_new;
        }
    }
}