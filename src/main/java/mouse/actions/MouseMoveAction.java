package mouse.actions;

import utility.GameWindow;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 19:24
 */
public class MouseMoveAction extends MouseAction {

    public MouseMoveAction(int x, int y) {
        super(x, y);
    }

    @Override
    public void process(Robot robot) {
        robot.mouseMove(location.x, location.y);
    }

    @Override
    public String getString(GameWindow gameWindow) {
        return gameWindow.getPercentageX(location.x) + ";" + gameWindow.getPercentageY(location.y);
    }

    @Override
    public void setButton(int button) {}

    @Override
    public String getType() {
        return ACTION_MOVE;
    }
}
