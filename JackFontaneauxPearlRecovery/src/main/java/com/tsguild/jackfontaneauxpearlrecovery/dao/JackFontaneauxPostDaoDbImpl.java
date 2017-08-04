/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.mapper.ImageMapper;
import com.tsguild.jackfontaneauxpearlrecovery.mapper.PostCategoryMapper;
import com.tsguild.jackfontaneauxpearlrecovery.mapper.PostMapper;
import com.tsguild.jackfontaneauxpearlrecovery.mapper.TagMapper;
import com.tsguild.jackfontaneauxpearlrecovery.mapper.UserMapper;
import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.Post;
import com.tsguild.jackfontaneauxpearlrecovery.model.PostCategory;
import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import com.tsguild.jackfontaneauxpearlrecovery.model.User;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxPostDaoDbImpl implements JackFontaneauxPostDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_POST = "insert into Posts "
            + "(Title, Synopsis, Body, PostDate, ExpirationDate, Published, Completed, Latitude, Longitude) "
            + "values (?,?,?,?,?,?,?,?,?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPost(Post newPost) {
        jdbcTemplate.update(SQL_ADD_POST,
                newPost.getTitle(),
                newPost.getSynopsis(),
                newPost.getContent(),
                newPost.getPostDate().toString(),
                newPost.getExpirationDate().toString(),
                newPost.isIsPublished(),
                newPost.isIsCompleted(),
                newPost.getLatitude(),
                newPost.getLongitude());
        int postId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        newPost.setPostId(postId);
        insertPostCategories(newPost);
        insertPostUsers(newPost);
        insertPostImages(newPost);
        insertPostTags(newPost);
    }

    private static final String SQL_REMOVE_POST_TAGS_BRIDGE = "delete from PostsTags "
            + "where PostId = ?";
    private static final String SQL_REMOVE_POST_IMAGES_BRIDGE = "delete from PostsImages "
            + "where PostId = ?";
    private static final String SQL_REMOVE_POST_CATEGORIES_BRIDGE = "delete from PostsCategories "
            + "where PostId = ?";
    private static final String SQL_REMOVE_POST_USERS_BRIDGE = "delete from PostsUsers "
            + "where PostId = ?";
    private static final String SQL_REMOVE_POST_COMMENTS = "delete from Comments "
            + "where PostId = ?";
    private static final String SQL_DELETE_POST = "delete from Posts where PostId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePost(int postId) {
        jdbcTemplate.update(SQL_REMOVE_POST_TAGS_BRIDGE, postId);
        jdbcTemplate.update(SQL_REMOVE_POST_IMAGES_BRIDGE, postId);
        jdbcTemplate.update(SQL_REMOVE_POST_CATEGORIES_BRIDGE, postId);
        jdbcTemplate.update(SQL_REMOVE_POST_USERS_BRIDGE, postId);
        jdbcTemplate.update(SQL_REMOVE_POST_COMMENTS, postId);
        jdbcTemplate.update(SQL_DELETE_POST, postId);
    }

    private static final String SQL_UPDATE_POST = "update Posts set "
            + "Title = ?, Synopsis = ?, Body = ?, PostDate = ?, "
            + "ExpirationDate = ?, Published = ?, Completed = ?, "
            + "Longitude = ?, Latitude = ? where PostId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void editPost(Post editedPost) {
        jdbcTemplate.update(SQL_UPDATE_POST,
                editedPost.getTitle(),
                editedPost.getSynopsis(),
                editedPost.getContent(),
                editedPost.getPostDate().toString(),
                editedPost.getExpirationDate().toString(),
                editedPost.isIsPublished(),
                editedPost.isIsCompleted(),
                editedPost.getLongitude(),
                editedPost.getLatitude(),
                editedPost.getPostId());
        jdbcTemplate.update(SQL_REMOVE_POST_TAGS_BRIDGE, editedPost.getPostId());
        jdbcTemplate.update(SQL_REMOVE_POST_IMAGES_BRIDGE, editedPost.getPostId());
        jdbcTemplate.update(SQL_REMOVE_POST_CATEGORIES_BRIDGE, editedPost.getPostId());
        jdbcTemplate.update(SQL_REMOVE_POST_USERS_BRIDGE, editedPost.getPostId());
        insertPostCategories(editedPost);
        insertPostUsers(editedPost);
        insertPostImages(editedPost);
        insertPostTags(editedPost);
    }

    private static final String SQL_GET_POST_BY_ID = "select * from Posts "
            + "where PostId = ?";

    @Override
    public Post getPostById(int postId) {
        try {
            Post post = jdbcTemplate.queryForObject(SQL_GET_POST_BY_ID,
                    new PostMapper(),
                    postId);
            post.setAuthorList(getAllUsersByPost(post));
            post.setImageList(getAllImagesByPost(post));
            post.setCategories(getAllPostCategoriesByPost(post));
            post.setTagList(getAllTagsByPost(post));
            return post;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_POSTS = "select * from Posts;";

    @Override
    public List<Post> getAllPosts() {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_ALL_POSTS, new PostMapper());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_POSTS_BY_AUTHOR = "select * from Posts as p "
            + "inner join PostsUsers as pu on p.PostId = pu.PostId "
            + "where pu.UserId = ?";

    @Override
    public List<Post> getAllPostsByAuthor(User user) {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_POSTS_BY_AUTHOR,
                    new PostMapper(),
                    user.getUserId());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_POSTS_BY_TAG = "select * from Posts as p "
            + "inner join PostsTags as pt on p.PostId = pt.PostId "
            + "where pt.TagId = ? and Published = 1";

    @Override
    public List<Post> getAllPostsByTag(Tag tag) {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_POSTS_BY_TAG,
                    new PostMapper(),
                    tag.getTagId());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_IMAGES_BY_POST = "select * from Images as i "
            + "inner join PostsImages as pi on i.ImageId = pi.ImageId "
            + "where pi.PostId = ?";

    @Override
    public List<Image> getAllImagesByPost(Post post) {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_IMAGES_BY_POST,
                    new ImageMapper(),
                    post.getPostId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_TAGS_BY_POST = "select * from Tags as t "
            + "inner join PostsTags as pt on t.TagId = pt.TagId "
            + "where pt.PostId = ?";

    @Override
    public List<Tag> getAllTagsByPost(Post post) {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_TAGS_BY_POST,
                    new TagMapper(),
                    post.getPostId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_USERS_BY_POST = "select * from Users as u "
            + "inner join PostsUsers as pu on u.UserId = pu.UserId "
            + "where pu.PostId = ?";

    @Override
    public List<User> getAllUsersByPost(Post post) {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_USERS_BY_POST,
                    new UserMapper(),
                    post.getPostId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_ALL_POST_CATEGORIES_BY_POST = "select * from Categories as c "
            + "inner join PostsCategories as pc on c.CategoryId = pc.CategoryId "
            + "where pc.PostId = ?";

    @Override
    public List<PostCategory> getAllPostCategoriesByPost(Post post) {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_POST_CATEGORIES_BY_POST,
                    new PostCategoryMapper(),
                    post.getPostId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_POSTS_WITH_CATEGORY
            = "SELECT PostId, PostDate, ExpirationDate, Title, Synopsis, "
            + "Latitude, Longitude, Body, Published, Completed "
            + "FROM Posts "
            + "LEFT JOIN PostsCategories USING (PostId) "
            + "LEFT JOIN Categories USING (CategoryId) "
            + "WHERE CategoryId = ? ORDER BY PostDate DESC LIMIT 10;";

    @Override
    public List<Post> getPostsWithCategory(PostCategory category) {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_POSTS_WITH_CATEGORY, new PostMapper(), category.getPostCategoryId());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    private static final String SQL_GET_PUBLISHED_POSTS_WITH_CATEGORY
            = "SELECT PostId, PostDate, ExpirationDate, Title, Synopsis, "
            + "Latitude, Longitude, Body, Published, Completed "
            + "FROM Posts "
            + "LEFT JOIN PostsCategories USING (PostId) "
            + "LEFT JOIN Categories USING (CategoryId) "
            + "WHERE CategoryId = ? AND Published = 1 ORDER BY PostDate DESC LIMIT 10;";

    @Override
    public List<Post> getPublishedPostsWithCategory(PostCategory category) {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_PUBLISHED_POSTS_WITH_CATEGORY, new PostMapper(), category.getPostCategoryId());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    
    
    

    private static final String SQL_GET_UNPUBLISHED_POSTS
            = "SELECT * FROM Posts WHERE Published = 0 ORDER BY PostDate DESC;";

    @Override
    public List<Post> getUnpublishedPosts() {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_UNPUBLISHED_POSTS, new PostMapper());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    private static final String SQL_GET_UNPUBLISHED_POSTS_BY_AUTHOR
            = "SELECT p.PostId, PostDate, ExpirationDate, Title, Synopsis, Body, p.Latitude, p.Longitude, Published, Completed "
            + "FROM Posts as p "
            + "LEFT JOIN PostsUsers as pu on p.PostId = pu.PostId "
            + "LEFT JOIN Users as u on pu.UserId = u.UserId "
            + "WHERE u.UserId = ? AND p.Published = 0 ORDER BY p.PostDate DESC;";

    @Override
    public List<Post> getUnpublishedPostsByAuthor(User user) {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_UNPUBLISHED_POSTS_BY_AUTHOR, new PostMapper(), user.getUserId());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    private static final String SQL_GET_LIMITED_NUMBER_OF_UNPUBLISHED_POSTS_IN_GROUP
            = "SELECT * FROM Posts WHERE Published = 0 AND Completed = 1 ORDER BY PostDate DESC LIMIT ?,?;";
    
    @Override
    public List<Post> getUnpublishedPostsByGroup(int startingPostNum, int groupSize) {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_LIMITED_NUMBER_OF_UNPUBLISHED_POSTS_IN_GROUP, 
                    new PostMapper(), 
                    startingPostNum, 
                    groupSize);
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_PUBLISHED_POSTS
            = "SELECT * FROM Posts WHERE Published = 1 ORDER BY PostDate DESC;";

    @Override
    public List<Post> getPublishedPosts() {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_PUBLISHED_POSTS, new PostMapper());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    private static final String SQL_GET_LIMITED_NUMBER_OF_PUBLISHED_POSTS_IN_GROUP
            = "SELECT * FROM Posts WHERE Published = 1 ORDER BY PostDate DESC LIMIT ?,?;";
    
    @Override
    public List<Post> getPublishedPostsByGroup(int startingPostNum, int groupSize) {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_LIMITED_NUMBER_OF_PUBLISHED_POSTS_IN_GROUP, 
                    new PostMapper(), 
                    startingPostNum, 
                    groupSize);
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_INCOMPLETE_POSTS
            = "SELECT * FROM Posts WHERE Completed = 0 ORDER BY PostDate DESC;";

    @Override
    public List<Post> getIncompletePosts() {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_INCOMPLETE_POSTS, new PostMapper());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_GET_COMPLETE_POSTS
            = "SELECT * FROM Posts WHERE Completed = 1 ORDER BY PostDate DESC;";

    @Override
    public List<Post> getCompletePosts() {
        try {
            List<Post> postList = jdbcTemplate.query(SQL_GET_COMPLETE_POSTS, new PostMapper());
            return associatePostCategoryUserImageTag(postList);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_INSERT_POSTS_CATEGORIES = "insert into PostsCategories "
            + "(PostId, CategoryId) values (?,?)";
    
    private void insertPostCategories(Post post) {
        int postId = post.getPostId();
        List<PostCategory> categoryList = post.getCategories();
        for (PostCategory currentCategory : categoryList) {
            jdbcTemplate.update(SQL_INSERT_POSTS_CATEGORIES,
                    postId,
                    currentCategory.getPostCategoryId());
        }
    }

    private static final String SQL_INSERT_POSTS_USERS = "insert into PostsUsers "
            + "(PostId, UserId) values (?,?)";

    private void insertPostUsers(Post post) {
        int postId = post.getPostId();
        List<User> userList = post.getAuthorList();
        for (User currentUser : userList) {
            jdbcTemplate.update(SQL_INSERT_POSTS_USERS,
                    postId,
                    currentUser.getUserId());
        }
    }

    private static final String SQL_INSERT_POSTS_IMAGES = "insert into PostsImages "
            + "(PostId, ImageId) values (?,?)";

    private void insertPostImages(Post post) {
        int postId = post.getPostId();
        List<Image> imageList = post.getImageList();
        for (Image currentImage : imageList) {
            jdbcTemplate.update(SQL_INSERT_POSTS_IMAGES,
                    postId,
                    currentImage.getImageId());
        }
    }

    private static final String SQL_INSERT_POSTS_TAGS = "insert into PostsTags "
            + "(PostId, TagId) values (?,?)";

    private void insertPostTags(Post post) {
        int postId = post.getPostId();
        List<Tag> tagList = post.getTagList();
        for (Tag currentTag : tagList) {
            jdbcTemplate.update(SQL_INSERT_POSTS_TAGS,
                    postId,
                    currentTag.getTagId());
        }
    }

    private List<Post> associatePostCategoryUserImageTag(List<Post> postList) {
        for (Post currentPost : postList) {
            currentPost.setAuthorList(getAllUsersByPost(currentPost));
            currentPost.setCategories(getAllPostCategoriesByPost(currentPost));
            currentPost.setImageList(getAllImagesByPost(currentPost));
            currentPost.setTagList(getAllTagsByPost(currentPost));
        }
        return postList;
    }

}
