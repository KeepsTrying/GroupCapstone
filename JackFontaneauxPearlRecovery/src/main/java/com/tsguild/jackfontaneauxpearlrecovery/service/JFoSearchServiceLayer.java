/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface JFoSearchServiceLayer {
    
    public List<Post> searchForPosts(String searchRequest);
    
    public List<StaticPage> searchForStaticPages(String searchRequest);
    
}
