/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.Comment;
import java.util.List;

/**
 *
 * @author scooke11
 */
public interface JFoCommentServiceLayer {
    
    public void addComment(Comment newComment);
    
    public void deleteComment(int commentId);
    
    public void editComment(Comment editedComment);
    
    public Comment getCommentById(int commentId);
    
    public List<Comment> getAllComments();
    
    public List<Comment> getCommentsForPost(int postId);
    
}
