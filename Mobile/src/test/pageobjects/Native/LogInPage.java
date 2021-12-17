package Native;

public enum LogInPage {
    LOG_IN_WITH_EMAIL("LOG IN WITH EMAIL"),
    Email_field("login4_txt_email"),
    Pass_field("login4_txt_password"),
    In_The_SamePage("Log in"),
    Done("login_with_email4_login_txt");

    private final String text;

    LogInPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
