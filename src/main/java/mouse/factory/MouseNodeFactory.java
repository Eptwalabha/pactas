package mouse.factory;

import mouse.MouseNode;
import mouse.actions.MouseAction;
import utility.GameWindow;

import java.util.HashMap;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 12:39
 */
public class MouseNodeFactory {

    private HashMap<String, MouseActionFactory> actions;

    public MouseNodeFactory(GameWindow gameWindow) {
        actions = new HashMap<String, MouseActionFactory>();
        actions.put("1", new MouseMoveFactory(gameWindow));
        actions.put("2", new MousePressFactory());
        actions.put("3", new MouseReleaseFactory());
    }

    public MouseNode createMouseActionFromString(String action) throws WrongParameter {

        String[] listOfParameters = action.split(";");
        MouseAction mouseAction;

        if (actions.containsKey(listOfParameters[0])) {
            mouseAction = actions.get(listOfParameters[0]).getMouseAction(listOfParameters);
        } else {
            throw new WrongParameter("unknown 'a' as MouseAction type");
        }

        long time;
        try {
            time = Long.parseLong(listOfParameters[1]);
        } catch (NumberFormatException e) {
            throw new WrongParameter("'" + listOfParameters[1] + "' cannot be converted as millisecond");
        }

        MouseNode mouseNode = new MouseNode();
        mouseNode.setAction(mouseAction);
        mouseNode.setTimeToWaitInMilli(time);
        return mouseNode;
    }
}
