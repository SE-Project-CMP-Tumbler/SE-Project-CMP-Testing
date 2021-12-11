/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('Basic Dashboard Tests', () => {
  before(() => {
    cy.visit(SEL.DASHBOARD.URL)
  })
  it('Should contain multiple posts', () => {
    cy.get('div.postBody').should('have.length.at.least', 1)
  })
  it('Posts should contain data', () => {
    cy.get('div.postBody span.text').each(($el) => {
      cy.wrap($el).should('not.have.html', '<span class="text"></span>')
    })
  })
})
