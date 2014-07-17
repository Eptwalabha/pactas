package mouse.group;

import mouse.MouseNode;
import mouse.actions.MouseAction;
import mouse.actions.MouseMoveAction;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * User: Eptwalabha
 * Date: 17/07/2014
 * Time: 23:04
 */
public class MouseNodeGroup {

    ArrayList<MouseNode> mouseNodes = new ArrayList<MouseNode>();

    public void add(MouseNode mouseNode) {
        if (!mouseNodes.contains(mouseNode))
            mouseNodes.add(mouseNode);
    }

    public int size() {
        return mouseNodes.size();
    }

    public void remove(MouseNode mouseNode) {
        mouseNodes.remove(mouseNode);
    }

    public void moveLocation(int x, int y) {
        for (MouseNode mouseNode : mouseNodes) {
            mouseNode.moveLocation(x, y);
        }
    }

    public void clearAllMouseNode() {
        mouseNodes = new ArrayList<MouseNode>();
    }

    public Rectangle getBoundingBox() {
        Point locationMin = new Point(), locationMax = new Point();
        boolean first = true;
        for (MouseNode mouseNode : mouseNodes) {
            try {
                MouseMoveAction mouseMoveAction = (MouseMoveAction) mouseNode.getMouseAction();
                Point nodeLocation = mouseMoveAction.getLocation();
                if (first) {
                    locationMin = new Point(nodeLocation);
                    locationMax = new Point(nodeLocation);
                    first = false;
                    continue;
                }
                locationMin.x = min(locationMin.x, nodeLocation.x);
                locationMin.y = min(locationMin.y, nodeLocation.y);
                locationMax.x = max(locationMax.x, nodeLocation.x);
                locationMax.y = max(locationMax.y, nodeLocation.y);

            } catch (ClassCastException ignored) {}
        }

        return new Rectangle(locationMin, new Dimension(locationMax.x - locationMin.x, locationMax.y - locationMin.y));
    }

    public void resizeBoundingBox(float percentX, float percentY) {
        Rectangle boundingBox = getBoundingBox();

        for (MouseNode mouseNode : mouseNodes) {
            try {
                MouseMoveAction mouseMoveAction = (MouseMoveAction) mouseNode.getMouseAction();
                Point location = mouseMoveAction.getLocation();
                int newX = (int) (boundingBox.x + (location.x - boundingBox.x) * percentX);
                int newY = (int) (boundingBox.y + (location.y - boundingBox.y) * percentY);
                mouseMoveAction.setLocation(newX, newY);
            } catch (ClassCastException ignored) {}
        }
    }
}
