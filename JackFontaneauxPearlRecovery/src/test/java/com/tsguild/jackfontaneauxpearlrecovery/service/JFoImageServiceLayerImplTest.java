/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class JFoImageServiceLayerImplTest extends ServiceTestingBase {
    

    /**
     * Test of addImage method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testAddImage() {
        Image expectedImage = new Image();
        expectedImage.setImageTypeId(1);
        expectedImage.setImageUrl("http://imgur.com/newImageURL");
        imageServ.addImage(expectedImage);
        
        Image actualImage = imageServ.getImageById(expectedImage.getImageId());
        Assert.assertEquals(expectedImage, actualImage);
    }

    /**
     * Test of deleteImage method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testDeleteImage() {
        Image image = imageServ.getImageById(1);
        imageServ.deleteImage(1);
        Assert.assertTrue(image != null);
        Assert.assertEquals(null, imageServ.getImageById(1));
    }

    /**
     * Test of editImage method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testEditImage() {
        Image image = imageServ.getImageById(1);
        image.setImageTypeId(3);
        image.setImageUrl("newImageURL");
        imageServ.editImage(image);
        
        Image updatedImage = imageServ.getImageById(1);
        Assert.assertEquals(3, updatedImage.getImageTypeId());
        Assert.assertTrue(updatedImage.getImageUrl().equals("newImageURL"));
    }

    /**
     * Test of getImageById method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetImageById() {
        Image expectedImage = new Image();
        expectedImage.setImageTypeId(1);
        expectedImage.setImageUrl("http://imgur.com/newImageURL");
        imageServ.addImage(expectedImage);
        
        Image actualImage = imageServ.getImageById(expectedImage.getImageId());
        Assert.assertEquals(expectedImage, actualImage);
    }

    /**
     * Test of getAllPreviewImages method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetAllPreviewImages() {
        
    }

    /**
     * Test of getPreviewImageForPost method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetPreviewImageForPost() {
        
    }

    /**
     * Test of getAllHeaderImages method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetAllHeaderImages() {
        
    }

    /**
     * Test of getHeaderImageForBlogPost method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetHeaderImageForBlogPost() {
        
    }

    /**
     * Test of getHeaderImageForStaticPage method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetHeaderImageForStaticPage() {
        
    }

    /**
     * Test of getAllStaticPageBodyImages method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetAllStaticPageBodyImages() {
        
    }

    /**
     * Test of getBodyImageForStaticPage method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetBodyImageForStaticPage() {
        
    }

    /**
     * Test of getAllBlogPostBodyImages method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetAllBlogPostBodyImages() {
        
    }

    /**
     * Test of getBodyImageForBlogPost method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetBodyImageForBlogPost() {
        
    }

    /**
     * Test of addImageType method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testAddImageType() {
        
    }

    /**
     * Test of deleteImageType method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testDeleteImageType() {
        
    }

    /**
     * Test of editImageType method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testEditImageType() {
        
    }

    /**
     * Test of getImageTypeById method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetImageTypeById() {
        
    }

    /**
     * Test of getAllImageTypes method, of class JFoImageServiceLayerImpl.
     */
    @Test
    public void testGetAllImageTypes() {
        
    }
    
}
