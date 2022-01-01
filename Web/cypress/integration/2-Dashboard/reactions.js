/// <reference types="cypress" />

describe('Posts: user reactions, likes, shares, reblogs, notes  ', () => {
  it('Block, unfollow', () => {
  /*
  TODO:
  * block some blogs
  * check the user profile for the blocked users/blogs
  * unblock, then re-check the user profile
  * unfollow, check the user profile
  * re-follow, .....
  * check if you can recieve or send messages to a blocked blog
  */
  })
  it('Reactions: like, comment, reblog, forward, mention, reply', () => {
  /*
  TODO:
  * give a like to several posts and check the respective counts
  * do the same for notes, adding the check for notes/comment existence
  * optional: check for nested threads, at least 1 level nesting should work to enable a reply
  * reblog a post, check for its exitence in the user the profile, perform this test on the rebolgged post and check the statistics
  * forward the post to other platforms, [[do not know what to do]]
  * mention a friend, the friend should check his notfications, check his actions if any
  * reply to note, the user should be notified when someone replys to his note/comment
  * copy link, I don't remember any use cases
  */
  })
  it()
})
