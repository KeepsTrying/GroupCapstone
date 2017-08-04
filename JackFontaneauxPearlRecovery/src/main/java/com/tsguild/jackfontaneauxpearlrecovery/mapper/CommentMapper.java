/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.mapper;

import com.tsguild.jackfontaneauxpearlrecovery.model.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class CommentMapper implements RowMapper<Comment>{

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("CommentId"));
        comment.setPostId(rs.getInt("PostId"));
        comment.setTextContent(rs.getString("Comment"));
        comment.setName(rs.getString("Name"));
        return comment;
    }
    
}
