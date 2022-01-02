package Cross;

import Base.Selector;
import Base.Setup;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class Utils extends Setup {


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
    public static boolean DoesExist_SEL(Selector S) {
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
    public static AndroidElement findElementByClassNameIndex(String classNameIndex) {
        return (AndroidElement) driver.findElements(By.xpath(classNameIndex)).get(0);
    }

    public static AndroidElement findElementBy(String text) {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                        "new UiSelector().textContains(\"" + text + "\")");
    }
}
