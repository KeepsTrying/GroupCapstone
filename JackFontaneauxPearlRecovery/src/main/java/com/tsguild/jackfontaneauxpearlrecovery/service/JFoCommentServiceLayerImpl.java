/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxCommentDao;
import com.tsguild.jackfontaneauxpearlrecovery.model.Comment;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author scooke11
 */
public class JFoCommentServiceLayerImpl implements JFoCommentServiceLayer {

    private JackFontaneauxCommentDao commentDao;

    public void setCommentDao(JackFontaneauxCommentDao commentDao) {
        this.commentDao = commentDao;
    }
    
    @Inject
    public JFoCommentServiceLayerImpl(JackFontaneauxCommentDao commentDao) {
        this.commentDao = commentDao;
    }
    
    @Override
    public void addComment(Comment newComment) {
        commentDao.addComment(newComment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentDao.deleteComment(commentId);
    }

    @Override
    public void editComment(Comment editedComment) {
        commentDao.editComment(editedComment);
    }

    @Override
    public Comment getCommentById(int commentId) {
        return commentDao.getCommentById(commentId);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.getAllComments();
    }

    @Override
    public List<Comment> getCommentsForPost(int postId) {
        return commentDao.getCommentsForPost(postId);
    }
    
}
