package Cross;

import Base.Setup;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class Utils extends Setup {

    public static AndroidElement findElementByClassNameIndex(String classNameIndex) {
        return (AndroidElement) driver.findElements(By.xpath(classNameIndex)).get(0);
    }

    public AndroidElement findElementBy(String text) {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                        "new UiSelector().textContains(\"" + text + "\")");
    }
}
