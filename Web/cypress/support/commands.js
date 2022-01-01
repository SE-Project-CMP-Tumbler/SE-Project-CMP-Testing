// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

import { LOGIN } from '../Page_Objects'

Cypress.Commands.add('login', (email, password) => {
  cy.visit(LOGIN.URL)
  cy.get(LOGIN.EMAIL).type(email)
  cy.get(LOGIN.PASSWORD).type(password)
  cy.get(LOGIN.SUBMIT).click()
})

Cypress.Commands.add('login', (idx) => {
  const emails = ['hsn@hi2.in', 'karm@hi2.in']
  const passwords = [atob('SHNuQGhpMi5pbg=='), atob('S2FybUBoaTIuaW4=')]

  cy.visit(LOGIN.URL)
  cy.get(LOGIN.EMAIL).type(emails[idx])
  cy.get(LOGIN.PASSWORD).type(passwords[idx])
  cy.get(LOGIN.SUBMIT).click()
})
