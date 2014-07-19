package utility;

import mouse.MouseNode;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 19/07/2014
 * Time: 19:39
 */
public class TASPlayer {

    private Robot robot;
    private boolean playing = false;

    public TASPlayer(Robot robot) {
        this.robot = robot;
    }

    public void play(MouseNode partition) {
        playing = true;

        MouseNode cursor = partition;

        try {
            do {
                cursor.getMouseAction().process(robot);
                Thread.sleep(cursor.getTimeToWaitMillis());
                cursor = cursor.getNext();
            } while (cursor != null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying() {
        return true;
    }
}
