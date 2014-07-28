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

    public int size() {
        MouseNode cursor = this;
        int index = 0;
        while (cursor != null) {
            index++;
            cursor = cursor.getNext();
        }
        return index;
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
        return transition.getLocation(action.getLocation(), nextNode.action.getLocation(), timeTillNext, time);
    }

    public int indexOf(MouseNode mouseNode) {
        MouseNode cursor = this;
        int index = 0;
        while (cursor != mouseNode && cursor != null) {
            index++;
            cursor = cursor.getNext();
        }
        return cursor != null ? index : -1;
    }

    public void appendNode(MouseNode mouseNode) {
        MouseNode cursor = get(this.size() - 1);
        cursor.setNext(mouseNode);
    }

    public void shiftNode(MouseNode mouseNode) {
        mouseNode.setNext(this);
    }

    public void insertNode(int index, MouseNode mouseNode) {
        if (index == 0) {
            this.shiftNode(mouseNode);
            return;
        }
        if (index >= this.size()) {
            this.appendNode(mouseNode);
            return;
        }
        MouseNode cursor = get(index);
        cursor.previousNode.setNext(mouseNode);
        mouseNode.setNext(cursor);
    }

    public MouseNode get(int index) {
        MouseNode cursor = this;
        int cursorIndex = 0;
        while (cursorIndex != index) {
            cursorIndex++;
            cursor = cursor.getNext();
        }
        return cursor;
    }
}
