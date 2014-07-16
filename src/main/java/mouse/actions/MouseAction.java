package mouse.actions;

import utility.GameWindow;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 14/07/2014
 * Time: 21:42
 */
public interface MouseAction {
    public void process(Robot robot);
    public String getString(GameWindow gameWindow);
}