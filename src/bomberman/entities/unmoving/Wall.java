package bomberman.entities.unmoving;

import javafx.scene.image.Image;

/**
 * đối tượng tường không thể phá hủy.
 */
public class Wall extends StaticObject {
    private static Image image = new Image(Wall.class.getResource("/sprites/wall.png").toExternalForm());
    public Wall(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x, y, width, height);
    }
}
