package mouse.actions;

import utility.GameWindow;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 19:28
 */
public class MouseReleaseAction implements MouseAction {
    private int button;

    public MouseReleaseAction(int button) {
        this.button = button;
    }

    public int getButton() {
        return button;
    }

    @Override
    public void process(Robot robot) {
        robot.mouseRelease(button);
    }

    @Override
    public String getString(GameWindow gameWindow) {
        return "3;0;" + button;
    }

    @Override
    public void moveLocation(int x, int y) {}

    @Override
    public void setLocation(int x, int y) {}

    @Override
    public void setButton(int button) {
        this.button = button;
    }
}
