public enum LogInCrossPage {
    Log_In_With_Email("Log in with email"),
    Log_In_Done("Log In"),
    Email_Input("Email"),
    Password_Input("Password");
    private final String text;

    LogInCrossPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
