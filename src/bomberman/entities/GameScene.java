package bomberman.entities;

import javafx.scene.canvas.Canvas;

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
                 manager.getStaticObjects()[i][j].render();     //render đối tượng tĩnh
            }
        }
        for (int i = 0; i < manager.getDynamicObjects().size(); i++) {
            manager.getDynamicObjects().get(i).update();        //render đối tượng động
        }
    }
}
