package Native;

import Base.Selector;

public enum ReactionsPage {
    PostNumLoves(Selector.Identifier.findByRescId, "post_num_notes"),
    ShareButton(Selector.Identifier.findByRescId, "post_share_icon"),
    CommentButton(Selector.Identifier.findByRescId, "post_comment_icon"),
    ReBlog(Selector.Identifier.findByRescId,"post_retweet_icon"),
    LoveButton(Selector.Identifier.findByRescId,"post_love_icon"),

    ;
    private final Selector S;

    ReactionsPage(Selector.Identifier f, String i) {
        S = new Selector(f, i);
    }

    public Selector getId() {
        return S;
    }
}
