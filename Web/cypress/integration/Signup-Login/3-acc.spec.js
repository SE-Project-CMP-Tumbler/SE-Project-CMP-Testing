/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

/*
- inconsistency:

* SOMETIMES it opens dashboard to non-logged in users
* keeps asking to log in every single step, which is just just AAAAAAAAAAAAAAAAAAAAAAAAA
*/

describe('Actual SignUp Tests', () => {
  // These credintials will be used to create an account, then delete it
  // Blog name is randome because Tumblr has a bug where you can't create
  // then delete same blog name more than twice, otherwise, it will say duplicated name
  const testEmail = 'rel_email' + Math.floor(Math.random() * 651324).toString() + '@hi2.com'
  const testPassword = 'Greatt_Password@best %3&6'
  const testBlogname = 'val-per' + Math.floor(Math.random() * 15478635).toString()
  const testAge = 19
  // ['empty', 'invalid', 'valid']
  const emails = ['', 'invalid.com@', `${testEmail}`]
  const passwords = ['', 'weak', `${testPassword}`]

  // This function is to bypass tubmlr ANNOYTINGLY asking to login every single step
  function proceedLogin () {
    if (cy.url().toString().indexOf(SEL.LOGIN.URL) !== -1) {
      return
    }

    cy.get(SEL.LOGIN.EMAIL).clear().type(testEmail)
    cy.get(SEL.LOGIN.PASSWORD).clear().type(testPassword)
    cy.get(SEL.LOGIN.SUBMIT).click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(4000)
    if (cy.url().toString().indexOf('Privacy') !== -1) {
      cy.contains('I Agree').click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
    }
  }

  it('Valid SignUp', () => {
    cy.visit(SEL.SIGNUP.URL)
    cy.get(SEL.SIGNUP.EMAIL).type(testEmail)
    cy.get(SEL.SIGNUP.PASSWORD).type(testPassword)
    cy.get(SEL.SIGNUP.BLOGNAME).type(testBlogname)
    cy.get(SEL.SIGNUP.SUBMIT).click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(10000) // Signing up takes a long time
    cy.get(SEL.SIGNUP.AGE).type(testAge)
    cy.get(SEL.SIGNUP.AGE_SUBMIT).click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(30000) // Signing up takes a long time
  })

  it('Should be in Welcome Page', () => {
    cy.url().should('have.string', SEL.WELCOME.URL)
  })

  it('Skip and go to home page', () => {
    cy.get(SEL.WELCOME.SKIP).click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(20000)
  })

  it('Test redirect from login to home', () => {
    cy.visit(SEL.LOGIN.URL)
    cy.url().should('have.string', SEL.DASHBOARD.URL)
  })

  it('Test redirect from signup to home', () => {
    cy.visit(SEL.SIGNUP.URL)
    cy.url().should('have.string', SEL.DASHBOARD.URL)
  })

  context('Logging Out', () => {
    it('Should successfully log out', () => {
      cy.visit(SEL.LOGOUT.URL)
    })

    // it('Should be now in root page', () => {
    //   cy.url().should('have.string', '/')
    // })
  })

  context('Test signup already existant', () => {
    it('existant email', () => {
      cy.visit(SEL.SIGNUP.URL)
      cy.get(SEL.SIGNUP.EMAIL).type(testEmail)
      cy.get(SEL.SIGNUP.PASSWORD).type(testPassword)
      cy.get(SEL.SIGNUP.BLOGNAME).type(testBlogname + '1')
      cy.get(SEL.SIGNUP.SUBMIT).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(10000) // Signing up takes a long time
      cy.get(SEL.SIGNUP.AGE).should('not.exist')
    })

    it('existant blog name', () => {
      cy.visit(SEL.SIGNUP.URL)
      cy.get(SEL.SIGNUP.EMAIL).type('rr' + testEmail)
      cy.get(SEL.SIGNUP.PASSWORD).type(testPassword)
      cy.get(SEL.SIGNUP.BLOGNAME).type(testBlogname)
      cy.get(SEL.SIGNUP.SUBMIT).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(10000) // Signing up takes a long time
      cy.get(SEL.SIGNUP.AGE).should('not.exist')
    })
  })

  context('Log in invalid', () => {
    function logInInvalid (ei, pi) {
      if (ei) {
        cy.get(SEL.LOGIN.EMAIL).clear().type(emails[ei])
      } else {
        cy.get(SEL.LOGIN.EMAIL).clear()
      }
      if (pi) {
        cy.get(SEL.LOGIN.PASSWORD).clear().type(passwords[pi])
      } else {
        cy.get(SEL.LOGIN.PASSWORD).clear()
      }

      cy.get(SEL.LOGIN.SUBMIT).click()

      // assert we didn't move to next page, (give time for page to load just in case)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      cy.url().should('have.string', SEL.LOGIN.URL)
    }

    it('', () => {
      cy.visit(SEL.LOGIN.URL)
      // each field of {email, password, blog name} has three different states: {empty, invalid, valid}
      // testing all combinations except {valid, valid, valid}
      const cnt = 3
      for (let ei = 0; ei < cnt; ei++) {
        for (let pi = 0; pi < cnt; pi++) {
          if (ei + pi < (cnt - 1) * 2) {
            logInInvalid(ei, pi)
          }
        }
      }
    })
  })

  context('Valid log in', () => {
    it('', () => {
      cy.get(SEL.LOGIN.EMAIL).clear().type(testEmail)
      cy.get(SEL.LOGIN.PASSWORD).clear().type(testPassword)
      cy.get(SEL.LOGIN.SUBMIT).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      if (cy.url().toString().indexOf('Privacy') !== -1) {
        cy.contains('I Agree').click()
        // eslint-disable-next-line cypress/no-unnecessary-waiting
        cy.wait(4000)
      }
    })

    it('Should be now in dashboard', () => {
      cy.url().should('have.string', SEL.DASHBOARD.URL)
    })
  })

  context('Deleting Account Tests', () => {
    function delInvalid (ei, pi) {
      if (ei) {
        cy.get(SEL.DELETE_ACCOUNT.EMAIL).clear().type(emails[ei])
      } else {
        cy.get(SEL.DELETE_ACCOUNT.EMAIL).clear()
      }
      if (pi) {
        cy.get(SEL.DELETE_ACCOUNT.PASSWORD).clear().type(passwords[pi])
      } else {
        cy.get(SEL.DELETE_ACCOUNT.PASSWORD).clear()
      }

      cy.get(SEL.DELETE_ACCOUNT.SUBMIT).click()

      // assert we didn't move to next page, (give time for page to load just in case)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      cy.url().should('not.have.string', '/deleted')
    }

    it('Invalid delete', () => {
      cy.visit(SEL.DELETE_ACCOUNT.URL)
      proceedLogin()
      // each field of {email, password, blog name} has three different states: {empty, invalid, valid}
      // testing all combinations except {valid, valid, valid}
      const cnt = 3
      for (let ei = 0; ei < cnt; ei++) {
        for (let pi = 0; pi < cnt; pi++) {
          if (ei + pi < (cnt - 1) * 2) {
            delInvalid(ei, pi)
          }
        }
      }
    })

    it('Valid Delete', () => {
      cy.visit(SEL.DELETE_ACCOUNT.URL)
      proceedLogin()
      cy.get(SEL.DELETE_ACCOUNT.EMAIL).clear().type(testEmail)
      cy.get(SEL.DELETE_ACCOUNT.PASSWORD).clear().type(testPassword)
      cy.get(SEL.DELETE_ACCOUNT.SUBMIT).click()

      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(10000) // Delete takes time

      cy.url().should('have.string', '/deleted')
      cy.get(SEL.ACCOUNT_DELETED.SIGNUP_AGAIN).should('exist')
    })

    // Can't cuz it has captcha
    it('forgot pass deleted', () => {
    })

    it('Log In Deleted Account', () => {
      cy.visit(SEL.LOGIN.URL)
      cy.get(SEL.LOGIN.EMAIL).type(testEmail)
      cy.get(SEL.LOGIN.PASSWORD).type(testPassword)
      cy.get(SEL.LOGIN.SUBMIT).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      cy.url().should('have.string', SEL.LOGIN.URL)
    })
  })
})
