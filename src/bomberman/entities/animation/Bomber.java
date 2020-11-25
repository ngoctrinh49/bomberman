package bomberman.entities.animation;

import bomberman.BombermanGame;
import bomberman.entities.GameScene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Bomber extends DynamicObject {
    //khởi tạo khi add
    public Bomber(int x_pixel, int y_pixel) {
        transition = Transition.RIGHT;
        images = new Image[4][];    //mảng lưu các hình ảnh khi di chuyển, ví dụ: LEFT sẽ có 3 ảnh để mượt hơn.
        isMoving = false;
        speed = 5;
        for (Transition t : Transition.values()) {
            Image[] images1 = new Image[3];
            findTransition(images1, t);
            images[t.getDirection()] = images1;
        }
        x = x_pixel;
        y = y_pixel;
        width = GameScene.SIZE;
        height = GameScene.SIZE;
    }

    /**
     * pt load ảnh hướng đi của bomber.
     */
    public void findTransition(Image [] image, Transition t) {
        //for (Transition t : Transition.values()) {
            if (t == Transition.UP) {
                image[0] = new Image(getClass().getResource("/sprites/player_up.png").toExternalForm());
                image[1] = new Image(getClass().getResource("/sprites/player_up_1.png").toExternalForm());
                image[2] = new Image(getClass().getResource("/sprites/player_up_2.png").toExternalForm());
            } else if (t == Transition.LEFT) {
                image[0] = new Image(getClass().getResource("/sprites/player_left.png").toExternalForm());
                image[1] = new Image(getClass().getResource("/sprites/player_left_1.png").toExternalForm());
                image[2] = new Image(getClass().getResource("/sprites/player_left_2.png").toExternalForm());
            } else if (t == Transition.DOWN) {
                image[0] = new Image(getClass().getResource("/sprites/player_down.png").toExternalForm());
                image[1] = new Image(getClass().getResource("/sprites/player_down_1.png").toExternalForm());
                image[2] = new Image(getClass().getResource("/sprites/player_down_2.png").toExternalForm());
            } else {
                image[0] = new Image(getClass().getResource("/sprites/player_right.png").toExternalForm());
                image[1] = new Image(getClass().getResource("/sprites/player_right_1.png").toExternalForm());
                image[2] = new Image(getClass().getResource("/sprites/player_right_2.png").toExternalForm());
            }
        //}
    }

    /**
     * pt xử lý bàn phím.
     */
    public void onKeyEvent(KeyEvent event) {
        if (event == null) {
            indexOfFrame = 0;
            return;
        }
        switch (event.getCode()) {
            case RIGHT:
                move(Transition.RIGHT);
                break;
            case DOWN:
                move(Transition.DOWN);
                break;
            case LEFT:
                move(Transition.LEFT);
                break;
            case UP:
                move(Transition.UP);
                break;
            default:
                isMoving = false;
                break;
        }
    }

    @Override
    public void update() {
        KeyEvent event = BombermanGame.getInstance().getEvents().poll();
        onKeyEvent(event);
        int currentDirection = transition.getDirection();
        int currentImage = indexOfFrame % images[transition.getDirection()].length;
        graphicsContext.drawImage(images[currentDirection][currentImage], x, y, width, height);

    }


    public boolean checkCanMoveThrough(int x, int y) {
        return super.checkCanMoveThrough(x, y);
    }
}
