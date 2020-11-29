package bomberman.entities.unmoving;

import bomberman.BombermanGame;
import bomberman.entities.GameObject;
import bomberman.entities.GameScene;
import bomberman.entities.animation.bomb.Bomb;

public abstract class StaticObject extends GameObject {
    protected int x_grid;
    protected int y_grid;

    public StaticObject(int x_grid, int y_grid) {
        this.x_grid = x_grid;
        this.y_grid = y_grid;
        x = x_grid * GameScene.SIZE;
        y = y_grid * GameScene.SIZE;
        height = GameScene.SIZE;
        width = GameScene.SIZE;
    }

    public int getX_grid() {
        return x_grid;
    }

    public int getY_grid() {
        return y_grid;
    }

    public abstract void render();//dành cho đối tượng tĩnh

    /**
     * pt xóa các đối tượng tĩnh.
     */
    public void deleteObject() {
        BombermanGame.getInstance().getObjectManager().deleteObject(this);
    }
}
