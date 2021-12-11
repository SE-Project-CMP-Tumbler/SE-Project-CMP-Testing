import io.appium.java_client.android.Activity;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class SignUpTest extends Setup {

    private ArrayList<String[]> CombineEmailData(String emailAddress) {
        String Pass = emailAddress.substring(0, 8);
        String Name = emailAddress.substring(9, 13);
        Random ran = new Random();
        String randString= new String("N"+String.valueOf(1 + ran.nextInt(500000)));
        randString+="Karim@mail.com";

        String[] emails = {"thisEmailAddressIsInvalid", "",randString};
        String[] pass = {"thisPasswordIsInvalid", "",Pass+"Aa"};
        String[] name = {"22@thisNameIsInvalid", "","Karim"};
        String[] Age = {"12", "-1","18"};
        ArrayList<String[]> combinations=new ArrayList<>();
        for (String e:emails) {
            for (String p: pass) {
                for (String n: name) {
                    for(String a:Age)
                    {
                        combinations.add(new String[]{e,p,n,a});
                    }

                }

            }
        }
        return combinations;
    }

    @Test(groups = {"Android"})
    public void SignUpAndroid() throws InterruptedException {
        driver.startActivity(nativeApp);

        findElementByText(StartPage.Android_Skip_Btn.getId()).click();
        findElementByText(StartPage.Android_SIGN_UP_Btn.getId()).click();
        findElementByText(SignUpPage.Android_SIGN_UP_WITH_EMAIL.getId()).click();
        List<String[]> comb =  CombineEmailData("d8c1f7c1-394c-494b-bac1-aed8006efd51@mailslurp.com");
        String [] validRow= comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length-1);
        for (String[] arr : comb) {
            findElementByRescId_Android(SignUpPage.Android_Email_field.getId()).replaceValue(arr[0]);
            findElementByRescId_Android(SignUpPage.Android_Pass_field.getId()).replaceValue(arr[1]);
            findElementByRescId_Android(SignUpPage.Android_Name_field.getId()).replaceValue(arr[2]);
            findElementByRescId_Android(SignUpPage.Android_Age_field.getId()).replaceValue( arr[3]);
            findElementByRescId_Android(SignUpPage.Done_Android.getId()).click();
            Thread.sleep(3000);
            assert DoesExist(SignUpPage.Android_In_The_SamePage.getId()):"DashBoard should not be entered";
        }
        findElementByRescId_Android(SignUpPage.Android_Email_field.getId()).replaceValue(validRow[0]);
        findElementByRescId_Android(SignUpPage.Android_Pass_field.getId()).replaceValue(validRow[1]);
        findElementByRescId_Android(SignUpPage.Android_Name_field.getId()).replaceValue(validRow[2]);
        findElementByRescId_Android(SignUpPage.Android_Age_field.getId()).replaceValue( validRow[3]);
        findElementByRescId_Android(SignUpPage.Done_Android.getId()).click();
        Thread.sleep(3000);
        assert  !DoesExist(SignUpPage.Android_In_The_SamePage.getId()):"DashBoard Should be reached";
        assert  DoesExist(DashBoardPage.CreatePostButton.getId()): "New posts should be addable";
    }
    @Test(groups = {"CrossPlatform"})
    public void SignUpCross ()
    {
        driver.startActivity(cross);

        driver.findElementByAccessibilityId(StartPage.Cross_SIGN_UP_Btn.getId()).click();
    }

}
