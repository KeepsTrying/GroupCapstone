/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.model;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class PostCategory {
    
    private int postCategoryId;
    
    private String categoryName;

    public int getPostCategoryId() {
        return postCategoryId;
    }

    public void setPostCategoryId(int postCategoryId) {
        this.postCategoryId = postCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.postCategoryId;
        hash = 67 * hash + Objects.hashCode(this.categoryName);
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
        final PostCategory other = (PostCategory) obj;
        if (this.postCategoryId != other.postCategoryId) {
            return false;
        }
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        return true;
    }
    
    
}
