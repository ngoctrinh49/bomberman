package bomberman.entities.unmoving;

import bomberman.entities.animation.Bomber;
import bomberman.music.Player;
import javafx.scene.image.Image;

/**
 * Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp
 */
public class SpeedItem extends Item {
    private Image image = getFxImage("/sprites/powerup_speed.png");
    public SpeedItem(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x, y, width, height);
    }

    @Override
    public boolean collide(Bomber bomber) {
        bomber.speed += 5;
        Player.playMusic(Player.eat_item);
        deleteObject();
        return true;
    }
}
