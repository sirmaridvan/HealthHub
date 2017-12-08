package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.Comment;
import halmob.healthhub.Models.Drug;

/**
 * Created by RIDVAN SIRMA on 12/8/2017.
 */

public interface CommentListener {
    public void commentRead(List<Comment> commentList);
}
