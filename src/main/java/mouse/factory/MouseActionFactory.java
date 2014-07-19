package mouse.factory;

import mouse.actions.MouseAction;

/**
 * User: Eptwalabha
 * Date: 15/07/2014
 * Time: 01:22
 */
interface MouseActionFactory {
    public MouseAction getMouseAction(String[] args) throws WrongParameter;
}
