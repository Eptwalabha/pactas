import mouse.*;
import mouse.actions.MouseAction;
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
    private MouseNode mouseNode;

    @Before
    public void setUp() throws AWTException {
        robot = new RobotMock();
        mouseNode = new MouseNode();
    }

    @Test
    public void byDefaultAMouseNodeHasAMouseNoActionAsMouseAction() {
        MouseAction mouseAction = mouseNode.getMouseAction();
        assertThat(mouseAction.getType()).isEqualTo(MouseAction.ACTION_NONE);
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
        mouseNodeA.setNext(mouseNodeB);
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

    @Test
    public void canChangeMouseMoveActionPosition() {
        MouseMoveAction mouseMoveAction = new MouseMoveAction(20, 30);
        mouseNode.setAction(mouseMoveAction);
        mouseNode.moveLocation(-10, 50);
        assertThat(mouseMoveAction.getX()).isEqualTo(10);
        assertThat(mouseMoveAction.getY()).isEqualTo(80);
    }

    @Test
    public void canSetMouseMoveActionPosition() {
        MouseMoveAction mouseMoveAction = new MouseMoveAction(20, 30);
        mouseNode.setAction(mouseMoveAction);
        mouseNode.setLocation(10, 50);
        assertThat(mouseMoveAction.getX()).isEqualTo(10);
        assertThat(mouseMoveAction.getY()).isEqualTo(50);
    }

    @Test
    public void canChangeMouseButtonOnMousePressAction() {
        MousePressAction mousePressAction = new MousePressAction(MouseEvent.BUTTON1);
        mouseNode.setAction(mousePressAction);
        mouseNode.setButton(MouseEvent.BUTTON2);
        assertThat(mousePressAction.getButton()).isEqualTo(MouseEvent.BUTTON2);
    }

    @Test
    public void canChangeMouseButtonOnMouseReleaseAction() {
        MouseReleaseAction mouseReleaseAction = new MouseReleaseAction(MouseEvent.BUTTON1);
        mouseNode.setAction(mouseReleaseAction);
        mouseNode.setButton(MouseEvent.BUTTON2);
        assertThat(mouseReleaseAction.getButton()).isEqualTo(MouseEvent.BUTTON2);
    }

    @Test
    public void canMouseActionReturnItsType() {
        MouseAction mouseMoveAction = new MouseMoveAction(10, 20);
        MouseAction mousePressAction = new MousePressAction(MouseEvent.BUTTON1);
        MouseAction mouseReleaseAction = new MouseReleaseAction(MouseEvent.BUTTON1);
        assertThat(mouseMoveAction.getType()).isEqualTo(MouseAction.ACTION_MOVE);
        assertThat(mousePressAction.getType()).isEqualTo(MouseAction.ACTION_PRESS);
        assertThat(mouseReleaseAction.getType()).isEqualTo(MouseAction.ACTION_RELEASE);
    }

    @Test
    public void canReturn1WhenForTheSizeChainWithASingleMouseNode() {
        assertThat(mouseNode.size()).isEqualTo(1);
    }

    @Test
    public void canReturnTheSizeOfAMouseNodeChain() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        MouseNode mouseNodeC = new MouseNode();

        mouseNodeA.setNext(mouseNodeB);
        mouseNodeB.setNext(mouseNodeC);

        assertThat(mouseNodeA.size()).isEqualTo(3);
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
