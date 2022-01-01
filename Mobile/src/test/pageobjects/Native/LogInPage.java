package Native;

import Base.Selector;
import Base.Selector.Identifier;

public enum LogInPage {
    LOG_IN_WITH_EMAIL(Identifier.findByText,"LOG IN WITH EMAIL"),
    Email_field(Identifier.findByRescId,"login4_txt_email"),
    Pass_field(Identifier.findByRescId,"login4_txt_password"),
    In_The_SamePage(Identifier.findByText,"Log in"),
    Done(Identifier.findByRescId,"login_with_email4_login_txt");

    private final Selector S;

    LogInPage(Identifier f,String i ) {
        S = new Selector(f,i);
    }

    public Selector getId() {
        return S;
    }
}
