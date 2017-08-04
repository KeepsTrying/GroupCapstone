/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.mapper.ImageMapper;
import com.tsguild.jackfontaneauxpearlrecovery.mapper.StaticPageMapper;
import com.tsguild.jackfontaneauxpearlrecovery.mapper.UserMapper;
import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxStaticPageDaoDbImpl implements JackFontaneauxStaticPageDao{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_ADD_STATIC_PAGE = "insert into StaticPages "
            + "(PageCategoryId, NavName, Title, PublishDate, TextArea, Published, Completed) "
            + "values (?,?,?,?,?,?,?)";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addStaticPage(StaticPage newPage) {
        jdbcTemplate.update(SQL_ADD_STATIC_PAGE,
                            newPage.getPageCategoryId(),
                            newPage.getNavName(),
                            newPage.getTitle(),
                            newPage.getPublishDate().toString(),
                            newPage.getTextArea(),
                            newPage.getIsPublished(),
                            newPage.getIsCompleted());
        int pageId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        newPage.setPageId(pageId);
        insertStaticPageImages(newPage);
        insertStaticPageUsers(newPage);
    }

    private static final String SQL_REMOVE_PAGES_IMAGES_BRIDGE = "delete from PagesImages "
            + "where PageId = ?";
    private static final String SQL_REMOVE_PAGES_USERS_BRIDGE = "delete from PagesUsers "
            + "where PageId = ?";
    private static final String SQL_REMOVE_PAGE = "delete from StaticPages "
            + "where PageId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteStaticPage(int pageId) {
        jdbcTemplate.update(SQL_REMOVE_PAGES_IMAGES_BRIDGE, pageId);
        jdbcTemplate.update(SQL_REMOVE_PAGES_USERS_BRIDGE, pageId);
        jdbcTemplate.update(SQL_REMOVE_PAGE, pageId);
    }
    
    private static final String SQL_UPDATE_PAGE = "update StaticPages "
            + "set PageCategoryId = ?, NavName = ?, Title = ?, PublishDate = ?, "
            + "TextArea = ?, Published = ?, Completed = ? where PageId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void editStaticPage(StaticPage editedPage) {
        jdbcTemplate.update(SQL_UPDATE_PAGE,
                            editedPage.getPageCategoryId(),
                            editedPage.getNavName(),
                            editedPage.getTitle(),
                            editedPage.getPublishDate().toString(),
                            editedPage.getTextArea(),
                            editedPage.getIsPublished(),
                            editedPage.getIsCompleted(),
                            editedPage.getPageId());
        jdbcTemplate.update(SQL_REMOVE_PAGES_IMAGES_BRIDGE, editedPage.getPageId());
        jdbcTemplate.update(SQL_REMOVE_PAGES_USERS_BRIDGE, editedPage.getPageId());
        insertStaticPageImages(editedPage);
        insertStaticPageUsers(editedPage);
    }

    private static final String SQL_GET_STATIC_PAGE_BY_ID = "select * from StaticPages "
            + "where PageId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public StaticPage getStaticPageById(int pageId) {
        try{
            StaticPage page = jdbcTemplate.queryForObject(SQL_GET_STATIC_PAGE_BY_ID,
                                               new StaticPageMapper(),
                                               pageId);
            page.setAuthorList(getUsersByStaticPage(page));
            page.setImageList(getImagesByStaticPage(page));
            return page;
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    
    private static final String SQL_GET_ALL_STATIC_PAGES = "select * from StaticPages";
    @Override
    public List<StaticPage> getAllStaticPages(){
        try {
            List<StaticPage> pages = jdbcTemplate.query(SQL_GET_ALL_STATIC_PAGES, new StaticPageMapper());
            return associatePagesWithImageUser(pages);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    
    
    private static final String SQL_GET_USERS_BY_PAGE = "select * from Users as u "
            + "inner join PagesUsers as pu on u.UserId = pu.UserId "
            + "inner join StaticPages as sp on pu.PageId = sp.PageId "
            + "where pu.PageId = ?";
    @Override
    public List<User> getUsersByStaticPage(StaticPage page) {
        try{
            return jdbcTemplate.query(SQL_GET_USERS_BY_PAGE,
                                                     new UserMapper(),
                                                     page.getPageId());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String SQL_GET_IMAGES_BY_PAGE = "select * from Images as i "
            + "inner join PagesImages as pi on i.ImageId = pi.ImageId "
            + "inner join StaticPages as sp on pi.PageId = sp.PageId "
            + "where pi.PageId = ?";
    @Override
    public List<Image> getImagesByStaticPage(StaticPage page) {
        try{
            return jdbcTemplate.query(SQL_GET_IMAGES_BY_PAGE,
                                                       new ImageMapper(),
                                                       page.getPageId());
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    
    private static final String SQL_ADD_STATIC_IMAGE_BRIDGE = "insert into PagesImages "
            + "(PageId, ImageId) values (?,?)";
    private void insertStaticPageImages(StaticPage page){
        int pageId = page.getPageId();
        List<Image> imageList = page.getImageList();
        for(Image current: imageList){
            jdbcTemplate.update(SQL_ADD_STATIC_IMAGE_BRIDGE,
                                pageId,
                                current.getImageId());
        }
    }
    
    
    private static final String SQL_ADD_STATIC_USER_BRIDGE = "insert into PagesUsers "
            + "(PageId, UserId) values (?,?)";
    private void insertStaticPageUsers(StaticPage page){
        int pageId = page.getPageId();
        List<User> userList = page.getAuthorList();
        for(User current: userList){
            jdbcTemplate.update(SQL_ADD_STATIC_USER_BRIDGE,
                                pageId,
                                current.getUserId());
        }
    }
    

    private static final String SQL_GET_STATIC_PAGES_WITH_CATEGORY
            = "SELECT PageId, PageCategoryId, NavName, Title, PublishDate, TextArea, Published, Completed "
            + "FROM StaticPages "
            + "LEFT JOIN PageCategories USING (PageCategoryId) "
            + "WHERE PageCategoryId = ?;";
    
    @Override
    public List<StaticPage> getStaticPagesWithCategory(PageCategory category) {
        try {
            List<StaticPage> pageList = jdbcTemplate.query(SQL_GET_STATIC_PAGES_WITH_CATEGORY, new StaticPageMapper(), category.getPageCategoryId());
            return associatePagesWithImageUser(pageList);
        } catch(EmptyResultDataAccessException | NullPointerException e){
            return null;
        }
    }
    

    private static final String SQL_GET_UNPUBLISHED_STATIC_PAGES
            = "SELECT * FROM StaticPages WHERE Published = 0;";
    
    @Override
    public List<StaticPage> getUnpublishedStaticPages() {
        try {
            List<StaticPage> pageList = jdbcTemplate.query(SQL_GET_UNPUBLISHED_STATIC_PAGES, new StaticPageMapper());
            return associatePagesWithImageUser(pageList);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    
    



    private static final String SQL_GET_UNPUBLISHED_STATIC_PAGES_BY_AUTHOR
            = "SELECT PageId, PageCategoryId, NavName, Title, PublishDate, TextArea, Published, Completed "
            + "FROM StaticPages "
            + "LEFT JOIN PagesUsers USING (PageId) "
            + "WHERE UserId = ? AND Published = 0;";
    
    @Override
    public List<StaticPage> getUnpublishedStaticPagesByAuthor(User author) {
        try {
            List<StaticPage> pageList = jdbcTemplate.query(SQL_GET_UNPUBLISHED_STATIC_PAGES_BY_AUTHOR, new StaticPageMapper(), author.getUserId());
            return associatePagesWithImageUser(pageList);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    
    private static final String SQL_GET_LIMITED_NUMBER_OF_UNPUBLISHED_PAGES_IN_GROUP
            = "SELECT * FROM StaticPages WHERE Published = 0 AND Completed = 1 ORDER BY PublishDate DESC LIMIT ?,?;";
    
    @Override
    public List<StaticPage> getUnpublishedStaticPagesByGroup(int startingPageNum, int groupSize) {
        try {
            List<StaticPage> pageList = jdbcTemplate.query(SQL_GET_LIMITED_NUMBER_OF_UNPUBLISHED_PAGES_IN_GROUP, 
                    new StaticPageMapper(), 
                    startingPageNum, 
                    groupSize);
            return associatePagesWithImageUser(pageList);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    
    
    
    private static final String SQL_GET_PUBLISHED_STATIC_PAGES  
            = "SELECT * FROM StaticPages WHERE Published = 1;";
    
    @Override
    public List<StaticPage> getPublishedStaticPages() {
        try {
            List<StaticPage> pageList = jdbcTemplate.query(SQL_GET_PUBLISHED_STATIC_PAGES, new StaticPageMapper());
            return associatePagesWithImageUser(pageList);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    

    private static final String SQL_GET_LIMITED_NUMBER_OF_PUBLISHED_PAGES_IN_GROUP
            = "SELECT * FROM StaticPages WHERE Published = 1 ORDER BY PublishDate DESC LIMIT ?,?;";
    
    @Override
    public List<StaticPage> getPublishedStaticPagesByGroup(int startingPageNum, int groupSize) {
        try {
            List<StaticPage> pageList = jdbcTemplate.query(SQL_GET_LIMITED_NUMBER_OF_PUBLISHED_PAGES_IN_GROUP, 
                    new StaticPageMapper(), 
                    startingPageNum, 
                    groupSize);
            return associatePagesWithImageUser(pageList);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }


    private List<StaticPage> associatePagesWithImageUser(List<StaticPage> pageList){
        for(StaticPage current: pageList){
            current.setAuthorList(getUsersByStaticPage(current));
            current.setImageList(getImagesByStaticPage(current));
        }
        return pageList;
    }

}
