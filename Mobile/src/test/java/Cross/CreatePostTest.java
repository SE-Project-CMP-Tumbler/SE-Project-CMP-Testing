package Cross;

import Base.Setup;
import org.testng.annotations.Test;
import pro.truongsinh.appium_flutter.FlutterFinder;

public class CreatePostTest extends Setup {
    @Test(groups = {"Cross"})
    public void addPostButton() throws InterruptedException {

        //validLogIn();

        FlutterFinder find = new FlutterFinder(driver);
        Thread.sleep(5000);

        Utils.touch(895, 1800);

        // add_post_button.click();

        Utils.findElementByText("Add something, if you'd like").click();
//        findElementByText("Add something, if you'd like").sendKeys("Hello world!");

        Utils.sendKeys("foo");

//        List<String> write_text_args = Arrays.asList("AAAAAAAAAA");
//        Map<String, Object> write_text = ImmutableMap
//                .of("command", "adb shell input text", "args", write_text_args);
//        driver.executeScript("mobile: shell", write_text);

//        driver.executeScript("mobile: shell", Arrays.asList("adb shell input text", "AAAAAAA") );


        Utils.findElementByContentDesc("Post").click();


    }
}
