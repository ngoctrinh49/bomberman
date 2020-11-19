package bomberman.entities.animation;

import bomberman.entities.GameObject;
import javafx.scene.image.Image;

public abstract class DynamicObject extends GameObject {
    protected Transition transition = Transition.RIGHT;
    protected int speed = 1;
    protected Image[][] images;
    protected boolean isMoving;
    protected int indexOfFrame = 0;

    public void move(Transition transition) {
        if(transition == this.transition){
            indexOfFrame++;
        } else {
            indexOfFrame = 0;
            this.transition = transition;
        }

        int addX = 0, addY = 0;
        switch (transition){
            case UP:
                addY = -1;
                break;
            case DOWN:
                addY = 1;
                break;
            case LEFT:
                addX = -1;
                break;
            case RIGHT:
                addX = 1;
                break;
            default:
                break;
        }
        addX *= speed;
        addY *= speed;
        x += addX;
        y += addY;
    }

    public abstract void update();//dành cho đối tượng di chuyển

    public abstract boolean testCollisions();

    public boolean testCollisionsWithDynamicObject() {
        return false;
    }

    public boolean canMove(int new_x, int new_y) {
        return testCollisionsWithDynamicObject();
    }
}
