package Cross;

import Base.Setup;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.util.List;

public class CreatePost extends Setup {
    @Test
    private void logIn() {
        // Note: Can be refactored inside the Cross package, not the base package, since different one will be used for android
        // TODO: this function logs in, needed to navigate to the dash board
    }

    private void signUp() {
        // TODO: this function signs up, needed to navigate to the dash board

    }

    @Test(groups = {"Cross"})
    public void Post() throws InterruptedException {
        //logIn();  not necessary to be called, maybe called after logIn test
        // singUp();
        Thread.sleep(10000);
        // click the home button
        findElementByContentDesc(DashBoardPage.HomeButton.getId()).click();
        //Utils.findElementByClassNameIndex(DashBoardPage.CreatePostButton.getId()).click();
        /*
        * TODO:
        *  write post
        *  click Post
        *  Navigate to profile tab
        *  click posts
        *  try to navigate to it
        *  Check if the post appears in the dashboard
        *
        */
    }

}
