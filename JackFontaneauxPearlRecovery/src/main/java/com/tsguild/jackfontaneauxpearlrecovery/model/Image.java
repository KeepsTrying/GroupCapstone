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
public class Image {
    
    private int imageId;
    
    private String imageUrl;
    
    private int imageTypeId;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageTypeId() {
        return imageTypeId;
    }

    public void setImageTypeId(int imageTypeId) {
        this.imageTypeId = imageTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.imageId;
        hash = 73 * hash + Objects.hashCode(this.imageUrl);
        hash = 73 * hash + this.imageTypeId;
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
        final Image other = (Image) obj;
        if (this.imageId != other.imageId) {
            return false;
        }
        if (this.imageTypeId != other.imageTypeId) {
            return false;
        }
        if (!Objects.equals(this.imageUrl, other.imageUrl)) {
            return false;
        }
        return true;
    }

    

}
