package mouse.transition;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 26/07/2014
 * Time: 14:53
 */
public class LinearTransition implements MouseTransition {

    @Override
    public Point getLocation(Point pointA, Point pointB, long deltaTime, long time) {
        if (deltaTime == 0)
            return new Point(pointA);
        int deltaX = pointB.x - pointA.x;
        int deltaY = pointB.y - pointA.y;
        float ratio = time / (float) deltaTime;
        return new Point(pointA.x + (int) (deltaX * ratio), pointA.y + (int) (deltaY * ratio));
    }

    @Override
    public int getType() {
        return MouseTransition.LINEAR;
    }
}
