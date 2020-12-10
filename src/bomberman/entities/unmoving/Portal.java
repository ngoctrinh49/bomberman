package bomberman.entities.unmoving;

import bomberman.BombermanGame;
import bomberman.entities.animation.Bomber;
import bomberman.music.Player;
import javafx.scene.image.Image;

/**
 * đối tượng qua màn.
 */
public class Portal extends Item {
    private Image image = getFxImage("/sprites/portal.png");
    public Portal(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x, y, width, height);
    }

    @Override
    public boolean collide(Bomber bomber) {
        int numberOfEnemy = BombermanGame.getInstance().getObjectManager().getNumberOfEnemy();
        System.out.println(numberOfEnemy);
        if (numberOfEnemy == 0) {
            Player.playMusic(Player.load_level);
            deleteObject();
            BombermanGame.getInstance().loadLevel();
            return true;
        }
        return false;
    }
}
