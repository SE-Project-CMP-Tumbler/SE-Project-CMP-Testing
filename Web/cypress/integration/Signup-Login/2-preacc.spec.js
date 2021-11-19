/// <reference types="cypress" />

import * as Selectors from '../../fixtures/selectors.json'

describe('pre-SignUp Tests', () => {
  context('Sign up - Invalid credentials', () => {
    function singUpInvalid (ei, pi, bi) {
      // ['empty', 'invalid', 'valid']
      const emails = ['', 'invalid.com@', 'real_email@hi2.com']
      const passwords = ['', 'weak', 'Good_Password@best %3&6']
      const blognames = ['', '%&person', 'valid-person-03489167']

      if (ei) {
        cy.get(Selectors.SIGNUP_PAGE.EMAIL).clear().type(emails[ei])
      } else {
        cy.get(Selectors.SIGNUP_PAGE.EMAIL).clear()
      }
      if (pi) {
        cy.get(Selectors.SIGNUP_PAGE.PASSWORD).clear().type(passwords[pi])
      } else {
        cy.get(Selectors.SIGNUP_PAGE.PASSWORD).clear()
      }
      if (bi) {
        cy.get(Selectors.SIGNUP_PAGE.NAME).clear().type(blognames[bi])
      } else {
        cy.get(Selectors.SIGNUP_PAGE.NAME).clear()
      }

      cy.contains('Sign up').click()

      // assert we didn't move to next page, "How old are you?"
      cy.get(Selectors.SIGNUP_PAGE.AGE).should('not.exist')
    }

    it('', () => {
      cy.visit(Selectors.SIGNUP_PAGE.URL)
      // each field of {email, password, blog name} has three different states: {empty, invalid, valid}
      // testing all combinations except {valid, valid, valid}
      const cnt = 3
      for (let ei = 0; ei < cnt; ei++) {
        for (let pi = 0; pi < cnt; pi++) {
          for (let bi = 0; bi < cnt; bi++) {
            if (ei + pi + bi < (cnt - 1) * 3) {
              singUpInvalid(ei, pi, bi)
            }
          }
        }
      }
      // testing valid combination but invalid age:
      cy.get(Selectors.SIGNUP_PAGE.EMAIL).clear().type('real_email@hi2.com')
      cy.get(Selectors.SIGNUP_PAGE.PASSWORD).clear().type('Good_Password@best %3&6')
      cy.get(Selectors.SIGNUP_PAGE.NAME).clear().type('valid-person-03489167')

      cy.contains('Sign up').click()

      cy.get(Selectors.SIGNUP_PAGE.AGE).type(11)
      cy.contains('Next').click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      // no wait means next assertion will be verified immediately, even before page loads (IF)
      cy.url().should('have.string', Selectors.SIGNUP_PAGE.URL)
    })
  })
})
