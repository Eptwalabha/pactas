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
    private MouseNode nextAction;
    private MouseAction action;
    private long timeToWait;

    public MouseNode() {
        action = new MouseNoAction();
    }

    public void setAction(MouseAction action) {
        this.action = action;
    }

    public void setNext(MouseNode mouseNode) {
        this.nextAction = mouseNode;
    }

    public MouseNode getNext() throws NoMoreActionException {
        if (nextAction == null)
            throw new NoMoreActionException();
        return nextAction;
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

        if (nextAction == null)
            return 1;

        int size = 1;
        MouseNode cursor = this;
        while (cursor.nextAction != null) {
            ++size;
            cursor = cursor.nextAction;
        }

        return size;
    }

    public String getActionType() {
        return action.getType();
    }
}
