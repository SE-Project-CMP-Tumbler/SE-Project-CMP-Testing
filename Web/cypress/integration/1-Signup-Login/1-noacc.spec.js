/// <reference types="cypress" />

import { DASHBOARD, ROOT, LOGIN, SIGNUP } from '../../Page_Objects/'

describe('No-account Tests', () => {
  context('Elements of root page', () => {
    before(() => {
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
    before(() => {
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
    before(() => {
      cy.visit(SIGNUP.URL)
    })

    it('assert login with google exists', () => {
      cy.get(SIGNUP.WITHGOOGLE).should('exist')
    })

    it('assert login button exists', () => {
      cy.get(SIGNUP.LOGIN).should('exist')
    })
  })

  it('Should redirect away of dashboard', () => {
    cy.visit(DASHBOARD.URL)
    cy.get(DASHBOARD.RADAR_DIV).should('not.exist')
  })
})
