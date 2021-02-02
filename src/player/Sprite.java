package player;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;


    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    public void loadImage(String imageName) throws IOException {

        URL url = new URL(imageName);
        image = ImageIO.read(url);
    }

    public void specialAttackImage(String specialAttackImage) {
        ImageIcon ii = new ImageIcon(specialAttackImage);
        image = ii.getImage();
    }

    public void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
