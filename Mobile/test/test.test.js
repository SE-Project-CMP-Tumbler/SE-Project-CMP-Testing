import StartPage from '../pageobjects/test.page.js'
import SignUp from '../pageobjects/SignUp.page.js'
// import { } from '../utils/Mails.js'

describe('SignUp testing', () => {
    it('Valid Email', async () => {
        const signupBtn = StartPage.signupBtn
        await (signupBtn.click())

        await expect(SignUp.checkIfExist('email')).toBeExisting({ message: 'Can not find element ' })

        await SignUp.checkIfExist('google')

        // await

        // withGoogle.setValue(email1)
    })
})
