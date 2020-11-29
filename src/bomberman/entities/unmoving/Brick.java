package bomberman.entities.unmoving;

import javafx.scene.image.Image;

/**
 * đá có thể phá hủy.
 */
public class Brick extends StaticObject {
    private Image image = getFxImage("/sprites/brick.png");

    public Brick(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x, y, width, height);
    }
}
