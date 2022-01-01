package Native;

import Base.Selector;
import Base.Setup;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

import static io.appium.java_client.touch.offset.PointOption.point;

public class Utils extends Setup {


    private static AndroidElement findElementByText(String text) {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                        "new UiSelector().textContains(\"" + text + "\")");
    }

    private static AndroidElement findElementByContentDesc(String text) {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                        "new UiSelector().descriptionContains(\"" + text + "\")");
    }

    private static AndroidElement findElementByRescId(String text) {
        String appPackage = "com.example.tumbler";

        return (AndroidElement)
                driver.findElement(By.id(appPackage + ":id/" + text));
    }

    public static AndroidElement findElementByBounds(String text) {
        return (AndroidElement)
                driver.findElement(By.xpath("//[@bounds='" + text + "']"));
    }

    public static List<AndroidElement> findElementByClass(String text) {
        return
                (List<AndroidElement>) driver.findElement(By.className(text));
    }

    public static boolean DoesExist(Selector S) {
        try {
            findElement(S);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static AndroidElement findElement(Base.Selector S) {
        switch (S.func) {
            case findByText -> {
                return findElementByText(S.id);
            }
            case findByContDesc -> {
                return findElementByContentDesc(S.id);
            }
            case findByRescId -> {
                return findElementByRescId(S.id);
            }
            default -> {
                return null;
            }
        }
    }
}
