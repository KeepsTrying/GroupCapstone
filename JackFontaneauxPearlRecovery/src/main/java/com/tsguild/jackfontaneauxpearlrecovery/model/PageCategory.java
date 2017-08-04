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
public class PageCategory {
     
    private int pageCategoryId;
    
    private String categoryName;

    public int getPageCategoryId() {
        return pageCategoryId;
    }

    public void setPageCategoryId(int pageCategoryId) {
        this.pageCategoryId = pageCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.pageCategoryId;
        hash = 97 * hash + Objects.hashCode(this.categoryName);
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
        final PageCategory other = (PageCategory) obj;
        if (this.pageCategoryId != other.pageCategoryId) {
            return false;
        }
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        return true;
    }
    
    
}
