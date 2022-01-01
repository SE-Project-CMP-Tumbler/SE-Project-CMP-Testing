/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('New Posts tests', () => {
  before(() => {
    cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))
  })

  it('Post text button exists', () => {
    cy.get(SEL.NEW_POST.TEXT).should('exist')
  })

  it('Post photo button exists', () => {
    cy.get(SEL.NEW_POST.PHOTO).should('exist')
  })

  it('Post video button exists', () => {
    cy.get(SEL.NEW_POST.VIDEO).should('exist')
  })

  it('Posting image from web', () => {
    const testImg = 'https://i.imgur.com/aTDBs0Y.jpeg'
    cy.get(SEL.NEW_POST.PHOTO).click()
    cy.get(SEL.NEW_POST.PHOTO_ADD).eq(1).click()
    cy.get(SEL.NEW_POST.PHOTO_ADD_WEB_TEXT).type(testImg)
    cy.get(SEL.NEW_POST.PHOTO_ADD_WEB_SUBMIT).click()

    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(3000)
    cy.get(SEL.DASHBOARD.POSTS.BODY).eq(0).then($ele => {
      expect($ele.text()).to.have.string(testImg)
    })
  })

  it('Posting video from web', () => {
    cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))

    const testVid = 'https://www.youtube.com/watch?v=d6gBu2Zd7Bc&list=PLPPomK5QKeyWV7PYC9s-PxrDhVIDpt4Oe&index=5'
    cy.get(SEL.NEW_POST.PHOTO).click()
    cy.get(SEL.NEW_POST.PHOTO_ADD).eq(1).click()
    cy.get(SEL.NEW_POST.PHOTO_ADD_WEB_TEXT).type(testVid)
    cy.get(SEL.NEW_POST.PHOTO_ADD_WEB_SUBMIT).click()

    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(3000)
    cy.get(SEL.DASHBOARD.POSTS.BODY).eq(0).then($ele => {
      expect($ele.text()).to.have.string(testVid)
    })
  })

  context('Posting Text', () => {
    it('redirect to dashboard if canceled', () => {
      cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))
      cy.get(SEL.NEW_POST.TEXT).click()

      cy.get(SEL.NEW_POST.CANCEL).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(3000)
      cy.get(SEL.DASHBOARD.RADAR.BLOG).should('exist')
    })

    it('Basic post', () => {
      cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))
      cy.get(SEL.NEW_POST.TEXT).click()

      const testText = 'testt1323-_*&^%'
      cy.get(SEL.NEW_POST.TEXT_AREA).type(testText)
      cy.get(SEL.NEW_POST.SUBMIT).click()
      // eslint-disable-next-line cypress/no-unnecessary-waiting
      cy.wait(3000)
      cy.get(SEL.DASHBOARD.POSTS.BODY).eq(0).then($ele => {
        expect($ele.text()).to.have.string(testText)
      })
    })
  })

  it('Edit Post', () => {
    cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(30000)

    cy.get(SEL.DASHBOARD.POSTS.EDIT).should('exist')
      .eq(0).click({ force: true })
    cy.get(SEL.NEW_POST.TEXT_AREA).should('exist')
  })

  it('Delete Post', () => {
    cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(30000)

    cy.get(SEL.DASHBOARD.POSTS.BODY).should('have.length.greaterThan', 5)
      .then($n => {
        cy.get(SEL.DASHBOARD.POSTS.DELETE).should('exist')
          .eq(0).click({ force: true })
        cy.get(SEL.DASHBOARD.POSTS.DELETE_CONFIRM).should('exist')
          .click({ force: true })
      })
  })
})
