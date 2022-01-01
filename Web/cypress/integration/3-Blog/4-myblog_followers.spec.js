/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('My blogs Followers tests', () => {
  before(() => {
    cy.login(0)
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(6000)
    cy.visit(SEL.MYBLOG.URL.H)
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(2000)
    cy.get(SEL.MYBLOG.FOLLOWERS).eq(0).click({ force: true })
  })

  it('check for karm', () => {
    cy.contains('karm').should('exist')
  })

  it('Numbers should be right', () => {
    const n3 = Cypress.$(SEL.MYBLOG.FOLLWERS_BLOGS).children().length
    cy.get(SEL.MYBLOG.FOLLWERS_COUNT1).then($ele => {
      expect(n3).to.be.eq(parseInt($ele.text()))
    })
  })

  it('Search follower!', () => {
    cy.get(SEL.MYBLOG.FOLLOWERS_SERACH).type('karm{enter}')
    cy.contains('follows you!').should('exist')
  })

  it('Search follower!', () => {
    cy.get(SEL.MYBLOG.FOLLOWERS_SERACH).type('Aijadfg{enter}')
    cy.contains('follows you!').should('not.exist')
  })
})
