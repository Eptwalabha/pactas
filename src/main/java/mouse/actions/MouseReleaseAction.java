package mouse.actions;

import utility.GameWindow;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 19:28
 */
public class MouseReleaseAction extends MouseAction {
    private int button;

    public MouseReleaseAction(int x, int y, int button) {
        super(x, y);
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
        return gameWindow.getPercentageX(location.x) + ";" + gameWindow.getPercentageY(location.y) + ";" + String.valueOf(button);
    }

    @Override
    public void setButton(int button) {
        this.button = button;
    }

    @Override
    public String getType() {
        return ACTION_RELEASE;
    }
}
