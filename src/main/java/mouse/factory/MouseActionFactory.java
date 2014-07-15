package mouse.factory;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import mouse.actions.MouseAction;

/**
 * User: Eptwalabha
 * Date: 15/07/2014
 * Time: 01:22
 */
public interface MouseActionFactory {
    public MouseAction getMouseAction(String[] args) throws WrongParameter;
}
