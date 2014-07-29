package utility.undoredo;

import mouse.MouseNode;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 29/07/2014
 * Time: 01:48
 */
public class MoveMouseNodeAction extends UndoRedoAction {

    private final MouseNode mouseNode;
    private final Point newLocation;
    private final Point oldLocation;

    public MoveMouseNodeAction(MouseNode mouseNode, int x, int y) {
        this.mouseNode = mouseNode;
        this.newLocation = new Point(x, y);
        this.oldLocation = mouseNode.getMouseAction().getLocation();
    }

    @Override
    public void doAction() {
        mouseNode.getMouseAction().setLocation(newLocation);
    }

    @Override
    public void undoAction() {
        mouseNode.getMouseAction().setLocation(oldLocation);
    }
}
