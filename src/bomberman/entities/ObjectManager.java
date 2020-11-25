package bomberman.entities;

import bomberman.entities.animation.Bomber;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.unmoving.StaticObject;

import java.util.ArrayList;

/**
 * class quản lí các đối tượng game.
 */
public class ObjectManager {
    private int height;
    private int width;
    //mảng lưu các đối tượng chuyển động.
    private ArrayList<DynamicObject> dynamicObjects = new ArrayList<>();
    private Bomber bomber;
    private ObjectsStack[][] staticObjects;

    public ArrayList<DynamicObject> getDynamicObjects() {
        return dynamicObjects;
    }

    public ObjectManager(int width, int height) {
        this.width = width;
        this.height = height;
        staticObjects = new ObjectsStack[height][width];
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
            //staticObjects[so.getY_grid()][so.getX_grid()] = so;
            int x = so.getX_grid();
            int y = so.getY_grid();
            if (staticObjects[y][x] == null) {
                staticObjects[y][x] = new ObjectsStack();
            }
            staticObjects[y][x].addStack(so);
        }
    }

    public ObjectsStack[][] getStaticObjects() {
        return staticObjects;
    }

    public Bomber getBomber() {
        return bomber;
    }

    public ArrayList<StaticObject> getStaticObjectInRec(int x, int y, int width, int height){
        int x_left, x_right, y_up, y_down;
        x_left = x / GameScene.SIZE;
        x_right = (x + width) / GameScene.SIZE;
        y_up = y / GameScene.SIZE;
        y_down = (y + height) / GameScene.SIZE;
        ArrayList<StaticObject> result = new ArrayList<>();
        result.add(staticObjects[y_up][x_left].getLast());
        result.add(staticObjects[y_down][x_left].getLast());
        result.add(staticObjects[y_up][x_right].getLast());
        result.add(staticObjects[y_down][x_right].getLast());
        return result;
    }
}
