/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.mapper;

import com.tsguild.jackfontaneauxpearlrecovery.model.ImageType;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class ImageTypeMapper implements RowMapper<ImageType>{

    @Override
    public ImageType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImageType type = new ImageType();
        type.setImageTypeId(rs.getInt("ImageTypeId"));
        type.setImageType(rs.getString("ImageType"));
        return type;
    }
    
}
