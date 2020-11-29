package bomberman.entities;

import bomberman.entities.animation.DynamicObject;
import bomberman.entities.animation.bomb.ChangeableObject;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class GameScene extends Canvas {
    public static int SIZE;
    public ObjectManager manager;

    public GameScene(ObjectManager objectManager, int width, int height) {
        super(width * SIZE, height * SIZE);
        manager = objectManager;
    }

    public void update() {
        getGraphicsContext2D().clearRect(0,0,getWidth(),getHeight());
        for (int i = 0; i < manager.getStaticObjects().length; i++) {
            for (int j = 0; j < manager.getStaticObjects()[i].length; j++) {
                 manager.getStaticObjects()[i][j].getLast().render();     //render đối tượng tĩnh
            }
        }
        for (int i = 0; i < manager.getDynamicObjects().size(); i++) {
            manager.getDynamicObjects().get(i).update();        //render đối tượng động
        }
        checkDynamicObjectCollide();
    }

    public void checkDynamicObjectCollide() {
        ArrayList<DynamicObject> dynamicObjects = manager.getDynamicObjects();
        for (int i = 0; i < dynamicObjects.size(); i++) {
            for (int j = i + 1; j < dynamicObjects.size(); j++) {
                dynamicObjects.get(i).onActionCollideEnemy(dynamicObjects.get(j));
            }
        }
    }


}
