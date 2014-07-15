import mouse.*;
import mouse.actions.MouseMoveAction;
import mouse.actions.MousePressAction;
import mouse.actions.MouseReleaseAction;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.MouseEvent;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

/**
 * User: Eptwalabha
 * Date: 05/07/2014
 * Time: 18:15
 */
public class MouseNodeTest {

    private RobotMock robot;

    @Before
    public void setUp() throws AWTException {
        this.robot = new RobotMock();
    }

    @Test
    public void canSaveAMouseMoveInAMouseMoveAction() {
        MouseMoveAction mouseMoveAction = new MouseMoveAction(10, 200);
        assertThat(mouseMoveAction.getX()).isEqualTo(10);
        assertThat(mouseMoveAction.getY()).isEqualTo(200);
    }

    @Test
    public void canSaveAMouseClickInAMouseClickAction() {
        MousePressAction mouseClickAction = new MousePressAction(MouseEvent.BUTTON1);
        assertThat(mouseClickAction.getButton()).isEqualTo(MouseEvent.BUTTON1);
    }

    @Test
    public void canMouseMoveActionProcessMoveTheMouse() {
        MouseMoveAction mouseMoveAction = new MouseMoveAction(10, 200);
        mouseMoveAction.process(robot);
        assertThat(robot.getX()).isEqualTo(10);
        assertThat(robot.getY()).isEqualTo(200);
    }

    @Test
    public void canPressTheRightMouseButtonWhenMousePressActionIsProcessed() {
        MousePressAction mousePressAction = new MousePressAction(MouseEvent.BUTTON1);
        mousePressAction.process(robot);
        assertThat(robot.getLastButtonMousePressed()).isEqualTo(MouseEvent.BUTTON1);
    }

    @Test
    public void canReleaseTheRightMouseButtonWhenMouseReleaseActionIsProcessed() {
        MouseReleaseAction mouseReleaseAction = new MouseReleaseAction(MouseEvent.BUTTON2);
        mouseReleaseAction.process(robot);
        assertThat(robot.getLastButtonMouseReleased()).isEqualTo(MouseEvent.BUTTON2);
    }

    @Test
    public void canSetTheNextMouseAction() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        mouseNodeA.setNextMouseAction(mouseNodeB);
        assertThat(mouseNodeA.getNext()).isEqualTo(mouseNodeB);
    }

    @Test
    public void throwAnExceptionWhenThereIsNoNextAction() {
        MouseNode mouseNode = new MouseNode();
        try {
            mouseNode.getNext();
            fail();
        } catch (NoMoreActionException ignored) {}

    }

    private class RobotMock extends Robot {

        private int x;
        private int y;
        private int lastButtonMousePressed;
        private int lastButtonMouseReleased;

        public RobotMock() throws AWTException {}

        @Override
        public synchronized void mouseMove(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public synchronized void mousePress(int button) {
            this.lastButtonMousePressed = button;
        }

        @Override
        public synchronized void mouseRelease(int button) {
            this.lastButtonMouseReleased = button;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getLastButtonMousePressed() {
            return lastButtonMousePressed;
        }

        public int getLastButtonMouseReleased() {
            return lastButtonMouseReleased;
        }
    }
}
