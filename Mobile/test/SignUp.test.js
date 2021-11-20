import StartPage from '../pageobjects/StartApp.page.js'
import SignUp from '../pageobjects/SignUp.page.js'
import DashBoard from '../pageobjects/DashBoard.page.js'
import { validLoginE1 } from '../utils/Mails.js'

async function NavigateToSignUp () {
    await StartPage.signupBtn.click()
    await expect(SignUp.checkIfExist('email')).toBeExisting()
    await SignUp.checkIfExist('google')
    await SignUp.withEmail.click()
    await expect(SignUp.checkIfExist('How old are you')).toBeExisting()
    await driver.pressKeyCode(8)
    await driver.pressKeyCode(15)
    await SignUp.nextBtn.click()
    await expect(SignUp.checkIfExist('what do you like')).toBeExisting()
    await driver.pause(15000)
    await DashBoard.accountIcon.click()
}

function CombineEmailData (inbox) {
    const getPass = inbox.emailAddress.slice(0, 8)
    const getName = inbox.emailAddress.slice(9, 13)

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
        },
        names: {
            valid: getName,
            invalid: 'Karim@CUFE',
            empty: ''

        }
    }
    const combinations =
        Object.keys(Emails.emails).flatMap((e) => {
            const ret = Object.keys(Emails.passwords).flatMap((p) => {
                const ret = Object.keys(Emails.names).flatMap((n) => {
                    const inbox = {
                        email: Emails.emails[e],
                        password: Emails.passwords[p],
                        name: Emails.names[n]
                    }
                    return inbox
                })
                return ret
            })
            return ret
        })
    return combinations.slice(1)
}
describe('SignUp testing', async () => {
    beforeEach('SignUp navigation', async () => {
        await NavigateToSignUp()
    })
    afterEach('bbb', async () => {
        await driver.reset()
    })
    it('SignUp, They will not be signed up ', async () => {
        const vE1 = await validLoginE1
        const data = CombineEmailData(vE1)
        const emailField = await SignUp.emailField
        const passField = await SignUp.passField
        const nameField = await SignUp.nameField
        for (const item of data) {
            await emailField.setValue(item.email)
            await passField.setValue(item.password)
            await nameField.setValue(item.name)
            if (item.email && item.password && item.name) {
                await SignUp.done.click()
            }
            await expect(SignUp.invalidTextField).toBeDisplayed()
            if (item.email) await $(`android=text("${item.email}")`).clearValue()
            if (item.password) await $(`android=text("${item.password}")`).clearValue()
            if (item.name) await $(`android=text("${item.name}")`).clearValue()

            // toHaveTextContaining(['This email address is already in use.', 'please', 'at least', 'choose stronger', 'username'],
            //   { message: `error in test case, data ${item}` })
        }
    })
})
