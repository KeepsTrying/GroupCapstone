/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.controller;

import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCategoryServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoPostServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoStaticPageServiceLayer;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author scooke11
 */
@Controller
public class AdminController {
    private JFoCategoryServiceLayer categoryService;
    private JFoStaticPageServiceLayer pageService;
    private JFoPostServiceLayer postService;

    public AdminController(JFoCategoryServiceLayer categoryService, 
            JFoStaticPageServiceLayer pageService,
            JFoPostServiceLayer postService) {
        
        this.categoryService = categoryService;
        this.pageService = pageService;
        this.postService = postService;
    }

    
    
    @RequestMapping(value="/adminPost", method=RequestMethod.GET)
    public String displayAdminPostPortal(Model model){
        List<Post> firstTenPublishedPosts = postService.getPublishedPostsByGroup(0, 10);
        List<Post> unpublishedPosts = postService.getUnpublishedPosts();
        List<Post> draftPosts = postService.getIncompletePosts();
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPosts", firstTenPublishedPosts);
        model.addAttribute("unpublishedPosts", unpublishedPosts);
        model.addAttribute("draftPosts", draftPosts);
        
        return "adminPost";
        
    }
    
    @RequestMapping(value="/adminPage", method=RequestMethod.GET)
    public String displayAdminPagePortal(Model model){
        List<StaticPage> firstTenPublishedPages = pageService.getPublishedStaticPagesByGroup(0, 10);
        List<StaticPage> unpublishedPages = pageService.getUnpublishedStaticPages();
        List<StaticPage> draftPages = pageService.getIncompletePages();
                
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        model.addAttribute("publishedPages", firstTenPublishedPages);
        model.addAttribute("unpublishedPages", unpublishedPages);
        model.addAttribute("draftPages", draftPages);
        
        return "adminPage";
        
    }
    
    @RequestMapping(value="/completePost/{postId}", method=RequestMethod.GET)
    public String completeDraftPost(@PathVariable int postId, Model model){
        List<Post> firstTenPublishedPosts = postService.getPublishedPostsByGroup(0, 10);
        List<Post> unpublishedPosts = postService.getUnpublishedPosts();
        List<Post> draftPosts = postService.getIncompletePosts();
        Post postToComplete = postService.getPostById(postId);
        
        postToComplete.setIsCompleted(true);
        postService.editPost(postToComplete);
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPosts", firstTenPublishedPosts);
        model.addAttribute("unpublishedPosts", unpublishedPosts);
        model.addAttribute("draftPosts", draftPosts);
        
        return "redirect:/adminPost";
    }
    
    @RequestMapping(value="/publishPost/{postId}", method=RequestMethod.GET)
    public String publishPost(@PathVariable int postId, Model model){
        List<Post> firstTenPublishedPosts = postService.getPublishedPostsByGroup(0, 10);
        List<Post> unpublishedPosts = postService.getUnpublishedPosts();
        List<Post> draftPosts = postService.getIncompletePosts();
        Post postToPublish = postService.getPostById(postId);
        
        postToPublish.setIsPublished(true);
        postService.editPost(postToPublish);
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPosts", firstTenPublishedPosts);
        model.addAttribute("unpublishedPosts", unpublishedPosts);
        model.addAttribute("draftPosts", draftPosts);
        
        return "redirect:/adminPost";
    }
    
    @RequestMapping(value="/unPublishPost/{postId}", method=RequestMethod.GET)
    public String unPublishPost(@PathVariable int postId, Model model){
        List<Post> firstTenPublishedPosts = postService.getPublishedPostsByGroup(0, 10);
        List<Post> unpublishedPosts = postService.getUnpublishedPosts();
        List<Post> draftPosts = postService.getIncompletePosts();
        Post postToUnPublish = postService.getPostById(postId);
       
        postToUnPublish.setIsPublished(false);
        postService.editPost(postToUnPublish);
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPosts", firstTenPublishedPosts);
        model.addAttribute("unpublishedPosts", unpublishedPosts);
        model.addAttribute("draftPosts", draftPosts);
        
        return "redirect:/adminPost";
    }
    
    @RequestMapping(value="/completePage/{pageId}", method=RequestMethod.GET)
    public String completePage(@PathVariable int pageId, Model model){
        List<StaticPage> firstTenPublishedPages = pageService.getPublishedStaticPagesByGroup(0, 10);
        List<StaticPage> unpublishedPages = pageService.getUnpublishedStaticPages();
        List<StaticPage> draftPages = pageService.getIncompletePages();
        StaticPage pageToComplete = pageService.getStaticPageById(pageId);
        
        pageToComplete.setIsCompleted(true);
        pageService.editStaticPage(pageToComplete);
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPages", firstTenPublishedPages);
        model.addAttribute("unpublishedPages", unpublishedPages);
        model.addAttribute("draftPages", draftPages);
        
        return "redirect:/adminPage";
    }
    
    @RequestMapping(value="/publishPage/{pageId}", method=RequestMethod.GET)
    public String publishPage(@PathVariable int pageId, Model model){
        List<StaticPage> firstTenPublishedPages = pageService.getPublishedStaticPagesByGroup(0, 10);
        List<StaticPage> unpublishedPages = pageService.getUnpublishedStaticPages();
        List<StaticPage> draftPages = pageService.getIncompletePages();
        StaticPage pageToPublish = pageService.getStaticPageById(pageId);
        
        pageToPublish.setIsPublished(true);
        pageService.editStaticPage(pageToPublish);
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPages", firstTenPublishedPages);
        model.addAttribute("unpublishedPages", unpublishedPages);
        model.addAttribute("draftPages", draftPages);
        
        return "redirect:/adminPage";
    }
    
    @RequestMapping(value="/unPublishPage/{pageId}", method=RequestMethod.GET)
    public String unPublishPage(@PathVariable int pageId, Model model){
        List<StaticPage> firstTenPublishedPages = pageService.getPublishedStaticPagesByGroup(0, 10);
        List<StaticPage> unpublishedPages = pageService.getUnpublishedStaticPages();
        List<StaticPage> draftPages = pageService.getIncompletePages();
        StaticPage pageToUnPublish = pageService.getStaticPageById(pageId);
        
        pageToUnPublish.setIsPublished(false);
        pageService.editStaticPage(pageToUnPublish);
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPages", firstTenPublishedPages);
        model.addAttribute("unpublishedPages", unpublishedPages);
        model.addAttribute("draftPages", draftPages);
        
        return "redirect:/adminPage";
    }
    
    //not working yet
    @RequestMapping(value="/nextPages/{pageNum}", method=RequestMethod.GET)
    public String nextPages(@PathVariable int pageNum, Model model){
        List<StaticPage> firstTenPublishedPages = pageService.getPublishedStaticPagesByGroup(0, 10);
        List<StaticPage> unpublishedPages = pageService.getUnpublishedStaticPages();
        List<StaticPage> draftPages = pageService.getIncompletePages();
        
        //add logic for next 10 pages and feed it to model
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPages", firstTenPublishedPages);
        model.addAttribute("unpublishedPages", unpublishedPages);
        model.addAttribute("draftPages", draftPages);
        
        
        
        return "adminPage";
        
    }
    
    //not working yet
    @RequestMapping(value="/nextPosts/{postNum}", method=RequestMethod.GET)
    public String nextPosts(@PathVariable int postNum, Model model){
        List<Post> firstTenPublishedPosts = postService.getPublishedPostsByGroup(0, 10);
        List<Post> unpublishedPosts = postService.getUnpublishedPosts();
        List<Post> draftPosts = postService.getIncompletePosts();
        
        //add logic for next 10 posts and feed it to model
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPosts", firstTenPublishedPosts);
        model.addAttribute("unpublishedPosts", unpublishedPosts);
        model.addAttribute("draftPosts", draftPosts);
        
        return "adminPost";
    }
    
    @RequestMapping(value="/adminPage2", method=RequestMethod.GET)
    public String displayAdminPage2Portal(Model model){
        List<StaticPage> firstTenPublishedPages = pageService.getPublishedStaticPagesByGroup(0, 10);
        List<StaticPage> unpublishedPages = pageService.getUnpublishedStaticPages();
        List<StaticPage> draftPages = pageService.getIncompletePages();
                
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        model.addAttribute("publishedPages", firstTenPublishedPages);
        model.addAttribute("unpublishedPages", unpublishedPages);
        model.addAttribute("draftPages", draftPages);
        
        return "oldAdminPage";
        
    }
    
    @RequestMapping(value="/adminPost2", method=RequestMethod.GET)
    public String displayAdminPost2Portal(Model model){
        List<Post> firstTenPublishedPosts = postService.getPublishedPostsByGroup(0, 10);
        List<Post> unpublishedPosts = postService.getUnpublishedPosts();
        List<Post> draftPosts = postService.getIncompletePosts();
        
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        //feed model appropriate lists and such here
        model.addAttribute("publishedPosts", firstTenPublishedPosts);
        model.addAttribute("unpublishedPosts", unpublishedPosts);
        model.addAttribute("draftPosts", draftPosts);
        
        return "adminPost2";
        
    }
    
    
}
