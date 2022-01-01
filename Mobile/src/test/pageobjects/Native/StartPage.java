package Native;

import Base.Selector;
import Base.Selector.Identifier;

public enum StartPage {
    Next_Btn(Identifier.findByText,"Next"),
    Skip_Btn(Identifier.findByText,"Skip"),
    LOGIN_Btn(Identifier.findByText,"LOG IN"),
    SIGN_UP_Btn(Identifier.findByText,"SIGN UP");


    private final Selector S;

    StartPage(Identifier f,String i) {
        S=new Selector(f,i);
    }

    public Selector getId() {
        return S;
    }
}

