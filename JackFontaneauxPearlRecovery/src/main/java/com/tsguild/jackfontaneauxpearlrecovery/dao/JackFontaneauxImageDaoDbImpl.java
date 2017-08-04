/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.mapper.ImageMapper;
import com.tsguild.jackfontaneauxpearlrecovery.mapper.ImageTypeMapper;
import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.ImageType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxImageDaoDbImpl implements JackFontaneauxImageDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_IMAGE = "insert into Images "
            + "(Path, ImageTypeId) values (?,?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addImage(Image newImage) {

        jdbcTemplate.update(SQL_ADD_IMAGE,
                newImage.getImageUrl(),
                newImage.getImageTypeId());
        int imageId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        newImage.setImageId(imageId);

    }

    private static final String SQL_REMOVE_IMAGE_POST_BRIDGE = "delete from PostsImages "
            + "where ImageId = ?";
    private static final String SQL_REMOVE_IMAGE_STATIC_PAGE_BRIDGE = "delete from PagesImages "
            + "where ImageId = ?";
    private static final String SQL_REMOVE_IMAGE = "delete from Images where ImageId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteImage(int imageId) {

        jdbcTemplate.update(SQL_REMOVE_IMAGE_POST_BRIDGE, imageId);
        jdbcTemplate.update(SQL_REMOVE_IMAGE_STATIC_PAGE_BRIDGE, imageId);
        jdbcTemplate.update(SQL_REMOVE_IMAGE, imageId);

    }

    private static final String SQL_UPDATE_IMAGE = "update Images "
            + "set Path = ?, ImageTypeId = ? where ImageId = ?";

    @Override
    public void editImage(Image editedImage) {
        jdbcTemplate.update(SQL_UPDATE_IMAGE,
                editedImage.getImageUrl(),
                editedImage.getImageTypeId(),
                editedImage.getImageId());
    }

    private static final String SQL_GET_IMAGE_BY_ID
            = "select * from Images where ImageId = ?";

    @Override
    public Image getImageById(int imageId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_IMAGE_BY_ID,
                    new ImageMapper(),
                    imageId);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_PREVIEW_IMAGES
            = "SELECT ImageId, Path, ImageTypeId "
            + "FROM Images "
            + "INNER JOIN ImageTypes USING (ImageTypeId) "
            + "WHERE ImageTypeId = 1;";

    @Override
    public List<Image> getAllPreviewImages() {
        List<Image> previewImages = new ArrayList<>();
        try {
            previewImages = jdbcTemplate.query(SQL_GET_ALL_PREVIEW_IMAGES, new ImageMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return previewImages;
    }

    private static final String SQL_GET_PREVIEW_IMAGE_FOR_POST
            = "SELECT ImageId, Path, ImageTypeId "
            + "FROM ImageTypes "
            + "INNER JOIN Images USING (ImageTypeId) "
            + "LEFT JOIN PostsImages USING (ImageId) "
            + "LEFT JOIN Posts USING (PostId) "
            + "WHERE PostId = ? AND ImageTypeId = 1;";

    @Override
    public Image getPreviewImageForPost(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_PREVIEW_IMAGE_FOR_POST, new ImageMapper(), postId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_HEADER_IMAGES
            = "SELECT ImageId, Path, ImageTypeId "
            + "FROM Images "
            + "INNER JOIN ImageTypes USING (ImageTypeId) "
            + "WHERE ImageTypeId = 2;";

    @Override
    public List<Image> getAllHeaderImages() {
        List<Image> headerImages = new ArrayList<>();
        try {
            headerImages = jdbcTemplate.query(SQL_GET_ALL_HEADER_IMAGES, new ImageMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return headerImages;
    }

    private static final String SQL_GET_HEADER_IMAGE_FOR_POST
            = "SELECT ImageId, Path, ImageTypeId "
            + "FROM ImageTypes "
            + "INNER JOIN Images USING (ImageTypeId) "
            + "LEFT JOIN PostsImages USING (ImageId) "
            + "LEFT JOIN Posts USING (PostId) "
            + "WHERE PostId = ? AND ImageTypeId = 2;";

    @Override
    public Image getHeaderImageForBlogPost(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_HEADER_IMAGE_FOR_POST, new ImageMapper(), postId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_HEADER_IMAGE_FOR_STATIC_PAGE
            = "SELECT ImageId, Path, ImageTypeId "
            + "FROM ImageTypes "
            + "INNER JOIN Images USING (ImageTypeId) "
            + "LEFT JOIN PagesImages USING (ImageId) "
            + "LEFT JOIN StaticPages USING (PageId) "
            + "WHERE PageId = ? AND ImageTypeId = 2;";

    @Override
    public Image getHeaderImageForStaticPage(int pageId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_HEADER_IMAGE_FOR_STATIC_PAGE, new ImageMapper(), pageId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_STATIC_PAGE_BODY_IMAGES
            = "SELECT ImageId, Path, ImageTypeId "
            + "FROM Images "
            + "INNER JOIN ImageTypes USING (ImageTypeId) "
            + "WHERE ImageTypeId = 3;";

    @Override
    public List<Image> getAllStaticPageBodyImages() {
        List<Image> staticPageImages = new ArrayList<>();
        try {
            staticPageImages = jdbcTemplate.query(SQL_GET_ALL_STATIC_PAGE_BODY_IMAGES, new ImageMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return staticPageImages;
    }

    private static final String SQL_GET_BODY_IMAGE_FOR_STATIC_PAGE
            = "SELECT ImageId, Path, ImageTypeId "
            + "FROM ImageTypes "
            + "INNER JOIN Images USING (ImageTypeId) "
            + "LEFT JOIN PagesImages USING (ImageId) "
            + "LEFT JOIN StaticPages USING (PageId) "
            + "WHERE PageId = ? AND ImageTypeId = 3;";

    @Override
    public Image getBodyImageForStaticPage(int pageId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_BODY_IMAGE_FOR_STATIC_PAGE, new ImageMapper(), pageId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_BLOG_POST_BODY_IMAGES
            = "SELECT ImageId, Path, ImageTypeId "
            + "FROM Images "
            + "INNER JOIN ImageTypes USING (ImageTypeId) "
            + "WHERE ImageTypeId = 4;";

    @Override
    public List<Image> getAllBlogPostBodyImages() {
        List<Image> blogPostImages = new ArrayList<>();
        try {
            blogPostImages = jdbcTemplate.query(SQL_GET_ALL_BLOG_POST_BODY_IMAGES, new ImageMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return blogPostImages;
    }

    private static final String SQL_GET_BODY_IMAGE_FOR_POST
            = "SELECT ImageId, Path, ImageTypeId "
            + "FROM ImageTypes "
            + "INNER JOIN Images USING (ImageTypeId) "
            + "LEFT JOIN PostsImages USING (ImageId) "
            + "LEFT JOIN Posts USING (PostId) "
            + "WHERE PostId = ? AND ImageTypeId = 4;";

    @Override
    public Image getBodyImageForBlogPost(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_BODY_IMAGE_FOR_POST, new ImageMapper(), postId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    
    
    
    
    

    /**
     * ************************************************************************
     *
     *
     * THESE ARE THE IMAGETYPE METHODS
     *
     *
     *************************************************************************
     */
    private static final String SQL_ADD_IMAGE_TYPE = "insert into ImageTypes "
            + "(ImageType) values (?)";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public ImageType addImageType(ImageType newType) {
        jdbcTemplate.update(SQL_ADD_IMAGE_TYPE,
                newType.getImageType());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        newType.setImageTypeId(id);

        return newType;
    }

    private static final String SQL_DELETE_IMAGE_TYPE
            = "DELETE FROM ImageTypes "
            + "WHERE ImageTypeId = ?";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void deleteImageType(int typeId) {
        String SQL_GET_IMAGE_IDS_OF_IMAGE_TYPE
                = "SELECT ImageId "
                + "FROM Images "
                + "LEFT JOIN ImageTypes USING (ImageTypeId) "
                + "WHERE ImageTypeId = " + typeId + ";";

        List<Integer> imageIds = jdbcTemplate.queryForList(SQL_GET_IMAGE_IDS_OF_IMAGE_TYPE, Integer.class);

        for (int imageId : imageIds) {
            deleteImage(imageId);
        }

        jdbcTemplate.update(SQL_DELETE_IMAGE_TYPE, typeId);
    }

    private static final String SQL_EDIT_IMAGE_TYPE
            = "UPDATE ImageTypes SET "
            + "ImageType = ? where ImageTypeId = ?";

    @Override
    public void editImageType(ImageType editedType) {
        jdbcTemplate.update(SQL_EDIT_IMAGE_TYPE,
                editedType.getImageType(),
                editedType.getImageTypeId());
    }

    private static final String SQL_GET_IMAGE_TYPE_BY_ID = "select * from ImageTypes "
            + "where ImageTypeId = ?";

    @Override
    public ImageType getImageTypeById(int typeId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_IMAGE_TYPE_BY_ID,
                    new ImageTypeMapper(),
                    typeId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_IMAGE_TYPES = "select * from ImageTypes";

    @Override
    public List<ImageType> getAllImageTypes() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_IMAGE_TYPES, new ImageTypeMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
