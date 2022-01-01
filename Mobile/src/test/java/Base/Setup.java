package Base;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.testng.IReporter;
public class Setup {

    static public AndroidDriver driver;
    static final String platform="Android";
    static final String platformVersion="11";
    static final String automationName="UiAutomator2";
    static final String deviceName="Pixel";
    static final String avd="Pixel";
    static final String crossAppPackage="com.tumbler";
    static final String crossAppActivity="com.tumbler.MainActivity";
    static final Activity crossActivity = new Activity(crossAppPackage, crossAppActivity);
    static final String nativeAppPackage="com.example.tumbler";
    static final String nativeAppActivity="com.example.tumbler.SplashScreenActivity";
    static final Activity nativeActivity = new Activity(nativeAppPackage, nativeAppActivity);
    static final String appiumURL="http://localhost:4723/wd/hub";

    /*
    * Setup function
    *
    * */
    @Test
    @BeforeGroups(groups = {"Native","Cross"})
    public void setup() {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", platform);
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("automationName", automationName);
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("avd",avd);
        caps.setCapability("noReset","true");

        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            System.out.println("Cannot find URL");
        }

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        System.out.println(driver.currentActivity());
    }

    @Test
    @BeforeGroups(groups = "Native",dependsOnMethods = {"setup"})
    public void NativeApp() {
        driver.startActivity(nativeActivity);
    }
    @Test
    @BeforeGroups(groups = "Cross",dependsOnMethods = {"setup"})
    public void CrossApp() {
        driver.startActivity(crossActivity);

    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

}
