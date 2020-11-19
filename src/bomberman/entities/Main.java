package bomberman.entities;

import bomberman.BombermanGame;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private BombermanGame bombermanGame;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bomberman Game");
        bombermanGame = BombermanGame.getInstance();
        bombermanGame.start(primaryStage);
        primaryStage.show();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                bombermanGame.update();
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
