package Native;

public enum SignUpPage {

    SIGN_UP_WITH_EMAIL("SIGN UP WITH EMAIL"),
    SIGN_UP_WITH_Google("SIGN UP IN WITH GOOGLE"),
    In_The_SamePage("What should we call you?"),
    Done("sign_up_done_txt"),
    Email_field("signup_txt_email"),
    Pass_field("signup_txt_password"),
    Name_field("signup_txt_name"),
    Age_field("signup_txt_age");
    private final String text;

    SignUpPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
