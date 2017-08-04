/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.service;

import com.tsguild.jackfontaneauxpearlrecovery.dao.JackFontaneauxTagDao;
import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author scooke11
 */
public class JFoTagServiceLayerImpl implements JFoTagServiceLayer {

    private JackFontaneauxTagDao tagDao;

    public void setTagDao(JackFontaneauxTagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Inject
    public JFoTagServiceLayerImpl(JackFontaneauxTagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag addTag(Tag newTag) {
        return tagDao.addTag(newTag);
    }

    @Override
    public void deleteTag(int tagId) {
        tagDao.deleteTag(tagId);
    }

    @Override
    public void editTag(Tag editedTag) {
        tagDao.editTag(editedTag);
    }

    @Override
    public Tag getTagById(int tagId) {
        return tagDao.getTagById(tagId);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Override
    public List<Tag> getTagsByName(String tagName) {
        return tagDao.getTagsByName(tagName);
    }

    @Override
    public List<Tag> getVerifiedAssociatedTags(String tagsString) {
        String trimmedTagsString = tagsString.trim();
        String tagsWithoutSpaces = trimmedTagsString.replaceAll("\\s", "");
        String DELIMITER = ",";
        
        List<Tag> verifiedAssociatedTags = new ArrayList<>();
        String[] tags = tagsWithoutSpaces.split(DELIMITER);
        
        for (String insertedTagName : tags) {
            List<Tag> tagList = getTagsByName(insertedTagName);
            if (tagList.isEmpty()) {
                Tag newTag = new Tag();
                newTag.setTagName(insertedTagName);
                Tag addedTag = addTag(newTag);
                verifiedAssociatedTags.add(addedTag);
            } else {
                for (Tag existingTag : tagList) {
                    verifiedAssociatedTags.add(existingTag);
                }
            }
        }
        
        return verifiedAssociatedTags;
    }

}
