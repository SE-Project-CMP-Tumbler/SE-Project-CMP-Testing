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

    public static AndroidElement findElementByText(String text) {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                        "new UiSelector().textContains(\"" + text + "\")");
    }

    public static AndroidElement findElementByContentDesc(String text) {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                        "new UiSelector().descriptionContains(\"" + text + "\")");
    }

    public static AndroidElement findElementByRescId(String text) {
        String appPackage = "com.tumbler";

        return (AndroidElement)
                driver.findElement(By.id(appPackage + ":id/" + text));
    }


    public static AndroidElement findElementByBounds(String text) {
        return (AndroidElement)
                driver.findElement(By.xpath("//[@bounds='" + text + "']"));
    }

    public static AndroidElement findElementByClass(String text) {
        return (AndroidElement)
                driver.findElement(By.className(text));
    }

    public static boolean DoesExist(String text) {
        try {
                findElementByText(text);
                return true;
        } catch (Exception ee) {
                try {
                    findElementByRescId(text);
                    return true;
                } catch (Exception eee) {
                    return false;
                }
        }
    }
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
