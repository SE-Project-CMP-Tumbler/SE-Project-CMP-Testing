/// <reference types="cypress" />

import * as SEL from '../../Page_Objects/'

describe('Elements of Dashboard Page', () => {
  before(() => {
    cy.login('hsn@hi2.in', atob('SHNuQGhpMi5pbg=='))
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    cy.wait(3000)
  })

  it('NavBar: Account', () => {
    cy.get(SEL.DASHBOARD.ACCOUNT._).should('exist')
  })

  it('NavBar: Chat', () => {
    cy.get(SEL.DASHBOARD.CHAT._).should('exist')
  })

  it('Check out these Blogs', () => {
    cy.get(SEL.DASHBOARD.CHECK_OUT_BLOGS).should('have.length.at.least', 1)
  })

  it('NavBar: Explore', () => {
    cy.get(SEL.DASHBOARD.EXPLORE).should('exist')
  })

  it('NavBar: Home', () => {
    cy.get(SEL.DASHBOARD.HOME).should('exist')
  })

  it('NavBar: inbox', () => {
    cy.get(SEL.DASHBOARD.INBOX).should('exist')
  })

  it('News feed', () => {
    cy.get(SEL.DASHBOARD.POSTS.BODY).should('have.length.at.least', 1)
  })

  it('NavBar: new post', () => {
    cy.get(SEL.DASHBOARD.NEW_POST).should('exist')
  })

  it('NavBar: Notifcation', () => {
    cy.get(SEL.DASHBOARD.NOTIFICATIONS._).should('exist')
  })

  it('Radar blog', () => {
    cy.get(SEL.DASHBOARD.RADAR.BLOG).should('exist')
  })

  it('Search bar', () => {
    cy.get(SEL.DASHBOARD.SEARCH_BAR).should('exist')
  })
})
