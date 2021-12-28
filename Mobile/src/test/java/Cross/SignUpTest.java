package Cross;

import Base.Setup;
import org.testng.annotations.Test;

import java.util.List;

import static Base.Utils.SignUpCombineEmailData;

public class SignUpTest extends Setup {
    @Test(groups = {"Cross"})
    public void SignUp() throws InterruptedException {

        driver.findElementByAccessibilityId(StartPage.SIGN_UP_Btn.getId()).click();
        driver.findElementByAccessibilityId(SignUpPage.SIGN_UP_WITH_EMAIL.getId()).click();
        findElementByText(SignUpPage.Age_field.getId()).click();
        findElementByText(SignUpPage.Age_field.getId()).replaceValue("3");
        findElementByContentDesc(SignUpPage.Age_Done.getId()).click();
        assert DoesExist(SignUpPage.Age_field.getId()) : "Age 3 is invalid";

        findElementByText(SignUpPage.Age_field.getId()).click();
        findElementByText(SignUpPage.Age_field.getId()).replaceValue("23");
        findElementByContentDesc(SignUpPage.Age_Done.getId()).click();
        assert !DoesExist(SignUpPage.Age_field.getId()) : "Age 23 is valid";

        // select 5 tags
        findElementByContentDesc("Art").click();
        findElementByContentDesc("Gaming").click();
        findElementByContentDesc("Writing").click();
        findElementByContentDesc("Positivity").click();
        findElementByContentDesc("Funny").click();
        findElementByContentDesc("Comics").click();
        findElementByContentDesc(SignUpPage.Tags_Done.getId()).click();

        List<String[]> comb = SignUpCombineEmailData("d8c1f7c1-394c-494b-bac1-aed8006efd51@mailslurp.com");
        String[] validRow = comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length - 1);
        for (String[] arr : comb) {
            findElementByText(SignUpPage.Email_field.getId()).click();
            findElementByText(SignUpPage.Email_field.getId()).replaceValue(arr[0]);
            findElementByText(SignUpPage.Pass_field.getId()).click();
            findElementByText(SignUpPage.Pass_field.getId()).replaceValue(arr[1]);
            findElementByText(SignUpPage.Name_field.getId()).click();
            findElementByText(SignUpPage.Name_field.getId()).replaceValue(arr[2]);
            findElementByContentDesc(SignUpPage.Done_Cross.getId()).click();
            Thread.sleep(3000);
            assert DoesExist(SignUpPage.Pass_field.getId()) : "DashBoard should not be entered";
        }
        findElementByText(SignUpPage.Email_field.getId()).click();
        findElementByText(SignUpPage.Email_field.getId()).replaceValue(validRow[0]);
        findElementByText(SignUpPage.Pass_field.getId()).click();
        findElementByText(SignUpPage.Pass_field.getId()).replaceValue(validRow[1]);
        findElementByText(SignUpPage.Name_field.getId()).click();
        findElementByText(SignUpPage.Name_field.getId()).replaceValue(validRow[2]);
        findElementByContentDesc(SignUpPage.Done_Cross.getId()).click();
        Thread.sleep(3000);
        assert !DoesExist(SignUpPage.Pass_field.getId()) : "DashBoard Should be reached";
        assert DoesExist(DashBoardPage.CreatePostButton.getId()) : "New posts should be addable";

    }

    public void validSignUp() {
        //TODO: implement a valid sign up test case
    }

}
