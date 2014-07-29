import mouse.MouseNode;
import org.junit.Test;
import utility.undoredo.DeleteMouseNodeAction;
import utility.undoredo.MoveMouseNodeAction;
import utility.undoredo.UndoRedoAction;

import java.awt.*;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Eptwalabha
 * Date: 29/07/2014
 * Time: 01:44
 */
public class UndoRedoTest {

    @Test
    public void canPerformAMoveMouseNodeAction() {
        MouseNode mouseNode = new MouseNode();
        UndoRedoAction undoRedoAction = new MoveMouseNodeAction(mouseNode, 10, 20);
        undoRedoAction.doAction();
        assertThat(mouseNode.getMouseAction().getLocation()).isEqualTo(new Point(10, 20));
    }

    @Test
    public void canUndoAMoveMouseNodeAction() {
        MouseNode mouseNode = new MouseNode();
        UndoRedoAction undoRedoAction = new MoveMouseNodeAction(mouseNode, 10, 20);
        undoRedoAction.doAction();
        undoRedoAction.undoAction();
        assertThat(mouseNode.getMouseAction().getLocation()).isEqualTo(new Point(0, 0));
    }

    @Test
    public void canPerformADeleteMouseNodeAction() {
        MouseNode mouseNodeA = createMouseNodeChain(3);
        MouseNode mouseNodeB = mouseNodeA.get(1);
        MouseNode mouseNodeC = mouseNodeA.get(2);

        UndoRedoAction undoRedoAction = new DeleteMouseNodeAction(mouseNodeB);
        undoRedoAction.doAction();
        assertThat(mouseNodeA.getNext()).isEqualTo(mouseNodeC);
    }

    @Test
    public void canUndoADeleteMouseNodeAction() {
        MouseNode mouseNodeA = createMouseNodeChain(2);
        MouseNode mouseNodeB = mouseNodeA.get(1);

        UndoRedoAction undoRedoAction = new DeleteMouseNodeAction(mouseNodeB);
        undoRedoAction.doAction();
        undoRedoAction.undoAction();

        assertThat(mouseNodeA.getNext()).isEqualTo(mouseNodeB);
    }

    private MouseNode createMouseNodeChain(int length) {

        MouseNode first = new MouseNode();
        int currentLength = 1;

        while (currentLength < length) {
            first.appendNode(new MouseNode());
            ++currentLength;
        }

        return first;
    }
}
