package bomberman.entities.unmoving;

import javafx.scene.image.Image;

public class BombItem extends StaticObject {
    private Image image = getFxImage("/sprites/powerup_bombs.png");
    public BombItem(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x , y, width, height);
    }
}
