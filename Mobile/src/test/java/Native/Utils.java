package Native;

import Base.Selector;
import Base.Setup;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

import static io.appium.java_client.touch.offset.PointOption.point;

public class Utils extends Setup {

    private static final TouchAction touch = new TouchAction(driver);
    private static final Actions act = new Actions(driver);

    public static void validLogIn() throws InterruptedException {
        //TODO: implement a valid log in test case, used in navigating the rest of the app
        findElement(StartPage.Skip_Btn.getId()).click();
        findElement(StartPage.LOGIN_Btn.getId()).click();
        findElement(LogInPage.LOG_IN_WITH_EMAIL.getId()).click();

        findElement(LogInPage.Email_field.getId()).replaceValue("kariem.taha2.7@gmail.com");
        findElement(LogInPage.Pass_field.getId()).replaceValue("K@kar27200");
        findElement(LogInPage.Done.getId()).click();
        Thread.sleep(1500);
        //try {
        Assert.assertFalse(DoesExist(LogInPage.Pass_field.getId()), "Test Failed with In valid login");
        //} catch (AssertionError e) {
        //    Reporter.log("Test Failed with In valid login ");
        //}

    }

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
            AndroidElement E = findElement(S);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static void touch(final int xOffset, final int yOffset) {
        touch.tap(point(xOffset, yOffset)).perform();
    }

    public static void sendKeys(String txt) {
        act.sendKeys(txt).perform();
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
