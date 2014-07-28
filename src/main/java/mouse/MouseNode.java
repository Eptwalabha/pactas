package mouse;

import mouse.actions.MouseAction;
import mouse.actions.MouseMoveAction;
import mouse.transition.ConstantTransition;
import mouse.transition.MouseTransition;
import utility.GameWindow;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 05/07/2014
 * Time: 18:19
 */
public class MouseNode {
    private MouseNode nextNode;
    private MouseNode previousNode;
    private MouseAction action;
    private long timeTillNext;
    private MouseTransition transition;

    public MouseNode() {
        this(new MouseMoveAction(0, 0));
    }

    public MouseNode(MouseAction action) {
        this.action = action;
        this.transition = new ConstantTransition();
    }

    public void setAction(MouseAction action) {
        this.action = action;
    }

    public void setNext(MouseNode mouseNode) {
        setNext(mouseNode, timeTillNext);
    }

    public void setNext(MouseNode mouseNode, long timeToWait) {
        mouseNode.previousNode = this;
        this.nextNode = mouseNode;
        this.timeTillNext = timeToWait;
    }

    public MouseNode getNext() {
        return nextNode;
    }

    public MouseNode getPrevious() {
        return previousNode;
    }

    public MouseAction getMouseAction() {
        return action;
    }

    public String getString(GameWindow gameWindow) {
        return action.getType() + ";" + timeTillNext + ";" + action.getString(gameWindow);
    }

    public long getTimeToWaitMillis() {
        return timeTillNext;
    }

    public void setTimeToWaitInMilli(long timeToWait) {
        this.timeTillNext = timeToWait;
    }

    public void moveLocation(int x, int y) {
        action.moveLocation(x, y);
    }

    public void setLocation(int x, int y) {
        action.setLocation(x, y);
    }

    public void setButton(int button) {
        action.setButton(button);
    }

    public Point getLocation() {
        return action.getLocation();
    }

    public int size() {

        if (nextNode == null)
            return 1;

        int size = 1;
        MouseNode cursor = this;
        while (cursor.nextNode != null) {
            ++size;
            cursor = cursor.nextNode;
        }

        return size;
    }

    public String getActionType() {
        return action.getType();
    }

    public void detachNode() {
        nextNode.previousNode = null;
        nextNode = null;
    }

    public void setMouseTransition(MouseTransition transition) {
        this.transition = transition;
    }

    public MouseTransition getTransition() {
        return transition;
    }

    public Point getLocation(long time) {
        return transition.getLocation(getLocation(), nextNode.getLocation(), timeTillNext, time);
    }
}
