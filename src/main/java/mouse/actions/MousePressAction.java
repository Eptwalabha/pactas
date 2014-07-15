package mouse.actions;

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
    public String getString() {
        return "2;0;" + button;
    }
}
