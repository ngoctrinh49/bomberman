package bomberman.entities.unmoving;

import javafx.scene.image.Image;

/**
 * đối tượng cỏ.
 */
public class Grass extends StaticObject {
    private static Image image = new Image(Grass.class.getResource("/sprites/grass.png").toExternalForm());
    public Grass(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x, y, width, height);
    }
}
