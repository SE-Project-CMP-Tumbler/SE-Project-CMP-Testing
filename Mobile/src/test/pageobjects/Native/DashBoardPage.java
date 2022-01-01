package Native;

import Base.Selector;
import Base.Selector.Identifier;

public enum DashBoardPage {
    CreatePostButton(Identifier.findByRescId, "createPostButton"),
    PostObject(Identifier.findByRescId,"post"),
    HomeButton(Identifier.findByContDesc, "Home"),
    SearchButton(Identifier.findByRescId, "Search"),
    MessagesButton(Identifier.findByContDesc, "Activity"),
    ProfileButton(Identifier.findByContDesc, "Profile");
    private final Selector S;

    DashBoardPage(Identifier f, String i) {
        S = new Selector(f, i);
    }

    public Selector getId() {
        return S;
    }
}

