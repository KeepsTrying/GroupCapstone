/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.Image;
import com.tsguild.jackfontaneauxpearlrecovery.model.ImageType;
import java.util.List;

/**
 *
 * @author scooke11
 */
public interface JFoImageServiceLayer {
    
    public void addImage(Image newImage);
    
    public void deleteImage(int imageId);
    
    public void editImage(Image editedImage);
    
    public Image getImageById(int imageId);
    
    public List<Image> getAllPreviewImages();
    
    public Image getPreviewImageForPost(int postId);
    
    public List<Image> getAllHeaderImages();
    
    public Image getHeaderImageForBlogPost(int postId);
    
    public Image getHeaderImageForStaticPage(int pageId);
    
    public List<Image> getAllStaticPageBodyImages();
    
    public Image getBodyImageForStaticPage(int pageId);
    
    public List<Image> getAllBlogPostBodyImages();
        
    public Image getBodyImageForBlogPost(int postId);
    
    public ImageType addImageType(ImageType newType);
    
    public void deleteImageType(int typeId);
    
    public void editImageType(ImageType editedType);
    
    public ImageType getImageTypeById(int typeId);
    
    public List<ImageType> getAllImageTypes();
    
}
