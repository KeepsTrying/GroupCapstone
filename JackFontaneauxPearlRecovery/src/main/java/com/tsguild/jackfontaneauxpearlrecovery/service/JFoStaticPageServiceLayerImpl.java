/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxStaticPageDao;
import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author scooke11
 */
public class JFoStaticPageServiceLayerImpl implements JFoStaticPageServiceLayer {

    private JackFontaneauxStaticPageDao staticPageDao;

    public void setStaticPageDao(JackFontaneauxStaticPageDao staticPageDao) {
        this.staticPageDao = staticPageDao;
    }

    @Inject
    public JFoStaticPageServiceLayerImpl(JackFontaneauxStaticPageDao staticPageDao) {
        this.staticPageDao = staticPageDao;
    }
    
    @Override
    public void addStaticPage(StaticPage newPage) {
        staticPageDao.addStaticPage(newPage);
    }

    @Override
    public void deleteStaticPage(int pageId) {
        staticPageDao.deleteStaticPage(pageId);
    }

    @Override
    public void editStaticPage(StaticPage editedPage) {
        staticPageDao.editStaticPage(editedPage);
    }

    @Override
    public StaticPage getStaticPageById(int pageId) {
        return staticPageDao.getStaticPageById(pageId);
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        return staticPageDao.getAllStaticPages();
    }

    @Override
    public List<User> getUsersByStaticPage(StaticPage page) {
        return staticPageDao.getUsersByStaticPage(page);
    }

    @Override
    public List<Image> getImagesByStaticPage(StaticPage page) {
        return staticPageDao.getImagesByStaticPage(page);
    }

    @Override
    public List<StaticPage> getStaticPagesWithCategory(PageCategory category) {
        return staticPageDao.getStaticPagesWithCategory(category);
    }

    @Override
    public List<StaticPage> getUnpublishedStaticPages() {
        List<StaticPage> allUnpublishedPages = staticPageDao.getUnpublishedStaticPages();
        List<StaticPage> unpublishedPages = new ArrayList();
        
        for(StaticPage page : allUnpublishedPages){
            if(page.getIsCompleted() && !page.getIsPublished()){
                unpublishedPages.add(page);
            }
        }
        
        return unpublishedPages;
    }

    @Override
    public List<StaticPage> getUnpublishedStaticPagesByAuthor(User author) {
        return staticPageDao.getUnpublishedStaticPagesByAuthor(author);
    }

    @Override
    public List<StaticPage> getPublishedStaticPages() {
        return staticPageDao.getPublishedStaticPages();
    }

    @Override
    public List<StaticPage> getUnpublishedStaticPagesByGroup(int startingPageNum, int groupSize) {
        return staticPageDao.getUnpublishedStaticPagesByGroup(startingPageNum, groupSize);
    }

    @Override
    public List<StaticPage> getPublishedStaticPagesByGroup(int startingPageNum, int groupSize) {
        return staticPageDao.getPublishedStaticPagesByGroup(startingPageNum, groupSize);
    }

    @Override
    public List<StaticPage> getIncompletePages() {
        List<StaticPage> allPages = staticPageDao.getAllStaticPages();
        List<StaticPage> incompletePages = new ArrayList();
        
        for(StaticPage page : allPages){
            if(!page.getIsCompleted()){
                incompletePages.add(page);
            }
        }
        
        return incompletePages;
    }
    
}
