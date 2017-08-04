/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxImageDao;
import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.ImageType;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author scooke11
 */

public class JFoImageServiceLayerImpl implements JFoImageServiceLayer {

    private JackFontaneauxImageDao imageDao;

    public void setImageDao(JackFontaneauxImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Inject
    public JFoImageServiceLayerImpl(JackFontaneauxImageDao imageDao) {
        this.imageDao = imageDao;
    }
    
    @Override
    public void addImage(Image newImage) {
        imageDao.addImage(newImage);
    }

    @Override
    public void deleteImage(int imageId) {
        imageDao.deleteImage(imageId);
    }

    @Override
    public void editImage(Image editedImage) {
        imageDao.editImage(editedImage);
    }

    @Override
    public Image getImageById(int imageId) {
        return imageDao.getImageById(imageId);
    }

    @Override
    public List<Image> getAllPreviewImages() {
        return imageDao.getAllPreviewImages();
    }

    @Override
    public Image getPreviewImageForPost(int postId) {
        return imageDao.getPreviewImageForPost(postId);
    }

    @Override
    public List<Image> getAllHeaderImages() {
        return imageDao.getAllHeaderImages();
    }

    @Override
    public Image getHeaderImageForBlogPost(int postId) {
        return imageDao.getHeaderImageForBlogPost(postId);
    }

    @Override
    public Image getHeaderImageForStaticPage(int pageId) {
        return imageDao.getHeaderImageForStaticPage(pageId);
    }

    @Override
    public List<Image> getAllStaticPageBodyImages() {
        return imageDao.getAllStaticPageBodyImages();
    }

    @Override
    public Image getBodyImageForStaticPage(int pageId) {
        return imageDao.getBodyImageForStaticPage(pageId);
    }

    @Override
    public List<Image> getAllBlogPostBodyImages() {
        return imageDao.getAllBlogPostBodyImages();
    }

    @Override
    public Image getBodyImageForBlogPost(int postId) {
        return imageDao.getBodyImageForBlogPost(postId);
    }

    @Override
    public ImageType addImageType(ImageType newType) {
        return imageDao.addImageType(newType);
    }

    @Override
    public void deleteImageType(int typeId) {
        imageDao.deleteImageType(typeId);
    }

    @Override
    public void editImageType(ImageType editedType) {
        imageDao.editImageType(editedType);
    }

    @Override
    public ImageType getImageTypeById(int typeId) {
        return imageDao.getImageTypeById(typeId);
    }

    @Override
    public List<ImageType> getAllImageTypes() {
        return imageDao.getAllImageTypes();
    }
    
}
