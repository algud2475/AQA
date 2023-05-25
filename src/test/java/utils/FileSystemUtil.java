package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileSystemUtil {
    private static final int WAIT_FOR_ROBOT = Integer.parseInt(new JsonSettingsFile("settings.json").getValue("/timeouts/timeoutRobotWorks").toString());

    public static void getFileByPath(String path) {
        StringSelection stringSelection = new StringSelection(path);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        try {
            Robot robot = new Robot();
            robot.delay(WAIT_FOR_ROBOT);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(WAIT_FOR_ROBOT);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            AqualityServices.getLogger().info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
