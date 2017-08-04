/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import java.util.List;

/**
 *
 * @author scooke11
 */
public interface JFoTagServiceLayer {
    
    public Tag addTag(Tag newTag);
    
    public void deleteTag(int tagId);
    
    public void editTag(Tag editedTag);
    
    public Tag getTagById(int tagId);
    
    public List<Tag> getAllTags();
    
    public List<Tag> getTagsByName(String tagName);
    
    public List<Tag> getVerifiedAssociatedTags(String tagsString);
    
}
