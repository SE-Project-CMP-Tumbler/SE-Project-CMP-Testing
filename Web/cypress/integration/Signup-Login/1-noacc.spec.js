/// <reference types="cypress" />

import * as Selectors from '../../fixtures/selectors.json'

describe('No-account Tests', () => {
  context('Elements of root page', () => {
    beforeEach(() => {
      cy.visit('/')
    })

    it('assert login with google exists', () => {
      cy.contains('with Google').should('exist')
    })

    it('assert signup button exists', () => {
      cy.contains('Sign up').should('exist')
    })

    it('assert login button exists', () => {
      cy.contains('Log in').should('exist')
    })
  })

  context('Elements of login page', () => {
    beforeEach(() => {
      cy.visit(Selectors.LOGIN_PAGE.URL)
    })

    it('assert login with google exists', () => {
      cy.contains('with Google').should('exist')
    })

    it('assert forgot password exists', () => {
      cy.contains('Forgot').should('exist')
    })

    it('assert signup button exists', () => {
      cy.contains('Sign up').should('exist')
    })

    // test forgot pass non-existent email -> can't cuz needs captcha
  })

  context('Elements of SIGNUP page', () => {
    beforeEach(() => {
      cy.visit(Selectors.SIGNUP_PAGE.URL)
    })

    it('assert login with google exists', () => {
      cy.contains('with Google').should('exist')
    })

    it('assert login button exists', () => {
      cy.contains('Log in').should('exist')
    })
  })
})
