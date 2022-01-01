/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('Blogs side-view tests', () => {
  context('elements', () => {
    before(() => {
      cy.login(0)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(3000)
      cy.visit(SEL.PROFILE_SIDE.URL.K)
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(3000)
    })

    it('Chat button exists', () => {
      cy.get(SEL.PROFILE_SIDE.CHAT).should('exist')
    })

    context('Options', () => {
      before(() => {
        cy.get(SEL.PROFILE_SIDE.OPTIONS._).should('exist').click()
      })

      after(() => {
        cy.visit(SEL.PROFILE_SIDE.URL.K)
      })

      it('Ask button exists', () => {
        cy.get(SEL.PROFILE_SIDE.OPTIONS.ASK).should('exist')
      })

      it('Ask button redirects to the right page', () => {
        cy.get(`a[href='${SEL.PRF.URL.K.ASK}']`).should('exist')
      })

      it('Block button exists', () => {
        cy.get(SEL.PROFILE_SIDE.OPTIONS.BLOCK).should('exist')
      })

      it('Following button exists', () => {
        cy.get(SEL.PROFILE_SIDE.OPTIONS.FOLLOWING).should('exist')
      })

      it('Following button redirects to the right page', () => {
        cy.get(`a[href='${SEL.PRF.URL.K.FOLLOWING}']`).should('exist')
      })

      it('Submit button exists', () => {
        cy.get(SEL.PROFILE_SIDE.OPTIONS.SUBMIT).should('exist')
      })

      it('Submit button redirects to the right page', () => {
        cy.get(`a[href='${SEL.PRF.URL.K.SUBMIT}']`).should('exist')
      })
    })

    it('Should contain multiple posts', () => {
      cy.get(SEL.PROFILE_SIDE.POSTS.BODY).should('have.length.at.least', 2)
    })

    it('Posts should contain data', () => {
      cy.get(SEL.PROFILE_SIDE.POSTS.BODY).each(($el) => {
        cy.wrap($el).should('not.have.html', '<span></span>')
      })
    })

    it('Follow button exists', () => {
      cy.get(SEL.PROFILE_SIDE.FOLLOW).should('exist')
    })
  })
})
