package mouse.transition;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 26/07/2014
 * Time: 15:24
 */
public class ConstantTransition implements MouseTransition {

    @Override
    public Point getLocation(Point pointA, Point pointB, long deltaTime, long time) {
        return new Point(pointA);
    }

    @Override
    public int getType() {
        return MouseTransition.CONSTANT;
    }
}
