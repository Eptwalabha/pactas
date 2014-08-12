package mouse.transition;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 26/07/2014
 * Time: 14:53
 */
public class LinearTransition implements MouseTransition {

    @Override
    public Point getLocation(Point pointA, Point pointB, float percent) {
        int deltaX = pointB.x - pointA.x;
        int deltaY = pointB.y - pointA.y;
        return new Point(pointA.x + (int) (deltaX * percent), pointA.y + (int) (deltaY * percent));
    }

    @Override
    public int getType() {
        return MouseTransition.LINEAR;
    }
}
