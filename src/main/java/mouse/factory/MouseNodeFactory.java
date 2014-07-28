package mouse.factory;

import mouse.MouseNode;
import mouse.NoMoreActionException;
import mouse.actions.MouseAction;
import utility.GameWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 12:39
 */
public class MouseNodeFactory {

    private final HashMap<String, MouseActionFactory> actions;

    public MouseNodeFactory(GameWindow gameWindow) {
        actions = new HashMap<String, MouseActionFactory>();
        actions.put(MouseAction.ACTION_MOVE, new MouseMoveFactory(gameWindow));
        actions.put(MouseAction.ACTION_PRESS, new MousePressFactory(gameWindow));
        actions.put(MouseAction.ACTION_RELEASE, new MouseReleaseFactory(gameWindow));
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

    public MouseNode loadChainFromBufferReader(BufferedReader bufferReader) throws IOException, WrongParameter {
        String line;
        MouseNode mouseNode = null;
        MouseNode mouseNodeCursor = null;
        while ((line = bufferReader.readLine()) != null) {

            MouseNode currentMouseNode = createMouseActionFromString(line);
            if (mouseNode == null) {
                mouseNode = currentMouseNode;
                mouseNodeCursor = mouseNode;
                continue;
            }

            mouseNodeCursor.setNext(currentMouseNode);
            mouseNodeCursor = mouseNodeCursor.getNext();
        }
        return mouseNode;
    }

    public void saveChainIntoBufferWriter(Writer bufferWriter, GameWindow gameWindow, MouseNode chain) {
        try {
            MouseNode cursor = chain;
            do {
                bufferWriter.write(cursor.getString(gameWindow));
            } while ((cursor = cursor.getNext()) != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
