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
public class ImageType {
    
    private int ImageTypeId;
    
    private String ImageType;

    public int getImageTypeId() {
        return ImageTypeId;
    }

    public void setImageTypeId(int ImageTypeId) {
        this.ImageTypeId = ImageTypeId;
    }

    public String getImageType() {
        return ImageType;
    }

    public void setImageType(String ImageType) {
        this.ImageType = ImageType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.ImageTypeId;
        hash = 53 * hash + Objects.hashCode(this.ImageType);
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
        final ImageType other = (ImageType) obj;
        if (this.ImageTypeId != other.ImageTypeId) {
            return false;
        }
        if (!Objects.equals(this.ImageType, other.ImageType)) {
            return false;
        }
        return true;
    }
    
    
}
