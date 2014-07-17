package utility;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 17/07/2014
 * Time: 00:26
 */
public class GameWindow extends Rectangle {

    public GameWindow(int width, int height) {
        super(width, height);
    }

    public GameWindow(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPositionX(float percentX) {
        return x + (int) (width * percentX);
    }

    public int getPositionY(float percentY) {
        return y + (int) (height * percentY);
    }

    public float getPercentageX(int positionX) {
        return (positionX - x) / (float) width;
    }

    public float getPercentageY(int positionY) {
        return (positionY - y) / (float) height;
    }
}
