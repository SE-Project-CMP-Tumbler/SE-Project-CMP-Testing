/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('My blogs Posts tests', () => {
  let oldNotes = 0
  before(() => {
    cy.login(0)
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(6000)
    cy.visit(SEL.MYBLOG.URL.H)
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(8000)
  })

  it('All Posts should be mine', () => {
    cy.get(SEL.MYBLOG.POSTS.BLOG).each(($el) => {
      expect($el.text()).to.eq('hsn')
    })
  })

  it('Saving first post\'s notes count', () => {
    // eslint-disable-next-line cypress/no-assigning-return-values
    oldNotes = parseInt(cy.get(SEL.MYBLOG.POSTS.NOTES).get(1).invoke('text'))
  })

  it('Commenting', () => {
    const tstComment = 'HelTstAA'
    cy.get(SEL.MYBLOG.POSTS.COMMENT).eq(0).click()
    cy.get(SEL.MYBLOG.POSTS.COMMENT_WRITE).type(tstComment)
    cy.contains(SEL.MYBLOG.POSTS.COMMENT_SEND).click()

    cy.contains(tstComment)
  })

  it('', () => {
    cy.get(SEL.MYBLOG.POSTS.COMMENT_CANCEL).eq(0).click()
  })

  it('Comments incerase notes test', () => {
    cy.get(SEL.MYBLOG.POSTS.NOTES).invoke('parseInt')
      .should('be.gt', oldNotes)
  })

  it('Reblog', () => {
    // eslint-disable-next-line cypress/no-assigning-return-values
    let oldPost = ''
    cy.get(SEL.MYBLOG.POSTS.BODY).eq(0).then($ele => {
      oldPost = $ele.html()
    })
    cy.get(SEL.MYBLOG.POSTS.REBLOG).eq(0).click()
    cy.contains('Post').click()

    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(13000)
    cy.get(SEL.MYBLOG.POSTS.BODY).eq(0).then($ele => {
      expect($ele.html()).to.contain(oldPost)
    })
  })

  it('Reblogs incerase notes test', () => {
    cy.get(SEL.MYBLOG.POSTS.NOTES).invoke('parseInt')
      .should('be.gt', oldNotes)
  })

  it('Should contain multiple posts', () => {
    cy.get(SEL.MYBLOG.POSTS.BODY).should('have.length.at.least', 2)
  })

  it('Posts should contain data', () => {
    cy.get(SEL.MYBLOG.POSTS.BODY).each(($el) => {
      cy.wrap($el).should('not.have.html', '<span></span>')
    })
  })

  // https://github.com/bahmutov/infinite-scroll-quotes/blob/main/cypress/integration/spec.js
  it('infinite scroll and pagination', () => {
    cy.get(SEL.MYBLOG.POSTS.BODY).should('have.length.greaterThan', 5)
      .then(quotes => {
        cy.window().scrollTo('bottom')
        cy.get(SEL.MYBLOG.POSTS.BODY).should('have.length', quotes.length * 2)

        cy.window().scrollTo('bottom')
        cy.get(SEL.MYBLOG.POSTS.BODY).should('have.length', quotes.length * 3)

        cy.window().scrollTo('bottom')
        cy.get(SEL.MYBLOG.POSTS.BODY).should('have.length', quotes.length * 4)
      })
  })
})
