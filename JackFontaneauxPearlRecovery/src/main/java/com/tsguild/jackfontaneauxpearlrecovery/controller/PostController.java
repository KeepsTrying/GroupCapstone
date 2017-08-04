/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.controller;

import com.tsguild.jackfontaneauxpearlrecovery.model.Comment;
import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCategoryServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCommentServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoImageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoPostServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoStaticPageServiceLayer;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
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
public class PostController {
    
    private JFoPostServiceLayer postServiceLayer;
    private JFoCategoryServiceLayer categoryService;
    private JFoStaticPageServiceLayer pageService;
    private JFoCommentServiceLayer commService;
    private JFoImageServiceLayer imageService;
    
    @Inject
    public PostController(JFoPostServiceLayer postServiceLayer,
            JFoCategoryServiceLayer categoryService,
            JFoStaticPageServiceLayer pageService,
            JFoCommentServiceLayer commService,
            JFoImageServiceLayer imageService) {
        
        this.postServiceLayer = postServiceLayer;
        this.categoryService = categoryService;
        this.pageService = pageService;
        this.commService = commService;
        this.imageService = imageService;
    }
    
    @RequestMapping(value="/post/{postID}", method=RequestMethod.GET)
    public String displayPostInfo(@PathVariable int postID, Model model){
        
        Post post = postServiceLayer.getPostById(postID);
        List<Image> imageList = post.getImageList();
        List<Tag> tagList = post.getTagList();
        List<PostCategory> catList = post.getCategories();
        List<User> authorList = post.getAuthorList();
        List<Post> similarPostsList;
        Image headerImage = new Image();
        Image postImage = new Image();
        
        for(Image currentImage: imageList){
            if(currentImage.getImageTypeId() == 1){
                postImage = currentImage;
            }
            if(currentImage.getImageTypeId() == 2){
                headerImage = currentImage;
            }
        }
        
        Random randomizer = new Random();
        int random = randomizer.nextInt(catList.size());
        PostCategory randoCat = catList.get(random);
        
        List<Post> similarList = postServiceLayer.getAllPublishedPostsByCategory(randoCat);
        if(similarList.contains(post)){
            similarList.remove(post);
        }
        List<Comment> postComments = commService.getCommentsForPost(postID);
        
        model.addAttribute("post", post);
        model.addAttribute("headerImage", headerImage);
        model.addAttribute("postImage", postImage);
        model.addAttribute("tagList", tagList);
        model.addAttribute("authorList", authorList);
        model.addAttribute("catList", catList);
        model.addAttribute("similarList", similarList);
        model.addAttribute("postComments", postComments);
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        
        return "post";
        
    }
    
   /* @RequestMapping(value="/comment/{postId}", method=RequestMethod.POST)
    public String addComment(@PathVariable int postId, HttpServletRequest request, Model model){
        Comment newComment = new Comment();
        newComment.setPostId(postId);
        newComment.setName(request.getParameter("anonymous-comment-name"));
        newComment.setTextContent(request.getParameter("anonymous-comment-content"));
        commService.addComment(newComment);
        
        Post post = postServiceLayer.getPostById(postId);
        List<Image> imageList = post.getImageList();
        List<Tag> tagList = post.getTagList();
        List<PostCategory> catList = post.getCategories();
        List<User> authorList = post.getAuthorList();
        List<Post> similarPostsList;
        Image headerImage = new Image();
        Image postImage = new Image();
        
        for(Image currentImage: imageList){
            if(currentImage.getImageTypeId() == 4){
                postImage = currentImage;
            }
            if(currentImage.getImageTypeId() == 2){
                headerImage = currentImage;
            }
        }
        
        Random randomizer = new Random();
        int random = randomizer.nextInt(catList.size());
        PostCategory randoCat = catList.get(random);
        
        List<Post> similarList = postServiceLayer.getAllPublishedPostsByCategory(randoCat);
        List<Comment> postComments = commService.getCommentsForPost(postId);
        
        model.addAttribute("post", post);
        model.addAttribute("headerImage", headerImage);
        model.addAttribute("postImage", postImage);
        model.addAttribute("tagList", tagList);
        model.addAttribute("authorList", authorList);
        model.addAttribute("catList", catList);
        model.addAttribute("similarList", similarList);
        model.addAttribute("postComments", postComments);
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        
        return "post";
        
    }
    
    /*@RequestMapping(value="/comment/delete/{postId}/{commentId}", method=RequestMethod.GET)
    public String deleteComment(@PathVariable("commentId") int commentId, @PathVariable("postId") int postId, Model model){
        commService.deleteComment(commentId);
        
        Post post = postServiceLayer.getPostById(postId);
        List<Image> imageList = post.getImageList();
        List<Tag> tagList = post.getTagList();
        List<PostCategory> catList = post.getCategories();
        List<User> authorList = post.getAuthorList();
        List<Post> similarPostsList;
        Image headerImage = new Image();
        Image postImage = new Image();
        
        for(Image currentImage: imageList){
            if(currentImage.getImageTypeId() == 4){
                postImage = currentImage;
            }
            if(currentImage.getImageTypeId() == 2){
                headerImage = currentImage;
            }
        }
        
        Random randomizer = new Random();
        int random = randomizer.nextInt(catList.size());
        PostCategory randoCat = catList.get(random);
        
        List<Post> similarList = postServiceLayer.getAllPublishedPostsByCategory(randoCat);
        List<Comment> postComments = commService.getCommentsForPost(postId);
        
        model.addAttribute("post", post);
        model.addAttribute("headerImage", headerImage);
        model.addAttribute("postImage", postImage);
        model.addAttribute("tagList", tagList);
        model.addAttribute("authorList", authorList);
        model.addAttribute("catList", catList);
        model.addAttribute("similarList", similarList);
        model.addAttribute("postComments", postComments);
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        
        return "post";
    }
    */
}
