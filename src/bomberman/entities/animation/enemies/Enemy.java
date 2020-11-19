package bomberman.entities.animation.enemies;

import bomberman.entities.GameScene;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.Transition;
import javafx.scene.image.Image;

public abstract class Enemy extends DynamicObject {
    private final int SPEED = 1;

    public Enemy(int x_pixel, int y_pixel) {
        images = new Image[4][];
        x = x_pixel;
        y = y_pixel;
        width = GameScene.SIZE;
        height = GameScene.SIZE;
        speed = SPEED;
        transition = Transition.RIGHT;
        isMoving = false;
    }

    public abstract Transition moveEnemy();  //pt chuyển động riêng của enermy

    /**
     * pt render ảnh động cho các enemy.
     */
    @Override
    public void update() {
        Transition new_transition = moveEnemy();
        if (new_transition != transition) {
            indexOfFrame = 0;
            transition = new_transition;
        }
        move(transition);   //tạo chuyển động cho các enemy.
        int currentDirection = transition.getDirection();
        int currentImage = indexOfFrame % images[transition.getDirection()].length;
        graphicsContext.drawImage(images[currentDirection][currentImage], x, y, width, height);
    }

    /**
     * pt kiểm tra va chạm.
     */
    @Override
    public boolean testCollisions() {
        return false;
    }
}
