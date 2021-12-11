public enum LogInNativePage {
    ANDROID_LOG_IN_WITH_EMAIL("LOG IN WITH EMAIL"),
    Android_Email_field("login4_txt_email"),
    Android_Pass_field("login4_txt_password"),
    Android_In_The_SamePage("Log in"),
    Android_Done("login_with_email4_login_txt"),
    Cross_Log_In_With_Email("Log in with email");

    private final String text;

    LogInNativePage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
