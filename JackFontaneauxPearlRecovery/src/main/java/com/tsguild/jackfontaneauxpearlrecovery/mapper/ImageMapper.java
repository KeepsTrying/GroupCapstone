/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.mapper;

import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class ImageMapper implements RowMapper<Image>{

    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image image = new Image();
        image.setImageId(rs.getInt("ImageId"));
        image.setImageTypeId(rs.getInt("ImageTypeId"));
        image.setImageUrl(rs.getString("Path"));
        
        return image;
    }
    
}
