package Native;

public enum StartPage {
    Next_Btn("Next"),
    Skip_Btn("Skip"),
    LOGIN_Btn("LOG IN"),
    SIGN_UP_Btn("SIGN UP");


    private final String text;

    StartPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}

