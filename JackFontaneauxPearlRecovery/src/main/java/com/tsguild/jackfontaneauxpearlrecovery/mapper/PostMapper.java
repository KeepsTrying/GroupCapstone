/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.mapper;

import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class PostMapper implements RowMapper<Post>{

    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setPostId(rs.getInt("PostId"));
        post.setTitle(rs.getString("Title"));
        post.setSynopsis(rs.getString("Synopsis"));
        post.setContent(rs.getString("Body"));
        post.setLatitude(rs.getBigDecimal("Latitude"));
        post.setLongitude(rs.getBigDecimal("Longitude"));
        post.setPostDate(rs.getDate("PostDate").toLocalDate());
        try{
        post.setExpirationDate(rs.getDate("ExpirationDate").toLocalDate());
        } catch(NullPointerException e){
            post.setExpirationDate(LocalDate.parse("2225-01-01"));
        }
        post.setIsPublished(rs.getBoolean("Published"));
        post.setIsCompleted(rs.getBoolean("Completed"));
        return post;
        
    }
    
}
