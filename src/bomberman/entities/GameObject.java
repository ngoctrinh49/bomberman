package bomberman.entities;

import bomberman.BombermanGame;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected GraphicsContext graphicsContext = BombermanGame.getInstance().getScene().getGraphicsContext2D();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
