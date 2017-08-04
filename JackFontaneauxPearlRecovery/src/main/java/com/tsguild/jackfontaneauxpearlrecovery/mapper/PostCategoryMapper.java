/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.mapper;

import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class PostCategoryMapper implements RowMapper<PostCategory>{

    @Override
    public PostCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        PostCategory pcat = new PostCategory();
        pcat.setPostCategoryId(rs.getInt("CategoryId"));
        pcat.setCategoryName(rs.getString("Category"));
        return pcat;
    }
    
}
