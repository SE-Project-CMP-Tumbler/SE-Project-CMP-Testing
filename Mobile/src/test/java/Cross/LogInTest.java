package Cross;

import Base.Setup;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;

import static io.appium.java_client.touch.offset.PointOption.point;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static Base.Utils.LogInCombineEmailData;

import io.appium.java_client.MobileElement;
import pro.truongsinh.appium_flutter.FlutterFinder;

public class LogInTest extends Setup {

    @Test(groups = {"Cross"})
    public void LogIn() throws InterruptedException {
        driver.findElementByAccessibilityId(StartPage.LOGIN_Btn.getId()).click();
        driver.findElementByAccessibilityId(LogInPage.Log_In_With_Email.getId()).click();

        List<String[]> comb = LogInCombineEmailData();
        String[] validRow = comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length - 1);

        for (String[] arr : comb) {
            findElementByText(LogInPage.Email_Input.getId()).click();
            findElementByText(LogInPage.Email_Input.getId()).replaceValue(arr[0]);
            Thread.sleep(300);
            findElementByText(LogInPage.Password_Input.getId()).click();
            findElementByText(LogInPage.Password_Input.getId()).replaceValue(arr[1]);

            findElementByContentDesc(LogInPage.Log_In_Done.getId()).click();
            Thread.sleep(3000);
            assert DoesExist(LogInPage.Password_Input.getId()) : "DashBoard should not be entered";
            //Assert.assertTrue(findElementByRescId_Android(LogInPage.Password_Input.getId()).isDisplayed(),"Errrrrrrrrrrrrrr");
        }
        findElementByText(LogInPage.Email_Input.getId()).click();
        findElementByText(LogInPage.Email_Input.getId()).replaceValue(validRow[0]);
        Thread.sleep(300);
        findElementByText(LogInPage.Password_Input.getId()).click();
        findElementByText(LogInPage.Password_Input.getId()).replaceValue(validRow[1]);
        findElementByContentDesc(LogInPage.Log_In_Done.getId()).click();
        Thread.sleep(3000);

        assert !DoesExist(LogInPage.Password_Input.getId()) : "DashBoard should be reached, but it's not";
        assert DoesExist(DashBoardPage.CreatePostButton.getId()) : "New posts should be addable";
    }

    @Test(groups = {"Cross"})
    public void validLogIn() throws InterruptedException {
        //TODO: implement a valid log in test case, used in navigating the rest of the app
        Thread.sleep(7000);
        driver.findElementByAccessibilityId(StartPage.LOGIN_Btn.getId()).click();
        driver.findElementByAccessibilityId(LogInPage.Log_In_With_Email.getId()).click();

        Thread.sleep(2000);
        findElementByText(LogInPage.Email_Input.getId()).click();
        Thread.sleep(500);
        findElementByText(LogInPage.Email_Input.getId()).replaceValue("tester11@tester.com");
        Thread.sleep(500);
        findElementByText(LogInPage.Password_Input.getId()).click();
        findElementByText(LogInPage.Password_Input.getId()).replaceValue("tester11A");
        findElementByContentDesc(LogInPage.Log_In_Done.getId()).click();
        Thread.sleep(3000);

    }

    @Test(groups = {"Cross"})
    public void addPostButton() throws InterruptedException {

//        validLogIn();

        FlutterFinder find = new FlutterFinder(driver);
        Thread.sleep(5000);

        // kimoooooooo
        TouchAction a2 = new TouchAction(driver)
                .tap(point(437, 667))
                .perform();

//        MobileElement add_post_button = find.byValueKey("ActionButton");
//        add_post_button.click();

        findElementByText("Add something, if you'd like").click();
//        findElementByText("Add something, if you'd like").sendKeys("Hello world!");

        // kimoooooooo
        Actions a = new Actions(driver);
        a.sendKeys("foo");
        a.perform();

//        List<String> write_text_args = Arrays.asList("AAAAAAAAAA");
//        Map<String, Object> write_text = ImmutableMap
//                .of("command", "adb shell input text", "args", write_text_args);
//        driver.executeScript("mobile: shell", write_text);

//        driver.executeScript("mobile: shell", Arrays.asList("adb shell input text", "AAAAAAA") );


        findElementByContentDesc("Post").click();


    }
}

