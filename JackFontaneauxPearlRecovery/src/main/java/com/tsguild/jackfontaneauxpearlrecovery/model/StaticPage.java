/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class StaticPage {
    
    private int pageId;
    
    private int pageCategoryId;
    
    private String textArea;
    
    private String navName;
    
    private String title;
    
    private List<User> authorList;
    
    private List<Image> imageList;
    
    private boolean isPublished;
    
    private boolean isCompleted;
    
    LocalDate publishDate;

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getPageCategoryId() {
        return pageCategoryId;
    }

    public void setPageCategoryId(int pageCategoryId) {
        this.pageCategoryId = pageCategoryId;
    }

    public String getTextArea() {
        return textArea;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<User> authorList) {
        this.authorList = authorList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.pageId;
        hash = 23 * hash + this.pageCategoryId;
        hash = 23 * hash + Objects.hashCode(this.textArea);
        hash = 23 * hash + Objects.hashCode(this.navName);
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.authorList);
        hash = 23 * hash + Objects.hashCode(this.imageList);
        hash = 23 * hash + (this.isPublished ? 1 : 0);
        hash = 23 * hash + (this.isCompleted ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.publishDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StaticPage other = (StaticPage) obj;
        if (this.pageId != other.pageId) {
            return false;
        }
        if (this.pageCategoryId != other.pageCategoryId) {
            return false;
        }
        if (this.isPublished != other.isPublished) {
            return false;
        }
        if (this.isCompleted != other.isCompleted) {
            return false;
        }
        if (!Objects.equals(this.textArea, other.textArea)) {
            return false;
        }
        if (!Objects.equals(this.navName, other.navName)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.authorList, other.authorList)) {
            return false;
        }
        if (!Objects.equals(this.imageList, other.imageList)) {
            return false;
        }
        if (!Objects.equals(this.publishDate, other.publishDate)) {
            return false;
        }
        return true;
    }

    

   

    

    

   
    
    
}
