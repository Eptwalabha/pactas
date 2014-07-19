package mock;

import java.awt.*;
import java.util.ArrayList;

/**
 * User: Eptwalabha
 * Date: 19/07/2014
 * Time: 19:49
 */
public class RobotMock extends Robot {

    private int x;
    private int y;
    private int lastButtonMousePressed;
    private int lastButtonMouseReleased;
    public ArrayList<int[]> positions;
    public ArrayList<Integer> pressed;
    public ArrayList<Integer> released;

    public RobotMock() throws AWTException {
        positions = new ArrayList<int[]>();
        pressed = new ArrayList<Integer>();
        released = new ArrayList<Integer>();
    }

    @Override
    public synchronized void mouseMove(int x, int y) {
        this.x = x;
        this.y = y;
        int[] position = new int[2];
        position[0] = x;
        position[1] = y;
        positions.add(position);
    }

    @Override
    public synchronized void mousePress(int button) {
        lastButtonMousePressed = button;
        pressed.add(button);
    }

    @Override
    public synchronized void mouseRelease(int button) {
        lastButtonMouseReleased = button;
        released.add(button);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLastButtonMousePressed() {
        return lastButtonMousePressed;
    }

    public int getLastButtonMouseReleased() {
        return lastButtonMouseReleased;
    }
}