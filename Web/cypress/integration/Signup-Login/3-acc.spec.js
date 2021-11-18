/// <reference types="cypress" />

import { inRange, random } from 'cypress/types/lodash'
import * as Selectors from '../../fixtures/selectors.json'

describe('Actual SignUp Tests', () => {
  // These credintials will be used to create an account, then delete it
  // Blog name is randome because Tumblr has a bug where you can't create
  // then delete same blog name more than twice, otherwise, it will say duplicated name
  const testEmail = 'real_email@hi2.com'
  const testPassword = 'Good_Password@best %3&6'
  const testBlogname = 'valid-person-' + random(3234589, 7246019, false).toString()
  const testAge = 19
  // ['empty', 'invalid', 'valid']
  const emails = ['', 'invalid.com@', `${testEmail}`]
  const passwords = ['', 'weak', `${testPassword}`]
  const blogNames = ['', '%&person', `${testBlogname}`]

  it('Valid SignUp', () => {
    cy.visit(Selectors.SIGNUP_PAGE.URL)
    cy.get(Selectors.SIGNUP_PAGE.EMAIL).type(testEmail)
    cy.get(Selectors.SIGNUP_PAGE.PASSWORD).type(testPassword)
    cy.get(Selectors.SIGNUP_PAGE.NAME).type(testBlogname)
    cy.contains('Sign up').click()
    cy.get(Selectors.SIGNUP_PAGE.AGE).type(testAge)
    cy.contains('Next').click()
  })

  context('Welcoeme Page', () => {
    it('Should be in Welcome Page', () => {
      cy.url().should('eq', Selectors.WELCOME_PAGE)
    })

    it('Skip and go to home page', () => {
      cy.contains('Skip').click()
    })
  })

  it('Test redirect from login to home', () => {
    cy.visit(Selectors.LOGIN_PAGE)
    cy.url().should('eq', Selectors.DASHBOARD_PAGE)
  })

  it('Test redirect from SIGNUP to home', () => {
    cy.visit(Selectors.SIGNUP_PAGE)
    cy.url().should('eq', Selectors.DASHBOARD_PAGE)
  })

  context('Logging Out', () => {
    it('Should successfully log out', () => {
      cy.contains('Account').click()
      cy.contains('Log out').click()
      cy.contains('OK').click()
    })

    it('Should be now in root page', () => {
      cy.url().should('eq', Selectors.ROOT_PAGE)
    })
  })

  context('Log in invalid', () => {
    function logInInvalid (ei, pi, bi) {
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
      if (bi) {
        cy.get(Selectors.LOGIN_PAGE.NAME).clear().type(blogNames[bi])
      } else {
        cy.get(Selectors.LOGIN_PAGE.NAME).clear()
      }

      cy.contains('Log in').click()

      // assert we didn't move to next page, (give time for page to load just in case)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      cy.url().should('eq', Selectors.LOGIN_PAGE)
    }

    it('', () => {
      cy.visit(Selectors.LOGIN_PAGE.URL)
      // each field of {email, password, blog name} has three different states: {empty, invalid, valid}
      // testing all combinations except {valid, valid, valid}
      const cnt = 3
      for (let ei = 0; ei < cnt; ei++) {
        for (let pi = 0; pi < cnt; pi++) {
          for (let bi = 0; bi < cnt; bi++) {
            if (ei + pi + bi < (cnt - 1) * 3) {
              logInInvalid(ei, pi, bi)
            }
          }
        }
      }
    })
  })

  context('Valid log in', () => {
    it('', () => {
      cy.get(Selectors.LOGIN_PAGE.EMAIL).clear().type(emails[2])
      cy.get(Selectors.LOGIN_PAGE.PASSWORD).clear().type(passwords[2])
      cy.get(Selectors.LOGIN_PAGE.NAME).clear().type(blogNames[2])
      cy.contains('Log in').click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      if (cy.url().should('contain.text', 'Privacy')) {
        cy.contains('I Agree').click()
        // eslint-disable-next-line cypress/no-unnecessary-waiting
        cy.wait(4000)
      }
    })

    it('Should be now in dashboard', () => {
      cy.url().should('eq', Selectors.DASHBOARD_PAGE)
    })
  })

  context('Deleting Account Tests', () => {
    // This test doesn't always work!, Tumblr sometimes disables it
    beforeEach(() => {
      // Navigate to Delete account page
      cy.visit(Selectors.SETTINGS_ACCOUNT)
      cy.contains('Delete account').click()
    })

    it('Invalid delete', () => {
    })

    it('Valid Delete', () => {
    })
  })

  it('Should redirect to SignUp', () => {
  })
  /*
    it('Log In Deleted Account', () => {
      cy.visit(Selectors.LOGIN_PAGE.URL)
      cy.get(Selectors.LOGIN_PAGE.EMAIL).type(testEmail)
      cy.get(Selectors.LOGIN_PAGE.PASSWORD).type(testPassword)
      cy.contains('Log in').click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      cy.url().should('eq', Selectors.LOGIN_PAGE)
    })
  */

  // Can't cuz it has captcha
  it('forgot pass deleted', () => {
  })
})
