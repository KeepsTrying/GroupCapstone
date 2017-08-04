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
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoCategoryServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoImageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoPostServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoStaticPageServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoTagServiceLayer;
import com.tsguild.jackfontaneauxpearlrecovery.service.JFoUserServiceLayer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
public class AuthorController {

    private JFoCategoryServiceLayer categoryServiceLayer;
    private JFoImageServiceLayer imageServiceLayer;
    private JFoTagServiceLayer tagServiceLayer;
    private JFoUserServiceLayer userServiceLayer;
    private JFoPostServiceLayer postServiceLayer;
    private JFoStaticPageServiceLayer staticPageServiceLayer;

    @Inject
    public AuthorController(JFoCategoryServiceLayer categoryServiceLayer, JFoImageServiceLayer imageServiceLayer,
            JFoTagServiceLayer tagServiceLayer, JFoUserServiceLayer userServiceLayer,
            JFoPostServiceLayer postServiceLayer, JFoStaticPageServiceLayer staticPageServiceLayer) {
        this.categoryServiceLayer = categoryServiceLayer;
        this.imageServiceLayer = imageServiceLayer;
        this.tagServiceLayer = tagServiceLayer;
        this.userServiceLayer = userServiceLayer;
        this.postServiceLayer = postServiceLayer;
        this.staticPageServiceLayer = staticPageServiceLayer;
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String displayAuthorPortal(Model model) {
        List<User> authorList = userServiceLayer.getAllUsers();
        List<PageCategory> pageList = categoryServiceLayer.getAllPageCategories();
        List<PostCategory> postList = categoryServiceLayer.getAllPostCategories();
        List<Post> unpublishedPostList = postServiceLayer.getUnpublishedPosts();
        List<StaticPage> unpublishedPageList = staticPageServiceLayer.getUnpublishedStaticPages();
        List<Post> incompletePostList = postServiceLayer.getIncompletePosts();
        List<StaticPage> incompletePageList = staticPageServiceLayer.getIncompletePages();
        List<Image> imageListHeader = imageServiceLayer.getAllHeaderImages();
        List<Image> imageListPreview = imageServiceLayer.getAllPreviewImages();
        List<Image> imageListPage = imageServiceLayer.getAllStaticPageBodyImages();
        model.addAttribute("authorList", authorList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("postList", postList);
        model.addAttribute("unpublishedPostList", unpublishedPostList);
        model.addAttribute("unpublishedPageList", unpublishedPageList);
        model.addAttribute("incompletePostList", incompletePostList);
        model.addAttribute("incompletePageList", incompletePageList);
        model.addAttribute("imageListHeader", imageListHeader);
        model.addAttribute("imageListPreview", imageListPreview);
        model.addAttribute("imageListPage", imageListPage);
        model.addAttribute("theHand", new TheHand(categoryServiceLayer, staticPageServiceLayer));
        return "author";

    }

    @RequestMapping(value = "/create/post", method = RequestMethod.POST)
    public String createPostPage(HttpServletRequest request, Model model) {
        int createType = Integer.parseInt(request.getParameter("page-type"));
        if (createType == 2) {
            String[] categories = request.getParameterValues("category-type-post");
            String[] authors = request.getParameterValues("author-chosen");
            String title = request.getParameter("title");
            String synopsis = request.getParameter("synopsis");
            int imageId = Integer.parseInt(request.getParameter("header-image-choice"));
            Image imageChosen = imageServiceLayer.getImageById(imageId);
            int imagePId = Integer.parseInt(request.getParameter("preview-image-choice"));
            Image previewChosen = imageServiceLayer.getImageById(imagePId);
            LocalDate postDate = LocalDate.parse(request.getParameter("post-date"), DateTimeFormatter.ISO_DATE);
            LocalDate expirationDate;
            
            
            String tagsString = request.getParameter("tags");
            
            List<Tag> verifiedAssociatedTags = tagServiceLayer.getVerifiedAssociatedTags(tagsString);

            
            try {
                expirationDate = LocalDate.parse(request.getParameter("expire-date"), DateTimeFormatter.ISO_DATE);
            } catch (NullPointerException | DateTimeParseException e) {
                expirationDate = LocalDate.parse("2225-01-01");
            }

            String bodyContent = request.getParameter("body-content");
            BigDecimal lat = new BigDecimal(request.getParameter("post-latitude"));
            BigDecimal lon = new BigDecimal(request.getParameter("post-longitude"));
            List<User> authorList = new ArrayList<>();
            List<Image> imageList = new ArrayList<>();
            List<PostCategory> catList = new ArrayList<>();

            for (String current : authors) {
                int userId = Integer.parseInt(current);
                User toAdd = userServiceLayer.getUserById(userId);
                authorList.add(toAdd);
            }
            for (String current : categories) {
                int catId = Integer.parseInt(current);
                PostCategory toAdd = categoryServiceLayer.getPostCategoryById(catId);
                catList.add(toAdd);
            }
            imageList.add(imageChosen);
            imageList.add(previewChosen);
            

            Post newPost = new Post();
            newPost.setAuthorList(authorList);
            newPost.setCategories(catList);
            newPost.setContent(bodyContent);
            newPost.setExpirationDate(expirationDate);
            newPost.setImageList(imageList);
            newPost.setLatitude(lat);
            newPost.setLongitude(lon);
            newPost.setPostDate(postDate);
            newPost.setSynopsis(synopsis);
            newPost.setTagList(verifiedAssociatedTags);
            newPost.setTitle(title);
            
           int complete = Integer.parseInt(request.getParameter("isComplete"));
            if(complete == 1){
                newPost.setIsCompleted(true);
            }
            else{
                newPost.setIsCompleted(false);
            }
            newPost.setIsPublished(false);

            postServiceLayer.addPost(newPost);

        }
        if (createType == 1) {
            StaticPage newPage = new StaticPage();
            int categoryId = Integer.parseInt(request.getParameter("category-type-page"));
            String navName = request.getParameter("title");
            String title = request.getParameter("title");
            String bodyContent = request.getParameter("body-content");
            int imageId = Integer.parseInt(request.getParameter("page-image-choice"));
            Image imageChosen = imageServiceLayer.getImageById(imageId);
            List<Image> imageList = new ArrayList<>();
            imageList.add(imageChosen);
            LocalDate postDate = LocalDate.parse(request.getParameter("post-date"), DateTimeFormatter.ISO_DATE);
            String[] authors = request.getParameterValues("author-chosen");
            List<User> authorList = new ArrayList<>();
            for (String current : authors) {
                int userId = Integer.parseInt(current);
                User toAdd = userServiceLayer.getUserById(userId);
                authorList.add(toAdd);
            }

            int complete = Integer.parseInt(request.getParameter("isComplete"));
            if(complete == 1){
                newPage.setIsCompleted(true);
            }
            else{
                newPage.setIsCompleted(false);
            }
            newPage.setIsPublished(false);

            newPage.setAuthorList(authorList);
            newPage.setImageList(imageList);
            newPage.setNavName(navName);
            newPage.setPageCategoryId(categoryId);
            newPage.setPublishDate(postDate);
            newPage.setTextArea(bodyContent);
            newPage.setTitle(title);
            staticPageServiceLayer.addStaticPage(newPage);

        }

        List<Post> postList = postServiceLayer.getPublishedPosts();

        for (Post post : postList) {
            Image image = imageServiceLayer.getPreviewImageForPost(post.getPostId());

            model.addAttribute("previewPost" + post.getPostId(), image);

        }

        model.addAttribute("theHand", new TheHand(categoryServiceLayer, staticPageServiceLayer));
        model.addAttribute("listOfPosts", postList);

        return "home";

    }

    @RequestMapping(value = "/edit/post/{postId}", method = RequestMethod.GET)
    public String displayEditPostPortal(@PathVariable int postId, Model model) {
        Post post = postServiceLayer.getPostById(postId);
        List<User> authorList = userServiceLayer.getAllUsers();
        List<User> authorListChosen = post.getAuthorList();
        for (User current : authorListChosen) {
            if (authorList.contains(current)) {
                authorList.remove(current);
            }
        }

        List<PostCategory> postList = categoryServiceLayer.getAllPostCategories();
        List<PostCategory> postListChosen = post.getCategories();
        for (PostCategory current : postListChosen) {
            if (postList.contains(current)) {
                postList.remove(current);
            }
        }
        

        List<Image> imageListHead = imageServiceLayer.getAllHeaderImages();
        Image headChosen = imageServiceLayer.getHeaderImageForBlogPost(postId);
        imageListHead.remove(headChosen);
        List<Image> imageListPreview = imageServiceLayer.getAllPreviewImages();
        Image previewChosen = imageServiceLayer.getPreviewImageForPost(postId);
        imageListPreview.remove(previewChosen);

        model.addAttribute("post", post);
        model.addAttribute("imageListHead", imageListHead);
        model.addAttribute("headChosen", headChosen);
        model.addAttribute("imageListPreview", imageListPreview);
        model.addAttribute("previewChosen", previewChosen);
        model.addAttribute("authorList", authorList);
        model.addAttribute("postList", postList);
        model.addAttribute("authorListChosen", authorListChosen);
        model.addAttribute("postListChosen", postListChosen);
        model.addAttribute("theHand", new TheHand(categoryServiceLayer, staticPageServiceLayer));

        return "editunpublished";

    }
    @RequestMapping(value = "/edit/page/{pageId}", method = RequestMethod.GET)
    public String displayEditPagePortal(@PathVariable int pageId, Model model) {
        StaticPage page = staticPageServiceLayer.getStaticPageById(pageId);
        List<User> authorList = userServiceLayer.getAllUsers();
        List<User> authorListChosen = page.getAuthorList();
        for (User current : authorListChosen) {
            if (authorList.contains(current)) {
                authorList.remove(current);
            }
        }

        int pageCatId = page.getPageCategoryId();
        PageCategory catChosen = categoryServiceLayer.getPageCategoryById(pageCatId);
        List<PageCategory> pageList = categoryServiceLayer.getAllPageCategories();
        pageList.remove(catChosen);
        

        List<Image> imageList = imageServiceLayer.getAllStaticPageBodyImages();
        List<Image> imageListChosen = page.getImageList();
        Image imageChosen = imageListChosen.get(0);
        imageList.remove(imageChosen);

        model.addAttribute("page", page);
        model.addAttribute("imageChosen", imageChosen);
        model.addAttribute("imageList", imageList);
        model.addAttribute("authorList", authorList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("authorListChosen", authorListChosen);
        model.addAttribute("catChosen", catChosen);
        model.addAttribute("theHand", new TheHand(categoryServiceLayer, staticPageServiceLayer));

        return "editunpublishedpage";

    }

    @RequestMapping(value = "/edit/post/{postId}", method = RequestMethod.POST)
    public String editPost(@PathVariable int postId, HttpServletRequest request, Model model) {
        Post toEdit = postServiceLayer.getPostById(postId);
        String[] categories = request.getParameterValues("category-type");
        String[] authors = request.getParameterValues("author-chosen");
        String title = request.getParameter("title");
        String synopsis = request.getParameter("synopsis");
        int imageIdHead = Integer.parseInt(request.getParameter("header-image-choice"));
        int imageIdPreview = Integer.parseInt(request.getParameter("preview-image-choice"));
        Image imageChosenHead = imageServiceLayer.getImageById(imageIdHead);
        Image imageChosenPreview = imageServiceLayer.getImageById(imageIdPreview);
        LocalDate postDate = LocalDate.parse(request.getParameter("post-date"), DateTimeFormatter.ISO_DATE);
        LocalDate expirationDate;
        try {
            expirationDate = LocalDate.parse(request.getParameter("expire-date"), DateTimeFormatter.ISO_DATE);
        } catch (NullPointerException e) {
            expirationDate = null;
        }

        String bodyContent = request.getParameter("body-content");
        BigDecimal lat = new BigDecimal(request.getParameter("post-latitude"));
        BigDecimal lon = new BigDecimal(request.getParameter("post-longitude"));
        List<User> authorList = new ArrayList<>();
        List<Image> imageList = new ArrayList<>();
        List<PostCategory> catList = new ArrayList<>();
        List<Tag> tagList = toEdit.getTagList();

        for (String current : authors) {
            int userId = Integer.parseInt(current);
            User toAdd = userServiceLayer.getUserById(userId);
            authorList.add(toAdd);
        }
        for (String current : categories) {
            int catId = Integer.parseInt(current);
            PostCategory toAdd = categoryServiceLayer.getPostCategoryById(catId);
            catList.add(toAdd);
        }
        imageList.add(imageChosenHead);
        imageList.add(imageChosenPreview);
        toEdit.setAuthorList(authorList);
        toEdit.setCategories(catList);
        toEdit.setContent(bodyContent);
        toEdit.setExpirationDate(expirationDate);
        toEdit.setImageList(imageList);
        int isComplete = Integer.parseInt(request.getParameter("isComplete"));
        if(isComplete == 1){
            toEdit.setIsCompleted(true);
        }
        if(isComplete == 0){
            toEdit.setIsCompleted(false);
        }
        toEdit.setIsPublished(false);
        toEdit.setLatitude(lat);
        toEdit.setLongitude(lon);
        toEdit.setPostDate(postDate);
        toEdit.setSynopsis(synopsis);
        toEdit.setTagList(tagList);
        toEdit.setTitle(title);
        postServiceLayer.editPost(toEdit);

        List<Post> postList = postServiceLayer.getPublishedPosts();

        for (Post post : postList) {
            Image image = imageServiceLayer.getPreviewImageForPost(post.getPostId());

            model.addAttribute("previewPost" + post.getPostId(), image);

        }

        model.addAttribute("theHand", new TheHand(categoryServiceLayer, staticPageServiceLayer));
        model.addAttribute("listOfPosts", postList);

        return "redirect:/";
    }
    
    @RequestMapping(value = "/edit/page/{pageId}", method = RequestMethod.POST)
    public String editPage(@PathVariable int pageId, HttpServletRequest request, Model model) {
        StaticPage toEdit = staticPageServiceLayer.getStaticPageById(pageId);
        int categoryId = Integer.parseInt(request.getParameter("category-type"));
        String[] authors = request.getParameterValues("author-chosen");
        String title = request.getParameter("title");
        String navName = request.getParameter("navName");
        int imageId = Integer.parseInt(request.getParameter("page-image-choice"));
        Image imageChosen = imageServiceLayer.getImageById(imageId);
        LocalDate postDate = LocalDate.parse(request.getParameter("post-date"), DateTimeFormatter.ISO_DATE);

        String bodyContent = request.getParameter("body-content");
        
        List<User> authorList = new ArrayList<>();
        List<Image> imageList = new ArrayList<>();
     

        for (String current : authors) {
            int userId = Integer.parseInt(current);
            User toAdd = userServiceLayer.getUserById(userId);
            authorList.add(toAdd);
        }
        
        imageList.add(imageChosen);
        toEdit.setAuthorList(authorList);
        toEdit.setNavName(navName);
        toEdit.setTitle(title);
        toEdit.setPageCategoryId(categoryId);
        toEdit.setPublishDate(postDate);
        toEdit.setTextArea(bodyContent);
        
        toEdit.setImageList(imageList);
        int isComplete = Integer.parseInt(request.getParameter("isComplete"));
        if(isComplete == 1){
            toEdit.setIsCompleted(true);
        }
        if(isComplete == 0){
            toEdit.setIsCompleted(false);
        }
        toEdit.setIsPublished(false);
        
        staticPageServiceLayer.editStaticPage(toEdit);

        List<Post> postList = postServiceLayer.getPublishedPosts();

        for (Post post : postList) {
            Image image = imageServiceLayer.getPreviewImageForPost(post.getPostId());

            model.addAttribute("previewPost" + post.getPostId(), image);

        }

        model.addAttribute("theHand", new TheHand(categoryServiceLayer, staticPageServiceLayer));
        model.addAttribute("listOfPosts", postList);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete/post/{postId}", method = RequestMethod.GET)
    public String deletePost(@PathVariable int postId, Model model) {
        postServiceLayer.deletePost(postId);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/delete/page/{pageId}", method = RequestMethod.GET)
    public String deletePage(@PathVariable int pageId, Model model) {
        staticPageServiceLayer.deleteStaticPage(pageId);
        return "redirect:/";
    }
}
