package bomberman.entities;

import bomberman.BombermanGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected GraphicsContext graphicsContext = BombermanGame.getInstance().getScene().getGraphicsContext2D();

    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    public int[] pixels;

    public Image getFxImage(String path) {
        setPixel(path);
        WritableImage wr = new WritableImage(16, 16);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                if ( pixels[x + y * 16] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                }
                else {
                    pw.setArgb(x, y, pixels[x + y * 16]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, 1);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }

    private void setPixel(String path)  {
        pixels = new int[32 * 32];
        try {
            URL a = GameObject.class.getResource(path);
            BufferedImage image = ImageIO.read(a);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
