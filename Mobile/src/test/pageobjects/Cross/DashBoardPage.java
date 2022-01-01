package Cross;

import Base.Selector;
import Base.Selector.Identifier;

public enum DashBoardPage {
    HomeButton(Identifier.findByText, "Tab "),
    SearchButton(Identifier.findByText, "Search\n"+"Tab "),
    ActivityButton(Identifier.findByText, "Activity\n"+"Tab "),
    ProfileButton(Identifier.findByText, "Profile\n"+"Tab "),
    CreatePostButton(Identifier.findByContDesc, "Hi");
    private final Selector S;

    DashBoardPage(Identifier f, String i) {
        S = new Selector(f, i);
    }

    public Selector getId() {
        return S;
    }
}

