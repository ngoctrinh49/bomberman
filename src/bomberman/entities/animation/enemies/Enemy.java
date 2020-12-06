package bomberman.entities.animation.enemies;

import bomberman.BombermanGame;
import bomberman.entities.GameScene;
import bomberman.entities.ObjectManager;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.Transition;
import bomberman.entities.animation.enemies.ai.AI;
import javafx.scene.image.Image;

public abstract class Enemy extends DynamicObject {
    private final int SPEED = 1;
    protected AI ai;

    public Enemy(int x_pixel, int y_pixel) {
        images = new Image[5][];
        x = x_pixel;
        y = y_pixel;
        width = GameScene.SIZE;
        height = GameScene.SIZE;
        speed = SPEED;
        transition = Transition.RIGHT;
        isMoving = false;
        isLiving = true;
    }

    public boolean getIsMoving(){
        return isMoving;
    }

    /**
     * pt render ảnh động cho các enemy.
     */
    @Override
    public void update() {
        Transition new_transition = moveEnemy();
        if (new_transition != transition) {
            transition = new_transition;
        }
        //nếu enemy chết.
        if (!isLiving) {
            graphicsContext.drawImage(images[4][0], x, y, width, height);           //load ảnh enemy died.
            BombermanGame.getInstance().getObjectManager().deleteObject(this);      //xóa enemy sau khi chết.
        } else {
            move(transition);   //tạo chuyển động cho các enemy.
            int currentDirection = 0;
            if (isMoving) {
                currentDirection = transition.getDirection();
            }
            int currentImage = indexOfFrame % (images[transition.getDirection()].length * 4) / 4;
            graphicsContext.drawImage(images[currentDirection][currentImage], x, y, width, height);
        }
    }

    public Transition moveEnemy() {
        int transition = ai.calculateDirection();
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
        return Transition.UP;
    }

    @Override
    public void move(Transition transition) {
        if(isMoving) {
            if (transition == this.transition) {
                indexOfFrame++;
            } else {
                indexOfFrame = 0;
                this.transition = transition;
            }
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
            if (x == findCoordinate(x)) {
                x_new = findCoordinate(x);
                y_new += y;
            }
        } else {
            if (y == findCoordinate(y)) {
                y_new = findCoordinate(y);
                x_new += x;
            }
        }
        isMoving = canMove(x_new, y_new);
        if (isMoving) {
            x = x_new;
            y = y_new;
        }
    }

    public void kill() {
        int numberOfEnemy = BombermanGame.getInstance().getObjectManager().getNumberOfEnemy();
        numberOfEnemy--;
        BombermanGame.getInstance().getObjectManager().setNumberOfEnemy(numberOfEnemy);
        isLiving = false;
    }
}