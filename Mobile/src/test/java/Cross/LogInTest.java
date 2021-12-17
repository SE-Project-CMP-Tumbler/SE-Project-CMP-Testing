package Cross;

import Base.Setup;
import static Base.Utils.*;
import org.testng.annotations.Test;
import java.util.List;


public class LogInTest extends Setup {
    @Test(groups = {"Cross"})
    public void LogInCross() throws InterruptedException {
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
}
