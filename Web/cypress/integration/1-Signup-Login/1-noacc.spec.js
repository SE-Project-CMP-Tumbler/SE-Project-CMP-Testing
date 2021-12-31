/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('No-account Tests', () => {
  context('Elements of root page', () => {
    const ROOT = SEL.ROOT
    beforeEach(() => {
      cy.visit('/')
    })

    it('assert login with google exists', () => {
      cy.get(ROOT.WITHGOOGLE).should('exist')
    })

    it('assert signup button exists', () => {
      cy.get(ROOT.SIGNUP).should('exist')
    })

    it('assert login button exists', () => {
      cy.get(ROOT.LOGIN).should('exist')
    })
  })

  context('Elements of login page', () => {
    const LOGIN = SEL.LOGIN
    beforeEach(() => {
      cy.visit(LOGIN.URL)
    })

    it('assert login with google exists', () => {
      cy.get(LOGIN.WITHGOOGLE).should('exist')
    })

    it('assert forgot password exists', () => {
      cy.get(LOGIN.FORGOTPASSWORD).should('exist')
    })

    it('assert signup button exists', () => {
      cy.get(LOGIN.SIGNUP).should('exist')
    })

    // test forgot pass non-existent email -> can't cuz needs captcha
  })

  context('Elements of SIGNUP page', () => {
    const SIGNUP = SEL.SIGNUP
    beforeEach(() => {
      cy.visit(SIGNUP.URL)
    })

    it('assert login with google exists', () => {
      cy.get(SIGNUP.WITHGOOGLE).should('exist')
    })

    it('assert login button exists', () => {
      cy.get(SIGNUP.LOGIN).should('exist')
    })
  })
})
