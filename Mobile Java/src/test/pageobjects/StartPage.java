public enum StartPage {
    Next_Btn_Android("Next"),
    Skip_Btn_Android("Skip"),
    LOGIN_Btn_Android("LOG IN"),
    SIGN_UP_Btn_Android("SIGN UP");
    private final String text;

    StartPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}

