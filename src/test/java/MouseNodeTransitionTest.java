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
        assertThat(transition.getLocation(new Point(), new Point(), 0f)).isInstanceOf(Point.class);
    }

    @Test
    public void canReturnTheSameValueForAConstantTransitionAtAnyPercentage() {
        ConstantTransition transition = new ConstantTransition();
        assertThat(transition.getLocation(new Point(35, 40), new Point(100, 100), 0)).isEqualTo(new Point(35, 40));
        assertThat(transition.getLocation(new Point(35, 40), new Point(100, 100), 0.5f)).isEqualTo(new Point(35, 40));
        assertThat(transition.getLocation(new Point(35, 40), new Point(100, 100), 1.5f)).isEqualTo(new Point(35, 40));
    }

    @Test
    public void canALinearTransitionReturnsTheOriginAtZeroPercent() {
        LinearTransition transition = new LinearTransition();
        Point point = transition.getLocation(new Point(20, 34), new Point(100, 100), 0f);
        assertThat(point).isEqualTo(new Point(20, 34));
    }

    @Test
    public void canALinearTransitionReturnsTheDestinationAtHundredPercent() {
        LinearTransition transition = new LinearTransition();
        Point point = transition.getLocation(new Point(20, 34), new Point(100, 100), 1f);
        assertThat(point).isEqualTo(new Point(100, 100));
    }

    @Test
    public void canALinearTransitionReturnsTheDestinationAtAnyPercentage() {
        LinearTransition transition = new LinearTransition();
        Point point = transition.getLocation(new Point(20, 34), new Point(100, 200), 0.2f);
        assertThat(point).isEqualTo(new Point(20 + (int) ((100 - 20) * 0.2f), 34 + (int) ((200 - 34) * 0.2f)));
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
        Point location = mouseNodeA.getLocation(0.5f);
        assertThat(location).isEqualTo(new Point(50, 50));
    }
}
