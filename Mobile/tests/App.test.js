/* eslint-disable  */

describe('Simple App testing', () => {
  it('My first test', async () => {
    const selector = await 'new UiSelector().resourceId("com.tumblr:id/sign_up_button")'
    const button = await $(`android=${selector}`)
    await button.click()
    const bg=await $(`android=${'new UiSelector().textContains("Email")'}`)
    driver.waitUntil (()=>bg)
  })
})
