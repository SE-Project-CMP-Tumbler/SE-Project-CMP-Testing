package Cross;

import Base.Setup;
import org.testng.annotations.Test;

import java.util.List;

import static Base.Utils.LogInCombineEmailData;

public class LogInTest extends Setup {

    @Test(groups = {"Cross"})
    public void LogIn() throws InterruptedException {
        driver.findElementByAccessibilityId(StartPage.LOGIN_Btn.getId()).click();
        driver.findElementByAccessibilityId(LogInPage.Log_In_With_Email.getId()).click();

        List<String[]> comb = LogInCombineEmailData();
        String[] validRow = comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length - 1);

        for (String[] arr : comb) {
            Utils.findElementByText(LogInPage.Email_Input.getId()).click();
            Utils.findElementByText(LogInPage.Email_Input.getId()).replaceValue(arr[0]);
            Thread.sleep(300);
            Utils.findElementByText(LogInPage.Password_Input.getId()).click();
            Utils.findElementByText(LogInPage.Password_Input.getId()).replaceValue(arr[1]);

            Utils.findElementByContentDesc(LogInPage.Log_In_Done.getId()).click();
            Thread.sleep(3000);
            assert Utils.DoesExist(LogInPage.Password_Input.getId()) : "DashBoard should not be entered";
            //Assert.assertTrue(findElementByRescId(LogInPage.Password_Input.getId()).isDisplayed(),"Errrrrrrrrrrrrrr");
        }
        Utils.findElementByText(LogInPage.Email_Input.getId()).click();
        Utils.findElementByText(LogInPage.Email_Input.getId()).replaceValue(validRow[0]);
        Thread.sleep(300);
        Utils.findElementByText(LogInPage.Password_Input.getId()).click();
        Utils.findElementByText(LogInPage.Password_Input.getId()).replaceValue(validRow[1]);
        Utils.findElementByContentDesc(LogInPage.Log_In_Done.getId()).click();
        Thread.sleep(3000);

        assert !Utils.DoesExist(LogInPage.Password_Input.getId()) : "DashBoard should be reached, but it's not";
        assert Utils.DoesExist(DashBoardPage.CreatePostButton.getId().id) : "New posts should be addable";
    }

    @Test(groups = {"Cross"})
    public void validLogIn() throws InterruptedException {
        //TODO: implement a valid log in test case, used in navigating the rest of the app
        Thread.sleep(7000);
        driver.findElementByAccessibilityId(StartPage.LOGIN_Btn.getId()).click();
        driver.findElementByAccessibilityId(LogInPage.Log_In_With_Email.getId()).click();

        Thread.sleep(2000);
        Utils.findElementByText(LogInPage.Email_Input.getId()).click();
        Thread.sleep(500);
        Utils.findElementByText(LogInPage.Email_Input.getId()).replaceValue("tester11@tester.com");
        Thread.sleep(500);
        Utils.findElementByText(LogInPage.Password_Input.getId()).click();
        Utils.findElementByText(LogInPage.Password_Input.getId()).replaceValue("tester11A");
        Utils.findElementByContentDesc(LogInPage.Log_In_Done.getId()).click();
        Thread.sleep(3000);

    }


}

