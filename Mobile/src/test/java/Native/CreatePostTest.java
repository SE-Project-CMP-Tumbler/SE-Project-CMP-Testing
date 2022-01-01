package Native;

import Base.Selector;
import Base.Setup;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Random;

import static Native.Utils.DoesExist;
import static Native.Utils.findElement;

@Test(groups = {"Native"})
public class CreatePostTest extends Setup {
    @Test(groups = {"Native"})
    public static void validLogIn() {
        //TODO: implement a valid log in test case, used in navigating the rest of the app
        findElement(StartPage.Skip_Btn.getId()).click();
        findElement(StartPage.LOGIN_Btn.getId()).click();
        findElement(LogInPage.LOG_IN_WITH_EMAIL.getId()).click();

        findElement(LogInPage.Email_field.getId()).replaceValue("kariem.taha2.7@gmail.com");
        findElement(LogInPage.Pass_field.getId()).replaceValue("K@kar27200");
        findElement(LogInPage.Done.getId()).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //try {
        Assert.assertFalse(DoesExist(LogInPage.Pass_field.getId()), "Test Failed with In valid login");
        //} catch (AssertionError e) {
        //    Reporter.log("Test Failed with In valid login ");
        //}

    }

    @Test(groups = {"Native"}, dependsOnMethods = {"validLogIn", "LinkPost"})
    public static Selector TextPost() {
        findElement(DashBoardPage.CreatePostButton.getId()).click();
        String time = String.valueOf(System.nanoTime());
        findElement(CreatePostPage.PostTextField.getId()).sendKeys(time);
        Selector S = new Selector(Selector.Identifier.findByText, time);
        findElement(S).click();
        findElement(CreatePostPage.AddStyle.getId()).click();
        findElement(CreatePostPage.AddStyle.getId()).click();
        findElement(CreatePostPage.SubmitPost.getId()).click();
        findElement(DashBoardPage.HomeButton.getId()).click();
        findElement(DashBoardPage.HomeButton.getId()).click();
        findElement(DashBoardPage.HomeButton.getId()).click();

        Assert.assertTrue(DoesExist(S), "Text post failed");
        //==================================================================
        findElement(DashBoardPage.ProfileButton.getId()).click();
        try {
            Assert.assertTrue(DoesExist(S), "Text post was submitted in the dashboard, but is not found in the profile page");
        } catch (AssertionError e) {
            Reporter.log(e.getMessage());
        }
        findElement(DashBoardPage.HomeButton.getId()).click();
        Reporter.log("The test was to submit a post and check if it appears in the dashboard," +
                " then navigate to the user profile to see if it appears there too.");

        return S;
    }

    /*@Test(groups = {"Native"})
    public void PhotoPost() {
        initPost();
    }*/

    @Test(groups = {"Native"}, dependsOnMethods = {"validLogIn"})
    public static void LinkPost() {
        findElement(DashBoardPage.CreatePostButton.getId()).click();
        final String Java = "Java" + (new Random(System.currentTimeMillis())).nextInt() % 5000;
        final String WikiJava = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        findElement(CreatePostPage.PostTextField.getId()).click();
        findElement(CreatePostPage.AddUrl.getId()).click();
        Selector S = new Selector(Selector.Identifier.findByText, "Enter the hyper word");
        Selector M = new Selector(Selector.Identifier.findByText, "Add a Link Url");
        Selector O = new Selector(Selector.Identifier.findByText, "OK");
        findElement(S).sendKeys(Java);
        findElement(M).sendKeys(WikiJava);
        findElement(O).click();
        findElement(CreatePostPage.SubmitPost.getId()).click();
        findElement(DashBoardPage.HomeButton.getId()).click();
        findElement(DashBoardPage.HomeButton.getId()).click();
        findElement(DashBoardPage.HomeButton.getId()).click();

        Selector Link = new Selector(Selector.Identifier.findByText, Java);
        Assert.assertTrue(DoesExist(Link), "hyper link not found");
        //===================================================================
        findElement(DashBoardPage.ProfileButton.getId()).click();
        try {
            Assert.assertTrue(DoesExist(Link), "Text post was submitted in the dashboard, but is not found in the profile page");
        } catch (AssertionError e) {
            Reporter.log(e.getMessage());
        }
        findElement(DashBoardPage.HomeButton.getId()).click();
        Reporter.log("The test was to submit a post and check if it appears in the dashboard," +
                " then navigate to the user profile to see if it appears there too.");

    }

    /*@Test(groups = {"Native"})
    public void AudioPost() {
        initPost();
    }*/

    /*@Test(groups = {"Native"})
    public void VideoPost() {
        initPost();
    }*/
}
