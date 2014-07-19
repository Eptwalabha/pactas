package mouse.actions;

import utility.GameWindow;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 19:28
 */
public class MousePressAction implements MouseAction {
    private int button;

    public MousePressAction(int button) {
        this.button = button;
    }

    public int getButton() {
        return button;
    }

    @Override
    public void process(Robot robot) {
        robot.mousePress(button);
    }

    @Override
    public String getString(GameWindow gameWindow) {
        return String.valueOf(button);
    }

    @Override
    public void moveLocation(int x, int y) {}

    @Override
    public void setLocation(int x, int y) {}

    @Override
    public void setButton(int button) {
        this.button = button;
    }

    @Override
    public String getType() {
        return ACTION_PRESS;
    }
}
