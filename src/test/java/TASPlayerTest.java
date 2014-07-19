import mock.RobotMock;
import mouse.MouseNode;
import mouse.actions.MouseMoveAction;
import org.junit.Before;
import org.junit.Test;
import utility.TASPlayer;

import java.awt.*;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Eptwalabha
 * Date: 19/07/2014
 * Time: 18:03
 */
public class TASPlayerTest {

    private TASPlayer tasPlayer;
    private RobotMock robotMock;

    @Before
    public void setUp() throws AWTException {
        robotMock = new RobotMock();
        tasPlayer = new TASPlayer(robotMock);
    }

    @Test
    public void canPlayerLaunch() {
        tasPlayer.play(new MouseNode());
        assertThat(tasPlayer.isPlaying()).isTrue();
    }

    @Test
    public void canPlayAPartition() {
        MouseNode mouseNodeA = new MouseNode(new MouseMoveAction(10, 10));
        MouseNode mouseNodeB = new MouseNode(new MouseMoveAction(100, 50));
        MouseNode mouseNodeC = new MouseNode(new MouseMoveAction(10, 100));
        mouseNodeA.setNext(mouseNodeB);
        mouseNodeB.setNext(mouseNodeC);

        tasPlayer.play(mouseNodeA);
        assertThat(robotMock.positions.size()).isEqualTo(3);
    }

    @Test
    public void canMovePositionWhenPartitionIsPlayed() {
        MouseNode mouseNodeA = new MouseNode(new MouseMoveAction(10, 10));
        MouseNode mouseNodeB = new MouseNode(new MouseMoveAction(100, 50));
        MouseNode mouseNodeC = new MouseNode(new MouseMoveAction(10, 100));
        mouseNodeA.setNext(mouseNodeB);
        mouseNodeB.setNext(mouseNodeC);

        tasPlayer.play(mouseNodeA);
        assertThat(robotMock.positions.get(0)).isEqualTo(new int[]{10, 10});
        assertThat(robotMock.positions.get(1)).isEqualTo(new int[]{100, 50});
        assertThat(robotMock.positions.get(2)).isEqualTo(new int[]{10, 100});
    }

    @Test
    public void canWaitBetweenTwoMouseNodes() {
        MouseNode mouseNodeA = new MouseNode(new MouseMoveAction(100, 95));
        MouseNode mouseNodeB = new MouseNode(new MouseMoveAction(30, 50));
        mouseNodeA.setNext(mouseNodeB, 50);

        long time = System.currentTimeMillis();
        tasPlayer.play(mouseNodeA);
        time = System.currentTimeMillis() - time;
        assertThat(time).isGreaterThanOrEqualTo(50);
    }
}
