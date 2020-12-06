package bomberman.entities.unmoving;

import bomberman.entities.animation.Bomber;
import bomberman.music.Player;
import javafx.scene.image.Image;

/**
 *  Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn).
 */
public class FlameItem extends Item {
    private Image image = getFxImage("/sprites/powerup_flames.png");
    public FlameItem(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x, y, width, height);
    }

    @Override
    public boolean collide(Bomber bomber) {
        Bomber.levelOfBomb++;
        Player.playMusic(Player.eat_item);
        deleteObject();
        return true;
    }
}
