package mouse.factory;

import mouse.actions.MouseAction;
import mouse.actions.MouseMoveAction;
import utility.GameWindow;

/**
 * User: Eptwalabha
 * Date: 15/07/2014
 * Time: 01:23
 */
public class MouseMoveFactory implements MouseActionFactory {
    private GameWindow gameWindow;

    public MouseMoveFactory(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    public MouseAction getMouseAction(String[] args) throws WrongParameter {
        if (args.length != 4) {
            throw new WrongParameter("got " + args.length + " parameters when 4 where expected");
        }

        int x = gameWindow.getPositionX(Float.parseFloat(args[2]));
        int y = gameWindow.getPositionY(Float.parseFloat(args[3]));
        return new MouseMoveAction(x, y);
    }
}
