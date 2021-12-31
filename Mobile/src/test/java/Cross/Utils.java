package Cross;

import Base.Setup;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static io.appium.java_client.touch.offset.PointOption.point;

public class Utils extends Setup {

    private static final TouchAction touch = new TouchAction(driver);
    private static final Actions act = new Actions(driver);

    public static AndroidElement findElementByClassNameIndex(String classNameIndex) {
        return (AndroidElement) driver.findElements(By.xpath(classNameIndex)).get(0);
    }

    public static AndroidElement findElementBy(String text) {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                        "new UiSelector().textContains(\"" + text + "\")");
    }

    public static void touch( final int xOffset, final int yOffset ) {
        touch.tap(point(xOffset, yOffset)).perform();
    }
    public static void sendKeys(String txt) {
        act.sendKeys(txt).perform();
    }

}
