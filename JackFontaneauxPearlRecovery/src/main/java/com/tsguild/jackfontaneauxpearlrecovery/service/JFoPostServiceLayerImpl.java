/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxPostDao;
import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author scooke11
 */
public class JFoPostServiceLayerImpl implements JFoPostServiceLayer {

    private JackFontaneauxPostDao postDao;

    public void setPostDao(JackFontaneauxPostDao postDao) {
        this.postDao = postDao;
    }

    @Inject
    public JFoPostServiceLayerImpl(JackFontaneauxPostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public void addPost(Post newPost) {
        postDao.addPost(newPost);
    }

    @Override
    public void deletePost(int postId) {
        postDao.deletePost(postId);
    }

    @Override
    public void editPost(Post editedPost) {
        postDao.editPost(editedPost);
    }

    @Override
    public Post getPostById(int postId) {
        return postDao.getPostById(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    public List<Post> getAllPostsByAuthor(User user) {
        return postDao.getAllPostsByAuthor(user);
    }

    @Override
    public List<Post> getAllPostsByTag(Tag tag) {
        return postDao.getAllPostsByTag(tag);
    }

    @Override
    public List<Image> getAllImagesByPost(Post post) {
        return postDao.getAllImagesByPost(post);
    }

    @Override
    public List<Tag> getAllTagsByPost(Post post) {
        return postDao.getAllTagsByPost(post);
    }

    @Override
    public List<User> getAllUsersByPost(Post post) {
        return postDao.getAllUsersByPost(post);
    }

    @Override
    public List<PostCategory> getAllPostCategoriesByPost(Post post) {
        return postDao.getAllPostCategoriesByPost(post);
    }

    @Override
    public List<Post> getPostsWithCategory(PostCategory category) {
        return postDao.getPostsWithCategory(category);
    }

    @Override
    public List<Post> getPublishedPostsWithCategory(PostCategory category) {
        return postDao.getPublishedPostsWithCategory(category);
    }

    @Override
    public List<Post> getUnpublishedPosts() {
        List<Post> allUnpublishedPosts = postDao.getUnpublishedPosts();
        List<Post> unpublishedPosts = new ArrayList();
        
        for(Post post : allUnpublishedPosts){
            if(post.isIsCompleted() && !post.isIsPublished()){
                unpublishedPosts.add(post);
            }
        }
        
        return unpublishedPosts;
        
    }

    @Override
    public List<Post> getUnpublishedPostsByAuthor(User user) {
        return postDao.getUnpublishedPostsByAuthor(user);
    }

    @Override
    public List<Post> getPublishedPosts() {
        return postDao.getPublishedPosts();
    }

    @Override
    public List<Post> getIncompletePosts() {
        return postDao.getIncompletePosts();
    }

    @Override
    public List<Post> getCompletePosts() {
        return postDao.getCompletePosts();
    }

    @Override
    public List<Post> getAllPublishedPostsByCategory(PostCategory postCat) {
        return postDao.getPublishedPostsWithCategory(postCat);
    }

    @Override
    public List<Post> getUnpublishedPostsByGroup(int startingPostNum, int groupSize) {
        return postDao.getUnpublishedPostsByGroup(startingPostNum, groupSize);
    }

    @Override
    public List<Post> getPublishedPostsByGroup(int startingPostNum, int groupSize) {
        return postDao.getPublishedPostsByGroup(startingPostNum, groupSize);
    }
    
}
