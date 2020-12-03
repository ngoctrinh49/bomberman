package bomberman.entities.animation;

import bomberman.BombermanGame;
import bomberman.entities.GameObject;
import bomberman.entities.GameScene;
import bomberman.entities.ObjectManager;
import bomberman.entities.animation.bomb.Bomb;
import bomberman.entities.animation.bomb.Flame;
import bomberman.entities.unmoving.Brick;
import bomberman.entities.unmoving.StaticObject;
import bomberman.entities.unmoving.Wall;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class DynamicObject extends GameObject {
    protected Transition transition = Transition.RIGHT;
    protected int speed = 1;
    protected Image[][] images;
    protected boolean isMoving;
    protected int indexOfFrame = 0;
    protected ObjectManager manager = BombermanGame.getInstance().getObjectManager();
    protected boolean isLiving = true;

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
        if (x_new == 0) {
            x_new = findCoordinate(x);
            y_new += y;
        } else {
            y_new = findCoordinate(y);
            x_new += x;
        }
        isMoving = canMove(x_new, y_new);
        if (isMoving) {
            x = x_new;
            y = y_new;
        }
    }

    public abstract void update();//dành cho đối tượng di chuyển

    public boolean canMove(int new_x, int new_y) {
        return checkCanMoveThrough(new_x, new_y);
    }

    public abstract void kill();
    /**
     * pt kiểm tra đối tượng có di chuyển qua Wall được không.
     */
    public boolean checkCanMoveThrough(int x, int y) {
        ArrayList<StaticObject> staticObjects = manager.getStaticObjectInRec(x, y, width -1, height -1);
        for (int i = 0; i < staticObjects.size(); i++) {
            StaticObject object = staticObjects.get(i);
            if (object instanceof Wall || object instanceof Brick) {
                return false;
            }
            if (object instanceof Flame) {
                kill();
                return true;    //enermy bị giết thì có thể đi qua.
            }
            if (object instanceof Bomb) {   //nếu bomber đứng chính giữa bom nổ
                if (((Bomb) object).isExploded()) {
                    kill();
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * update toa do moi.
     */
    public int findCoordinate(int center){
        return (center + GameScene.SIZE / 2) / GameScene.SIZE * GameScene.SIZE;
    }

    public abstract boolean onActionCollideEnemy(DynamicObject dynamicObject);

}