package mouse.transition;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 26/07/2014
 * Time: 14:01
 */
public interface MouseTransition {

    public static final int CONSTANT = 0;
    public static final int LINEAR = 1;

    public Point getLocation(Point pointA, Point pointB, float percent);

    public int getType();
}
