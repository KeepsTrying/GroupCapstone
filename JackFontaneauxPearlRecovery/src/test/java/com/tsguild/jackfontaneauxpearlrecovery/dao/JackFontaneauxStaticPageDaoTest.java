/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxStaticPageDaoTest extends TestingBase{
    
    
    /**
     * Test of addStaticPage method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testAddStaticPage() {
      StaticPage newPage = new StaticPage();
      newPage.setTitle("Myahh");
      newPage.setNavName("The Myah Page");
      PageCategory newOne = new PageCategory();
      newOne.setCategoryName("Myahhhhh");
      catDao.addPageCategory(newOne);
      newPage.setPageCategoryId(newOne.getPageCategoryId());
      newPage.setPublishDate(LocalDate.parse("2017-09-12", DateTimeFormatter.ISO_DATE));
      newPage.setIsCompleted(true);
      newPage.setIsPublished(true);
      newPage.setTextArea("This is the content of the new page and of courses I must fills it with" 
      + "MYAAAAAAAHHHHHHHHH");
      List<Image> newList = new ArrayList<>();
      List<User> userList = new ArrayList<>();
      newList.add(imageDao.getImageById(1));
      userList.add(userDao.getUserById(1));
      newPage.setAuthorList(userList);
      newPage.setImageList(newList);
      pageDao.addStaticPage(newPage);
      StaticPage fromDao = pageDao.getStaticPageById(newPage.getPageId());
      assertEquals(fromDao, newPage);
      
    }

    /**
     * Test of deleteStaticPage method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testDeleteStaticPage() {
        StaticPage delete = pageDao.getStaticPageById(1);
        pageDao.deleteStaticPage(1);
        assertEquals(null, pageDao.getStaticPageById(delete.getPageId()));
    }

    /**
     * Test of editStaticPage method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testEditStaticPage() {
        StaticPage toEdit = pageDao.getStaticPageById(1);
        assertEquals("Read First!", toEdit.getNavName());
        toEdit.setNavName("Myah");
        pageDao.editStaticPage(toEdit);
        StaticPage fromDao = pageDao.getStaticPageById(toEdit.getPageId());
        assertEquals(fromDao, toEdit);
    }

   
    /**
     * Test of getAllStaticPages method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetAllStaticPages() {
        List<StaticPage> pageList = pageDao.getAllStaticPages();
        assertEquals(2, pageList.size());
        
    }

    /**
     * Test of getUsersByStaticPage method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetUsersByStaticPage() {
       List<User> userList = pageDao.getUsersByStaticPage(pageDao.getStaticPageById(1));
       assertEquals(2, userList.size());
       assertEquals(true, userList.contains(userDao.getUserById(1)));
       assertEquals(true, userList.contains(userDao.getUserById(2)));
    }

    /**
     * Test of getImagesByStaticPage method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetImagesByStaticPage() {
       List<Image> imageList = pageDao.getImagesByStaticPage(pageDao.getStaticPageById(1));
       assertEquals(3, imageList.size());
       assertEquals(true, imageList.contains(imageDao.getImageById(2)));
    }

    /**
     * Test of getStaticPageById method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetStaticPageById() {
        StaticPage staticPage = pageDao.getStaticPageById(1);
        Assert.assertTrue(staticPage.getNavName().equals("Read First!"));
    }
    
    /**
     * Test of getStaticPagesWithCategory method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetStaticPagesWithCategory() {
        PageCategory pageCategory = catDao.getPageCategoryById(2);
        StaticPage staticPage = pageDao.getStaticPageById(1);
        Assert.assertEquals(pageCategory, catDao.getPageCategoryById(staticPage.getPageCategoryId()));
    }
    
    /**
     * Test of getUnpublishedStaticPages method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetUnpublishedStaticPages() {
        List<StaticPage> unpublishedPages = pageDao.getUnpublishedStaticPages();
        StaticPage unpublishedPage = unpublishedPages.get(0);
        Assert.assertEquals(1, unpublishedPages.size());
        Assert.assertEquals(pageDao.getStaticPageById(unpublishedPage.getPageId()), unpublishedPage);
    }
    
    /**
     * Test of getUnpublishedStaticPagesByAuthor method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetUnpublishedStaticPagesByAuthor() {
        User author = userDao.getUserById(1);
        List<StaticPage> staticPages = pageDao.getUnpublishedStaticPagesByAuthor(author);
        StaticPage staticPage = staticPages.get(0);
        Assert.assertEquals(1, staticPages.size());
        Assert.assertTrue(staticPage.getAuthorList().contains(author));
    }
    
    /**
     * Test of getUnpublishedStaticPagesByGroup method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetUnpublishedStaticPagesByGroup() {
        List<StaticPage> firstGroup = pageDao.getUnpublishedStaticPagesByGroup(0, 10);
        List<StaticPage> secondGroup = pageDao.getUnpublishedStaticPagesByGroup(10, 10);
        Assert.assertEquals(1, firstGroup.size());
        Assert.assertTrue(secondGroup.isEmpty());
    }
    
    /**
     * Test of getPublishedStaticPages method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetPublishedStaticPages() {
        List<StaticPage> publishedPages = pageDao.getPublishedStaticPages();
        StaticPage publishedPage = pageDao.getStaticPageById(1);
        Assert.assertTrue(publishedPages.contains(publishedPage));
    }
    
    /**
     * Test of getPublishedStaticPagesByGroup method, of class JackFontaneauxStaticPageDao.
     */
    @Test
    public void testGetPublishedStaticPagesByGroup() {
        List<StaticPage> firstGroup = pageDao.getPublishedStaticPagesByGroup(0, 10);
        List<StaticPage> secondGroup = pageDao.getPublishedStaticPagesByGroup(10, 10);
        Assert.assertEquals(1, firstGroup.size());
        Assert.assertTrue(secondGroup.isEmpty());
    }
    
}
