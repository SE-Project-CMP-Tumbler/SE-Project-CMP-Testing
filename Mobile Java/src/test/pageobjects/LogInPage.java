public enum LogInPage {
    LOG_IN_WITH_EMAIL_ANDROID("LOG IN WITH EMAIL"),
    Email_field_Android("login4_txt_email"),
    Pass_field_Android("login4_txt_password"),
    Done_Android("login_with_email4_login_txt");

    private final String text;

    LogInPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
