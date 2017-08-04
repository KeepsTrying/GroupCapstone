/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.controller;

import com.tsguild.jackfontaneauxpearlrecovery.model.Comment;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCategoryServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCommentServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoImageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoPostServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoStaticPageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoTagServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoUserServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
public class RestController {

    private JFoCategoryServiceLayer categoryServiceLayer;
    private JFoImageServiceLayer imageServiceLayer;
    private JFoTagServiceLayer tagServiceLayer;
    private JFoUserServiceLayer userServiceLayer;
    private JFoPostServiceLayer postServiceLayer;
    private JFoStaticPageServiceLayer staticPageServiceLayer;
    private JFoCommentServiceLayer commLayer;

    @Inject
    public RestController(JFoCategoryServiceLayer categoryServiceLayer, JFoImageServiceLayer imageServiceLayer,
            JFoTagServiceLayer tagServiceLayer, JFoUserServiceLayer userServiceLayer,
            JFoPostServiceLayer postServiceLayer, JFoStaticPageServiceLayer staticPageServiceLayer,
            JFoCommentServiceLayer commLayer) {
        this.categoryServiceLayer = categoryServiceLayer;
        this.imageServiceLayer = imageServiceLayer;
        this.tagServiceLayer = tagServiceLayer;
        this.userServiceLayer = userServiceLayer;
        this.postServiceLayer = postServiceLayer;
        this.staticPageServiceLayer = staticPageServiceLayer;
        this.commLayer = commLayer;
    }

    @RequestMapping(value = "/create/category", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createCategory(@Valid @RequestBody PostCategory postCat) {
        categoryServiceLayer.addPostCategory(postCat);
    }

    @RequestMapping(value = "/author/categories", method = RequestMethod.GET)
    @ResponseBody
    public List<PostCategory> getAllPostCategories() {
        return categoryServiceLayer.getAllPostCategories();
    }
    
    @RequestMapping(value = "/post/comments/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> getPostComments(@PathVariable int postId) {
        return commLayer.getCommentsForPost(postId);
    }
    
    @RequestMapping(value = "/post/comment/delete/{commentId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteComment(@PathVariable int commentId){
        commLayer.deleteComment(commentId);
    }
    
    @RequestMapping(value = "/post/comment/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createComment(@Valid @RequestBody Comment comment) {
        commLayer.addComment(comment);
    }
    
}
