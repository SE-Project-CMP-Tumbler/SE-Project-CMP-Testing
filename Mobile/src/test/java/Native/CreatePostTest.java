package Native;

import Base.Selector;
import Base.Setup;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Native.Utils.DoesExist;
import static Native.Utils.findElement;

@Test(groups = {"Native"})
public class CreatePostTest extends Setup {
    @Test(groups = {"Native"})
    static private void initPost() {
        try {
            Utils.validLogIn();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findElement(DashBoardPage.CreatePostButton.getId()).click();
    }

    @Test(groups = {"Native"})
    static void TextPost() {
        initPost();
        String time = String.valueOf(System.nanoTime());
        findElement(CreatePostPage.PostTextField.getId()).sendKeys("0" + time);
        Selector S = new Selector(Selector.Identifier.findByText, time);
        findElement(S).click();
        findElement(S).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findElement(CreatePostPage.SubmitPost.getId()).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findElement(DashBoardPage.ProfileButton.getId()).click();


        findElement(S);
        findElement(DashBoardPage.HomeButton.getId()).click();
        Assert.assertTrue(DoesExist(DashBoardPage.HomeButton.getId()), "Did not return to dashboard after post");
    }

    @Test(groups = {"Native"})
    public void PhotoPost() {
        initPost();
    }

    @Test(groups = {"Native"})
    public void LinkPost() {
        initPost();
    }

    @Test(groups = {"Native"})
    public void AudioPost() {
        initPost();
    }

    @Test(groups = {"Native"})
    public void VideoPost() {
        initPost();
    }
}
