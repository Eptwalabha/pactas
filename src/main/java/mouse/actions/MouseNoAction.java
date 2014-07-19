package mouse.actions;

import utility.GameWindow;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 19/07/2014
 * Time: 14:44
 */
public class MouseNoAction implements MouseAction {
    @Override
    public void process(Robot robot) {}

    @Override
    public String getString(GameWindow gameWindow) {
        return String.valueOf(ACTION_NONE);
    }

    @Override
    public void moveLocation(int x, int y) {}

    @Override
    public void setLocation(int x, int y) {}

    @Override
    public void setButton(int button) {}

    @Override
    public String getType() {
        return ACTION_NONE;
    }
}
