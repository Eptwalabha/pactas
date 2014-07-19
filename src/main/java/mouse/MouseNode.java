package mouse;

import mouse.actions.MouseAction;
import mouse.actions.MouseNoAction;
import utility.GameWindow;

/**
 * User: Eptwalabha
 * Date: 05/07/2014
 * Time: 18:19
 */
public class MouseNode {
    private MouseNode nextNode;
    private MouseNode previousNode;
    private MouseAction action;
    private long timeToWait;

    public MouseNode() {
        this(new MouseNoAction());
    }

    public MouseNode(MouseAction action) {
        this.action = action;
    }

    public void setAction(MouseAction action) {
        this.action = action;
    }

    public void setNext(MouseNode mouseNode) {
        setNext(mouseNode, timeToWait);
    }

    public void setNext(MouseNode mouseNode, long timeToWait) {
        mouseNode.previousNode = this;
        this.nextNode = mouseNode;
        this.timeToWait = timeToWait;
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
        return action.getType() + ";" + timeToWait + ";" + action.getString(gameWindow);
    }

    public long getTimeToWaitMillis() {
        return timeToWait;
    }

    public void setTimeToWaitInMilli(long timeToWait) {
        this.timeToWait = timeToWait;
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
}
