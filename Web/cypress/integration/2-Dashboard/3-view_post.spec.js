/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('Viewing Dashboard Posts', () => {
  let oldNotes = 0
  before(() => {
    cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(3000)
  })

  it('Saving fist post\'s notes count', () => {
    // eslint-disable-next-line cypress/no-assigning-return-values
    oldNotes = parseInt(cy.get(SEL.DASHBOARD.POSTS.NOTES).get(1).invoke('text'))
  })

  it('Liking a post', () => {
    cy.get(SEL.DASHBOARD.POSTS.LIKE).eq(0).click()

    cy.get(SEL.DASHBOARD.POSTS.NOTES).invoke('parseInt')
      .should('be.gt', oldNotes)
  })

  it('Commenting', () => {
    const tstComment = 'HelTstAA'
    cy.get(SEL.DASHBOARD.POSTS.COMMENT).eq(0).click()
    cy.get(SEL.DASHBOARD.POSTS.COMMENT_WRITE).type(tstComment)
    cy.contains(SEL.DASHBOARD.POSTS.COMMENT_SEND).click()

    cy.contains(tstComment)
  })

  it('', () => {
    cy.get(SEL.DASHBOARD.POSTS.COMMENT_CANCEL).eq(0).click()
  })

  it('Comments incerase notes test', () => {
    cy.get(SEL.DASHBOARD.POSTS.NOTES).invoke('parseInt')
      .should('be.gt', oldNotes)
  })

  it('Reblog', () => {
    // eslint-disable-next-line cypress/no-assigning-return-values
    const oldPost = cy.get(SEL.DASHBOARD.POSTS.BODY).eq(0).invoke('text')
    cy.get(SEL.DASHBOARD.POSTS.REBLOG).eq(0).click()
    cy.get(SEL.NEW_POST.SUBMIT).click()

    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(3000)
    cy.get(SEL.DASHBOARD.POSTS.BODY).eq(0).then(($ele) => {
      cy.wrap($ele).should('have.string', oldPost)
    })
  })

  it('Reblogs incerase notes test', () => {
    cy.get(SEL.DASHBOARD.POSTS.NOTES).invoke('parseInt')
      .should('be.gt', oldNotes)
  })

  it('infinite scroll and pagination', () => {
    cy.get(SEL.DASHBOARD.POSTS.BODY).should('have.length.greaterThan', 5)
      .then(quotes => {
        cy.window().scrollTo('bottom')
        cy.get(SEL.DASHBOARD.POSTS.BODY).should('have.length', quotes.length * 2)

        cy.window().scrollTo('bottom')
        cy.get(SEL.DASHBOARD.POSTS.BODY).should('have.length', quotes.length * 3)

        cy.window().scrollTo('bottom')
        cy.get(SEL.DASHBOARD.POSTS.BODY).should('have.length', quotes.length * 4)
      })
  })
})
