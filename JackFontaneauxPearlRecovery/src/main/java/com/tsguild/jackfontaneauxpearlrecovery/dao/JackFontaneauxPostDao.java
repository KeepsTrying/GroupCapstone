/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;


import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface JackFontaneauxPostDao {
    
    public void addPost(Post newPost);
    
    public void deletePost(int postId);
    
    public void editPost(Post editedPost);
    
    public Post getPostById(int postId);
    
    public List<Post> getAllPosts();
    
    public List<Post> getAllPostsByAuthor(User user);
    
    public List<Post> getAllPostsByTag(Tag tag);
    
    public List<Image> getAllImagesByPost(Post post);
    
    public List<Tag> getAllTagsByPost(Post post);
    
    public List<User> getAllUsersByPost(Post post);
    
    public List<PostCategory> getAllPostCategoriesByPost(Post post);
    
    public List<Post> getPostsWithCategory(PostCategory category);
    
    public List<Post> getPublishedPostsWithCategory(PostCategory category);
    
    public List<Post> getUnpublishedPosts();
    
    public List<Post> getUnpublishedPostsByAuthor(User user);
    
    public List<Post> getUnpublishedPostsByGroup(int startingPostNum, int groupSize);
    
    public List<Post> getPublishedPosts();
    
    public List<Post> getPublishedPostsByGroup(int startingPostNum, int groupSize);
    
    public List<Post> getIncompletePosts();
    
    public List<Post> getCompletePosts();
    
    
}
