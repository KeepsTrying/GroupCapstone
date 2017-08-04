/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.mapper;

import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class StaticPageMapper implements RowMapper<StaticPage>{

    @Override
    public StaticPage mapRow(ResultSet rs, int rowNum) throws SQLException {
        StaticPage page = new StaticPage();
        page.setPageId(rs.getInt("PageId"));
        page.setPageCategoryId(rs.getInt("PageCategoryId"));
        page.setTextArea(rs.getString("TextArea"));
        page.setNavName(rs.getString("NavName"));
        page.setTitle(rs.getString("Title"));
        page.setPublishDate(rs.getDate("PublishDate").toLocalDate());
        page.setIsPublished(rs.getBoolean("Published"));
        page.setIsCompleted(rs.getBoolean("Completed"));
        return page;
    }
    
}
