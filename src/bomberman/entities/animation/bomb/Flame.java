package bomberman.entities.animation.bomb;

import bomberman.BombermanGame;
import bomberman.entities.ObjectManager;
import bomberman.entities.unmoving.Brick;
import bomberman.entities.unmoving.Item;
import javafx.scene.image.Image;

public class Flame extends ChangeableObject {
    private long time;
    private Image[] imageOfLevel;
    public Flame(int x_grid, int y_grid, Status status) {
        super(x_grid, y_grid);
        imageOfLevel = new Image[3];
        if (status == Status.horizontal) {
            imageOfLevel[0] = getFxImage("/sprites/explosion_horizontal.png");
            imageOfLevel[1] = getFxImage("/sprites/explosion_horizontal1.png");
            imageOfLevel[2] = getFxImage("/sprites/explosion_horizontal2.png");
        } else if (status == Status.horizontal_left_last) {
            imageOfLevel[0] = getFxImage("/sprites/explosion_horizontal_left_last.png");
            imageOfLevel[1] = getFxImage("/sprites/explosion_horizontal_left_last1.png");
            imageOfLevel[2] = getFxImage("/sprites/explosion_horizontal_left_last2.png");
        } else if (status == Status.horizontal_right_last) {
            imageOfLevel[0] = getFxImage("/sprites/explosion_horizontal_right_last.png");
            imageOfLevel[1] = getFxImage("/sprites/explosion_horizontal_right_last1.png");
            imageOfLevel[2] = getFxImage("/sprites/explosion_horizontal_right_last2.png");
        } else if (status == Status.vertical) {
            imageOfLevel[0] = getFxImage("/sprites/explosion_vertical.png");
            imageOfLevel[1] = getFxImage("/sprites/explosion_vertical1.png");
            imageOfLevel[2] = getFxImage("/sprites/explosion_vertical2.png");
        } else if (status == Status.vertical_down_last) {
            imageOfLevel[0] = getFxImage("/sprites/explosion_vertical_down_last.png");
            imageOfLevel[1] = getFxImage("/sprites/explosion_vertical_down_last1.png");
            imageOfLevel[2] = getFxImage("/sprites/explosion_vertical_down_last2.png");
        } else {
            imageOfLevel[0] = getFxImage("/sprites/explosion_vertical_top_last.png");
            imageOfLevel[1] = getFxImage("/sprites/explosion_vertical_top_last1.png");
            imageOfLevel[2] = getFxImage("/sprites/explosion_vertical_top_last2.png");
        }
        time = System.currentTimeMillis();
        ObjectManager manager = BombermanGame.getInstance().getObjectManager();

        if (manager.getChangeableObject(x_grid, y_grid) instanceof Brick) {
            manager.removeChangeableObject(x_grid, y_grid);
        } else if (manager.getChangeableObject(x_grid, y_grid) instanceof Bomb) {
            ((Bomb) manager.getChangeableObject(x_grid, y_grid)).bum();
        } else if (manager.getChangeableObject(x_grid, y_grid) instanceof Item) {
            manager.removeChangeableObject(x_grid, y_grid);
        }
        manager.addObject(this);
    }

    public enum Status {
        horizontal, horizontal_left_last, horizontal_right_last, vertical, vertical_down_last, vertical_top_last
    }

    @Override
    public void render() {
        if (indexOfFrame >= 3) {
            BombermanGame.getInstance().getObjectManager().deleteObject(this);  //xoa flame
        } else {
            graphicsContext.drawImage(imageOfLevel[indexOfFrame], x, y, width, height);
            indexOfFrame++;
        }
    }
}
