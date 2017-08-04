/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class TestingBase {
    
    protected JackFontaneauxCategoryDao catDao;
    protected JackFontaneauxCommentDao commentDao;
    protected JackFontaneauxImageDao imageDao;
    protected JackFontaneauxPostDao postDao;
    protected JackFontaneauxStaticPageDao pageDao;
    protected JackFontaneauxTagDao tagDao;
    protected JackFontaneauxUserDao userDao;
    
    protected JdbcTemplate jdbcTemplate;
    
    @Before
    public void setUp() {
        
        ApplicationContext ctxFactory = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        catDao = ctxFactory.getBean("categoryDao", JackFontaneauxCategoryDao.class);
        commentDao = ctxFactory.getBean("commentDao", JackFontaneauxCommentDao.class);
        imageDao = ctxFactory.getBean("imageDao", JackFontaneauxImageDao.class);
        postDao = ctxFactory.getBean("postDao", JackFontaneauxPostDao.class);
        pageDao = ctxFactory.getBean("staticPageDao", JackFontaneauxStaticPageDao.class);
        tagDao = ctxFactory.getBean("tagDao", JackFontaneauxTagDao.class);
        userDao = ctxFactory.getBean("userDao", JackFontaneauxUserDao.class);
        jdbcTemplate = ctxFactory.getBean("jdbcTemplate", JdbcTemplate.class);
        
        jdbcTemplate.execute("DELETE FROM PostsTags where 1=1");
        jdbcTemplate.execute("DELETE FROM PostsCategories WHERE 1=1");
        jdbcTemplate.execute("DELETE FROM PostsUsers WHERE 1=1");
        jdbcTemplate.execute("DELETE FROM PostsImages WHERE 1=1");
        jdbcTemplate.execute("DELETE FROM PagesImages WHERE 1=1");
        jdbcTemplate.execute("DELETE FROM PagesUsers WHERE 1=1");
        jdbcTemplate.execute("DELETE FROM Comments WHERE 1=1");
        jdbcTemplate.execute("DELETE FROM Tags WHERE 1=1");
        jdbcTemplate.execute("DELETE FROM Categories where 1=1");
        jdbcTemplate.execute("DELETE FROM Posts where 1=1");
        jdbcTemplate.execute("DELETE FROM Authorities where 1=1");
        jdbcTemplate.execute("DELETE FROM Users where 1=1");
        jdbcTemplate.execute("DELETE FROM StaticPages where 1=1");
        jdbcTemplate.execute("DELETE FROM PageCategories where 1=1");
        jdbcTemplate.execute("DELETE FROM Images where 1=1");
        jdbcTemplate.execute("DELETE FROM ImageTypes where 1=1");
      
        
        
        jdbcTemplate.execute("INSERT INTO Tags (TagId, Tag) "
                    + "VALUES (1, '#sweet'), (2, '#cool'), (3, '#awesome'), (4, '#DRY')");


        jdbcTemplate.execute("INSERT INTO Categories (CategoryId, Category) "
                    + "VALUES (1, 'Diving'), (2, 'Snorkling'), (3, 'Oysters'), (4, 'Rando')");


        jdbcTemplate.execute("INSERT INTO PageCategories (PageCategoryId, PageCategory) "
                    + "VALUES (1, 'Bio'), (2, 'FAQ'), (3, 'Contact Info'), (4, 'Predetermined Category')");


        jdbcTemplate.execute("INSERT INTO Users (UserId, UserName, Password, Enabled, Role, FirstName, LastName, Email, Latitude, Longitude) "
                    + "VALUES (1, 'thisUsername', 'thisPassword', 1, 'Admin', 'thisFirst', 'thisLast', 'email@gmail.com', 66.66, 66.66), "
                    + "(2, 'thatUsername', 'thatPassword', 1, 'Author', 'thatFirst', 'thatLast', 'that@email.com', 12.22, 45.67)");
        
        jdbcTemplate.execute("INSERT INTO Authorities (UserName, Authority) "
                    + "VALUES ('thisUsername', 'ROLE_ADMIN')");

        jdbcTemplate.execute("INSERT INTO ImageTypes (ImageTypeId, ImageType) values (1, \"preview\"), (2, \"header\"), (3, \"page\"), (4, \"post\")");
        
        jdbcTemplate.execute("INSERT INTO Images (ImageId, Path, ImageTypeId) "
                    + "VALUES (1, \"http://imgur.com/preview1.jpg\", 1), (5, \"http://imgur.com/preview2.jpg\",1), (2, \"http://imgur.com/headerPic.jpg\", 2), (3, \"http://imgur.com/staticPagePic.jpg\", 3), (4, \"http://imgur.com/blogPostPic.jpg\", 4)");


        jdbcTemplate.execute("INSERT INTO StaticPages (PageId, PageCategoryId, NavName, Title, PublishDate, TextArea, Published, Completed) "
                    + "VALUES (1, 2, 'Read First!', 'Our Site''s FAQ', '2017-07-18', 'This is the where users would learn about the company and site.', 1, 1), " 
                    + "(2, 1, 'A Bio Page', 'Author''s Bio', '2017-07-19', 'Second static page will have this text body', 0, 1)");


        jdbcTemplate.execute("INSERT INTO Posts (PostId, PostDate, Title, Synopsis, Body, Published, Completed, Latitude, Longitude) "
                    + "VALUES (1, '2017-07-18', 'First Blog Post', 'Quick synopsis for this Post', 'Text area for the first Post', 1, 1, 45.55, 23.45), " 
                    + "(2, '2017-07-19', 'Unpublished Post', 'This post is unfinished', 'This will be an awesome blog foreal', 0, 0, 12.34, 23.22)");

        jdbcTemplate.execute("INSERT INTO Comments (CommentId, PostId, Comment) "
                    + "VALUES (1, 1, 'A comment for the first blog post'), "
                    + "(2, 1, 'Another comment for the first blog post'), "
                    + "(3, 2, 'The first comment for the second blog post'), "
                    + "(4, 2, 'Another comment for the second blog post')");

        jdbcTemplate.execute("INSERT INTO PostsTags (PostId, TagId) "
                    + "VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 4)");


        jdbcTemplate.execute("INSERT INTO PostsCategories (PostId, CategoryId) "
                    + "VALUES (1, 1), (1, 3), (2, 1), (2, 4)");


        jdbcTemplate.execute("INSERT INTO PostsUsers (PostId, UserId) "
                    + "VALUES (1, 1), (2, 1), (2, 2)");


        jdbcTemplate.execute("INSERT INTO PagesUsers (PageId, UserId) "
                    + "VALUES (1, 1), (1, 2), (2, 1)");


        jdbcTemplate.execute("INSERT INTO PostsImages (PostId, ImageId) "
                    + "VALUES (1, 1), (1, 2), (2, 3), (2,5)");


        jdbcTemplate.execute("INSERT INTO PagesImages (PageId, ImageId) "
                    + "VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 3)");
        
        
        
    }
}