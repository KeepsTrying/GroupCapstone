/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Post {
    
    private int postId;
    
    private String title;
    
    private String synopsis;
    
    private String content;
    
    private LocalDate postDate;
    
    private BigDecimal latitude;
    
    private BigDecimal longitude;
    
    private LocalDate expirationDate;
    
    private List<PostCategory> categories;
    
    private List<Image> imageList;
    
    private List<User> authorList;
    
    private List<Tag> tagList;

    private boolean isPublished;
    
    private boolean isCompleted;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<PostCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<PostCategory> categories) {
        this.categories = categories;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<User> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<User> authorList) {
        this.authorList = authorList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public boolean isIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.postId;
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.synopsis);
        hash = 53 * hash + Objects.hashCode(this.content);
        hash = 53 * hash + Objects.hashCode(this.postDate);
        hash = 53 * hash + Objects.hashCode(this.latitude);
        hash = 53 * hash + Objects.hashCode(this.longitude);
        hash = 53 * hash + Objects.hashCode(this.expirationDate);
        hash = 53 * hash + Objects.hashCode(this.categories);
        hash = 53 * hash + Objects.hashCode(this.imageList);
        hash = 53 * hash + Objects.hashCode(this.authorList);
        hash = 53 * hash + Objects.hashCode(this.tagList);
        hash = 53 * hash + (this.isPublished ? 1 : 0);
        hash = 53 * hash + (this.isCompleted ? 1 : 0);
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
        final Post other = (Post) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.isPublished != other.isPublished) {
            return false;
        }
        if (this.isCompleted != other.isCompleted) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.synopsis, other.synopsis)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.postDate, other.postDate)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.expirationDate, other.expirationDate)) {
            return false;
        }
        if (!Objects.equals(this.categories, other.categories)) {
            return false;
        }
        if (!Objects.equals(this.imageList, other.imageList)) {
            return false;
        }
        if (!Objects.equals(this.authorList, other.authorList)) {
            return false;
        }
        if (!Objects.equals(this.tagList, other.tagList)) {
            return false;
        }
        return true;
    }

    
    
}
