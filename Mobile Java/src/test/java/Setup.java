import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Setup {
    static public AndroidDriver driver;

    @BeforeTest
    public void setup()
    {
        String appPackage="com.example.tumbler";
        String appActivity="com.example.tumbler.SplashScreenActivity";
        Activity activity=new Activity(appPackage,appActivity);

        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("platformVersion","11");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("deviceName","Pixel");
        caps.setCapability("appPackage","com.example.tumbler");
        caps.setCapability("appActivity","com.example.tumbler.SplashScreenActivity");
        caps.setCapability("avd","Pixel");

        try {
            driver =new AndroidDriver(new URL("http://localhost:4723/wd/hub"),caps);
        } catch (MalformedURLException e) {
            System.out.println("Cannot find URL");
        }
        //driver.startActivity(activity);

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }
    @AfterTest
    public void Close()
    {
        if (driver!=null)
            driver.quit();
    }
    public AndroidElement findElementByText( String text)
    {
        return (AndroidElement)
                driver.findElementByAndroidUIAutomator(
                "new UiSelector().textContains(\""+text+"\")");
    }
    public AndroidElement findElementByRescId( String text)
    {
        return (AndroidElement)
                driver.findElement(By.id(text));
    }
}
