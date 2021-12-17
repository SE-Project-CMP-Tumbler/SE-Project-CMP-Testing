package Cross;

public enum SignUpPage {

    SIGN_UP_WITH_EMAIL("Sign up with email"),
    Age_Done("Next"),
    Tags_Done("Next"),
    Email_field("Email"),
    Pass_field("Password"),
    Name_field("Name"),
    Done_Cross("Done"),
    Age_field("How old are you?");

    private final String text;

    SignUpPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
