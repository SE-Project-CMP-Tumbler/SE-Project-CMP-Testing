/// <reference types="cypress" />

import * as Selectors from '../../fixtures/selectors.json'

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
    if (cy.url().toString().indexOf(Selectors.LOGIN_PAGE.URL) !== -1) {
      return
    }

    cy.get(Selectors.LOGIN_PAGE.EMAIL).clear().type(testEmail)
    cy.get(Selectors.LOGIN_PAGE.PASSWORD).clear().type(testPassword)
    cy.contains('Log in').click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(4000)
    if (cy.url().toString().indexOf('Privacy') !== -1) {
      cy.contains('I Agree').click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
    }
  }

  it('Valid SignUp', () => {
    cy.visit(Selectors.SIGNUP_PAGE.URL)
    cy.get(Selectors.SIGNUP_PAGE.EMAIL).type(testEmail)
    cy.get(Selectors.SIGNUP_PAGE.PASSWORD).type(testPassword)
    cy.get(Selectors.SIGNUP_PAGE.NAME).type(testBlogname)
    cy.contains('Sign up').click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(10000) // Signing up takes a long time
    cy.get(Selectors.SIGNUP_PAGE.AGE).type(testAge)
    cy.contains('Next').click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(30000) // Signing up takes a long time
  })

  it('Should be in Welcome Page', () => {
    cy.url().should('have.string', Selectors.WELCOME_PAGE.URL)
  })

  it('Skip and go to home page', () => {
    cy.contains('Skip').click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(20000)
  })

  it('Test redirect from login to home', () => {
    cy.visit(Selectors.LOGIN_PAGE.URL)
    cy.url().should('have.string', Selectors.DASHBOARD_PAGE.URL)
  })

  it('Test redirect from signup to home', () => {
    cy.visit(Selectors.SIGNUP_PAGE.URL)
    cy.url().should('have.string', Selectors.DASHBOARD_PAGE.URL)
  })

  context('Logging Out', () => {
    it('Should successfully log out', () => {
      cy.visit(Selectors.LOGOUT)
    })

    // it('Should be now in root page', () => {
    //   cy.url().should('have.string', '/')
    // })
  })

  it('Test signup already existant', () => {
    cy.visit(Selectors.SIGNUP_PAGE.URL)
    cy.get(Selectors.SIGNUP_PAGE.EMAIL).type(testEmail)
    cy.get(Selectors.SIGNUP_PAGE.PASSWORD).type(testPassword)
    cy.get(Selectors.SIGNUP_PAGE.NAME).type(testBlogname)
    cy.contains('Sign up').click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(10000) // Signing up takes a long time
    cy.get(Selectors.SIGNUP_PAGE.AGE).should('not.exist')
  })

  context('Log in invalid', () => {
    function logInInvalid (ei, pi) {
      if (ei) {
        cy.get(Selectors.LOGIN_PAGE.EMAIL).clear().type(emails[ei])
      } else {
        cy.get(Selectors.LOGIN_PAGE.EMAIL).clear()
      }
      if (pi) {
        cy.get(Selectors.LOGIN_PAGE.PASSWORD).clear().type(passwords[pi])
      } else {
        cy.get(Selectors.LOGIN_PAGE.PASSWORD).clear()
      }

      cy.contains('Log in').click()

      // assert we didn't move to next page, (give time for page to load just in case)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      cy.url().should('have.string', Selectors.LOGIN_PAGE.URL)
    }

    it('', () => {
      cy.visit(Selectors.LOGIN_PAGE.URL)
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
      cy.get(Selectors.LOGIN_PAGE.EMAIL).clear().type(testEmail)
      cy.get(Selectors.LOGIN_PAGE.PASSWORD).clear().type(testPassword)
      cy.contains('Log in').click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      if (cy.url().toString().indexOf('Privacy') !== -1) {
        cy.contains('I Agree').click()
        // eslint-disable-next-line cypress/no-unnecessary-waiting
        cy.wait(4000)
      }
    })

    it('Should be now in dashboard', () => {
      cy.url().should('have.string', Selectors.DASHBOARD_PAGE.URL)
    })
  })

  context('Deleting Account Tests', () => {
    function delInvalid (ei, pi) {
      if (ei) {
        cy.get(Selectors.DELETE_PAGE.EMAIL).clear().type(emails[ei])
      } else {
        cy.get(Selectors.DELETE_PAGE.EMAIL).clear()
      }
      if (pi) {
        cy.get(Selectors.DELETE_PAGE.PASSWORD).clear().type(passwords[pi])
      } else {
        cy.get(Selectors.DELETE_PAGE.PASSWORD).clear()
      }

      cy.contains('Delete').click()

      // assert we didn't move to next page, (give time for page to load just in case)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      cy.url().should('not.have.string', '/deleted')
    }

    it('Invalid delete', () => {
      cy.visit(Selectors.DELETE_PAGE.URL)
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
      cy.visit(Selectors.DELETE_PAGE.URL)
      proceedLogin()
      cy.get(Selectors.DELETE_PAGE.EMAIL).clear().type(testEmail)
      cy.get(Selectors.DELETE_PAGE.PASSWORD).clear().type(testPassword)
      cy.contains('Delete').click()

      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(10000) // Delete takes time

      cy.url().should('have.string', '/deleted')
      cy.contains('Sign').should('exist')
    })

    // Can't cuz it has captcha
    it('forgot pass deleted', () => {
    })

    it('Log In Deleted Account', () => {
      cy.visit(Selectors.LOGIN_PAGE.URL)
      cy.get(Selectors.LOGIN_PAGE.EMAIL).type(testEmail)
      cy.get(Selectors.LOGIN_PAGE.PASSWORD).type(testPassword)
      cy.contains('Log in').click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      cy.url().should('have.string', Selectors.LOGIN_PAGE.URL)
    })
  })
})
