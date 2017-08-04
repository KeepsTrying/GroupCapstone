/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxCategoryDao;
import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author scooke11
 */
public class JFoCategoryServiceLayerImpl implements JFoCategoryServiceLayer {
    
    private JackFontaneauxCategoryDao categoryDao;
    
    @Inject
    public JFoCategoryServiceLayerImpl(JackFontaneauxCategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    public void setCategoryDao(JackFontaneauxCategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    

    @Override
    public void addPostCategory(PostCategory newCategory) {
        categoryDao.addPostCategory(newCategory);
    }

    @Override
    public void deletePostCategory(int postCategoryId) {
        categoryDao.deletePostCategory(postCategoryId);
    }

    @Override
    public void editPostCategory(PostCategory editedCategory) {
        categoryDao.editPostCategory(editedCategory);
    }

    @Override
    public PostCategory getPostCategoryById(int postCategoryId) {
        return categoryDao.getPostCategoryById(postCategoryId);
    }

    @Override
    public List<PostCategory> getAllPostCategories() {
        return categoryDao.getAllPostCategories();
    }

    @Override
    public void addPageCategory(PageCategory newCategory) {
        categoryDao.addPageCategory(newCategory);
    }

    @Override
    public void deletePageCategory(int pageCategoryId) {
        categoryDao.deletePageCategory(pageCategoryId);
    }

    @Override
    public void editPageCategory(PageCategory editedCategory) {
        categoryDao.editPageCategory(editedCategory);
    }

    @Override
    public PageCategory getPageCategoryById(int pageCategoryId) {
        return categoryDao.getPageCategoryById(pageCategoryId);
    }

    @Override
    public List<PageCategory> getAllPageCategories() {
        return categoryDao.getAllPageCategories();
    }
    
}
