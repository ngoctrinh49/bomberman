package bomberman;

import bomberman.entities.MapLoader;
import bomberman.entities.ObjectManager;
import bomberman.entities.GameScene;
import bomberman.music.Player;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.Queue;

public class BombermanGame {
    private int height;
    private int width;
    protected static BombermanGame instance;
    private GameScene gameScene;
    public static ObjectManager objectManager;
    private MapLoader mapLoader;
    private Queue<KeyEvent> events = new LinkedList<>();
    private int level = 1;

    protected enum Status{
        END, PLAYING, LOAD_LEVEL;
    }

    private Status status;

    public BombermanGame() {
        status = Status.PLAYING;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static BombermanGame getInstance() {
        if (instance == null) {
            instance = new BombermanGame();
        }
        return instance;
    }

    public GameScene getScene() {
        return gameScene;
    }

    public void start(Stage primaryStage) {
        mapLoader = new MapLoader();
        mapLoader.loadMap(1);
        GameScene.SIZE = 36;
        objectManager = new ObjectManager(width, height);
        gameScene = new GameScene(objectManager, width, height);
        mapLoader.loadObject(objectManager);
        loadMusicOfGame();
        gameScene.setFocusTraversable(true);

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (events.isEmpty()) {
                    events.add(event);
                }
            }
        });

        VBox root = new VBox();
        root.getChildren().add(gameScene);
        Scene screen1 = new Scene(root);
        primaryStage.setScene(screen1);
    }

    public void update() {
        switch (status) {
            case END:
                gameScene.alert("Game Over!");
                if(!events.isEmpty()  && events.poll().getCode() == KeyCode.ENTER){
                    refresh();
                    status = Status.PLAYING;
                }
                break;
            case PLAYING:
                gameScene.update();
                break;
            case LOAD_LEVEL:
                gameScene.alert("Level " + level);
                if(!events.isEmpty()  && events.poll().getCode() == KeyCode.ENTER){
                    events = new LinkedList<>();
                    status = Status.PLAYING;
                }
                break;
        }
    }

    /**
     * ql cac doi tuong
     */
    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public Queue<KeyEvent> getEvents() {
        return events;
    }

    public void endGame() {
        status = Status.END;
    }

    public void loadLevel() {
        level++;
        mapLoader.loadMap(level);
        mapLoader.loadObject(objectManager);
        status = Status.LOAD_LEVEL;
    }

    public void refresh() {
        level = 1;
        events = new LinkedList<>();
        mapLoader.loadMap(level);
        mapLoader.loadObject(objectManager);
    }

    public void loadMusicOfGame() {
        if (objectManager.getBomber().getIsLiving()) {
            Player.playMusic(Player.musicOfGame);
        }
    }
}
