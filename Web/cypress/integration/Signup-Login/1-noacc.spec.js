/// <reference types="cypress" />

import * as Selectors from '../../fixtures/selectors.json'

describe('No-account Tests', () => {
  it('test redirect from home to login', () => {
    cy.visit(Selectors.DASHBOARD_PAGE)
    cy.url().should('have.string', '/login')
  })

  context('Elements of login page', () => {
    beforeEach(() => {
      cy.visit(Selectors.LOGIN_PAGE)
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
})
