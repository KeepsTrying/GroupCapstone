/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxCategoryDaoTest extends TestingBase {

    /**
     * Test of addPostCategory method, of class JackFontaneauxCategoryDao.
     */
    @Test
    public void testAddPostCategory() {
        PostCategory pCat = catDao.getPostCategoryById(1);
        assertEquals("Diving", pCat.getCategoryName());
        PostCategory newOne = new PostCategory();
        newOne.setCategoryName("Killing");
        catDao.addPostCategory(newOne);
 
        PostCategory fromDao = catDao.getPostCategoryById(newOne.getPostCategoryId());
        assertEquals(fromDao, newOne);
    }

    /**
     * Test of deletePostCategory method, of class JackFontaneauxCategoryDao.
     */
    @Test
    public void testDeletePostCategory() {
       
        List<PostCategory> pCatList = catDao.getAllPostCategories();
        assertEquals(4, pCatList.size());
        catDao.deletePostCategory(1);
        List<PostCategory> pCatList2 = catDao.getAllPostCategories();
        assertEquals(3, pCatList2.size());
        assertEquals(null, catDao.getPostCategoryById(1));
    }

    /**
     * Test of editPostCategory method, of class JackFontaneauxCategoryDao.
     */
    @Test
    public void testEditPostCategory() {
       PostCategory toEdit = catDao.getPostCategoryById(1);
       assertEquals("Diving", toEdit.getCategoryName());
       toEdit.setCategoryName("Killing");
       catDao.editPostCategory(toEdit);
       PostCategory fromDao = catDao.getPostCategoryById(1);
       assertEquals(fromDao, toEdit);
    }

  
    /**
     * Test of addPageCategory method, of class JackFontaneauxCategoryDao.
     */
    @Test
    public void testAddPageCategory() {
        
        PageCategory pCat = new PageCategory();
        pCat.setCategoryName("BleeeBloo");
        catDao.addPageCategory(pCat);
        PageCategory fromDao = catDao.getPageCategoryById(pCat.getPageCategoryId());
        assertEquals(fromDao, pCat);
    }

    /**
     * Test of deletePageCategory method, of class JackFontaneauxCategoryDao.
     */
    @Test
    public void testDeletePageCategory() {
       
        List<PageCategory> pCatList = catDao.getAllPageCategories();
        assertEquals(4, pCatList.size());
        catDao.deletePageCategory(1);
        List<PageCategory> pCatList2 = catDao.getAllPageCategories();
        assertEquals(3, pCatList2.size());
        assertEquals(null, catDao.getPageCategoryById(1));
        
    }

    /**
     * Test of editPageCategory method, of class JackFontaneauxCategoryDao.
     */
    @Test
    public void testEditPageCategory() {
        
        PageCategory toEdit = catDao.getPageCategoryById(1);
        assertEquals("Bio", toEdit.getCategoryName());
        toEdit.setCategoryName("BleeBloops");
        catDao.editPageCategory(toEdit);
        PageCategory fromDao = catDao.getPageCategoryById(1);
        assertEquals(fromDao, toEdit);
    }



}
