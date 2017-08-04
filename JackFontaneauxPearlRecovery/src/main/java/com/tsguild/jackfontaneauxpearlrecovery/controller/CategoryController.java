/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.controller;

import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCategoryServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoImageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoPostServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoStaticPageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoTagServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoUserServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
public class CategoryController {

    private JFoCategoryServiceLayer categoryService;
    private JFoStaticPageServiceLayer pageService;
    private JFoImageServiceLayer imageServiceLayer;
    private JFoTagServiceLayer tagServiceLayer;
    private JFoUserServiceLayer userServiceLayer;
    private JFoPostServiceLayer postServiceLayer;

    @Inject
    public CategoryController(JFoCategoryServiceLayer categoryService,
            JFoStaticPageServiceLayer pageService, JFoImageServiceLayer imageServiceLayer,
            JFoTagServiceLayer tagServiceLayer, JFoUserServiceLayer userServiceLayer,
            JFoPostServiceLayer postServiceLayer) {

        this.categoryService = categoryService;
        this.pageService = pageService;
        this.imageServiceLayer = imageServiceLayer;
        this.tagServiceLayer = tagServiceLayer;
        this.userServiceLayer = userServiceLayer;
        this.postServiceLayer = postServiceLayer;
    }

    @RequestMapping(value = "/category/{pageId}")
    public String displayCategoryPage(@PathVariable int pageId, Model model) {

        model.addAttribute("staticPage", pageService.getStaticPageById(pageId));
        model.addAttribute("theHand", new TheHand(categoryService, pageService));

        return "category";

    }

    @RequestMapping(value = "author/create/category", method = RequestMethod.POST)
    public String createCategory(HttpServletRequest request, Model model) {
        String categoryName = request.getParameter("category-add");
        PostCategory newCategory = new PostCategory();
        newCategory.setCategoryName(categoryName);
        categoryService.addPostCategory(newCategory);
        List<User> authorList = userServiceLayer.getAllUsers();
        List<PageCategory> pageList = categoryService.getAllPageCategories();
        List<PostCategory> postList = categoryService.getAllPostCategories();
        List<Post> unpublishedList = postServiceLayer.getUnpublishedPosts();
        List<Image> imageList = imageServiceLayer.getAllHeaderImages();
        model.addAttribute("authorList", authorList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("postList", postList);
        model.addAttribute("unpublishedList", unpublishedList);
        model.addAttribute("imageList", imageList);
        model.addAttribute("theHand", new TheHand(categoryService, pageService));
        return "author";
    }

}
