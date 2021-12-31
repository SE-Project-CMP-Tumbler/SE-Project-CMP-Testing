package Native;

public enum DashBoardPage {
    CreatePostButton("createPostButton"),
    HomeButton("Home"),
    SearchButton("Search"),
    MessagesButton("Activity"),
    ProfileButton("Profile");
    private final String text;

    DashBoardPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}

