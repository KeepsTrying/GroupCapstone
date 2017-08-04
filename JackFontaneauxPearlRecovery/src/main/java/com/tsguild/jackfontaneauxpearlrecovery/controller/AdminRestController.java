/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.controller;

import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCategoryServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoImageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoPostServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoStaticPageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoTagServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoUserServiceLayer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class AdminRestController {
    
    private JFoCategoryServiceLayer categoryServiceLayer;
    private JFoImageServiceLayer imageServiceLayer;
    private JFoTagServiceLayer tagServiceLayer;
    private JFoUserServiceLayer userServiceLayer;
    private JFoPostServiceLayer postServiceLayer;
    private JFoStaticPageServiceLayer staticPageServiceLayer;

    @Inject
    public AdminRestController(JFoCategoryServiceLayer categoryServiceLayer, JFoImageServiceLayer imageServiceLayer,
            JFoTagServiceLayer tagServiceLayer, JFoUserServiceLayer userServiceLayer,
            JFoPostServiceLayer postServiceLayer, JFoStaticPageServiceLayer staticPageServiceLayer) {
        this.categoryServiceLayer = categoryServiceLayer;
        this.imageServiceLayer = imageServiceLayer;
        this.tagServiceLayer = tagServiceLayer;
        this.userServiceLayer = userServiceLayer;
        this.postServiceLayer = postServiceLayer;
        this.staticPageServiceLayer = staticPageServiceLayer;
    }
    
    @ResponseBody
    @RequestMapping(value = "/adminPage/published/{pageId}", method = RequestMethod.GET)
    public Map<String, Object> getListOfPublishedPages(@PathVariable int pageId) {
        int startingIndex = (pageId - 1) * 10;
        
        List<StaticPage> publishedPagesByGroup = staticPageServiceLayer.getPublishedStaticPagesByGroup(startingIndex, 10);
        
        int numberOfStaticPages = staticPageServiceLayer.getPublishedStaticPages().size();
        
        int numberOfPages = numberOfStaticPages / 10;
        
        if ((numberOfStaticPages > 0) && (numberOfStaticPages % 10) > 0) {
            numberOfPages++;
        }
        
        Map<String, Object> model = new HashMap<>();
        
        model.put("pages", publishedPagesByGroup);
        model.put("numberOfPages", numberOfPages);
        
        return model;
    }
    
    @ResponseBody
    @RequestMapping(value = "/adminPage/unpublished/{pageId}", method = RequestMethod.GET)
    public Map<String, Object> getListOfUnublishedPages(@PathVariable int pageId, HttpServletRequest request) {
        int startingIndex = (pageId -1) * 10;
        
        List<StaticPage> unpublishedPagesByGroup = staticPageServiceLayer.getUnpublishedStaticPagesByGroup(startingIndex, 10);
        
        int numberOfStaticPages = staticPageServiceLayer.getUnpublishedStaticPages().size();
        
        int numberOfPages = numberOfStaticPages / 10;
        
        if ((numberOfStaticPages > 0) &&(numberOfStaticPages % 10) > 0) {
            numberOfPages++;
        }
        
        Map<String, Object> model = new HashMap<>();
        
        model.put("pages", unpublishedPagesByGroup);
        model.put("numberOfPages", numberOfPages);
        
        return model;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/adminPost2/published/{pageId}", method = RequestMethod.GET)
    public Map<String, Object> getListOfPublishedPosts(@PathVariable int pageId) {
        int startingIndex = (pageId -1) * 10;
        
        List<Post> publishedPostsByGroup = postServiceLayer.getPublishedPostsByGroup(startingIndex, 10);
        
        int numberOfBlogPosts = postServiceLayer.getPublishedPosts().size();
        
        int numberOfPages = numberOfBlogPosts / 10;
        
        if ((numberOfBlogPosts > 0) && (numberOfBlogPosts % 10) > 0) {
            numberOfPages++;
        }
        
        Map<String, Object> model = new HashMap<>();
        
        model.put("posts", publishedPostsByGroup);
        model.put("numberOfPages", numberOfPages);
        
        return model;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/adminPost2/unpublished/{pageId}", method = RequestMethod.GET)
    public Map<String, Object> getListOfUnpublishedPosts(@PathVariable int pageId) {
        int startingIndex = (pageId -1) * 10;
        
        List<Post> unpublishedPostsByGroup = postServiceLayer.getUnpublishedPostsByGroup(startingIndex, 10);
        
        int numberOfBlogPosts = postServiceLayer.getUnpublishedPosts().size();
        
        int numberOfPages = numberOfBlogPosts / 10;
        
        if ((numberOfBlogPosts > 0) && (numberOfBlogPosts % 10) > 0) {
            numberOfPages++;
        }
        
       
        Map<String, Object> model = new HashMap<>();
        
        model.put("posts", unpublishedPostsByGroup);
        model.put("numberOfPages", numberOfPages);
        
        return model;
    }
    
}
