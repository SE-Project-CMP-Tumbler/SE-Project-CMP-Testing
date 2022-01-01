package Native;

import Base.Setup;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static Base.Utils.SignUpCombineEmailData;
import static Native.Utils.findElement;


public class SignUpTest extends Setup {

    @Test(groups = {"Native", "Old"})
    public void SignUp() throws InterruptedException {
        findElement(StartPage.Skip_Btn.getId()).click();
        findElement(StartPage.SIGN_UP_Btn.getId()).click();
        findElement(SignUpPage.SIGN_UP_WITH_EMAIL.getId()).click();

        List<String[]> comb = SignUpCombineEmailData("d8c1f7c1-394c-494b-bac1-aed8006efd51@mailslurp.com");
        String[] validRow = comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length - 1);
        for (String[] arr : comb) {
            findElement(SignUpPage.Email_field.getId()).replaceValue(arr[0]);
            findElement(SignUpPage.Pass_field.getId()).replaceValue(arr[1]);
            findElement(SignUpPage.Name_field.getId()).replaceValue(arr[2]);
            findElement(SignUpPage.Age_field.getId()).replaceValue(arr[3]);
            findElement(SignUpPage.Done.getId()).click();
            Thread.sleep(3000);
            Assert.assertTrue(Utils.DoesExist(SignUpPage.In_The_SamePage.getId()), "DashBoard should not be entered");
        }
        findElement(SignUpPage.Email_field.getId()).replaceValue(validRow[0]);
        findElement(SignUpPage.Pass_field.getId()).replaceValue(validRow[1]);
        findElement(SignUpPage.Name_field.getId()).replaceValue(validRow[2]);
        findElement(SignUpPage.Age_field.getId()).replaceValue(validRow[3]);
        findElement(SignUpPage.Done.getId()).click();
        Thread.sleep(1500);
        driver.navigate().back();
        driver.navigate().back();
    }
}
