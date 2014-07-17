package mouse.actions;

import utility.GameWindow;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 19:24
 */
public class MouseMoveAction implements MouseAction {
    private int x;
    private int y;

    public MouseMoveAction(int x, int y) {
        setLocation(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void process(Robot robot) {
        robot.mouseMove(x, y);
    }

    @Override
    public String getString(GameWindow gameWindow) {
        return "1;0;" + gameWindow.getPercentageX(x) + ";" + gameWindow.getPercentageY(y);
    }

    @Override
    public void moveLocation(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setButton(int button) {}
}
