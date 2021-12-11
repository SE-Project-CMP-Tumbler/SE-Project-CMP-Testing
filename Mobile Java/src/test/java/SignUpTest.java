import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Test(groups = {"Android"})
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

    public void SignUp() throws InterruptedException {
        findElementByText(StartPage.Skip_Btn_Android.getId()).click();
        findElementByText(StartPage.SIGN_UP_Btn_Android.getId()).click();
        findElementByText(SignUpPage.SIGN_UP_WITH_EMAIL_Android.getId()).click();
        List<String[]> comb =  CombineEmailData("d8c1f7c1-394c-494b-bac1-aed8006efd51@mailslurp.com");
        String [] validRow= comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length-1);
        for (String[] arr : comb) {
            findElementByRescId(SignUpPage.Email_field_Android.getId()).replaceValue(arr[0]);
            findElementByRescId(SignUpPage.Pass_field_Android.getId()).replaceValue(arr[1]);
            findElementByRescId(SignUpPage.Name_field_Android.getId()).replaceValue(arr[2]);
            findElementByRescId(SignUpPage.Age_field_Android.getId()).replaceValue( arr[3]);
            findElementByRescId(SignUpPage.Done_Android.getId()).click();
            Thread.sleep(3000);
            assert findElementByText(SignUpPage.In_The_SamePage.getId()).isDisplayed():"this must fail";
        }
        findElementByRescId(SignUpPage.Email_field_Android.getId()).replaceValue(validRow[0]);
        findElementByRescId(SignUpPage.Pass_field_Android.getId()).replaceValue(validRow[1]);
        findElementByRescId(SignUpPage.Name_field_Android.getId()).replaceValue(validRow[2]);
        findElementByRescId(SignUpPage.Age_field_Android.getId()).replaceValue( validRow[3]);
        findElementByRescId(SignUpPage.Done_Android.getId()).click();
        Thread.sleep(3000);
        assert  !findElementByText(SignUpPage.In_The_SamePage.getId()).isDisplayed():"this should have signed up";
        assert  findElementByRescId(DashBoardPage.CreatePostButton.getId()).isDisplayed(): "we should be able to add posts";
    }

}
