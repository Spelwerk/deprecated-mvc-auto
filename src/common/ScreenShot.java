package common;

import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import common.enums.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenShot extends Core {
    private WebDriver driver;

    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }

    public String takeFullScreenshot(String testName) throws IOException, AWTException {
        // Takes a screenshot of entire screen and then returns the full filepath so that the image can be used for testing.
        FileType filetype = FileType.PNG;
        Robot robot = new Robot();

        String fullpath = getScreenshotName(testName, filetype);

        BufferedImage fullImg = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        ImageIO.write(fullImg, filetype.fileName(), new File(fullpath));

        System.out.println("Screenshot path: " + fullpath);

        return fullpath;
    }

    public String takePartialScreenshot(String testName, By locator) throws IOException, AWTException {
        // Takes a screenshot of partial screen and then returns the full filepath so that the image can be used for testing.
        FileType filetype = FileType.PNG;
        Robot robot = new Robot();

        String fullpath = getScreenshotName(testName, filetype);

        BufferedImage fullImg = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        // Cropping area to only take screenshot on Locator position.
        WebElement we = driver.findElement(locator);
        Point point = we.getLocation();

        int elementWidth = we.getSize().getWidth();
        int elementHeight = we.getSize().getHeight()+100;

        BufferedImage elementScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);

        ImageIO.write(elementScreenshot, filetype.fileName(), new File(fullpath));

        System.out.println("Screenshot path: " + fullpath);

        return fullpath;
    }

    /** PRIVATE **/

    // Helper method to get filepath for screenshot with png extension.
    private String getScreenshotName(String testName, FileType filetype) {
        String file = testName + "_" + getTimestamp() + filetype.getExt();
        return getFilePath("screenshot", file);
    }
}
