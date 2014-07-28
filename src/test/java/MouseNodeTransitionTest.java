import mouse.MouseNode;
import mouse.actions.MouseMoveAction;
import mouse.transition.ConstantTransition;
import mouse.transition.LinearTransition;
import mouse.transition.MouseTransition;
import org.junit.Test;

import java.awt.*;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Eptwalabha
 * Date: 26/07/2014
 * Time: 13:47
 */
public class MouseNodeTransitionTest {

    @Test
    public void canATransitionReturnAPoint() {
        MouseTransition transition = new LinearTransition();
        assertThat(transition.getLocation(new Point(), new Point(), 0, 0)).isInstanceOf(Point.class);
    }

    @Test
    public void canReturnTheSameValueForAConstantTransitionAtAnyTime() {
        ConstantTransition transition = new ConstantTransition();
        assertThat(transition.getLocation(new Point(35, 40), new Point(100, 100), 0, 0)).isEqualTo(new Point(35, 40));
        assertThat(transition.getLocation(new Point(35, 40), new Point(100, 100), 0, 100)).isEqualTo(new Point(35, 40));
        assertThat(transition.getLocation(new Point(35, 40), new Point(100, 100), 0, 500)).isEqualTo(new Point(35, 40));
    }

    @Test
    public void canALinearTransitionReturnsTheOriginAtBeginningOfTransition() {
        LinearTransition transition = new LinearTransition();
        Point point = transition.getLocation(new Point(20, 34), new Point(100, 100), 100, 0);
        assertThat(point).isEqualTo(new Point(20, 34));
    }

    @Test
    public void canALinearTransitionReturnsTheDestinationAtTheEndOfTransition() {
        LinearTransition transition = new LinearTransition();
        Point point = transition.getLocation(new Point(20, 34), new Point(100, 100), 100, 0);
        assertThat(point).isEqualTo(new Point(20, 34));
    }

    @Test
    public void canALinearTransitionReturnsTheDestinationAtAnyTime() {
        LinearTransition transition = new LinearTransition();
        Point point = transition.getLocation(new Point(20, 34), new Point(100, 200), 100, 20);
        assertThat(point).isEqualTo(new Point(20 + (int) ((100 - 20) * 20 / 100.0), 34 + (int) ((200 - 34) * 20 / 100.0)));
    }

    @Test
    public void canReturnTheOriginPositionInCaseTheTotalTimeIsNull() {
        LinearTransition transition = new LinearTransition();
        Point point = transition.getLocation(new Point(), new Point(100, 100), 0, 0);
        assertThat(point).isEqualTo(new Point());
    }

    @Test
    public void canSetAMouseTransitionToAMouseNode() {
        MouseNode mouseNode = new MouseNode(new MouseMoveAction(10, 10));
        MouseTransition transition = new LinearTransition();
        mouseNode.setMouseTransition(transition);
        assertThat(mouseNode.getTransition().getType()).isEqualTo(MouseTransition.LINEAR);
        assertThat(mouseNode.getTransition()).isEqualTo(transition);
    }

    @Test
    public void testTheDefaultTransitionForAMouseNodeIsConstantTransition() {
        MouseNode mouseNode = new MouseNode();
        assertThat(mouseNode.getTransition().getType()).isEqualTo(MouseTransition.CONSTANT);
    }

    @Test
    public void canDeterminePositionFromTwoMouseNodeAndALinearTransition() {
        MouseNode mouseNodeA = new MouseNode(new MouseMoveAction(0, 0));
        MouseNode mouseNodeB = new MouseNode(new MouseMoveAction(100, 100));
        mouseNodeA.setNext(mouseNodeB, 100);
        mouseNodeA.setMouseTransition(new LinearTransition());
        Point location = mouseNodeA.getLocation(50);
        assertThat(location).isEqualTo(new Point(50, 50));
    }
}
