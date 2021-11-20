// import mailslurp-client
import { MailSlurp } from 'mailslurp-client'

// create a client
// This is fatal, though I had a problem in reading it from the environment variable, it should be
/*
    *** const apiKey = process.env.API_KEY
    author: Karim
*/
const apiKey = 'b3e42306bca352b73da03e1b1d087eafda0e6a3b6344a63a7857aa8e275a1fbb'

const mailslurp = new MailSlurp({ apiKey })

export const validLoginE1 = mailslurp.inboxController.getInbox({ inboxId: 'd8c1f7c1-394c-494b-bac1-aed8006efd51' })
export const validLoginE2 = mailslurp.inboxController.getInbox({ inboxId: '773eaf51-e61b-4f90-84ec-8c56477a65a7' })
// export const newEmail = mailslurp.createInbox()
// console.log(await validLoginE)
