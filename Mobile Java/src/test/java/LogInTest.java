import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LogInTest extends Setup {

    private List<String[]> CombineEmailData() {

        String[] emails = {"invalid.com@", "", "tester11@tester.com"};
        String[] pass = {"weak", "", "tester11A"};
        ArrayList<String[]> combinations = new ArrayList<>();
        for (String e : emails) {
            for (String p : pass) {
                {
                    combinations.add(new String[]{e, p});
                }
            }
        }
        return combinations;
    }

    @Test(groups = {"Android"})
    public void LogInAndroid() throws InterruptedException {
        driver.startActivity(nativeApp);

        findElementByText(StartPage.Android_Skip_Btn.getId()).click();
        findElementByText(StartPage.Android_LOGIN_Btn.getId()).click();
        findElementByText(LogInNativePage.ANDROID_LOG_IN_WITH_EMAIL.getId()).click();
        List<String[]> comb = CombineEmailData();
        String[] validRow = comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length - 1);
        for (String[] arr : comb) {
            findElementByRescId_Android(LogInNativePage.Android_Email_field.getId()).replaceValue(arr[0]);
            findElementByRescId_Android(LogInNativePage.Android_Pass_field.getId()).replaceValue(arr[1]);

            findElementByRescId_Android(LogInNativePage.Android_Done.getId()).click();
            Thread.sleep(3000);
            assert DoesExist(LogInNativePage.Android_Pass_field.getId()) : "DashBoard should not be entered";
        }
        findElementByRescId_Android(LogInNativePage.Android_Email_field.getId()).replaceValue(validRow[0]);
        findElementByRescId_Android(LogInNativePage.Android_Pass_field.getId()).replaceValue(validRow[1]);
        findElementByRescId_Android(LogInNativePage.Android_Done.getId()).click();
        Thread.sleep(3000);

        assert !DoesExist(LogInNativePage.Android_In_The_SamePage.getId()) : "DashBoard should be reached, but it's not";
        assert DoesExist(DashBoardPage.CreatePostButton.getId()) : "New posts should be addable";
    }

    @Test(groups = {"CrossPlatform"})
    public void LogInCross() throws InterruptedException {
        driver.startActivity(cross);
        driver.findElementByAccessibilityId(StartPage.Cross_LOGIN_Btn.getId()).click();
        driver.findElementByAccessibilityId(LogInCrossPage.Log_In_With_Email.getId()).click();
        findElementByBounds(LogInCrossPage.Email_Input.getId()).replaceValue("AAAAAAAAAA");
        findElementByBounds(LogInCrossPage.Password_Input.getId()).replaceValue("AAAAAAAAAA");
        driver.findElementByAccessibilityId(LogInCrossPage.Log_In_Done.getId()).click();

    }

}
