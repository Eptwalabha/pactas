package utility;

import mouse.MouseNode;

import java.awt.*;

/**
 * User: Eptwalabha
 * Date: 19/07/2014
 * Time: 19:39
 */
public class TASPlayer implements Runnable {

    private Robot robot;
    private boolean playing = false;
    private MouseNode partition;

    public TASPlayer(Robot robot) {
        this.robot = robot;
    }

    public void play() {
        playing = true;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        if (partition == null) {
            playing = false;
            return;
        }

        MouseNode cursor = partition;
        playing = true;

        try {
            while (playing){
                cursor.getMouseAction().process(robot);
                Thread.sleep(cursor.getTimeToWaitMillis());
                if ((cursor = cursor.getNext()) == null)
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            playing = false;
        }
    }

    public boolean isPlaying() {
        return playing;
    }

    public void stop() {
        playing = false;
    }

    public void setPartition(MouseNode partition) {
        this.partition = partition;
    }

    public MouseNode getPartition() {
        return partition;
    }

    public Point getLocation(MouseNode mouseNode, float percent) {
        return null;
    }
}
