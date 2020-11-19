package bomberman.entities.unmoving;

import javafx.scene.image.Image;

/**
 * đối tượng qua màn.
 */
public class Portal extends StaticObject {
    private Image image = new Image(Portal.class.getResource("/sprites/portal.png").toExternalForm());
    public Portal(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x, y, width, height);
    }
}
