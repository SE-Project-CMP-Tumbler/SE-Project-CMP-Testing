package Native;

import static Base.Utils.*;
import org.testng.annotations.Test;

import java.util.List;

import Base.Setup;

public class LogInTest extends Setup {


    @Test(groups = {"Native"})
    public void LogIn() throws InterruptedException {

        findElementByText(StartPage.Skip_Btn.getId()).click();
        findElementByText(StartPage.LOGIN_Btn.getId()).click();
        findElementByText(LogInPage.LOG_IN_WITH_EMAIL.getId()).click();
        List<String[]> comb = LogInCombineEmailData();
        String[] validRow = comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length - 1);
        for (String[] arr : comb) {
            findElementByRescId_Android(LogInPage.Email_field.getId()).replaceValue(arr[0]);
            findElementByRescId_Android(LogInPage.Pass_field.getId()).replaceValue(arr[1]);

            findElementByRescId_Android(LogInPage.Done.getId()).click();
            Thread.sleep(3000);
            assert DoesExist(LogInPage.Pass_field.getId()) : "DashBoard should not be entered";
        }
        findElementByRescId_Android(LogInPage.Email_field.getId()).replaceValue(validRow[0]);
        findElementByRescId_Android(LogInPage.Pass_field.getId()).replaceValue(validRow[1]);
        findElementByRescId_Android(LogInPage.Done.getId()).click();
        Thread.sleep(3000);

        assert !DoesExist(LogInPage.In_The_SamePage.getId()) : "DashBoard should be reached, but it's not";
        assert DoesExist(DashBoardPage.CreatePostButton.getId()) : "New posts should be addable";
    }



}
