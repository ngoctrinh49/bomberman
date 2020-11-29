package bomberman.entities.animation.bomb;

import bomberman.entities.unmoving.StaticObject;
import javafx.scene.image.Image;

public abstract class ChangeableObject extends StaticObject {
    protected Image[][] images;
    protected int indexOfFrame = 0;

    public ChangeableObject(int x_grid, int y_grid) {
        super(x_grid, y_grid);
    }

    @Override
    public abstract void render();
}
