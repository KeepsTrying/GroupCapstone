/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxCategoryDao;
import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxPostDao;
import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxStaticPageDao;
import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxTagDao;
import com.tsguild.jackfontaneauxpearlrecovery.model.PageCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.StaticPage;
import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class JFoSearchServiceLayerImpl implements JFoSearchServiceLayer {

    private JackFontaneauxCategoryDao catDao;
    private JackFontaneauxStaticPageDao pageDao;
    private JackFontaneauxPostDao postDao;
    private JackFontaneauxTagDao tagDao;

    public void setCatDao(JackFontaneauxCategoryDao catDao) {
        this.catDao = catDao;
    }

    public void setPageDao(JackFontaneauxStaticPageDao pageDao) {
        this.pageDao = pageDao;
    }

    public void setPostDao(JackFontaneauxPostDao postDao) {
        this.postDao = postDao;
    }

    public void setTagDao(JackFontaneauxTagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Inject
    public JFoSearchServiceLayerImpl(JackFontaneauxCategoryDao catDao,
            JackFontaneauxStaticPageDao pageDao,
            JackFontaneauxPostDao postDao,
            JackFontaneauxTagDao tagDao) {
        this.catDao = catDao;
        this.pageDao = pageDao;
        this.postDao = postDao;
        this.tagDao = tagDao;
    }

    @Override
    public List<Post> searchForPosts(String searchRequest) {
        List<Post> relatedPosts = new ArrayList<>();

        try {
            List<PostCategory> postCategories = catDao.getAllPostCategories();
            List<PostCategory> relatedPostCategories = new ArrayList<>();

            for (PostCategory postCategory : postCategories) {
                if (postCategory.getCategoryName().toLowerCase().contains(searchRequest.toLowerCase())) {
                    relatedPostCategories.add(postCategory);
                }
            }

            for (PostCategory relatedPostCategory : relatedPostCategories) {
                List<Post> someRelatedPosts = postDao.getPublishedPostsWithCategory(relatedPostCategory);
                for (Post relatedPost : someRelatedPosts) {
                    if (!relatedPosts.contains(relatedPost)) {
                        relatedPosts.add(relatedPost);
                    }
                }
            }

        } catch (NullPointerException e) {

        }

        try {
            List<Tag> tags = tagDao.getAllTags();
            List<Tag> relatedTags = new ArrayList<>();
            for (Tag tag : tags) {
                if (tag.getTagName().toLowerCase().contains(searchRequest.toLowerCase())) {
                    relatedTags.add(tag);
                }
            }

            for (Tag relatedTag : relatedTags) {
                List<Post> postsRelatedByTag = postDao.getAllPostsByTag(relatedTag);

                for (Post postRelatedByTag : postsRelatedByTag) {
                    if (!relatedPosts.contains(postRelatedByTag)) {
                        relatedPosts.add(postRelatedByTag);
                    }
                }
            }

        } catch (NullPointerException e) {

        }

        return relatedPosts;
    }

    @Override
    public List<StaticPage> searchForStaticPages(String searchRequest) {
        List<StaticPage> relatedPages = new ArrayList<>();

        try {
            List<PageCategory> pageCategories = catDao.getAllPageCategories();
            List<PageCategory> relatedPageCategories = new ArrayList<>();

            for (PageCategory category : pageCategories) {
                if (category.getCategoryName().toLowerCase().contains(searchRequest.toLowerCase())) {
                    relatedPageCategories.add(category);
                }
            }

            for (PageCategory relatedPageCategory : relatedPageCategories) {
                List<StaticPage> someRelatedPages = pageDao.getStaticPagesWithCategory(relatedPageCategory);

                for (StaticPage relatedPage : someRelatedPages) {
                    relatedPages.add(relatedPage);
                }

            }
        } catch (NullPointerException e) {

        }

        return relatedPages;
    }

}
