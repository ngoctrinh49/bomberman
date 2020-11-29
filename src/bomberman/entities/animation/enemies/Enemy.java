package bomberman.entities.animation.enemies;

import bomberman.BombermanGame;
import bomberman.entities.GameScene;
import bomberman.entities.animation.Bomber;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.Transition;
import bomberman.entities.animation.bomb.Bomb;
import bomberman.entities.animation.bomb.ChangeableObject;
import bomberman.entities.animation.bomb.Flame;
import bomberman.entities.animation.enemies.ai.AI;
import javafx.scene.image.Image;

import java.awt.geom.Rectangle2D;
import java.awt.*;

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
            int currentDirection = transition.getDirection();
            int currentImage = indexOfFrame % images[transition.getDirection()].length;
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

    public void kill() {
        isLiving = false;
    }
}