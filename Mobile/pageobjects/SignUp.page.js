class SignUpPage {
    get withEmail () {
        const selector = 'com.tumblr:id/email_auth_button'
        return $(`android=resourceId("${selector}")`)
    }

    get withGoogle () {
        const selector = 'com.tumblr:id/google_auth_button'
        return $(`android=resourceId("${selector}")`)
    }

    get ageField () {
        const selector = 'com.tumblr:id/tm_edit_text'
        return $(`android=resourceId("${selector}")`)
    }

    get nextBtn () {
        const selector = 'com.tumblr:id/next_button'
        return $(`android=resourceId("${selector}")`)
    }

    checkIfExist (str) {
        return $(`android=textContains("${str}")`)
    }

    get emailField () {
        const selector = 'email'
        return $(`android=textContains("${selector}")`)
    }

    get passField () {
        const selector = 'password'
        return $(`android=textContains("${selector}")`)
    }

    get nameField () {
        const selector = 'name'
        return $(`android=text("${selector}")`)
    }

    get done () {
        const selector = 'com.tumblr:id/done_button'
        return $(`android=resourceId("${selector}")`)
    }

    get invalidTextField () {
        const selector = 'com.tumblr:id/textinput_error'
        return $(`android=resourceId("${selector}")`)
    }
}

export default new SignUpPage()
