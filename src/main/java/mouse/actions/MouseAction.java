package mouse.actions;

import utility.GameWindow;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 21:42
 */
public abstract class MouseAction {
    public static final String ACTION_MOVE = "1";
    public static final String ACTION_PRESS = "2";
    public static final String ACTION_RELEASE = "3";

    protected Point location;

    public MouseAction() {
        location = new Point();
    }

    public MouseAction(int x, int y) {
        location = new Point(x, y);
    }

    public abstract void process(Robot robot);

    public abstract String getString(GameWindow gameWindow);

    public void moveLocation(int x, int y) {
        location.x += x;
        location.y += y;
    }

    public void setLocation(int x, int y) {
        location.x = x;
        location.y = y;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public abstract void setButton(int button);

    public abstract String getType();
}
