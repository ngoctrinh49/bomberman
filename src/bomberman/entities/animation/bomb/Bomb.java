package bomberman.entities.animation.bomb;

import bomberman.BombermanGame;
import bomberman.entities.ObjectManager;
import bomberman.entities.unmoving.Wall;
import javafx.scene.image.Image;

public class Bomb extends ChangeableObject {
    private static final int TIME = 2000;
    private boolean isExploded = false;
    private int level;  //muc do no cua bom
    private int indexOfFrame = 0;
    private long timeToExploded;
    //ObjectManager manager = BombermanGame.getInstance().getObjectManager();

    public Bomb(int x, int y, int level) {
        super(x, y);
        this.level = level;
        timeToExploded =(System.currentTimeMillis() + TIME);
        images = new Image[2][3];
        images[0][0] = getFxImage("/sprites/bomb.png");
        images[0][1] = getFxImage("/sprites/bomb_1.png");
        images[0][2] = getFxImage("/sprites/bomb_2.png");
        images[1][0] = getFxImage("/sprites/bomb_exploded.png");
        images[1][1] = getFxImage("/sprites/bomb_exploded1.png");
        images[1][2] = getFxImage("/sprites/bomb_exploded2.png");
        BombermanGame.getInstance().getObjectManager().addObject(this);
    }

    @Override
    public void render() {
        indexOfFrame++;
        if (!isExploded && timeToExploded < System.currentTimeMillis()) {
            isExploded = true;
            indexOfFrame = 0;
            explode();
        }
        if (isExploded) {
            graphicsContext.drawImage(images[1][(indexOfFrame % 12) / 4], x, y, width, height);
        } else {
            graphicsContext.drawImage(images[0][(indexOfFrame % 12) / 4], x, y, width, height);
        }
        if (isExploded && indexOfFrame >= 6) {
            BombermanGame.getInstance().getObjectManager().deleteObject(this);  // xóa hình ảnh nổ trung tâm
        }
    }

    public void explode() {
        ObjectManager manager = BombermanGame.getInstance().getObjectManager();
        for (int i = x_grid + 1; i <= x_grid + level; i++){
            if(manager.getChangeableObject(i, y_grid) instanceof Wall) {
                i--;
                if(i != x_grid && manager.getChangeableObject(i, y_grid) != null){
                    new Flame(i , y_grid, Flame.Status.horizontal_right_last);
                }
                break;
            }
            if(i == x_grid + level) {
                new Flame(i, y_grid, Flame.Status.horizontal_right_last);
            } else {
                new Flame(i, y_grid, Flame.Status.horizontal);
            }
        }

        for (int i = y_grid + 1; i <= y_grid + level; i++) {
            if(manager.getChangeableObject(x_grid, i) instanceof Wall) {
                i--;
                if(i != y_grid && manager.getChangeableObject(x_grid, i ) != null) {
                    new Flame(x_grid, i, Flame.Status.vertical_down_last);
                }
                break;
            }
            if(i == y_grid + level) {
                new Flame(x_grid, i, Flame.Status.vertical_down_last);
            } else {
                new Flame(x_grid, i, Flame.Status.vertical);
            }
        }

        for (int i = x_grid - 1; i >= x_grid - level; i--) {
            if(manager.getChangeableObject(i, y_grid) instanceof Wall) {
                i++;
                if(i != x_grid && manager.getChangeableObject(i, y_grid) != null) {
                    new Flame(i, y_grid, Flame.Status.horizontal_left_last);
                }
                break;
            }
            if(i == x_grid - level) {
                new Flame(i, y_grid, Flame.Status.horizontal_left_last);
            } else {
                new Flame(i, y_grid, Flame.Status.horizontal);
            }
        }

        for (int i = y_grid - 1; i >= y_grid - level; i--) {
            if(manager.getChangeableObject(x_grid, i) instanceof Wall) {
                i++;
                if(i != y_grid && manager.getChangeableObject(x_grid, i) != null ) {
                    new Flame(x_grid, i, Flame.Status.vertical_top_last);
                }
                break;
            }
            if(i == y_grid - level){
                new Flame(x_grid, i, Flame.Status.vertical_top_last);
            } else {
                new Flame(x_grid, i, Flame.Status.vertical);
            }
        }
    }

    public boolean isExploded() {
        return isExploded;
    }
}
