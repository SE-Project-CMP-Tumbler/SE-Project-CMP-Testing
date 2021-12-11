public enum StartPage {
    Android_Next_Btn("Next"),
    Android_Skip_Btn("Skip"),
    Android_LOGIN_Btn("LOG IN"),
    Android_SIGN_UP_Btn("SIGN UP"),
    Cross_LOGIN_Btn("Log in"),
    Cross_SIGN_UP_Btn("Sign up");


    private final String text;

    StartPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}

