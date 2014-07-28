import mouse.MouseNode;
import mouse.actions.MouseAction;
import mouse.factory.MouseNodeFactory;
import mouse.actions.MouseMoveAction;
import mouse.actions.MousePressAction;
import mouse.actions.MouseReleaseAction;
import mouse.factory.WrongParameter;
import org.junit.Before;
import org.junit.Test;
import utility.GameWindow;

import javax.swing.text.StringContent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;

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
    private GameWindow gameWindow;

    @Before
    public void setUp() {
        gameWindow = new GameWindow(100, 100);
        mouseNodeFactory = new MouseNodeFactory(gameWindow);
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
            mouseNode = mouseNodeFactory.createMouseActionFromString("1;0;0.1;0.2");
            MouseMoveAction mouseMoveAction = (MouseMoveAction) mouseNode.getMouseAction();
            Point location = mouseMoveAction.getLocation();
            assertThat(location).isEqualTo(new Point(10, 20));
        } catch (WrongParameter e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canCreateAMousePressActionFromAStringOfParameters() {
        try {
            mouseNode = mouseNodeFactory.createMouseActionFromString("2;0;0;0;" + MouseEvent.BUTTON1);
            MousePressAction mousePressAction = (MousePressAction) mouseNode.getMouseAction();
            assertThat(mousePressAction.getButton()).isEqualTo(MouseEvent.BUTTON1);
        } catch (WrongParameter e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canCreateAMouseReleaseActionFromAStringOfParameters() {
        try {
            mouseNode = mouseNodeFactory.createMouseActionFromString("3;0;0;0;" + MouseEvent.BUTTON2);
            MouseReleaseAction mouseReleaseAction = (MouseReleaseAction) mouseNode.getMouseAction();
            assertThat(mouseReleaseAction.getButton()).isEqualTo(MouseEvent.BUTTON2);
        } catch (WrongParameter e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canThrowsAnExceptionWhenParametersAreMissing() {
        try {
            mouseNodeFactory.createMouseActionFromString("3;0;0.0;0.0");
            fail();
        } catch (WrongParameter e) {
            assertThat(e.getMessage()).isEqualTo("wrong parameter : got 4 parameters when 5 where expected");
        }
    }

    @Test
    public void canThrowsAnExceptionWhenTypeParameterIsIncorrect() {
        try {
            mouseNodeFactory.createMouseActionFromString("a;0;0.0;0.0");
            fail();
        } catch (WrongParameter e) {
            assertThat(e.getMessage()).isEqualTo("wrong parameter : unknown 'a' as MouseAction type");
        }
    }

    @Test
    public void canThrowsAnExceptionWhenTimeParameterIsIncorrect() {
        try {
            mouseNodeFactory.createMouseActionFromString("1;b;0.0;0.0");
            fail();
        } catch (WrongParameter e) {
            assertThat(e.getMessage()).isEqualTo("wrong parameter : 'b' cannot be converted as millisecond");
        }
    }

    @Test
    public void canSetTimeToWaitTillNextNode() {
        try {
            mouseNode = mouseNodeFactory.createMouseActionFromString(MouseAction.ACTION_MOVE + ";200;0.1;0.2");
            assertThat(mouseNode.getTimeToWaitMillis()).isEqualTo(200L);
        } catch (WrongParameter e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void canMouseNodeMoveGenerateAString() {
        mouseNode.setAction(new MouseMoveAction(10, 20));
        assertThat(mouseNode.getString(new GameWindow(100, 100))).isEqualTo(MouseAction.ACTION_MOVE + ";0;0.1;0.2");
        assertThat(mouseNode.getString(new GameWindow(50, 200))).isEqualTo(MouseAction.ACTION_MOVE + ";0;0.2;0.1");
    }

    @Test
    public void canMouseNodePressGenerateAString() {
        mouseNode.setAction(new MousePressAction(10, 20, MouseEvent.BUTTON1));
        assertThat(mouseNode.getString(gameWindow)).isEqualTo(MouseAction.ACTION_PRESS + ";0;0.1;0.2;" + MouseEvent.BUTTON1);
    }

    @Test
    public void canMouseNodeReleaseGenerateAString() {
        mouseNode.setAction(new MouseReleaseAction(30, 40, MouseEvent.BUTTON2));
        assertThat(mouseNode.getString(gameWindow)).isEqualTo(MouseAction.ACTION_RELEASE + ";0;0.3;0.4;" + MouseEvent.BUTTON2);
    }

    @Test
    public void canMouseNodeWithTimeToWaitGenerateAString() {
        mouseNode.setAction(new MouseMoveAction(10, 20));
        mouseNode.setTimeToWaitInMilli(20);
        assertThat(mouseNode.getString(new GameWindow(100, 100))).isEqualTo(MouseAction.ACTION_MOVE + ";20;0.1;0.2");
    }

    @Test
    public void canCreateAMouseNodeChainFromABufferedReader() throws IOException, WrongParameter {
        String eol = System.getProperty("line.separator");
        Reader fileReader = new StringReader("1;10;0.5;0.1" + eol +"2;20;0;0;1" + eol + "1;10;0.1;0.35268");
        BufferedReader bufferReader = new BufferedReader(fileReader);
        MouseNode mouseNode = mouseNodeFactory.loadChainFromBufferReader(bufferReader);
        assertThat(mouseNode.size()).isEqualTo(3);
    }

    @Test
    public void canCreateCorrectMouseActionFromFile() throws IOException, WrongParameter {
        String eol = System.getProperty("line.separator");
        Reader fileReader = new StringReader("1;10;0.5;0.1" + eol +"2;20;0;0;1" + eol + "3;50;0;0;1");
        BufferedReader bufferReader = new BufferedReader(fileReader);
        MouseNode mouseNode = mouseNodeFactory.loadChainFromBufferReader(bufferReader);

        assertThat(mouseNode.getActionType()).isEqualTo(MouseAction.ACTION_MOVE);
        mouseNode = mouseNode.getNext();
        assertThat(mouseNode.getActionType()).isEqualTo(MouseAction.ACTION_PRESS);
        mouseNode = mouseNode.getNext();
        assertThat(mouseNode.getActionType()).isEqualTo(MouseAction.ACTION_RELEASE);
    }

    @Test
    public void canSaveAChainIntoFile() {
        MouseNode mouseNodeA = new MouseNode(new MouseMoveAction(10, 200));
        MouseNode mouseNodeB = new MouseNode(new MousePressAction(0, 0, MouseEvent.BUTTON1));
        MouseNode mouseNodeC = new MouseNode(new MouseReleaseAction(0, 0, MouseEvent.BUTTON2));

        mouseNodeA.setNext(mouseNodeB, 10);
        mouseNodeB.setNext(mouseNodeC, 100);

        String eol = System.getProperty("line.separator");
        MockWriter bufferWriter = new MockWriter();
        mouseNodeFactory.saveChainIntoBufferWriter(bufferWriter, gameWindow, mouseNodeA);
        assertThat(bufferWriter.writenContent).isEqualTo("1;10;0.1;2.0" + eol +"2;100;0.0;0.0;1" + eol + "3;0;0.0;0.0;2");
    }

    private class MockWriter extends Writer {

        public String writenContent = "";

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            if (writenContent.length() > 0)
                writenContent += System.getProperty("line.separator");
            writenContent += String.copyValueOf(cbuf, off, len);
        }

        @Override
        public void flush() throws IOException {

        }

        @Override
        public void close() throws IOException {

        }
    }
}
