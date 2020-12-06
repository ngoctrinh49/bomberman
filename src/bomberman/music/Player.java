package bomberman.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Player {
    public static String moveOfBomber = "res/music/foot_short.wav";
    public static String bombExplode = "res/music/bomb_explode.wav";
    public static String eat_item = "res/music/eat_item.wav";
    public static String load_level = "res/music/next_level.wav";
    public static String bomber_died = "res/music/died.wav";
    public static String make_bomb = "res/music/make_bomb.wav";
    public static String dead_of_enemy = "res/music/deathOfEnemy.wav" ;
    public static String musicOfGame = "res/music/nhac_nen.wav";

    public static void playMusic(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
