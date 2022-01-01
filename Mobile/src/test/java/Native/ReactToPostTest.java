package Native;

import Base.Selector;
import Base.Setup;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static Native.CreatePostTest.TextPost;
import static Native.Utils.DoesExist;
import static Native.Utils.findElement;

@Test(groups = {"Native"})
public class ReactToPostTest extends Setup {
    static String postTxt;

    @Test(groups = {"Native"})
    public void CreatePost() {
        postTxt = TextPost().id;
    }

    @Test(groups = {"Native"}, dependsOnMethods = {"CommentReaction"})
    public void LoveReaction() {
        Integer before = Integer.valueOf(findElement(ReactionsPage.PostNumLoves.getId()).getText());
        findElement(ReactionsPage.LoveButton.getId()).click();
        Integer after = Integer.valueOf(findElement(ReactionsPage.PostNumLoves.getId()).getText());
        Assert.assertTrue(before + 1 == after, "love did not count");
        Selector S = new Selector(Selector.Identifier.findByText, "LIKES");
        Selector M = new Selector(Selector.Identifier.findByText, postTxt);
        //=============================================================================
        findElement(DashBoardPage.ProfileButton.getId()).click();
        findElement(S).click();

        try {
            Assert.assertTrue(DoesExist(M), "Text post was liked , but is not found in the profile page");
        } catch (AssertionError e) {
            Reporter.log(e.getMessage());
        }
        findElement(DashBoardPage.HomeButton.getId()).click();
        Reporter.log("The test was to submit a post, like it" +
                " then navigate to the user profile to see if it appears there too.");

    }

    @Test(groups = {"Native"}, dependsOnMethods = {"CreatePost"})
    public void CommentReaction() {
        findElement(ReactionsPage.CommentButton.getId()).click();
        Selector T = new Selector(Selector.Identifier.findByText, "Say something nice");
        Selector R = new Selector(Selector.Identifier.findByText, "REPLY");
        String Comment = String.valueOf(System.currentTimeMillis());
        findElement(T).sendKeys(Comment);
        T.id = Comment;
        findElement(R).click();

        Assert.assertTrue(DoesExist(T), "note was not submitted");
        try {
            Assert.assertTrue(driver.
                    findElementsByAndroidUIAutomator("new UiSelector().textContains(\"" + T.id + "\")").size() == 1, "the comment is duplicated");
        } catch (AssertionError e) {
            Reporter.log("This is a bug, submitting a comment then navigating to notes tab, two instances of the comment are found");
        }
        driver.navigate().back();
        findElement(ReactionsPage.CommentButton.getId()).click();
        Assert.assertTrue(DoesExist(T), "note was not submitted");
        try {
            Assert.assertTrue(driver.
                    findElementsByAndroidUIAutomator("new UiSelector().textContains(\"" + T.id + "\")").size() == 1, "the comment is duplicated");
        } catch (AssertionError e) {
            Reporter.log("This is a bug, submitting a comment then navigating to notes tab, two instances of the comment are found");
        }
        driver.navigate().back();
        findElement(DashBoardPage.HomeButton.getId()).click();

    }

    /*@Test(groups = {"Native"}, dependsOnMethods = {"CreatePost"})
    public void Follow() {
        findElement(DashBoardPage.SearchButton.getId()).click();
        Selector S=new Selector(Selector.Identifier.findByRescId,"btnFollowBlog");

        if(findElement(S).getText()=="FOLLOW") {
            findElement(S).click();
            findElement(DashBoardPage.ProfileButton.getId()).click();
            Selector T =new Selector(Selector.Identifier.findByText,"FOLLOWING");
            findElement(T).click();
        }

    }*/

}
