package Cross;

public enum StartPage {
    LOGIN_Btn("Log in"),
    SIGN_UP_Btn("Sign up");


    private final String text;

    StartPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}

