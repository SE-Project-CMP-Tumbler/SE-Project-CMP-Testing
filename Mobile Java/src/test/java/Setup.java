import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Setup {
    static final public Activity cross = new Activity("com.tumbler", "com.tumbler.MainActivity");
    static final public Activity nativeApp = new Activity("com.example.tumbler", "com.example.tumbler.SplashScreenActivity");
    static public AndroidDriver driver;

    @BeforeTest
    public void setup() {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceName", "Pixel");
        //caps.setCapability("appPackage","com.example.tumbler");
        //caps.setCapability("appActivity","com.example.tumbler.SplashScreenActivity");
        caps.setCapability("avd", "Pixel");

        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            System.out.println("Cannot find URL");
        }

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    @AfterTest
    public void Close() {
        if (driver != null)
            driver.quit();
    }

    public AndroidElement findElementByText(String text) {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                        "new UiSelector().textContains(\"" + text + "\")");
    }

    public AndroidElement findElementByRescId_Android(String text) {
        String appPackage = "com.example.tumbler";

        return (AndroidElement)
                driver.findElement(By.id(appPackage + ":id/" + text));
    }

    public AndroidElement findElementByRescId_Cross(String text) {
        String appPackage = "com.tumbler";

        return (AndroidElement)
                driver.findElement(By.id(appPackage + ":id/" + text));
    }

    public AndroidElement findElementByBounds(String text) {
        return (AndroidElement)
                driver.findElement(By.xpath("//[@bounds='" + text + "']"));
    }

    public boolean DoesExist(String text) {
        try {
            findElementByRescId_Android(text);
            return true;
        } catch (Exception e) {
            try {
                findElementByText(text);
                return true;
            } catch (Exception ee) {
                try {
                    findElementByRescId_Cross(text);
                    return true;
                } catch (Exception eee) {
                    return false;
                }
            }
        }
    }
}
