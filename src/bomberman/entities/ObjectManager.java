package bomberman.entities;

import bomberman.entities.animation.Bomber;
import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.enemies.Enemy;
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
    private int numberOfEnemy;

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
            } else if (object instanceof Enemy) {
                numberOfEnemy++;
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

    /**
     * tạo mảng lưu đối tượng để kiểm tra collection với static objects.
     */
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

    public StaticObject getChangeableObject(int x_grid, int y_grid) {
        if (x_grid < 0 || y_grid < 0 || x_grid > width || y_grid > height) {
            return null;
        }
        return staticObjects[y_grid][x_grid].getLast();
    }

    public void removeChangeableObject(int x_grid, int y_grid) {
        staticObjects[y_grid][x_grid].removeLast();
    }

    /**
     * pt xóa đối tuọng
     */
    public void deleteObject(GameObject object) {
        if (object instanceof DynamicObject) {
            dynamicObjects.remove(object);
        }
        if (object instanceof StaticObject) {
            StaticObject object1 = (StaticObject) object;
            int x = object1.getX_grid();
            int y = object1.getY_grid();
            staticObjects[y][x].delete(object1);
        }
    }

    public void setNumberOfEnemy(int numberOfEnemy) {
        this.numberOfEnemy = numberOfEnemy;
    }

    public int getNumberOfEnemy() {
        return numberOfEnemy;
    }

    /**
     * pt load lại đối tượng khi qua màn chơi.
     */
    public void refresh() {
        staticObjects = new ObjectsStack[height][width];
        dynamicObjects = new ArrayList<>();
        numberOfEnemy = 0;
    }
}
