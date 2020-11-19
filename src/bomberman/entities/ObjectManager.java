package bomberman.entities;

import bomberman.entities.animation.Bomber;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.unmoving.StaticObject;

import java.util.ArrayList;

/**
 * class quản lí các đối tượng game.
 */
public class ObjectManager {
    private StaticObject[][] staticObjects;
    private int height;
    private int width;
    //mảng lưu các đối tượng chuyển động.
    private ArrayList<DynamicObject> dynamicObjects = new ArrayList<>();
    private Bomber bomber;

    public ArrayList<DynamicObject> getDynamicObjects() {
        return dynamicObjects;
    }

    public ObjectManager(int width, int height) {
        this.width = width;
        this.height = height;
        staticObjects = new StaticObject[height][width];
    }

    public void addObject(GameObject object) {
        if (object instanceof DynamicObject) {  //nêu đôi tượng thêm vào là có thể di chuyển
            DynamicObject object1 = (DynamicObject) object;
            dynamicObjects.add(object1);
            if (object instanceof Bomber) {     //nếu đối tượng được thêm vào là bomber
                bomber = (Bomber) object;
            }
        } else {
            StaticObject so = (StaticObject) object;    // đối tượng tĩnh
            staticObjects[so.getY_grid()][so.getX_grid()] = so;
        }
    }

    public StaticObject[][] getStaticObjects() {
        return staticObjects;
    }

    public Bomber getBomber() {
        return bomber;
    }
}
