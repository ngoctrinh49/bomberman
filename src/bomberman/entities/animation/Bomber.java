package bomberman.entities.animation;

import bomberman.BombermanGame;
import bomberman.entities.GameScene;
import bomberman.entities.animation.bomb.Bomb;
import bomberman.entities.unmoving.Item;
import bomberman.entities.unmoving.StaticObject;
import bomberman.music.Player;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import bomberman.entities.animation.enemies.Enemy;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Bomber extends DynamicObject {
    //khởi tạo khi add
    public static int maxNumberOfBomb = 1;    //số lượng bom bomber có thể mang.
    public static int currentNumberOfBomb = 1;
    public static int levelOfBomb = 1;
    public Bomber(int x_pixel, int y_pixel) {
        transition = Transition.RIGHT;
        images = new Image[4][];
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
            image[0] = getFxImage("/sprites/player_up.png");
            image[1] = getFxImage("/sprites/player_up_1.png");
            image[2] = getFxImage("/sprites/player_up_2.png");
        } else if (t == Transition.LEFT) {
            image[0] = getFxImage("/sprites/player_left.png");
            image[1] = getFxImage("/sprites/player_left_1.png");
            image[2] = getFxImage("/sprites/player_left_2.png");
        } else if (t == Transition.DOWN) {
            image[0] = getFxImage("/sprites/player_down.png");
            image[1] = getFxImage("/sprites/player_down_1.png");
            image[2] = getFxImage("/sprites/player_down_2.png");
        } else {
            image[0] = getFxImage("/sprites/player_right.png");
            image[1] = getFxImage("/sprites/player_right_1.png");
            image[2] = getFxImage("/sprites/player_right_2.png");
        }
        //}
    }

    /**
     * pt xử lý bàn phím.
     */
    public void onKeyEvent(KeyEvent event) {
        if (event == null) {
            return;
        }
        switch (event.getCode()) {
            case RIGHT:
                move(Transition.RIGHT);
                Player.playMusic(Player.moveOfBomber);
                break;
            case DOWN:
                move(Transition.DOWN);
                Player.playMusic(Player.moveOfBomber);
                break;
            case LEFT:
                move(Transition.LEFT);
                Player.playMusic(Player.moveOfBomber);
                break;
            case UP:
                move(Transition.UP);
                Player.playMusic(Player.moveOfBomber);
                break;
            case SPACE:
                placeBomb(x, y);
                Player.playMusic(Player.moveOfBomber);
                break;
        }
    }

    @Override
    public void update() {
        KeyEvent event = BombermanGame.getInstance().getEvents().poll();
        if (!isLiving) {
            BombermanGame.getInstance().endGame();
        } else {
            if (event == null || event.getCode() == KeyCode.SPACE) {
                checkCanMoveThrough(x, y);
            }
            onKeyEvent(event);
        }
        int currentDirection = transition.getDirection();
        int currentImage = indexOfFrame % (images[transition.getDirection()].length * 4) / 4;
        graphicsContext.drawImage(images[currentDirection][currentImage], x, y, width, height);
    }

    public void kill() {
        isLiving = false;
        Player.playMusic(Player.bomber_died);
    }

    /**
     *  pt kiểm tra bomber va chạm với enemy.
     */
    public boolean onActionCollideEnemy(DynamicObject dynamicObject) {
        Rectangle2D bomber = new Rectangle(x, y, width, height);
        Rectangle2D enemy = new Rectangle(dynamicObject.getX(), dynamicObject.getY(), dynamicObject.getWidth(), dynamicObject.getHeight());
        if (((Rectangle) bomber).intersects(enemy)) {
            if (dynamicObject instanceof Enemy) {
                kill();
            }
            return true;
        } else {
            return false;
        }
    }

    public void placeBomb(int x, int y) {
        if (Bomber.currentNumberOfBomb > 0 && Bomber.currentNumberOfBomb <= Bomber.maxNumberOfBomb) {
            new Bomb((x + width / 2) / GameScene.SIZE, (y + height / 2) / GameScene.SIZE, levelOfBomb);
            Bomber.currentNumberOfBomb--;
            Player.playMusic(Player.make_bomb);
        }
        System.out.println("current:" + currentNumberOfBomb + "\n" + "max:" + maxNumberOfBomb + "\n");
    }

    /**
     * pt xử lí bomber ăn item.
     */
    @Override
    public boolean checkCanMoveThrough(int x, int y) {
        ArrayList<StaticObject> objects = manager.getStaticObjectInRec(x, y, width, height);
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof Item) {
                return ((Item) objects.get(i)).collide(this);
            }
        }
        return super.checkCanMoveThrough(x, y);
    }

    public boolean getIsLiving() {
        return isLiving;
    }
}
