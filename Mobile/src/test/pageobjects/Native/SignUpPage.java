package Native;

import Base.Selector;
import Base.Selector.Identifier;

public enum SignUpPage {

    SIGN_UP_WITH_EMAIL(Identifier.findByText, "SIGN UP WITH EMAIL"),
    SIGN_UP_WITH_Google(Identifier.findByText, "SIGN UP IN WITH GOOGLE"),
    In_The_SamePage(Identifier.findByText, "What should we call you?"),
    Done(Identifier.findByRescId, "sign_up_done_txt"),
    Email_field(Identifier.findByRescId, "signup_txt_email"),
    Pass_field(Identifier.findByRescId, "signup_txt_password"),
    Name_field(Identifier.findByRescId, "signup_txt_name"),
    Age_field(Identifier.findByRescId, "signup_txt_age");
    private final Selector S;

    SignUpPage(Identifier f, String i) {

        S = new Selector(f, i);
    }

    public Selector getId() {
        return S;
    }
}
