/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.model.Comment;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxCommentDaoTest extends TestingBase{
    
   
    /**
     * Test of addComment method, of class JackFontaneauxCommentDao.
     */
    @Test
    public void testAddComment() {
       Comment comm = new Comment();
       comm.setPostId(1);
       comm.setTextContent("Myahhh romulunuygt");
       comm.setName(("Roofles"));
       commentDao.addComment(comm);
       Comment fromDao = commentDao.getCommentById(comm.getCommentId());
       assertEquals(fromDao, comm);
    }

    /**
     * Test of deleteComment method, of class JackFontaneauxCommentDao.
     */
    @Test
    public void testDeleteComment() {
       Comment comm = commentDao.getCommentById(1);
       assertEquals("A comment for the first blog post", comm.getTextContent());
       commentDao.deleteComment(1);
       assertEquals(null, commentDao.getCommentById(1));
       List<Comment> commentList = commentDao.getAllComments();
       assertEquals(3, commentList.size());
    }

    /**
     * Test of editComment method, of class JackFontaneauxCommentDao.
     */
    @Test
    public void testEditComment() {
       Comment comm = commentDao.getCommentById(1);
       assertEquals("A comment for the first blog post", comm.getTextContent());
       comm.setTextContent("MYAHHHLAHH");
       commentDao.editComment(comm);
       Comment fromDao = commentDao.getCommentById(1);
       assertEquals(fromDao, comm);
    }
    
    /**
     * Test of getCommentById method, of class JackFontaneauxCommentDao.
     */
    @Test
    public void testGetCommentById() {
        Comment comment = commentDao.getCommentById(1);
        Assert.assertTrue(comment.getTextContent().equals("A comment for the first blog post"));
    }
    
    /**
     * Test of getAllComments method, of class JackFontaneauxCommentDao.
     */
    @Test
    public void testGetAllComments() {
        List<Comment> comments = commentDao.getAllComments();
        Comment comment = comments.get(0);
        Assert.assertEquals(4, comments.size());
        Assert.assertTrue(comment.getTextContent().equals("A comment for the first blog post"));
    }
            
    /**
     * Test of getCommentsForPost method, of class JackFontaneauxCommentDao.
     */
    @Test
    public void testGetCommentsForPost() {
        List<Comment> comments = commentDao.getCommentsForPost(1);
        Comment comment = comments.get(0);
        Assert.assertEquals(2, comments.size());
        Assert.assertTrue(comment.getTextContent().equals("A comment for the first blog post"));
    }
    
}
