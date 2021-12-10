/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('pre-SignUp Tests', () => {
  const SIGNUP = SEL.SIGNUP
  // ['empty', 'invalid', 'valid']
  const emails = ['', 'invalid.com@', 'reaal_email@hi2.com']
  const passwords = ['', 'weak', 'Gooood_Pasword@best %3&6']
  const blogNames = ['', '%&person', 'valpes-489167']
  context('Sign up - Invalid credentials', () => {
    function singUpInvalid (ei, pi, bi) {
      if (ei) {
        cy.get(SIGNUP.EMAIL).clear().type(emails[ei])
      } else {
        cy.get(SIGNUP.EMAIL).clear()
      }
      if (pi) {
        cy.get(SIGNUP.PASSWORD).clear().type(passwords[pi])
      } else {
        cy.get(SIGNUP.PASSWORD).clear()
      }
      if (bi) {
        cy.get(SIGNUP.BLOGNAME).clear().type(blogNames[bi])
      } else {
        cy.get(SIGNUP.BLOGNAME).clear()
      }

      cy.get(SIGNUP.SUBMIT).click()

      // assert we didn't move to next page, "How old are you?"
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(2000)
      cy.get(SIGNUP.AGE).should('not.exist')
    }

    it('', () => {
      cy.visit(SIGNUP.URL)
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
      cy.get(SIGNUP.EMAIL).clear().type(emails[2])
      cy.get(SIGNUP.PASSWORD).clear().type(passwords[2])
      cy.get(SIGNUP.BLOGNAME).clear().type(blogNames[2])

      cy.get(SIGNUP.SUBMIT).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      cy.get(SIGNUP.AGE).type(11)
      cy.get(SIGNUP.AGE_SUBMIT).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(4000)
      // no wait means next assertion will be verified immediately, even before page loads (IF)
      cy.url().should('have.string', SIGNUP.URL)
    })
  })
})
