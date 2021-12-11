public enum SignUpPage {

    SIGN_UP_WITH_EMAIL_Android("SIGN UP WITH EMAIL"),
    SIGN_UP_WITH_Google_Android("SIGN UP IN WITH GOOGLE"),
    In_The_SamePage("What should we call you?"),
    Done_Android("sign_up_done_txt"),
    Email_field_Android("signup_txt_email"),
    Pass_field_Android("signup_txt_password"),
    Name_field_Android("signup_txt_name"),
    Age_field_Android("signup_txt_age");
    private final String text;

    SignUpPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
