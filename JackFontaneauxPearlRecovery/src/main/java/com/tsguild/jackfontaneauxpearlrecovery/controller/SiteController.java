/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.controller;

import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCategoryServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoImageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoPostServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoSearchServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoStaticPageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoTagServiceLayer;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class SiteController {
    
    private JFoPostServiceLayer postService;
    private JFoImageServiceLayer imageService;
    private JFoCategoryServiceLayer categoryService;
    private JFoStaticPageServiceLayer pageService;
    private JFoTagServiceLayer tagService;
    private JFoSearchServiceLayer searchService;
    
    @Inject
    public SiteController(JFoPostServiceLayer postService, 
            JFoImageServiceLayer imageService,
            JFoCategoryServiceLayer categoryService,
            JFoStaticPageServiceLayer pageService, 
            JFoTagServiceLayer tagService, 
            JFoSearchServiceLayer searchService) {
        
        this.postService = postService;
        this.imageService = imageService;
        this.categoryService = categoryService;
        this.pageService = pageService;
        this.tagService = tagService;
        this.searchService = searchService;
        
    }
    
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String goHome(Model model){
        
        List<Post> postList = postService.getPublishedPosts();
        
        for(Post post : postList){
            Image image = imageService.getPreviewImageForPost(post.getPostId());
            
            model.addAttribute("previewPost"+post.getPostId(), image);
            
        }
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        model.addAttribute("listOfPosts", postList);
        
        return "home";
        
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, Model model) {
        
        String searchRequest = request.getParameter("search");
        
        List<StaticPage> relatedPages = searchService.searchForStaticPages(searchRequest);
        List<Post> relatedPosts = searchService.searchForPosts(searchRequest);
        
        String searchMessage = null;
        
        if (relatedPosts.isEmpty()) {
            searchMessage = "No blog posts fit your search criteria.";
        } else {
            searchMessage = "Here are the blog posts that fit your search criteria: ";
        }
        
        
        model.addAttribute("searchMessage", searchMessage);
        model.addAttribute("listOfPosts", relatedPosts);
        model.addAttribute("listOfPages", relatedPages);
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        
        return "home";
    }
    
}
