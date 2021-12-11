import StartPage from '../pageobjects/StartApp.page.js'
import LogIn from '../pageobjects/SignUp.page.js'
// import DashBoard from '../pageobjects/DashBoard.page.js'
import { validLoginE1 } from '../utils/Mails.js'

async function NavigateToLogIn () {
    await StartPage.loginBtn.click()
    await expect(LogIn.checkIfExist('email')).toBeExisting()
    await expect(LogIn.checkIfExist('google')).toBeExisting()
    await LogIn.withEmail.click()
    await expect(LogIn.checkIfExist('email')).toBeExisting()
}

function CombineEmailData (inbox) {
    const getPass = inbox.emailAddress.slice(0, 8)
    const Emails = {
        emails: {
            valid: inbox.emailAddress,
            invalid: 'thisEmailAddressIsInvalid',
            empty: ''

        },
        passwords: {
            valid: getPass,
            invalid: 'invalidPassword',
            empty: ''
        }
    }
    const combinations =
        Object.keys(Emails.emails).flatMap((e) => {
            const ret = Object.keys(Emails.passwords).flatMap((p) => {
                const inbox = {
                    email: Emails.emails[e],
                    password: Emails.passwords[p]
                }
                return inbox
            })
            return ret
        })
    return combinations.slice(1)
}
describe('LogIn testing', async () => {
    beforeEach('LogIn navigation', async () => {
        await NavigateToLogIn()
    })
    afterEach('reset app', async () => {
        await driver.reset()
    })
    it('LogIn verfication', async () => {
        const vE1 = await validLoginE1
        const data = CombineEmailData(vE1)
        const emailField = await LogIn.emailField
        const passField = await LogIn.passField
        const passBtn = await $('android=textContains("Enter password")')
        const item0 = data[0]
        await emailField.setValue(item0.email)
        await $('android=resourceId("com.tumblr:id/primary_button")').click()
        await expect(LogIn.checkIfExist('Magic link')).toBeExisting()
        await expect(LogIn.checkIfExist('Enter password')).toBeExisting()
        await passBtn.click()
        await passField.setValue(item0.password)
        await $('android=testContains("Log in")').click()
        await expect(LogIn.invalidTextField).toBeDisplayed()

        for (const item of data.slice(1)) {
            if (item.email) await $(`android=text("${item.email}")`).clearValue()
            if (item.password) await $(`android=text("${item.password}")`).clearValue()
            await emailField.setValue(item.email)
            await passField.setValue(item.password)
            await $('android=testContains("Log in")').click()
            await expect(LogIn.invalidTextField).toBeDisplayed()
        }
    })
})
