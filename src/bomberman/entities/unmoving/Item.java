package bomberman.entities.unmoving;

import bomberman.entities.animation.Bomber;
import javafx.scene.image.Image;

/**
 * đối tượng bị ẩn dưới brick.
 */
public abstract class Item extends StaticObject {
    protected boolean isHiding;
    protected Brick brick;
    protected Image image;

    public Item(int x_grid, int y_grid) {
        super(x_grid, y_grid);
        isHiding = true;
        brick = new Brick(x_grid, y_grid);
    }

    @Override
    public void render() {
        graphicsContext.drawImage(image, x, y, width, height);
    }

    /**
     * pt xử lí bomber ăn item
     */
    public abstract boolean collide(Bomber bomber);

}
