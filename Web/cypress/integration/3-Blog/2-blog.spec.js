/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('Blogs tests', () => {
  before(() => {
    cy.login(0)
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(6000)
    cy.visit(SEL.PRF.URL.K._)
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(3000)
  })

  it('Side-view button redirects to the right page', () => {
    cy.get(`a[href='${SEL.PROFILE_SIDE.URL.K}']`).should('exist')
  })

  context('Options', () => {
    before(() => {
      cy.get(SEL.PRF.OPTIONS._).should('exist').click({ scrollBehavior: false })
    })

    after(() => {
      cy.login(0)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(6000)
      cy.visit(SEL.PRF.URL.K._)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(3000)
    })

    it('Should have follow button', () => {
      cy.get(SEL.PRF.OPTIONS.FOLLOW).should('exist')
    })

    it('Should have block button', () => {
      cy.get(SEL.PRF.OPTIONS.BLOCK).should('exist')
    })
  })

  it('Should contain multiple posts', () => {
    cy.get(SEL.PRF.POSTS.BODY).should('have.length.at.least', 2)
  })

  it('Posts should contain data', () => {
    cy.get(SEL.PRF.POSTS.BODY).each(($el) => {
      cy.wrap($el).should('not.have.html', '<span></span>')
    })
  })

  context('Liked Posts Tab', () => {
    before(() => {
      cy.get(SEL.PRF.LIKES).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(6000)
    })

    after(() => {
      cy.login(0)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(6000)
      cy.visit(SEL.PRF.URL.K._)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(3000)
    })

    it('Should contain multiple posts', () => {
      cy.get(SEL.PRF.LIKES_POSTS_BODY).should('have.length.at.least', 2)
    })

    it('Posts should contain data', () => {
      cy.get(SEL.PRF.LIKES_POSTS_BODY).each(($el) => {
        cy.wrap($el).should('not.have.html', '<span></span>')
      })
    })
  })
})
