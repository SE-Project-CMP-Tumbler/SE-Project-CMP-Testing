/// <reference types="cypress" />

import * as Selectors from '../fixtures/selectors.json'

describe('pre-SignUp Tests', () => {
  context('Elements of SIGNUP page', () => {
    beforeEach(() => {
      cy.visit(Selectors.SIGNUP_PAGE)
    })

    it('assert login with google exists', () => {
      cy.contains('with Google').should('exist')
    })

    it('assert login button exists', () => {
      cy.contains('Log in').should('exist')
    })
  })

  function singUpInvalid (ei, pi, bi) {
    // ['empty', 'invalid', 'valid']
    const emails = ['', 'invalid.com@', 'real_email@hi2.com']
    const passwords = ['', 'weak', 'Good_Password@best %3&6']
    const blognames = ['', '%&person', 'valid-person-03489167']

    if (ei) {
      cy.get('input[name="email"]').clear().type(emails[ei])
    } else {
      cy.get('input[name="email"]').clear()
    }
    if (pi) {
      cy.get('input[name="password"]').clear().type(passwords[pi])
    } else {
      cy.get('input[name="password"]').clear()
    }
    if (bi) {
      cy.get('input[name="blogName"]').clear().type(blognames[bi])
    } else {
      cy.get('input[name="blogName"]').clear()
    }

    cy.contains('Sign up').click()

    // assert we didn't move to next page, "How old are you?"
    cy.contains('old').should('not.exist')
  }

  it('Sign up - Invalid credentials', () => {
    cy.visit(Selectors.SIGNUP_PAGE)
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
    cy.get('input[name="email"]').clear().type('real_email@hi2.com')
    cy.get('input[name="password"]').clear().type('Good_Password@best %3&6')
    cy.get('input[name="blogName"]').clear().type('valid-person-03489167')

    cy.contains('Sign up').click()

    cy.get('input[name="age"]').type(3)
    cy.contains('Next').click()
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(4000)
    // no wait means next assertion will be verified immediately, even before page loads (IF)
    cy.url().should('eq', Selectors.SIGNUP_PAGE)
  })
})
