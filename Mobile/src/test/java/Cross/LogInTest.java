package Cross;

import Base.Setup;
import org.testng.annotations.Test;

import java.util.List;

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
            findElementByText(LogInPage.Password_Input.getId()).click();
            findElementByText(LogInPage.Password_Input.getId()).replaceValue(arr[1]);

            findElementByContentDesc(LogInPage.Log_In_Done.getId()).click();
            Thread.sleep(3000);
            assert DoesExist(LogInPage.Password_Input.getId()) : "DashBoard should not be entered";
            //Assert.assertTrue(findElementByRescId_Android(LogInPage.Password_Input.getId()).isDisplayed(),"Errrrrrrrrrrrrrr");
        }
        findElementByText(LogInPage.Email_Input.getId()).click();
        findElementByText(LogInPage.Email_Input.getId()).replaceValue(validRow[0]);
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
        FlutterFinder find = new FlutterFinder(driver);
        Thread.sleep(3000);

        MobileElement  add_post_button = find.byValueKey("ActionButton");
        add_post_button.click();
    }

}
