package bomberman.entities.unmoving;

import javafx.scene.image.Image;

/**
 * Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp
 */
public class SpeedItem extends StaticObject{
    private Image image = new Image(SpeedItem.class.getResource("/sprites/powerup_speed.png").toExternalForm());
    public SpeedItem(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x, y, width, height);
    }
}
