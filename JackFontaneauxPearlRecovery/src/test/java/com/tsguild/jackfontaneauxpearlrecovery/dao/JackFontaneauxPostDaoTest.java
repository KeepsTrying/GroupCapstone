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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxPostDaoTest extends TestingBase {

    /**
     * Test of addPost method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testAddPost() {

        List<Post> postList = postDao.getAllPosts();
        assertEquals(2, postList.size());
        Post newPost = new Post();
        List<User> userList = new ArrayList<>();
        userList.add(userDao.getUserById(1));
        userList.add(userDao.getUserById(2));
        List<Image> imageList = new ArrayList<>();
        imageList.add(imageDao.getImageById(1));
        List<PostCategory> catList = catDao.getAllPostCategories();
        List<Tag> tagList = tagDao.getAllTags();
        assertEquals(2, userList.size());
        assertEquals(1, imageList.size());
        assertEquals(4, catList.size());
        assertEquals(4, tagList.size());
        newPost.setAuthorList(userList);
        newPost.setCategories(catList);
        newPost.setImageList(imageList);
        newPost.setTagList(tagList);
        newPost.setContent("This is the awesome content of this blog post. It can be a whole lot of content so I actually"
                + " have to test that I can enter a long string of text and also make multiple sentences and stuff");
        newPost.setTitle("Long Post");
        newPost.setSynopsis("The really long awesome Post");
        newPost.setPostDate(LocalDate.parse("2017-09-12", DateTimeFormatter.ISO_DATE));
        newPost.setExpirationDate(LocalDate.parse("2222-09-12", DateTimeFormatter.ISO_DATE));
        newPost.setIsPublished(true);
        newPost.setIsCompleted(true);
        newPost.setLatitude(new BigDecimal("45.56"));
        newPost.setLongitude(new BigDecimal("23.34"));
        postDao.addPost(newPost);
        List<Post> postList2 = postDao.getAllPosts();
        assertEquals(3, postList2.size());
        Post fromDao = postDao.getPostById(newPost.getPostId());
        assertEquals(1, fromDao.getImageList().size());
        assertEquals(fromDao.getContent(), newPost.getContent());

    }

    /**
     * Test of deletePost method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testDeletePost() {
        Post fromDao = postDao.getPostById(1);
        postDao.deletePost(fromDao.getPostId());
        assertEquals(null, postDao.getPostById(1));
    }

    /**
     * Test of editPost method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testEditPost() {
        Post toEdit = postDao.getPostById(1);
        assertEquals("First Blog Post", toEdit.getTitle());
        toEdit.setTitle("The First One");
        postDao.editPost(toEdit);
        Post fromDao = postDao.getPostById(1);
        assertEquals("The First One", fromDao.getTitle());
        assertEquals(fromDao, toEdit);
    }

    /**
     * Test of getAllPostsByAuthor method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetAllPostsByAuthor() {
        User userOne = userDao.getUserById(1);
        List<Post> userPostList = postDao.getAllPostsByAuthor(userOne);
        assertEquals(2, userPostList.size());
        User UserTwo = userDao.getUserById(2);
        List<Post> userPostListTwo = postDao.getAllPostsByAuthor(UserTwo);
        assertEquals(1, userPostListTwo.size());
        assertEquals(true, userPostList.contains(postDao.getPostById(1)));
        assertEquals(true, userPostList.contains(postDao.getPostById(2)));
        assertEquals(true, userPostListTwo.contains(postDao.getPostById(2)));
        assertEquals(false, userPostListTwo.contains(postDao.getPostById(1)));
    }

    /**
     * Test of getAllPostsByTag method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetAllPostsByTag() {
        Tag oneTag = tagDao.getTagById(1);
        List<Post> tagPostList = postDao.getAllPostsByTag(oneTag);
        assertEquals(1, tagPostList.size());
    }

    /**
     * Test of getAllImagesByPost method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetAllImagesByPost() {
        Post theOne = postDao.getPostById(1);
        List<Image> imageList = postDao.getAllImagesByPost(theOne);
        assertEquals(2, imageList.size());
    }

    /**
     * Test of getAllTagsByPost method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetAllTagsByPost() {
        Post theOne = postDao.getPostById(1);
        List<Tag> tagList = postDao.getAllTagsByPost(theOne);
        assertEquals(3, tagList.size());
    }

    /**
     * Test of getAllUsersByPost method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetAllUsersByPost() {
        Post theOne = postDao.getPostById(1);
        List<User> userList = postDao.getAllUsersByPost(theOne);
        assertEquals(1, userList.size());
    }

    /**
     * Test of getAllPostCategoriesByPost method, of class
     * JackFontaneauxPostDao.
     */
    @Test
    public void testGetAllPostCategoriesByPost() {
        Post theOne = postDao.getPostById(1);
        List<PostCategory> catList = postDao.getAllPostCategoriesByPost(theOne);
        assertEquals(2, catList.size());
    }
    
    /**
     * Test of getPostById method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetPostById() {
        Post post = postDao.getPostById(1);
        Assert.assertTrue(post.getTitle().equals("First Blog Post"));
    }
    
    /**
     * Test of getAllPosts method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetAllPosts() {
        List<Post> posts = postDao.getAllPosts();
        Assert.assertEquals(2, posts.size());
        Post testPost = postDao.getPostById(1);
        Assert.assertTrue(posts.contains(testPost));
    }
    
    /**
     * Test of getPostsWithCategory method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetPostsWithCategory() {
        PostCategory category = catDao.getPostCategoryById(1);
        List<Post> posts = postDao.getPostsWithCategory(category);
        Assert.assertEquals(2, posts.size());
        Assert.assertTrue(posts.get(0).getCategories().get(0).equals(category));
    }
    
    /**
     * Test of getPublishedPostsWithCategory method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetPublishedPostsWithCategory() {
        PostCategory category = catDao.getPostCategoryById(1);
        List<Post> publishedPosts = postDao.getPublishedPostsWithCategory(category);
        Assert.assertEquals(1, publishedPosts.size());
        Assert.assertTrue(publishedPosts.get(0).getCategories().get(0).equals(category));
    }
    
    /**
     * Test of getUnpublishedPosts method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetUnpublishedPosts() {
        List<Post> unpublishedPosts = postDao.getUnpublishedPosts();
        Post unpublishedPost = postDao.getPostById(2);
        Assert.assertTrue(unpublishedPosts.contains(unpublishedPost));
        Assert.assertEquals(1, unpublishedPosts.size());
    }
    
    /**
     * Test of getUnpublishedPostsByAuthor method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetUnpublishedPostsByAuthor() {
        User author = userDao.getUserById(1);
        List<Post> unpublishedPostsByAuthor = postDao.getUnpublishedPostsByAuthor(author);
        Assert.assertEquals(1, unpublishedPostsByAuthor.size());
        Post unpublishedPost = postDao.getPostById(2);
        Assert.assertTrue(unpublishedPostsByAuthor.contains(unpublishedPost));
    }
    
    /**
     * Test of getUnpublishedPostsByGroup method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGeUnpublishedPostsByGroup() {
        List<Post> firstListOfPublishedPosts = postDao.getUnpublishedPostsByGroup(0, 6);
        List<Post> secondListOfPublishedPosts = postDao.getUnpublishedPostsByGroup(6, 6);
        Assert.assertEquals(0, firstListOfPublishedPosts.size());
        Assert.assertTrue(secondListOfPublishedPosts.isEmpty());
    }

    /**
     * Test of getPublishedPosts method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetPublishedPosts() {
        List<Post> publishedPosts = postDao.getPublishedPosts();
        Assert.assertEquals(1, publishedPosts.size());
        Post publishedPost = postDao.getPostById(1);
        Assert.assertTrue(publishedPosts.contains(publishedPost));
    }
    
    /**
     * Test of getPublishedPostsByGroup method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetPublishedPostsByGroup() {
        List<Post> firstListOfPublishedPosts = postDao.getPublishedPostsByGroup(0, 6);
        List<Post> secondListOfPublishedPosts = postDao.getPublishedPostsByGroup(6, 6);
        Assert.assertEquals(1, firstListOfPublishedPosts.size());
        Assert.assertTrue(secondListOfPublishedPosts.isEmpty());
    }
    
            
    /**
     * Test of getIncompletePosts method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetIncompletePosts() {
        List<Post> incompletePosts = postDao.getIncompletePosts();
        Assert.assertEquals(1, incompletePosts.size());
        Post incompletePost = postDao.getPostById(2);
        Assert.assertTrue(incompletePosts.contains(incompletePost));
    }
    
    /**
     * Test of getCompletePosts method, of class JackFontaneauxPostDao.
     */
    @Test
    public void testGetCompletePosts() {
        List<Post> completePosts = postDao.getCompletePosts();
        Assert.assertEquals(1, completePosts.size());
        Post completePost = postDao.getPostById(1);
        Assert.assertTrue(completePosts.contains(completePost));
    }
    
}



