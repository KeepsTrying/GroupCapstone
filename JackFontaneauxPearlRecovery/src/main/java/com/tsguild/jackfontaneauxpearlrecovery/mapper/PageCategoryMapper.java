/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.mapper;

import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class PageCategoryMapper implements RowMapper<PageCategory>{

    @Override
    public PageCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        PageCategory pageCat = new PageCategory();
        pageCat.setPageCategoryId(rs.getInt("PageCategoryId"));
        pageCat.setCategoryName(rs.getString("PageCategory"));
        return pageCat;
    }
    
}
