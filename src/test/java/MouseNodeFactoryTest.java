import mouse.MouseNode;
import mouse.factory.MouseNodeFactory;
import mouse.actions.MouseMoveAction;
import mouse.actions.MousePressAction;
import mouse.actions.MouseReleaseAction;
import mouse.factory.WrongParameter;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.MouseEvent;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 12:35
 */
public class MouseNodeFactoryTest {

    private MouseNodeFactory mouseNodeFactory;
    private MouseNode mouseNode;

    @Before
    public void setUp() {
        mouseNodeFactory = new MouseNodeFactory();
        mouseNode = new MouseNode();
    }

    @Test
    public void canCreateAMotionActionFromAString() {
        try {
            mouseNode = mouseNodeFactory.createMouseActionFromString("1;0;0;0");
            assertThat(mouseNode).isInstanceOf(MouseNode.class);
        } catch (WrongParameter e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canCreateAMouseMoveActionFromAStringOfParameters() {
        try {
            mouseNode = mouseNodeFactory.createMouseActionFromString("1;0;10;200");
            MouseMoveAction mouseMoveAction = (MouseMoveAction) mouseNode.getMouseActionInterface();
            assertThat(mouseMoveAction.getX()).isEqualTo(10);
            assertThat(mouseMoveAction.getY()).isEqualTo(200);
        } catch (WrongParameter e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canCreateAMousePressActionFromAStringOfParameters() {
        try {
            mouseNode = mouseNodeFactory.createMouseActionFromString("2;0;" + MouseEvent.BUTTON1);
            MousePressAction mousePressAction = (MousePressAction) mouseNode.getMouseActionInterface();
            assertThat(mousePressAction.getButton()).isEqualTo(MouseEvent.BUTTON1);
        } catch (WrongParameter e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canCreateAMouseReleaseActionFromAStringOfParameters() {
        try {
            mouseNode = mouseNodeFactory.createMouseActionFromString("3;0;" + MouseEvent.BUTTON2);
            MouseReleaseAction mouseReleaseAction = (MouseReleaseAction) mouseNode.getMouseActionInterface();
            assertThat(mouseReleaseAction.getButton()).isEqualTo(MouseEvent.BUTTON2);
        } catch (WrongParameter e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canSetTimeToWaitBetweenBeforeTheNextMouseNode() {
        mouseNode.setTimeToWaitInMilli(250);
        assertThat(mouseNode.getTimeToWaitMillis()).isEqualTo(250);
    }

    @Test
    public void canThrowsAnExceptionWhenParametersAreMissing() {
        try {
            mouseNodeFactory.createMouseActionFromString("3;0;0;0");
            fail();
        } catch (WrongParameter e) {
            assertThat(e.getMessage()).isEqualTo("wrong parameter : got 4 parameters when 3 where expected");
        }
    }

    @Test
    public void canThrowsAnExceptionWhenTypeParameterIsIncorrect() {
        try {
            mouseNodeFactory.createMouseActionFromString("a;0;0;0");
            fail();
        } catch (WrongParameter e) {
            assertThat(e.getMessage()).isEqualTo("wrong parameter : unknown 'a' as MouseAction type");
        }
    }

    @Test
    public void canThrowsAnExceptionWhenTimeParameterIsIncorrect() {
        try {
            mouseNodeFactory.createMouseActionFromString("1;b;0;0");
            fail();
        } catch (WrongParameter e) {
            assertThat(e.getMessage()).isEqualTo("wrong parameter : 'b' cannot be converted as millisecond");
        }
    }

    @Test
    public void canSetTimeToWaitTillNextNode() {
        try {
            mouseNode = mouseNodeFactory.createMouseActionFromString("1;200;10;200");
            assertThat(mouseNode.getTimeToWaitMillis()).isEqualTo(200L);
        } catch (WrongParameter e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canMouseNodeMoveGenerateAString() {
        mouseNode.setAction(new MouseMoveAction(10, 20));
        assertThat(mouseNode.getString()).isEqualTo("1;0;10;20");
    }

    @Test
    public void canMouseNodePressGenerateAString() {
        mouseNode.setAction(new MousePressAction(MouseEvent.BUTTON1));
        assertThat(mouseNode.getString()).isEqualTo("2;0;" + MouseEvent.BUTTON1);
    }

    @Test
    public void canMouseNodeReleaseGenerateAString() {
        mouseNode.setAction(new MouseReleaseAction(MouseEvent.BUTTON2));
        assertThat(mouseNode.getString()).isEqualTo("3;0;" + MouseEvent.BUTTON2);
        Integer.parseInt("1");
    }
}
