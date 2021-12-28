package Native;

import Base.Setup;
import static Base.Utils.*;
import java.util.List;
import org.testng.annotations.Test;




public class SignUpTest extends Setup {

    @Test(groups = {"Native"})
    public void SignUp() throws InterruptedException {

        findElementByText(StartPage.Skip_Btn.getId()).click();
        findElementByText(StartPage.SIGN_UP_Btn.getId()).click();
        findElementByText(SignUpPage.SIGN_UP_WITH_EMAIL.getId()).click();
        List<String[]> comb = SignUpCombineEmailData("d8c1f7c1-394c-494b-bac1-aed8006efd51@mailslurp.com");
        String[] validRow = comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length - 1);
        for (String[] arr : comb) {
            findElementByRescId_Android(SignUpPage.Email_field.getId()).replaceValue(arr[0]);
            findElementByRescId_Android(SignUpPage.Pass_field.getId()).replaceValue(arr[1]);
            findElementByRescId_Android(SignUpPage.Name_field.getId()).replaceValue(arr[2]);
            findElementByRescId_Android(SignUpPage.Age_field.getId()).replaceValue(arr[3]);
            findElementByRescId_Android(SignUpPage.Done.getId()).click();
            Thread.sleep(3000);
            assert DoesExist(SignUpPage.In_The_SamePage.getId()) : "DashBoard should not be entered";
        }
        findElementByRescId_Android(SignUpPage.Email_field.getId()).replaceValue(validRow[0]);
        findElementByRescId_Android(SignUpPage.Pass_field.getId()).replaceValue(validRow[1]);
        findElementByRescId_Android(SignUpPage.Name_field.getId()).replaceValue(validRow[2]);
        findElementByRescId_Android(SignUpPage.Age_field.getId()).replaceValue(validRow[3]);
        findElementByRescId_Android(SignUpPage.Done.getId()).click();
        Thread.sleep(3000);
        assert !DoesExist(SignUpPage.In_The_SamePage.getId()) : "DashBoard Should be reached";
        assert DoesExist(DashBoardPage.CreatePostButton.getId()) : "New posts should be addable";
    }


}
