import mock.RobotMock;
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
    public void canSetTheNextMouseNode() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        mouseNodeA.setNext(mouseNodeB);
        assertThat(mouseNodeA.getNext()).isEqualTo(mouseNodeB);
    }

    @Test
    public void canRetrievePreviousMouseNode() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        mouseNodeA.setNext(mouseNodeB);
        assertThat(mouseNodeB.getPrevious()).isEqualTo(mouseNodeA);
    }

    @Test
    public void canFullyDetachMouseNode() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        mouseNodeA.setNext(mouseNodeB);
        mouseNodeA.detachNode();
        assertThat(mouseNodeA.getNext()).isNull();
        assertThat(mouseNodeB.getPrevious()).isNull();
    }

    @Test
    public void canSetTheNextMouseActionAndTheTimeToWait() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        mouseNodeA.setNext(mouseNodeB, 200);
        assertThat(mouseNodeA.getTimeToWaitMillis()).isEqualTo(200);
    }

    @Test
    public void canSetTimeToWaitBeforeTheNextMouseNode() {
        mouseNode.setTimeToWaitInMilli(250);
        assertThat(mouseNode.getTimeToWaitMillis()).isEqualTo(250);
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

}
