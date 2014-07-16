package mouse;

import mouse.actions.MouseAction;
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

    public void setAction(MouseAction action) {
        this.action = action;
    }

    public void setNextMouseAction(MouseNode mouseNode) {
        this.nextAction = mouseNode;
    }

    public MouseNode getNext() throws NoMoreActionException {
        if (nextAction == null)
            throw new NoMoreActionException();
        return nextAction;
    }

    public MouseAction getMouseActionInterface() {
        return action;
    }

    public String getString(GameWindow gameWindow) {
        return action.getString(gameWindow);
    }

    public long getTimeToWaitMillis() {
        return timeToWait;
    }

    public void setTimeToWaitInMilli(long timeToWait) {
        this.timeToWait = timeToWait;
    }
}
