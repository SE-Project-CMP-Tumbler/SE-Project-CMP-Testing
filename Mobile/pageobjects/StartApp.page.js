class StartPage {
    get signupBtn () {
        const selector = 'com.tumblr:id/sign_up_button'
        return $(`android=resourceId("${selector}")`)
    }

    get loginBtn () {
        const selector = 'com.tumblr:id/login_button'
        return $(`android=resourceId("${selector}")`)
    }
}

export default new StartPage()
