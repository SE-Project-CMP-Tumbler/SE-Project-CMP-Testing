public enum LogInCrossPage {
    Log_In_With_Email("Log in with email"),
    Log_In_Done("Log In"),
    Email_Input("[110,1025][970,1157]"),
    Password_Input("[110,1198][970,1330]");
    private final String text;

    LogInCrossPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
