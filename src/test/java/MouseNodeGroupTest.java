import mouse.actions.MouseMoveAction;
import mouse.group.MouseNodeGroup;
import mouse.MouseNode;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Eptwalabha
 * Date: 17/07/2014
 * Time: 01:04
 */
public class MouseNodeGroupTest {

    private MouseNodeGroup mouseNodeGroup;
    private MouseNode mouseNodeA;
    private MouseNode mouseNodeB;
    private MouseNode mouseNodeC;

    @Before
    public void setUp() {
        mouseNodeGroup = new MouseNodeGroup();
        mouseNodeA = new MouseNode();
        mouseNodeB = new MouseNode();
        mouseNodeC = new MouseNode();

    }

    @Test
    public void canAddAMouseNodeToAGroup() {
        mouseNodeGroup.add(new MouseNode());
        assertThat(mouseNodeGroup.size()).isEqualTo(1);
    }

    @Test
    public void cannotAddAMouseNodeTwiceInTheSameGroup() {
        MouseNode mouseNode = new MouseNode();
        mouseNodeGroup.add(mouseNode);
        mouseNodeGroup.add(mouseNode);
        assertThat(mouseNodeGroup.size()).isEqualTo(1);
    }

    @Test
    public void canRemoveAMouseNodeFromAGroup() {
        MouseNode mouseNode = new MouseNode();
        mouseNodeGroup.add(mouseNode);
        mouseNodeGroup.remove(mouseNode);
        assertThat(mouseNodeGroup.size()).isEqualTo(0);
    }

//    @Test
//    public void GroupManagerCreateAGroup() {
//        GroupManager groupManager = new GroupManager();
//        groupManager.createGroup("toto");
//        assertThat(groupManager.size()).isEqualTo(1);
//    }

    @Test
    public void canMoveAllMouseNodesFromAGroupAtTheSameTime() {
        MouseNode mouseNodeA = new MouseNode();
        MouseMoveAction mouseMoveActionA = new MouseMoveAction(10, 20);
        mouseNodeA.setAction(mouseMoveActionA);
        MouseNode mouseNodeB = new MouseNode();
        MouseMoveAction mouseMoveActionB = new MouseMoveAction(20, 500);
        mouseNodeB.setAction(mouseMoveActionB);

        mouseNodeGroup.add(mouseNodeA);
        mouseNodeGroup.add(mouseNodeB);
        mouseNodeGroup.moveLocation(10, 20);
        assertThat(mouseMoveActionA.getX()).isEqualTo(20);
        assertThat(mouseMoveActionA.getY()).isEqualTo(40);
        assertThat(mouseMoveActionB.getX()).isEqualTo(30);
        assertThat(mouseMoveActionB.getY()).isEqualTo(520);
    }

    @Test
    public void canDeleteAllMouseNodeFromAGroup() {
        MouseNode mouseNodeA = new MouseNode();
        MouseNode mouseNodeB = new MouseNode();

        mouseNodeGroup.add(mouseNodeA);
        mouseNodeGroup.add(mouseNodeB);
        mouseNodeGroup.clearAllMouseNode();
        assertThat(mouseNodeGroup.size()).isEqualTo(0);
    }

    @Test
    public void canDetermineTheBoundingBoxFromAGroupOfMouseNode() {
        mouseNodeA.setAction(new MouseMoveAction(20, 50));
        mouseNodeB.setAction(new MouseMoveAction(200, 30));
        mouseNodeC.setAction(new MouseMoveAction(100, 80));

        mouseNodeGroup.add(mouseNodeA);
        mouseNodeGroup.add(mouseNodeB);
        mouseNodeGroup.add(mouseNodeC);

        Rectangle boundingBox = mouseNodeGroup.getBoundingBox();
        assertThat(boundingBox.x).isEqualTo(20);
        assertThat(boundingBox.y).isEqualTo(30);
        assertThat(boundingBox.width).isEqualTo(180);
        assertThat(boundingBox.height).isEqualTo(50);
    }

    @Test
    public void canRelocateMouseNodePositionWhenGroupBoundingBoxIsResized() {
        MouseMoveAction mouseMoveActionA = new MouseMoveAction(20, 130);
        MouseMoveAction mouseMoveActionB = new MouseMoveAction(100, 50);

        mouseNodeA.setAction(mouseMoveActionA);
        mouseNodeB.setAction(mouseMoveActionB);

        mouseNodeGroup.add(mouseNodeA);
        mouseNodeGroup.add(mouseNodeB);

        mouseNodeGroup.resizeBoundingBox(0.5f, 0.5f);

        assertThat(mouseMoveActionA.getX()).isEqualTo(20);
        assertThat(mouseMoveActionA.getY()).isEqualTo((int) ((130 - 50) * 0.5f) + 50);
        assertThat(mouseMoveActionB.getX()).isEqualTo((int) ((100 - 20) * 0.5f) + 20);
        assertThat(mouseMoveActionB.getY()).isEqualTo(50);
    }
}
