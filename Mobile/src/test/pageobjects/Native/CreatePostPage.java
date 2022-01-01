package Native;

import Base.Selector;
import Base.Selector.Identifier;

public enum CreatePostPage {

    SubmitPost(Identifier.findByRescId, "submitPost"),
    PostTextField(Identifier.findByText, "Add something"), // resource-id
    ExitCreatePost(Identifier.findByRescId, "exitCreatePost"),
    AddTag(Identifier.findByRescId, "addTag"),
    AddStyle(Identifier.findByRescId, "addStyle"),
    AddUrl(Identifier.findByRescId, "addUrl"),
    AddGif(Identifier.findByRescId, "addGif"),
    AddPhoto(Identifier.findByRescId, "addPhoto"),
    AddMusic(Identifier.findByRescId, "addMusic"),

    ;
    private final Selector S;

    CreatePostPage(Identifier f, String i) {

        S = new Selector(f, i);
    }


    public Selector getId() {
        return S;
    }
}
