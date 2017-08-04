/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.mapper.CommentMapper;
import com.tsguild.jackfontaneauxpearlrecovery.model.Comment;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxCommentDaoDbImpl implements JackFontaneauxCommentDao{
    
    JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_COMMENT = "insert into Comments "
            + "(PostId,Comment,Name) values (?,?,?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addComment(Comment newComment) {
        jdbcTemplate.update(SQL_ADD_COMMENT,
                            newComment.getPostId(),
                            newComment.getTextContent(),
                            newComment.getName());
        int commentId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        newComment.setCommentId(commentId);
    }

    private static final String SQL_DELETE_COMMENT = "delete from Comments "
            + "where CommentId = ?";
    @Override
    public void deleteComment(int commentId) {
        jdbcTemplate.update(SQL_DELETE_COMMENT, commentId);
    }

    private static final String SQL_EDIT_COMMENT = "update Comments set "
            + "PostId = ?, Comment = ?, Name = ? where CommentId = ?";
    @Override
    public void editComment(Comment editedComment) {
        jdbcTemplate.update(SQL_EDIT_COMMENT, 
                            editedComment.getPostId(),
                            editedComment.getTextContent(),
                            editedComment.getName(),
                            editedComment.getCommentId());         
    }

    private static final String SQL_GET_COMMENT_BY_ID = "select * from Comments "
            + "where CommentId = ?";
    @Override
    public Comment getCommentById(int commentId) {
        try {
        return jdbcTemplate.queryForObject(SQL_GET_COMMENT_BY_ID,
                                           new CommentMapper(),
                                           commentId);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String SQL_GET_ALL_COMMENTS = "select * from Comments";
    @Override
    public List<Comment> getAllComments() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_COMMENTS,
                                      new CommentMapper());
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String SQL_GET_COMMENTS_FOR_POST
            = "SELECT * FROM Comments WHERE PostId = ?;";
    
    @Override
    public List<Comment> getCommentsForPost(int postId) {
        return jdbcTemplate.query(SQL_GET_COMMENTS_FOR_POST, new CommentMapper(), postId);
    }
    
}
