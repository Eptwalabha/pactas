package mouse.actions;

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
    public String getString() {
        return "3;0;" + button;
    }
}
