public enum DashBoardPage {
    CreatePostButton("createPostButton");
    private final String text;

    DashBoardPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}

