package mouse.factory;

import mouse.actions.MouseAction;
import mouse.actions.MouseReleaseAction;
import utility.GameWindow;

/**
 * User: Eptwalabha
 * Date: 15/07/2014
 * Time: 01:25
 */
public class MouseReleaseFactory implements MouseActionFactory {
    private GameWindow gameWindow;

    public MouseReleaseFactory(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }
    @Override
    public MouseAction getMouseAction(String[] args) throws WrongParameter {
        if (args.length != 5) {
            throw new WrongParameter("got " + args.length + " parameters when 5 where expected");
        }
        int x = gameWindow.getPositionX(Float.parseFloat(args[2]));
        int y = gameWindow.getPositionY(Float.parseFloat(args[3]));
        int button = Integer.parseInt(args[4]);
        return new MouseReleaseAction(x, y, button);
    }
}
