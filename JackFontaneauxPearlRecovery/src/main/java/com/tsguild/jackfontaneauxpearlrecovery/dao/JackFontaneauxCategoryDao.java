/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface JackFontaneauxCategoryDao {
    
    public void addPostCategory(PostCategory newCategory);
    
    public void deletePostCategory(int postCategoryId);
    
    public void editPostCategory(PostCategory editedCategory);
    
    public PostCategory getPostCategoryById(int postCategoryId);
    
    public List<PostCategory> getAllPostCategories();
    
    public void addPageCategory(PageCategory newCategory);
    
    public void deletePageCategory(int pageCategoryId);
    
    public void editPageCategory(PageCategory editedCategory);
    
    public PageCategory getPageCategoryById(int pageCategoryId);
    
    public List<PageCategory> getAllPageCategories();
}
