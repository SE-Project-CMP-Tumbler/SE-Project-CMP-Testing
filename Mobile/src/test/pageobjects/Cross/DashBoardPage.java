package Cross;

import Base.Selector;

public enum DashBoardPage {
    //CreatePostButton("createPostButton"),
    HomeButton("Home\n"+"Tab "),
    SearchButton("Search\n"+"Tab "),
    ActivityButton("Activity\n"+"Tab "),
    ProfileButton("Profile\n"+"Tab "),
    CreatePostButton("//android.widget.Button[@index='1']");
    private final String text;

    DashBoardPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}

