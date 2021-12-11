import org.testng.annotations.Test;

@Test(groups = {"Android"})
public class LogInTest extends Setup {

    private List<String[]> CombineEmailData()) {

        String[] emails = {"invalid.com@", "","tester11@tester.com"};
        String[] pass = {"weak", "","tester11A"};
        ArrayList<String[]> combinations=new ArrayList<>();
        for (String e:emails) {
          for (String p: pass) {
            {
              combinations.add(new String[]{e,p});
            }
        }
        return combinations;
    }

    public void LogIn() throws Exception {
        findElementByText(StartPage.Skip_Btn_Android.getId()).click();
        findElementByText(StartPage.LOGIN_Btn_Android.getId()).click();
        findElementByText(LogInPage.LOG_IN_WITH_EMAIL_ANDROID.getId()).click();
        List<String[]> comb =  CombineEmailData();
        String [] validRow= comb.get((comb.toArray().length - 1));
        comb.remove(comb.toArray().length-1);
        for (String[] arr : comb) {
            findElementByRescId(LogInPage.Email_field_Android.getId()).replaceValue(arr[0]);
            findElementByRescId(LogInPage.Pass_field_Android.getId()).replaceValue(arr[1]);

            findElementByRescId(LogInPage.Done_Android.getId()).click();
            Thread.sleep(3000);
            assert findElementByRescId(LogInPage.Pass_field_Android.getId()).isDisplayed():"this must fail";
        }
        findElementByRescId(LogInPage.Email_field_Android.getId()).replaceValue(validRow[0]);
        findElementByRescId(LogInPage.Pass_field_Android.getId()).replaceValue(validRow[1]);
        findElementByRescId(LogInPage.Done_Android.getId()).click();
        Thread.sleep(3000);
        assert  !findElementByRescId(LogInPage.Pass_field_Android.getId()).isDisplayed():"this should have logged in";
        assert  findElementByRescId(DashBoardPage.CreatePostButton.getId()).isDisplayed(): "we should be able to add posts";
    }

}
