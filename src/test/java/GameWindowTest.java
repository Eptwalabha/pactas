import org.junit.Before;
import org.junit.Test;
import utility.GameWindow;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Eptwalabha
 * Date: 17/07/2014
 * Time: 00:25
 */
public class GameWindowTest {

    private GameWindow gameWindow;

    @Before
    public void setUp() {
        gameWindow = new GameWindow(100, 200);
    }

    @Test
    public void canRetrieveWithOfAGameWindow() {
        assertThat(gameWindow.getWidth()).isEqualTo(100);
    }

    @Test
    public void canRetrieveHeightOfAGameWindow() {
        assertThat(gameWindow.getHeight()).isEqualTo(200);
    }

    @Test
    public void canModifyDimensionOfAnExistingGameWindow() {
        gameWindow.setDimension(300, 10);
        assertThat(gameWindow.getWidth()).isEqualTo(300);
        assertThat(gameWindow.getHeight()).isEqualTo(10);
    }

    @Test
    public void byDefaultPositionXOfGameWindowIsZero() {
        assertThat(gameWindow.getPositionX()).isEqualTo(0);
    }

    @Test
    public void byDefaultPositionYOfGameWindowIsZero() {
        assertThat(gameWindow.getPositionY()).isEqualTo(0);
    }

    @Test
    public void canChangePositionOfGameWindow() {
        gameWindow.setPosition(50, 130);
        assertThat(gameWindow.getPositionX()).isEqualTo(50);
        assertThat(gameWindow.getPositionY()).isEqualTo(130);
    }

    @Test
    public void canReturnAPositionXFromAPercentage() {
        gameWindow.setDimension(100, 200);
        gameWindow.setPosition(50, 60);
        assertThat(gameWindow.getPositionX(0.5f)).isEqualTo((int)(50 + 100 * 0.5f));
    }

    @Test
    public void canReturnAPositionYFromAPercentage() {
        gameWindow.setDimension(100, 200);
        gameWindow.setPosition(50, 60);
        assertThat(gameWindow.getPositionY(0.2f)).isEqualTo((int)(60 + 200 * 0.2f));
    }

    @Test
    public void canReturnAPercentageFromAPositionX() {
        gameWindow.setDimension(100, 200);
        gameWindow.setPosition(50, 60);
        assertThat(gameWindow.getPercentageX(60)).isEqualTo((60 - 50) / (float) 100);
    }

    @Test
    public void canReturnAPercentageFromAPositionY() {
        gameWindow.setDimension(100, 200);
        gameWindow.setPosition(50, 60);
        assertThat(gameWindow.getPercentageY(99)).isEqualTo((99 - 60) / (float) 200);
    }
}
