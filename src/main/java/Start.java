import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.*;

/**
 * User: Eptwalabha
 * Date: 12/07/2014
 * Time: 17:02
 */
public class Start {

    public static void main(String[] args) {

        Test t = new Test();
        System.out.println("test before couter = " + t.counter);

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();

            System.exit(1);
        }

        GlobalScreen.getInstance().addNativeMouseMotionListener(t);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        long time = System.currentTimeMillis();

//        while (System.currentTimeMillis() - time < 1000) {
//
//        }

        System.out.println("test after couter = " + t.counter);

    }

    public static class Test implements NativeMouseMotionListener, NativeMouseListener, NativeMouseWheelListener {

        public int counter;

        public Test() {
            counter = 0;
        }

        @Override
        public void nativeMouseMoved(NativeMouseEvent e) {
            ++counter;
        }

        @Override
        public void nativeMouseDragged(NativeMouseEvent e) {
            ++counter;
        }

        @Override
        public void nativeMouseClicked(NativeMouseEvent e) {

        }

        @Override
        public void nativeMousePressed(NativeMouseEvent e) {

        }

        @Override
        public void nativeMouseReleased(NativeMouseEvent e) {

        }

        @Override
        public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {

        }
    }


}
