package bomberman.entities;

import bomberman.entities.animation.DynamicObject;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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

    public void alert(String s) {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, getWidth(), getHeight());
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFill(Color.RED);
        double sizeOfAlert = getHeight() / 12;
        double x_alert = getWidth() / 2;
        double y_alert = getHeight() / 2;
        graphicsContext.setFont(new Font(sizeOfAlert));
        graphicsContext.fillText(s, x_alert, y_alert);
    }

}
