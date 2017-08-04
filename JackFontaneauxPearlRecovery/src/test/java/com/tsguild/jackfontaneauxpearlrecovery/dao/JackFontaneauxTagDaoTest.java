/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.jackfontaneauxpearlrecovery.dao;

import com.tsguild.jackfontaneauxpearlrecovery.model.Tag;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class JackFontaneauxTagDaoTest extends TestingBase {

    /**
     * Test of addTag method, of class JackFontaneauxTagDao.
     */
    @Test
    public void testAddTag() {

        Tag newTag = new Tag();
        newTag.setTagName("#Blewboo");
        Tag addedTag = tagDao.addTag(newTag);
        Tag fromDao = tagDao.getTagById(newTag.getTagId());
        assertEquals(fromDao, newTag);
        Assert.assertEquals(addedTag, fromDao);
    }

    /**
     * Test of deleteTag method, of class JackFontaneauxTagDao.
     */
    @Test
    public void testDeleteTag() {

        tagDao.deleteTag(1);
        List<Tag> tagList = tagDao.getAllTags();
        assertEquals(3, tagList.size());
        assertEquals(null, tagDao.getTagById(1));
    }

    /**
     * Test of editTag method, of class JackFontaneauxTagDao.
     */
    @Test
    public void testEditTag() {
        Tag toEdit = tagDao.getTagById(1);
        assertEquals("#sweet", toEdit.getTagName());
        toEdit.setTagName("#blahs");
        tagDao.editTag(toEdit);
        Tag fromDao = tagDao.getTagById(1);
        assertEquals(fromDao, toEdit);
    }

    /**
     * Test of getTagById method, of class JackFontaneauxTagDao.
     */
    @Test
    public void testGetTagById() {
        Tag expectedTag = tagDao.getTagById(1);
        Assert.assertEquals("#sweet", expectedTag.getTagName());
        Assert.assertEquals(expectedTag, tagDao.getTagById(expectedTag.getTagId()));
    }

    /**
     * Test of getAllTags method, of class JackFontaneauxTagDao.
     */
    @Test
    public void testGetAllTags() {
       List<Tag> allTags = tagDao.getAllTags();
       Tag tag = allTags.get(0);
       Assert.assertEquals(4, allTags.size());
       Assert.assertTrue(tag.getTagName().equals("#sweet"));
    }

    /**
     * Test of getTagsByName method, of class JackFontaneauxTagDao.
     */
    @Test
    public void testGetTagsByName() {
        List<Tag> associatedTags = tagDao.getTagsByName("#");
        Assert.assertEquals(4, associatedTags.size());
        List<Tag> testTags = tagDao.getTagsByName("dry");
        Tag testTag = testTags.get(0);
        Assert.assertEquals(1, testTags.size());
        Assert.assertTrue(testTag.getTagName().equals("#DRY"));
    }

}
