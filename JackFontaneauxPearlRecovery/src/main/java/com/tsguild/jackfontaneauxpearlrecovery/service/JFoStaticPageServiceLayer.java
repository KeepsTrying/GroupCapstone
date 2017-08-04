/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.util.List;

/**
 *
 * @author scooke11
 */
public interface JFoStaticPageServiceLayer {
    
    public void addStaticPage(StaticPage newPage);
    
    public void deleteStaticPage(int pageId);
    
    public void editStaticPage(StaticPage editedPage);
    
    public StaticPage getStaticPageById(int pageId);
    
    public List<StaticPage> getAllStaticPages();
    
    public List<User> getUsersByStaticPage(StaticPage page);
    
    public List<Image> getImagesByStaticPage(StaticPage page);
    
    public List<StaticPage> getStaticPagesWithCategory(PageCategory category);

    public List<StaticPage> getUnpublishedStaticPages();

    public List<StaticPage> getUnpublishedStaticPagesByAuthor(User author);

    public List<StaticPage> getPublishedStaticPages();
    
    public List<StaticPage> getUnpublishedStaticPagesByGroup(int startingPageNum, int groupSize);
    
    public List<StaticPage> getPublishedStaticPagesByGroup(int startingPageNum, int groupSize);
    
    public List<StaticPage> getIncompletePages();
}
