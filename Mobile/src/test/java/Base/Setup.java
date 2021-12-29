package Base;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Setup {
    static public AndroidDriver driver;

    @Test
    @BeforeGroups(groups = {"Native","Cross"})
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
    @Test
    @BeforeGroups(groups = "Native",dependsOnMethods = {"setup"})
    public void NativeApp() {
        final Activity activity = new Activity("com.example.tumbler", "com.example.tumbler.SplashScreenActivity");
        driver.startActivity(activity);
    }
    @Test
    @BeforeGroups(groups = "Cross",dependsOnMethods = {"setup"})
    public void CrossApp() {
        final Activity activity = new Activity("com.tumbler", "com.tumbler.MainActivity");
        driver.startActivity(activity);
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

    public AndroidElement findElementByContentDesc(String text) {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                        "new UiSelector().descriptionContains(\"" + text + "\")");
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

    public AndroidElement findElementByClass(String text) {
        return (AndroidElement)
                driver.findElement(By.className(text));
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
