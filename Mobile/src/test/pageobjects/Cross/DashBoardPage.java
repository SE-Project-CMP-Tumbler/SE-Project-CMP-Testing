package Cross;

import Base.Selector;

public enum DashBoardPage {
    //CreatePostButton("createPostButton"),
    HomeButton(Selector.Identifier.findByContDesc, "Home\n" + "Tab 1 of 4"),
    SearchButton(Selector.Identifier.findByContDesc, "Search\n" +"Tab 2 of 4"),
    ActivityButton(Selector.Identifier.findByContDesc, "Activity\n" +"Tab 3 of 4"),
    ProfileButton(Selector.Identifier.findByContDesc, "Profile\n" + "Tab "),
    CreatePostButton(Selector.Identifier.findByContDesc, "Create Post");
    private final Selector S;

    DashBoardPage(Selector.Identifier f, String i) {
        S = new Selector(f, i);
    }

    public Selector getId() {
        return S;
    }
}

