package Cross;

public enum LogInPage {
    Email_Input("Email"),
    Log_In_Done("Log In"),
    Log_In_With_Email("Log in with email"),
    Password_Input("Password");
    private final String text;

    LogInPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}