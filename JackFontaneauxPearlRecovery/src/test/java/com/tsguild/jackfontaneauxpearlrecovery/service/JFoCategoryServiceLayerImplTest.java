/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class JFoCategoryServiceLayerImplTest extends ServiceTestingBase {
    
    
    /**
     * Test of addPostCategory method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testAddPostCategory() {
        PostCategory expectedCategory = new PostCategory();
        expectedCategory.setCategoryName("testName");
        catServ.addPostCategory(expectedCategory);
        
        PostCategory actualCategory = catServ.getPostCategoryById(expectedCategory.getPostCategoryId());
        Assert.assertEquals(expectedCategory, actualCategory);
    }

    /**
     * Test of deletePostCategory method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testDeletePostCategory() {
        List<PostCategory> postCategories = catServ.getAllPostCategories();
        int numOfCategories = postCategories.size();
        int categoryId = postCategories.get(0).getPostCategoryId();
        catServ.deletePostCategory(categoryId);
        Assert.assertEquals((numOfCategories - 1), catServ.getAllPostCategories().size());
        Assert.assertEquals(null, catServ.getPostCategoryById(categoryId));
    }

    /**
     * Test of editPostCategory method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testEditPostCategory() {
        PostCategory category = catServ.getPostCategoryById(1);
        category.setCategoryName("newName");
        catServ.editPostCategory(category);
        
        Assert.assertEquals(category.getCategoryName(), catServ.getPostCategoryById(1).getCategoryName());
    }

    /**
     * Test of getPostCategoryById method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testGetPostCategoryById() {
        PostCategory pCat = catServ.getPostCategoryById(1);
        Assert.assertTrue(pCat.getCategoryName().equals("Diving"));
    }

    /**
     * Test of getAllPostCategories method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testGetAllPostCategories() {
        List<PostCategory> postCategories = catServ.getAllPostCategories();
        Assert.assertEquals(4, postCategories.size());
        Assert.assertTrue(postCategories.get(3).getCategoryName().equals("Rando"));
    }

    /**
     * Test of addPageCategory method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testAddPageCategory() {
        PageCategory expectedCategory = new PageCategory();
        expectedCategory.setCategoryName("newName");
        catServ.addPageCategory(expectedCategory);
        
        PageCategory actualCategory = catServ.getPageCategoryById(expectedCategory.getPageCategoryId());
        Assert.assertEquals(expectedCategory, actualCategory);
    }

    /**
     * Test of deletePageCategory method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testDeletePageCategory() {
        List<PageCategory> pageCategories = catServ.getAllPageCategories();
        int numOfPageCategories = pageCategories.size();
        int pageCategoryId = pageCategories.get(0).getPageCategoryId();
        catServ.deletePageCategory(pageCategoryId);
        Assert.assertEquals((numOfPageCategories -1), catServ.getAllPageCategories().size());
        Assert.assertEquals(null, catServ.getPageCategoryById(pageCategoryId));
    }

    /**
     * Test of editPageCategory method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testEditPageCategory() {
        PageCategory category = catServ.getPageCategoryById(1);
        category.setCategoryName("newName");
        catServ.editPageCategory(category);
        Assert.assertTrue(catServ.getPageCategoryById(category.getPageCategoryId()).getCategoryName().equals("newName"));
    }

    /**
     * Test of getPageCategoryById method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testGetPageCategoryById() {
        PageCategory category = catServ.getPageCategoryById(2);
        Assert.assertTrue(category.getCategoryName().equals("FAQ"));
    }

    /**
     * Test of getAllPageCategories method, of class JFoCategoryServiceLayerImpl.
     */
    @Test
    public void testGetAllPageCategories() {
        List<PageCategory> pageCategories = catServ.getAllPageCategories();
        PageCategory category = pageCategories.get(2);
        Assert.assertEquals(4, pageCategories.size());
        System.out.println(category.getCategoryName());
        Assert.assertTrue(category.getCategoryName().equals("FAQ"));
    }
    
}
