package task5.utils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UpLoadFile {

    public static void upLoadImage(String path) {
        try {
            Robot robot = new Robot();
            robot.delay(1000);
            for (int i = 0; i < path.length(); i++) {
                char c = path.charAt(i);
                if (Character.isUpperCase(c)) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                }
                if (c == ':') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(Character.toUpperCase(';'));
                    robot.keyRelease(Character.toUpperCase(';'));
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    robot.keyPress(Character.toUpperCase(c));
                    robot.keyRelease(Character.toUpperCase(c));
                }

                if (Character.isUpperCase(c)) {
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1000);
        } catch (AWTException e) {
            Logger.error(e);
        }
    }
}
