/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('Checkout and Radar Components', () => {
  describe('Radar component', () => {
    before(() => {
      cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(3000)
    })

    it('blog user is shown', () => {
      cy.get(SEL.DASHBOARD.RADAR.BLOG).should('exist')
    })

    it('Radar blog not empty', () => {
      cy.get(SEL.DASHBOARD.RADAR.BODY).should('not.have.html', '<span></span>')
    })

    it('Notes count should be shown', () => {
      cy.get(SEL.DASHBOARD.RADAR.NOTES).should('exist')
    })

    it('Send, follow, commnet, like and reblog buttons exist', () => {
      cy.get(SEL.DASHBOARD.RADAR.COMMENT).should('exist')
      cy.get(SEL.DASHBOARD.RADAR.LIKE).should('exist')
      cy.get(SEL.DASHBOARD.RADAR.REBLOG).should('exist')
      cy.get(SEL.DASHBOARD.RADAR.SEND).should('exist')
    })

    it('Scenario of following radar user', () => {
      cy.get(SEL.DASHBOARD.RADAR.FOLLOW).click()
      cy.get(SEL.DASHBOARD.RADAR.BLOG).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(10000)
      cy.get(SEL.BLOG_VIEW.FOLLOW).invoke('text').should('have.string', 'Un')
    })
  })

  describe('Checkout component', () => {
    before(() => {
      cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(3000)
    })

    it('Scenario of following first checkout user', () => {
      // https://github.com/cypress-io/cypress-example-recipes/blob/master/examples/testing-dom__tab-handling-links/cypress/integration/tab_handling_anchor_links_spec.js
      cy.get(SEL.DASHBOARD.CHECK_OUT1.BLOG).then(($a) => {
        // extract the fully qualified href property
        const href = $a.prop('href')

        cy.get(SEL.DASHBOARD.CHECK_OUT1.FOLLOW).click()
        // eslint-disable-next-line cypress/no-unnecessary-waiting
        cy.wait(1000)
        // and now visit the href directly
        cy.visit(href)
      })
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(10000)
      cy.get(SEL.BLOG_VIEW.FOLLOW).invoke('text').should('have.string', 'Un')
    })
  })
})
