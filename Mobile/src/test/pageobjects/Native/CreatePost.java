package Native;

public enum CreatePost {
    SubmitPost("submitPost"),
    PostTextField("editor"),
    ExitCreatePost("exitCreatePost"),
    AddTag("addTag"),
    ;
    private final String text;

    CreatePost(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
