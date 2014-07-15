package mouse.factory;

import mouse.actions.MouseAction;
import mouse.actions.MouseReleaseAction;

/**
 * User: Eptwalabha
 * Date: 15/07/2014
 * Time: 01:25
 */
public class MouseReleaseFactory implements MouseActionFactory {
    @Override
    public MouseAction getMouseAction(String[] args) throws WrongParameter {
        if (args.length != 3) {
            throw new WrongParameter("got " + args.length + " parameters when 3 where expected");
        }
        int button = Integer.parseInt(args[2]);
        return new MouseReleaseAction(button);
    }
}
