package Native;

import Base.Setup;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

import static Base.Utils.LogInCombineEmailData;
import static Native.Utils.DoesExist;
import static Native.Utils.findElement;

public class LogInTest extends Setup {


    @Test(groups = {"Native", "Old"})
    public void LogIn() throws InterruptedException {

        //findElement(StartPage.Skip_Btn.getId()).click();
        findElement(StartPage.LOGIN_Btn.getId()).click();
        findElement(LogInPage.LOG_IN_WITH_EMAIL.getId()).click();

        List<String[]> comb = LogInCombineEmailData();
        String[] validRow = comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length - 1);
        for (String[] arr : comb) {
            findElement(LogInPage.Email_field.getId()).replaceValue(arr[0]);
            findElement(LogInPage.Pass_field.getId()).replaceValue(arr[1]);

            findElement(LogInPage.Done.getId()).click();
            Thread.sleep(3000);
            try {
                Assert.assertTrue(DoesExist(LogInPage.Pass_field.getId()), "DashBoard should not be entered");
            } catch (AssertionError e) {
                Reporter.log("Test Failed with input " + arr[0] + " " + arr[1]);
            }
        }
        findElement(LogInPage.Email_field.getId()).replaceValue(validRow[0]);
        findElement(LogInPage.Pass_field.getId()).replaceValue(validRow[1]);
        findElement(LogInPage.Done.getId()).click();
        Thread.sleep(3000);

        Assert.assertFalse(DoesExist(LogInPage.In_The_SamePage.getId()), "DashBoard should be reached, but it's not");
        Assert.assertTrue(DoesExist(DashBoardPage.CreatePostButton.getId()), "New posts should be addable");
    }


}
