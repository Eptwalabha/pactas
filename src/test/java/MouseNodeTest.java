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
    public void byDefaultAMouseNodeHasAMouseMoveActionAsMouseAction() {
        MouseAction mouseAction = mouseNode.getMouseAction();
        assertThat(mouseAction.getType()).isEqualTo(MouseAction.ACTION_MOVE);
    }

    @Test
    public void canSaveAMouseMoveInAMouseMoveAction() {
        MouseMoveAction mouseMoveAction = new MouseMoveAction(10, 200);
        assertThat(mouseMoveAction.getLocation()).isEqualTo(new Point(10, 200));
    }

    @Test
    public void canSaveAMouseClickInAMouseClickAction() {
        MousePressAction mouseClickAction = new MousePressAction(0, 0, MouseEvent.BUTTON1);
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
        MousePressAction mousePressAction = new MousePressAction(0, 0, MouseEvent.BUTTON1);
        mousePressAction.process(robot);
        assertThat(robot.getLastButtonMousePressed()).isEqualTo(MouseEvent.BUTTON1);
    }

    @Test
    public void canReleaseTheRightMouseButtonWhenMouseReleaseActionIsProcessed() {
        MouseReleaseAction mouseReleaseAction = new MouseReleaseAction(0, 0, MouseEvent.BUTTON2);
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
        mouseMoveAction.moveLocation(-10, 50);
        assertThat(mouseMoveAction.getLocation()).isEqualTo(new Point(10, 80));
    }

    @Test
    public void canSetMouseMoveActionPosition() {
        MouseMoveAction mouseMoveAction = new MouseMoveAction(20, 30);
        mouseNode.setAction(mouseMoveAction);
        mouseMoveAction.setLocation(10, 50);
        assertThat(mouseMoveAction.getLocation()).isEqualTo(new Point(10, 50));
    }

    @Test
    public void canChangeMouseButtonOnMousePressAction() {
        MousePressAction mousePressAction = new MousePressAction(0, 0, MouseEvent.BUTTON1);
        mouseNode.setAction(mousePressAction);
        mousePressAction.setButton(MouseEvent.BUTTON2);
        assertThat(mousePressAction.getButton()).isEqualTo(MouseEvent.BUTTON2);
    }

    @Test
    public void canChangeMouseButtonOnMouseReleaseAction() {
        MouseReleaseAction mouseReleaseAction = new MouseReleaseAction(0, 0, MouseEvent.BUTTON1);
        mouseNode.setAction(mouseReleaseAction);
        mouseReleaseAction.setButton(MouseEvent.BUTTON2);
        assertThat(mouseReleaseAction.getButton()).isEqualTo(MouseEvent.BUTTON2);
    }

    @Test
    public void canMouseActionReturnItsType() {
        MouseAction mouseMoveAction = new MouseMoveAction(10, 20);
        MouseAction mousePressAction = new MousePressAction(0, 0, MouseEvent.BUTTON1);
        MouseAction mouseReleaseAction = new MouseReleaseAction(0, 0, MouseEvent.BUTTON1);
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

    @Test
    public void canReturnTheIndexOfANodeIfItBelongsToTheChain() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        MouseNode mouseNodeC = new MouseNode();

        mouseNodeA.setNext(mouseNodeB);
        mouseNodeB.setNext(mouseNodeC);

        assertThat(mouseNodeA.indexOf(mouseNodeB)).isEqualTo(1);
    }

    @Test
    public void canReturnIndexMinusOneWhenLookingForNullInAChain() {
        MouseNode mouseNodeA = new MouseNode();
        assertThat(mouseNodeA.indexOf(null)).isEqualTo(-1);
    }

    @Test
    public void canReturnIndexZeroWhenNodeIsTheFirstNodeInAChain() {
        assertThat(mouseNode.indexOf(mouseNode)).isEqualTo(0);
    }

    @Test
    public void canReturnTheFirstNodeAtIndexZero() {
        MouseNode mouseNodeA = new MouseNode();
        assertThat(mouseNodeA.get(0)).isEqualTo(mouseNodeA);
    }

    @Test
    public void canReturnANodeAtAGivenIndex() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        mouseNodeA.setNext(mouseNodeB);
        assertThat(mouseNodeA.get(1)).isEqualTo(mouseNodeB);
    }

    @Test
    public void canAppendASingleNodeToAChain() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        MouseNode mouseNodeC = new MouseNode();

        mouseNodeA.setNext(mouseNodeB);
        mouseNodeA.appendNode(mouseNodeC);
        assertThat(mouseNodeA.indexOf(mouseNodeC)).isEqualTo(2);
    }

    @Test
    public void canShiftASingleNodeToAChain() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        MouseNode mouseNodeC = new MouseNode();

        mouseNodeA.setNext(mouseNodeB);
        mouseNodeA.shiftNode(mouseNodeC);
        assertThat(mouseNodeC.size()).isEqualTo(3);
    }

    @Test
    public void canInsertASingleNodeToAChainAtASpecificIndex() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        MouseNode mouseNodeC = new MouseNode();

        mouseNodeA.setNext(mouseNodeC);
        mouseNodeA.insertNode(1, mouseNodeB);
        assertThat(mouseNodeA.getNext()).isEqualTo(mouseNodeB);
        assertThat(mouseNodeC.getPrevious()).isEqualTo(mouseNodeB);
    }

    @Test
    public void canInsertASingleNodeToAChainAtIndexZero() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        MouseNode mouseNodeC = new MouseNode();

        mouseNodeA.setNext(mouseNodeB);
        mouseNodeA.insertNode(0, mouseNodeC);
        assertThat(mouseNodeC.getNext()).isEqualTo(mouseNodeA);
    }

    @Test
    public void canInsertASingleNodeToAChainAtItsEnd() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();
        MouseNode mouseNodeC = new MouseNode();

        mouseNodeA.setNext(mouseNodeB);
        mouseNodeA.insertNode(2, mouseNodeC);
        assertThat(mouseNodeB.getNext()).isEqualTo(mouseNodeC);
        assertThat(mouseNodeC.getPrevious()).isEqualTo(mouseNodeB);
    }
}
