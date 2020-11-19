package bomberman.entities;

import bomberman.BombermanGame;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected GraphicsContext graphicsContext = BombermanGame.getInstance().getScene().getGraphicsContext2D();
}
