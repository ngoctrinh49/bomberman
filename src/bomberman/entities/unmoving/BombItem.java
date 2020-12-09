package bomberman.entities.unmoving;

import bomberman.entities.animation.Bomber;
import bomberman.music.Player;
import javafx.scene.image.Image;

public class BombItem extends Item {
    private Image image = getFxImage("/sprites/powerup_bombs.png");
    public BombItem(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x , y, width, height);
    }

    @Override
    public boolean collide(Bomber bomber) {
        Bomber.maxNumberOfBomb++;
        Bomber.currentNumberOfBomb++;
        Player.playMusic(Player.eat_item);
        deleteObject();
        return true;
    }
}
