package mouse.factory;

import mouse.actions.MouseAction;
import mouse.actions.MouseMoveAction;

/**
 * User: Eptwalabha
 * Date: 15/07/2014
 * Time: 01:23
 */
public class MouseMoveFactory implements MouseActionFactory {
    @Override
    public MouseAction getMouseAction(String[] args) throws WrongParameter {
        if (args.length != 4) {
            throw new WrongParameter("got " + args.length + " parameters when 4 where expected");
        }

        int x = Integer.parseInt(args[2]);
        int y = Integer.parseInt(args[3]);
        return new MouseMoveAction(x, y);
    }
}
