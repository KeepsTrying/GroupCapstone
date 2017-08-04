/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.Comment;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class JFoCommentServiceLayerImplTest extends ServiceTestingBase {
    

    /**
     * Test of addComment method, of class JFoCommentServiceLayerImpl.
     */
    @Test
    public void testAddComment() {
        Comment expectedComment = new Comment();
        expectedComment.setPostId(1);
        expectedComment.setTextContent("Love this post!");
        comServ.addComment(expectedComment);
        Comment actualComment = comServ.getCommentById(expectedComment.getCommentId());
        Assert.assertEquals(expectedComment, actualComment);
    }

    /**
     * Test of deleteComment method, of class JFoCommentServiceLayerImpl.
     */
    @Test
    public void testDeleteComment() {
        Comment comment = new Comment();
        comment.setPostId(1);
        comment.setTextContent("Love this post!");
        comServ.addComment(comment);
        int numOfComments = comServ.getAllComments().size();
        int commentId = comment.getCommentId();
        comServ.deleteComment(commentId);
        Assert.assertEquals((numOfComments - 1), comServ.getAllComments().size());
        Assert.assertEquals(null, comServ.getCommentById(commentId));
    }

    /**
     * Test of editComment method, of class JFoCommentServiceLayerImpl.
     */
    @Test
    public void testEditComment() {
        Comment comment = comServ.getCommentById(1);
        comment.setTextContent("newTextContent");
        comServ.editComment(comment);
        Assert.assertTrue(comServ.getCommentById(comment.getCommentId()).getTextContent().equals("newTextContent"));
    }

    /**
     * Test of getCommentById method, of class JFoCommentServiceLayerImpl.
     */
    @Test
    public void testGetCommentById() {
        Comment comment = comServ.getCommentById(3);
        Assert.assertTrue(comment.getTextContent().equals("The first comment for the second blog post"));
    }

    /**
     * Test of getAllComments method, of class JFoCommentServiceLayerImpl.
     */
    @Test
    public void testGetAllComments() {
        List<Comment> comments = comServ.getAllComments();
        int numOfComments = comments.size();
        Comment comment = comments.get(0);
        Assert.assertEquals(4, numOfComments);
        Assert.assertTrue(comment.getTextContent().equals("A comment for the first blog post"));
    }
    
    /**
     * Test of getCommentsForPost method, of class JFoCommentServiceLayerImpl.
     */
    @Test
    public void testGetCommentsForPost() {
        List<Comment> comments = comServ.getCommentsForPost(1);
        int numOfComments = comments.size();
        Comment comment = comments.get(1);
        Assert.assertEquals(2, numOfComments);
        Assert.assertTrue(comment.getTextContent().equals("Another comment for the first blog post"));
    }
    
}
