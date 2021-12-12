public enum SignUpPage {

    Android_SIGN_UP_WITH_EMAIL("SIGN UP WITH EMAIL"),
    Android_SIGN_UP_WITH_Google("SIGN UP IN WITH GOOGLE"),
    Android_In_The_SamePage("What should we call you?"),
    Done_Android("sign_up_done_txt"),
    Android_Email_field("signup_txt_email"),
    Android_Pass_field("signup_txt_password"),
    Android_Name_field("signup_txt_name"),
    Android_Age_field("signup_txt_age"),

    Cross_SIGN_UP_WITH_EMAIL("Sign up with email"),
    Cross_Age_Done("Next"),
    Cross_Tags_Done("Next"),
    Cross_Email_field("Email"),
    Cross_Pass_field("Password"),
    Cross_Name_field("Name"),
    Done_Cross("Done"),
    Cross_Age_field("How old are you?");

    private final String text;

    SignUpPage(String txt) {
        text = txt;
    }

    public String getId() {
        return text;
    }
}
