package Cross;

import Base.Selector;
import Base.Setup;
import Native.CreatePostPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static Native.Utils.DoesExist;
import static Native.Utils.findElement;

public class CreatePost extends Setup {
    public static Selector TextPost() {
        findElement(Cross.DashBoardPage.CreatePostButton.getId()).click();
        String time = String.valueOf(System.nanoTime());
        findElement(CreatePostPage.PostTextField.getId()).sendKeys(time);
        Selector S = new Selector(Selector.Identifier.findByText, time);
        findElement(S).click();
        findElement(CreatePostPage.AddStyle.getId()).click();
        findElement(CreatePostPage.AddStyle.getId()).click();
        findElement(CreatePostPage.SubmitPost.getId()).click();
        findElement(Cross.DashBoardPage.HomeButton.getId()).click();
        findElement(Cross.DashBoardPage.HomeButton.getId()).click();
        findElement(Cross.DashBoardPage.HomeButton.getId()).click();

        Assert.assertTrue(DoesExist(S), "Text post failed");
        //==================================================================
        findElement(Cross.DashBoardPage.ProfileButton.getId()).click();
        try {
            Assert.assertTrue(DoesExist(S), "Text post was submitted in the dashboard, but is not found in the profile page");
        } catch (AssertionError e) {
            Reporter.log(e.getMessage());
        }
        findElement(Cross.DashBoardPage.HomeButton.getId()).click();
        Reporter.log("The test was to submit a post and check if it appears in the dashboard," +
                " then navigate to the user profile to see if it appears there too.");

        return S;
    }

    @Test
    private void logIn() {
        // Note: Can be refactored inside the Cross package, not the base package, since different one will be used for android
        // TODO: this function logs in, needed to navigate to the dash board
    }

    private void signUp() {
        // TODO: this function signs up, needed to navigate to the dash board

    }

    @Test(groups = {"Cross"})
    public void Post() {
        //logIn();  not necessary to be called, maybe called after logIn test
        // singUp();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // click the home button
        findElement(DashBoardPage.CreatePostButton.getId()).click();
        findElement(DashBoardPage.HomeButton.getId()).click();

        /*
         * TODO:
         *  write post
         *  click Post
         *  Navigate to profile tab
         *  click posts
         *  try to navigate to it
         *  Check if the post appears in the dashboard
         */
    }

}
