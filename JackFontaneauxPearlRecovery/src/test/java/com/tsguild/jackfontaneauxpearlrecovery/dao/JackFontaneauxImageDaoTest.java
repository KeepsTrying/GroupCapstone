/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.ImageType;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxImageDaoTest extends TestingBase{
    
   

    /**
     * Test of addImage method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testAddImage() {
      Image newOne = new Image();
      newOne.setImageTypeId(1);
      newOne.setImageUrl("https://myaahhhh.org/23.3/2");
      imageDao.addImage(newOne);
      Image fromDao = imageDao.getImageById(newOne.getImageId());
      assertEquals(fromDao, newOne);
    }

    /**
     * Test of deleteImage method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testDeleteImage() {
      Image newOne = new Image();
      newOne.setImageTypeId(1);
      newOne.setImageUrl("https://myaahhhh.org/23.3/2");
      imageDao.addImage(newOne);
      Image fromDao = imageDao.getImageById(newOne.getImageId());
      assertEquals(fromDao, newOne);
      imageDao.deleteImage(newOne.getImageId());
      assertEquals(null, imageDao.getImageById(newOne.getImageId()));
    }

    /**
     * Test of editImage method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testEditImage() {
        
      Image newOne = new Image();
      newOne.setImageTypeId(1);
      newOne.setImageUrl("https://myaahhhh.org/23.3/2");
      imageDao.addImage(newOne);
      Image fromDao = imageDao.getImageById(newOne.getImageId());
      assertEquals(fromDao, newOne);
      fromDao.setImageTypeId(2);
      fromDao.setImageUrl("https://sinsssler/23/3.com");
      imageDao.editImage(fromDao);
      Image edited = imageDao.getImageById(fromDao.getImageId());
      assertEquals(edited, fromDao);
    }

    /**
     * Test of getImageById method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetImageById() {
       Image testImage = imageDao.getImageById(1);
       Assert.assertTrue(testImage.getImageUrl().equals("http://imgur.com/preview1.jpg"));
    }
    
    /**
     * Test of getAllPreviewImages method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetAllPreviewImages() {
        List<Image> previewImages = imageDao.getAllPreviewImages();
        assertEquals(2, previewImages.size());
        assertEquals(true, previewImages.contains(imageDao.getImageById(1)));
    }
    
    
    /**
     * Test of getPreviewImageForPost method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetPreviewImageForPost() {
        Image previewImage = imageDao.getPreviewImageForPost(2);
        assertEquals("http://imgur.com/preview2.jpg", previewImage.getImageUrl());
    }
    
    
    /**
     * Test of getAllHeaderImages method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetAllHeaderImages() {
        List<Image> headers = imageDao.getAllHeaderImages();
        assertEquals(true, headers.contains(imageDao.getImageById(2)));
        assertEquals(1, headers.size());
    }

    
    /**
     * Test of getHeaderImageForBlogPost method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetHeaderImageForBlogPost() {
        Image forPostHeader = imageDao.getHeaderImageForBlogPost(1);
        assertEquals(2, forPostHeader.getImageTypeId());
    }
    
    
    /**
     * Test of getHeaderImageForStaticPage method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetHeaderImageForStaticPage() {
        Image headerImage = imageDao.getHeaderImageForStaticPage(1);
        Assert.assertTrue(headerImage.getImageUrl().equals("http://imgur.com/headerPic.jpg"));
    }
    
    
    
    /**
     * Test of getAllStaticPageBodyImages method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetAllStaticPageBodyImages() {
        List<Image> staticImage = imageDao.getAllStaticPageBodyImages();
        assertEquals(1, staticImage.size());
        assertEquals(true, staticImage.contains(imageDao.getImageById(3)));
    }
    
    
    /**
     * Test of getBodyImageForStaticPage method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetBodyImageForStaticPage() {
        Image bodyImage = imageDao.getBodyImageForStaticPage(1);
        assertEquals(3, bodyImage.getImageTypeId());
    }
    
    
    /**
     * Test of getAllBlogPostBodyImages method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetAllBlogPostBodyImages() {
        List<Image> blogImages = imageDao.getAllBlogPostBodyImages();
        Image actualImage = blogImages.get(0);
        Image expectedImage = imageDao.getImageById(actualImage.getImageId());
        
        Assert.assertEquals(expectedImage, actualImage);
    }
    
    
    /**
     * Test of getBodyImageForBlogPost method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetBodyImageForBlogPost() {
        jdbcTemplate.execute("INSERT INTO Posts (PostId, PostDate, Title, Synopsis, Body, Published, Completed) "
                + "VALUES (3, '2017-07-20', 'New Post', 'synopsis for new post', 'New post for testing purposes', 0, 0)");
        jdbcTemplate.execute("INSERT INTO PostsImages (PostId, ImageId) "
                + "VALUES (3, 4)");
        Image actualImage = imageDao.getBodyImageForBlogPost(3);
        Image expectedImage = imageDao.getImageById(actualImage.getImageId());
        
        Assert.assertEquals(expectedImage, actualImage);
    }
    
    
    /**
     * Test of addImageType method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testAddImageType() {
        ImageType newImageType = new ImageType();
        newImageType.setImageType("footer");
        ImageType actualImageType = imageDao.addImageType(newImageType);
        ImageType expectedImageType = imageDao.getImageTypeById(actualImageType.getImageTypeId());
        Assert.assertEquals(expectedImageType, actualImageType);
    }
    
    
    /**
     * Test of deleteImageType method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testDeleteImageType() {
        int numOfImageTypes = imageDao.getAllImageTypes().size();
        imageDao.deleteImageType(1);
        
        Assert.assertEquals(imageDao.getAllImageTypes().size(), (numOfImageTypes - 1));
    }
    
    
    /**
     * Test of editImageType method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testEditImageType() {
    ImageType initialImageType = imageDao.getImageTypeById(1);
    initialImageType.setImageType("new image type");
    imageDao.editImageType(initialImageType);
    
    Assert.assertTrue(imageDao.getImageTypeById(initialImageType.getImageTypeId()).equals(initialImageType));
    }
    
    
    
    /**
     * Test of getImageTypeById method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetImageTypeById() {
        ImageType testImageType = imageDao.getImageTypeById(1);
        
        Assert.assertTrue(testImageType.getImageType().equals("preview"));
    }
    
    
    /**
     * Test of getAllImageTypes method, of class JackFontaneauxImageDao.
     */
    @Test
    public void testGetAllImageTypes() {
        List<ImageType> imageTypes = imageDao.getAllImageTypes();
        Assert.assertEquals(4, imageTypes.size());
        
        ImageType actualImageType = imageTypes.get(0);
        Assert.assertTrue(actualImageType.getImageType().equals("preview"));
    }
    
}
