package bomberman.entities;

import bomberman.BombermanGame;
import bomberman.entities.animation.Bomber;
import bomberman.entities.animation.enemies.Balloom;
import bomberman.entities.animation.enemies.Oneal;
import bomberman.entities.unmoving.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapLoader {
    public static int countEnemy = 0;
    private char map[][];
    private int height;
    private int width;

    /**
     * pt đọc kí tự từ level.
     * @param level level đang chơi
     */
    public void loadMap(int level) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(getClass().getResource("/levels/Level1.txt").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        level = scanner.nextInt();
        height = scanner.nextInt();
        width = scanner.nextInt();
        scanner.nextLine();
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            String readLine = scanner.nextLine();
            for (int j = 0; j < width; j++) {
                map[i][j] = readLine.charAt(j);
            }
        }
        BombermanGame.getInstance().setHeight(height);
        BombermanGame.getInstance().setWidth(width);
    }

    public void loadObject(ObjectManager objectManager) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (map[y][x]) {
                    case '#':
                        objectManager.addObject(new Wall(x, y));
                        break;
                    case 'p':
                        objectManager.addObject(new Bomber(x * GameScene.SIZE, y * GameScene.SIZE));    //đặt đúng vị trí
                        objectManager.addObject(new Grass(x, y));
                        break;
                    case 'x':
                        objectManager.addObject(new Grass(x, y));
                        objectManager.addObject(new Portal(x, y));
                        objectManager.addObject(new Brick(x, y));
                        break;
                    case 's':
                        objectManager.addObject(new Grass(x, y));
                        objectManager.addObject(new SpeedItem(x, y));
                        objectManager.addObject(new Brick(x, y));
                        break;
                    case 'f':
                        objectManager.addObject(new Grass(x, y));
                        objectManager.addObject(new FlameItem(x, y));
                        objectManager.addObject(new Brick(x, y));
                        break;
                    case 'b':
                        objectManager.addObject(new Grass(x, y));
                        objectManager.addObject(new BombItem(x, y));
                        objectManager.addObject(new Brick(x, y));
                        break;
                    case '*':
                        objectManager.addObject(new Grass(x, y));
                        objectManager.addObject(new Brick(x, y));
                        break;
                    case '1':
                        objectManager.addObject(new Balloom(x * GameScene.SIZE, y * GameScene.SIZE));
                        objectManager.addObject(new Grass(x, y));
                        countEnemy += 1;
                        break;
                    case '2':
                        objectManager.addObject(new Oneal(x * GameScene.SIZE, y * GameScene.SIZE, objectManager));
                        objectManager.addObject(new Grass(x, y));
                        countEnemy += 1;
                        break;
                    default:
                        objectManager.addObject(new Grass(x,y));
                        break;
                }
            }
        }
    }
}
