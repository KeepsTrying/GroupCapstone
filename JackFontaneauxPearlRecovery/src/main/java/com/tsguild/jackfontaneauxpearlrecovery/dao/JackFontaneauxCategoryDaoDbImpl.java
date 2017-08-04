/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.mapper.PageCategoryMapper;
import com.tsguild.jackfontaneauxpearlrecovery.mapper.PostCategoryMapper;
import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxCategoryDaoDbImpl implements JackFontaneauxCategoryDao{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

     /***************************************************************************************
     * 
     * 
     * 
     *     THESE NEXT METHODS HAVE TO DO WITH THE POST CATEGORIES COLUMN
     * 
     * 
     * 
     * 
     ***************************************************************************************/
    
    private static final String SQL_ADD_POST_CATEGORY = "insert into Categories "
            + "(Category) values (?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPostCategory(PostCategory newCategory) {
        jdbcTemplate.update(SQL_ADD_POST_CATEGORY,
                            newCategory.getCategoryName());
        int categoryId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        newCategory.setPostCategoryId(categoryId);
    }

    private static final String SQL_REMOVE_POSTS_CATEGORY_BRIDGE = "delete from PostsCategories "
            + "where CategoryId = ?";
    private static final String SQL_DELETE_CATEGORY = "delete from Categories "
            + "where CategoryId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePostCategory(int postCategoryId) {
        jdbcTemplate.update(SQL_REMOVE_POSTS_CATEGORY_BRIDGE, postCategoryId);
        jdbcTemplate.update(SQL_DELETE_CATEGORY, postCategoryId);
    }

    private static final String SQL_EDIT_POST_CATEGORY = "update Categories set "
            + "Category = ? where CategoryId = ?";
    @Override
    public void editPostCategory(PostCategory editedCategory) {
        jdbcTemplate.update(SQL_EDIT_POST_CATEGORY,
                            editedCategory.getCategoryName(),
                            editedCategory.getPostCategoryId());
    }

    private static final String SQL_GET_POST_CATEGORY_BY_ID = "select * from Categories "
            + "where CategoryId = ?";
    @Override
    public PostCategory getPostCategoryById(int postCategoryId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_POST_CATEGORY_BY_ID,
                                               new PostCategoryMapper(),
                                               postCategoryId);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String SQL_GET_ALL_POST_CATEGORIES = "select * from Categories";
    @Override
    public List<PostCategory> getAllPostCategories() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_POST_CATEGORIES, new PostCategoryMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    
    /***************************************************************************************
     * 
     * 
     * 
     *     THESE NEXT METHODS HAVE TO DO WITH THE STATIC PAGE CATEGORIES COLUMN
     * 
     * 
     * 
     * 
     ***************************************************************************************/
    
    private static final String SQL_ADD_PAGE_CATEGORY = "insert into PageCategories "
            + "(PageCategory) values (?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPageCategory(PageCategory newCategory) {
        jdbcTemplate.update(SQL_ADD_PAGE_CATEGORY,
                            newCategory.getCategoryName());
        int categoryId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        newCategory.setPageCategoryId(categoryId);
    }
    
    private static final String SQL_UPDATE_STATIC_PAGE_CATEGORY = "update StaticPages "
            + "set PageCategoryId = ? where PageCategoryId = ?";
    private static final String SQL_DELETE_PAGE_CATEGORY = "delete from PageCategories "
            + "where PageCategoryId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePageCategory(int pageCategoryId) {
        jdbcTemplate.update(SQL_UPDATE_STATIC_PAGE_CATEGORY, null, pageCategoryId);
        jdbcTemplate.update(SQL_DELETE_PAGE_CATEGORY, pageCategoryId);
    }

    private static final String SQL_UPDATE_PAGE_CATEGORY = "update PageCategories "
            + "set PageCategory = ? where PageCategoryId = ?";
    @Override
    public void editPageCategory(PageCategory editedCategory) {
        jdbcTemplate.update(SQL_UPDATE_PAGE_CATEGORY,
                            editedCategory.getCategoryName(),
                            editedCategory.getPageCategoryId());
        
    }

    private static final String SQL_GET_PAGE_CATEGORY_BY_ID = "select * from PageCategories "
            + "where PageCategoryId = ?";
    @Override
    public PageCategory getPageCategoryById(int pageCategoryId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_PAGE_CATEGORY_BY_ID,
                                               new PageCategoryMapper(),
                                               pageCategoryId);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String SQL_GET_ALL_PAGE_CATEGORIES = "select * from PageCategories";
    @Override
    public List<PageCategory> getAllPageCategories() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_PAGE_CATEGORIES, new PageCategoryMapper());
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    
}
